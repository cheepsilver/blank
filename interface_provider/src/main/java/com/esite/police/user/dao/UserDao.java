package com.esite.police.user.dao;


import com.esite.police.user.entity.User;

public interface UserDao {

    public User findByName(String name);
}
