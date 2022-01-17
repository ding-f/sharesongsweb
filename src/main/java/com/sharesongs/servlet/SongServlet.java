package com.sharesongs.servlet;


import com.sharesongs.entity.Song;
import com.sharesongs.service.SongService;
import com.sharesongs.service.impl.SongServiceImpl;
import com.sharesongs.util.GetAllSongsUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@MultipartConfig
@WebServlet("/songupload")//映射(访问)路径（ajax提交所需）
public class SongServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public SongServlet() {
        super();
    }
    SongService songOper = new SongServiceImpl();
    protected void doGet(HttpServletRequest request, HttpServletResponse
            response)
            throws ServletException, IOException {
        doPost(request,response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String flag = request.getParameter("flag");

        System.out.println(flag);
        String photopath=null;

        ///////////////////////////////
//        System.out.println(flag);
//        String songnam=request.getParameter("songname");
//        System.out.println(songnam);
        ////////////////////////////////
        switch (flag){
            case "checkSongName":{
                //获取前端发送的数据
                String songname = request.getParameter("name");
//调用方法获取结果

                boolean res = false;
                try {
                    res = songOper.checkSongName(songname);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
//使用输出流将结果返回给Ajax
                PrintWriter out = response.getWriter();
                out.print(res);
                out.close();
            }break;
            case "addSong":{

                String songname=request.getParameter("songname");
                String singgername=request.getParameter("singername");
                String yourname=request.getParameter("yourname");

                String photoRealPath=request.getSession().getServletContext().getRealPath("")+"photos/songsphotos/";        //得到到服务器的真实路径
                String songRealPath=request.getSession().getServletContext().getRealPath("")+"songs/";
//                System.out.println(photoRealPath);

                File photoFile = new File(photoRealPath);
                File songFile = new File(songRealPath);
                if(!(photoFile.exists() & songFile.exists())) {     //& 与 && 的区别：& 和 | 左右两边的式子一定会执行（比较笨），&& 和 || 只要左边的式子能得出结果，右边的式子就不会执行（比较聪明）。
                    //不存在就连续创建
//                    System.out.println(!photoFile.exists()&&!songFile.exists());
                    photoFile.mkdirs();     //连续创建文件夹
                    songFile.mkdirs();
                }

                Part photo=request.getPart("photoname");        //得到图片文件

                if(photo.getSubmittedFileName()==null || photo.getSubmittedFileName().equals("")){        //如果没有上传图片
                    photopath = "/photos/default.jpeg";
                }else {
                    String photoName = photo.getSubmittedFileName();        //如果得到图片，获得歌曲名称
                    // request.getSession().getServletContext().getRealPath("")生成路径后有斜杠，跟${ctx}相反
                    photopath = "/photos/songsphotos/" + photoName;
                    photo.write(photoRealPath+photoName);       //写入文件操作


                }


                Part song=request.getPart("songfile");      //得到歌曲文件
                String songName=song.getSubmittedFileName();
                String songpath="/songs/"+songName;     //写相对路径可在jsp前加上${pageContext.request.contextPath}

//                System.out.println(request.getSession().getServletContext().getRealPath("") );
//                System.out.println(songpath);




                song.write(songRealPath+songName);

                Song addSong=new Song(0,1,songname,singgername,yourname,photopath,songpath,new Date());

                try {
                    songOper.checkAddSong(addSong);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                //传输页面不会刷新
                 new GetAllSongsUtils(request,response);

//                System.out.println(path);
            }break;
            case "AndroidTakeList" :{
                System.out.println("~~~~~~~~~~~~~~AndroidTakeList~~~~~~~~~~~~");
                new GetAllSongsUtils(request,response);
            }
        }

    }



}
