package com.bestkasscn.web;

import com.bestkasscn.domain.ResultInfo;
import com.bestkasscn.domain.User;
import com.bestkasscn.service.UserService;
import com.bestkasscn.service.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/registerUserServlet")
public class registerUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //验证校验
        String check = request.getParameter("check");
        //从session中获取验证码
        HttpSession session = request.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");
        //比较
        if(checkcode_server == null || !checkcode_server.equalsIgnoreCase(check)){
            //验证码错误
            ResultInfo info = new ResultInfo();
            info.setFlag(false);
            info.setErrorMsg("验证码错误");
            //序列化
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(info);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(json);
            return;
        }
        //获取数据
        Map<String,String[]> map = request.getParameterMap();
        //封装对象
        User user = new User();
        try{
            BeanUtils.populate(user,map);
        }catch (IllegalAccessException e){
            e.printStackTrace();
        }catch (InvocationTargetException e){
            e.printStackTrace();
        }
        //调用service完成注册
        UserService service = new UserServiceImpl();
        boolean flag = service.register(user);
        ResultInfo info = new ResultInfo();
        //响应结果
        if(flag){
            info.setFlag(true);
        }else {
            info.setFlag(false);
            info.setErrorMsg("注册失败");
        }
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(info);
        //将json写回客户端
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }
}
