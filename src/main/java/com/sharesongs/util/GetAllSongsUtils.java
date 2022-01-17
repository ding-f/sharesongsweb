package com.sharesongs.util;

import com.sharesongs.entity.Song;
import com.sharesongs.service.SongService;
import com.sharesongs.service.impl.SongServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

public  class  GetAllSongsUtils {
    public GetAllSongsUtils(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SongService songOper = new SongServiceImpl();
        List<Song> songlist = null;
        String jsonSongs="";
        try {
            songlist= songOper.checkAllSong();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        String flag=request.getParameter("Android");

        if (flag!=null){        //判断是Android端
            jsonSongs=JsonUtils.SerializerToJson(songlist);     //序列化音乐表成JSOn格式
//            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out=response.getWriter();       //以字符串的形式返回到Android
            out.write(jsonSongs);
            System.out.println("~~~~~~~~~~~~~~~~~~~~Android~~~~~~~~~~~~~~~~~~~~~");
        }else {     //判断不是Android端
            //刷新进入歌曲页面
            HttpSession session = request.getSession();
            session.setAttribute("allsong", songlist);      //直接将一个List集合传递到songlist.jsp
            response.sendRedirect("songlist.jsp");      //刷新进入songlist.jsp"
        }
    }

}
