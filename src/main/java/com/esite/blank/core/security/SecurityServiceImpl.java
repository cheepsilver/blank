package com.esite.blank.core.security;

import com.esite.blank.core.helper.StringHelper;
import com.esite.blank.core.security.dao.SecurityDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("security")
@Repository
public class SecurityServiceImpl implements SecurityService{

    @Autowired
    private SecurityDao securityDao;

    @Override
    @GET
    @Path(value="/excuteToken/{username}/{password}")
    @Produces({MediaType.APPLICATION_JSON})
    public String excuteToken(@PathParam("username") String username, @PathParam("password") String password) {
        System.out.println("start");
        String tokenValue = username+password+ StringHelper.createUUID();
        String token = StringHelper.getMd5(tokenValue);
        try {
            securityDao.saveToken2Redis(token);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return token;
    }

    @Override
    @GET
    @Path("/validateToken/{token}")
    @Produces({MediaType.APPLICATION_JSON})
    public Boolean validateToken(@PathParam("token") String token) {
        return securityDao.validateTokenFromRedis(token);
    }
}
