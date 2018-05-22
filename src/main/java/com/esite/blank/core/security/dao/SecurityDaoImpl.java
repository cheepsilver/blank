package com.esite.blank.core.security.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

@Repository
public class SecurityDaoImpl implements SecurityDao{

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveToken2Redis(String token) {
        stringRedisTemplate.opsForValue().set("1", "test1");
       // stringRedisTemplate.opsForValue().set(token, "test1",10, TimeUnit.SECONDS);
        String a = null;
        System.out.println(Integer.parseInt(a) );

        System.out.println("开始存储第一对");
        System.out.println("开始存储第二对");
        stringRedisTemplate.opsForValue().set("2", "test2");
        //stringRedisTemplate.opsForValue().set("2", "test1",10, TimeUnit.SECONDS);
    }


    @Override
    public Boolean validateTokenFromRedis(String token) {
        if(null != token && stringRedisTemplate.hasKey(token)){
            return true;
        }
        return false;
    }
}
