package com.esite.blank.user.service.impl;

import com.esite.blank.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.util.List;
import java.util.Map;

/**
 * @author admin
 */ //@Service
@Path("users")
@Repository
public class UserServiceImpl implements UserService{

//    @Autowired
//    DataSource dataSource;
//
//    @PostConstruct
//    private void initialize() {
//        setDataSource(dataSource);
//    }

    @Autowired
    private JdbcTemplate jdbcTemplate;

//    @Autowired
//    public UserServiceImpl(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }

    @Override
    public List<Map<String, Object>> getUserList() {
        return null;
    }

    @Override
    @GET
    @Path("{name}")
//    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public List<Map<String, Object>> getUser(@PathParam("name") String name) {
        System.out.println("start");
        String sql = "SELECT * FROM sys_security_user where account_name=?";
        System.out.println(sql);
        return jdbcTemplate.queryForList(sql,name);
    }
}
