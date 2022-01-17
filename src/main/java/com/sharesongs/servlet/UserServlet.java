package com.sharesongs.servlet;


import com.sharesongs.service.UserService;
import com.sharesongs.service.impl.UserServiceImpl;
import com.sharesongs.util.GetAllSongsUtils;
import com.sharesongs.util.MD5Util;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import  javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@MultipartConfig
@WebServlet("/useraction")//映射(访问)路径（ajax提交所需）
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public UserServlet() {
        super();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse
            response)
            throws ServletException, IOException {
//        doPost(request,response);

    }
    protected void doPost(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String flag= request.getParameter("flag");
        UserService us = new UserServiceImpl();
        switch (flag){
            case "checkYourName":{
                //获取前端发送的数据
                String yourname = request.getParameter("yourname");
//调用方法获取结果

                boolean res = false;
                try {
                    res = us.checkYourName(yourname);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
//使用输出流将结果返回给Ajax
                PrintWriter out = response.getWriter();
                out.print(res);
                out.close();
            }break;
            case "checkYourNameAndPasswd":{
                String ANDROID=request.getParameter("Android");
                String username = request.getParameter("username");
                String password = request.getParameter("passwd");
                password = MD5Util.MD5Encode(password,"UTF-8");      //将密码转换成MD5进行查询
//                System.out.println(password);
                boolean user= false;
                try {
                    user = us.checkYourNameAndPasswd(username,password);        //判断用户名密码是否存在
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

//                boolean isMobile=new FindMobilePhone().webCheckMoblieOrPC(request,response);        //判断请求头是否为移动端，是true，否false
//
//                if(isMobile){   //移动端访问
//                System.out.println(user&ANDROID!=null);

                    if(user&ANDROID!=null){
                        PrintWriter out=response.getWriter();
                        out.write("Welcome");       //写字符串
                        out.close();
                    }else {
                        PrintWriter out = response.getWriter();
                        out.print(user);        //返回是否有这个User,告诉Ajax
                        out.close();
                       new GetAllSongsUtils(request,response);
                    }
//                }else {     //PC端访问
//                    PrintWriter out = response.getWriter();
//                    out.print(user);        //返回是否有这个User，如果是Android调用一样返回boolean判断用户是否存在，专门写boolean
//                    out.close();
//                    //传输页面不会刷新
                         //查询所有歌曲并返回一个"songlist.jsp"页面
//                }



            }break;
        }

    }
}
