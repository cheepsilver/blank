package com.esite.sso.security.service;


import com.alibaba.dubbo.config.annotation.Service;
import com.esite.sso.security.api.SecurityService;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author admin
 */
@Path("security")
@Service(protocol = "rest")
public class SecurityServiceImpl implements SecurityService {


    @Override
    @GET
    @Path("produceToken")
    public String produceToken(HttpServletRequest request) {
        request.getParameterNames();
        System.out.println("111");
        return null;
    }

    @GET
    @Path("{name}")
    @Produces({MediaType.APPLICATION_JSON})
    public String test(@PathParam("name") String name) {
        System.out.println(name);
        return name;
    }

    @Override
    public Boolean validateToken() {
        return null;
    }
}
