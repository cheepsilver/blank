package com.esite.blank.core.security.dao;

public interface SecurityDao {

    public void saveToken2Redis(String token) throws InterruptedException;

    Boolean validateTokenFromRedis(String token);
}
