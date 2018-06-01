package com.esite.police.user.dao.impl;

import com.esite.police.user.dao.UserDao;
import com.esite.police.user.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {

    @Override
    public User findByName(String name) {
        return null;
    }
}
