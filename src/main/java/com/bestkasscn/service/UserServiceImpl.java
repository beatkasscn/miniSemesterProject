package com.bestkasscn.service;

import com.bestkasscn.dao.UserDao;
import com.bestkasscn.dao.UserDaoImpl;
import com.bestkasscn.domain.User;
import com.bestkasscn.util.MailUtils;
import com.bestkasscn.util.UuidUtil;

public class UserServiceImpl implements UserService{
    private UserDao userDao = new UserDaoImpl();
    @Override
    public boolean register(User user) {
        User user_email = userDao.findByEmail(user.getEmail());
        if(user_email!=null){
            return false;
        }
        //设置激活码
        user.setCode(UuidUtil.getUuid());
        user.setStatus("N");
        userDao.save(user);
        //激活邮件发送
        String content = "<a href=http://localhost/microSemesterProject/activeUserServlet?code="+user.getCode();
        MailUtils.sendMail(user.getEmail(),content,"激活邮件");
        return true;
    }

    @Override
    public boolean active(String code) {
        User user = userDao.findByCode(code);
        if(user!=null){
            userDao.updateStatus(user);
            return true;
        }
        return false;
    }
}
