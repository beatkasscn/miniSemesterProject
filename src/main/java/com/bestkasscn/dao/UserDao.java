package com.bestkasscn.dao;

import com.bestkasscn.domain.User;

public interface UserDao {
    public User findByEmail(String email);
    public User findByCode(String code);
    public void save(User user);
    public void updateStatus(User user);
}
