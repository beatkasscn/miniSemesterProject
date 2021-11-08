package com.bestkasscn.dao;

import com.bestkasscn.domain.User;
import com.bestkasscn.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDaoImpl implements UserDao{
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public User findByEmail(String email) {
        User user = null;
        try {
            String sql = "select * from cat_users where email=?";
            user = template.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class),email);
        }catch (Exception e){

        }
        return user;
    }

    @Override
    public User findByCode(String code) {
        User user = null;
        try {
            String sql = "select * from cat_user where code=?";
            user = template.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class),code);
        }catch (DataAccessException e){
            e.printStackTrace();
        }
        return user;
    }

    public void save(User user){
        //定义sql
        String sql = "insert into cat_users(email,password,status,code) values(?,?,?,?)";
        template.update(sql,user.getEmail(), user.getPassword(),user.getStatus(), user.getCode());
    }

    @Override
    public void updateStatus(User user) {
        String sql = "update cat_user set status='Y' where id=?";
        template.update(sql,user.getId());
    }
}
