package com.bestkasscn.service;

import com.bestkasscn.domain.User;

public interface UserService {
    public boolean register(User user);
    public boolean active(String code);
}
