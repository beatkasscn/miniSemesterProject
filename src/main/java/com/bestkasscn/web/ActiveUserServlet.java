package com.bestkasscn.web;

import com.bestkasscn.service.UserService;
import com.bestkasscn.service.UserServiceImpl;
import com.sun.mail.imap.protocol.BODY;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/activeUserServlet")
public class ActiveUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");
        if(code != null){
            //调用service完成激活
            UserService userService = new UserServiceImpl();
            boolean flag = userService.active(code);
            String msg = null;
            if(flag){
                msg = "激活成功，请<a href='login.html'>登录</a>";
            }else {
                msg = "激活失败";
            }
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write(msg);
        }
    }
}
