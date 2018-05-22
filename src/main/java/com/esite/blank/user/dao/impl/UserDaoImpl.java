package com.esite.blank.user.dao.impl;

import com.esite.blank.user.dao.UserDao;
import com.esite.blank.user.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {

    @Override
    public User findByName(String name) {
        return null;
    }
}
