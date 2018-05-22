package com.esite.blank.user.dao;

import com.esite.blank.user.entity.User;

public interface UserDao {

    public User findByName(String name);
}
