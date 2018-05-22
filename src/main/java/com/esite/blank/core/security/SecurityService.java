package com.esite.blank.core.security;

import javax.servlet.http.HttpServletRequest;

public interface SecurityService {
    public String excuteToken(String username, String password) throws InterruptedException;
    public Boolean validateToken(String token);
}
