package com.esite.sso.security.api;

import javax.servlet.http.HttpServletRequest;

/**
 * @author admin
 */
public interface SecurityService {

    String produceToken(HttpServletRequest request);
    Boolean validateToken();

}
