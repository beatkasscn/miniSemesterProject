package com.bestkasscn.web;

import com.bestkasscn.domain.ResultInfo;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Map;

@WebServlet(name = "LoginUserServlet", value = "/loginUserServlet")
public class LoginUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> map = request.getParameterMap();
        System.out.println(map);
        //3.调用Service查询
        ResultInfo info = new ResultInfo();

        //4.判断用户对象是否为null
        //6.判断登录成功
//        if(email.equals("admin")&password.equals("admin")){
//            //登录成功
//            info.setFlag(true);
//        }

        //响应数据
        ObjectMapper mapper = new ObjectMapper();

        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(),info);
    }
}
