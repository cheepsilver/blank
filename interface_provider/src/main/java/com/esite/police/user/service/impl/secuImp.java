package com.esite.police.user.service.impl;

import com.esite.sso.security.api.SecurityService;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("test")
@Repository
public class secuImp implements SecurityService {

    @Override
    public String produceToken(HttpServletRequest request) {
        return null;
    }

    @Override
    @GET
    @Path("{test}")
    @Produces({MediaType.APPLICATION_JSON})
    public Boolean validateToken() {
        return null;
    }
}
