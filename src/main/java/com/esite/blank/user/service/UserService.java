package com.esite.blank.user.service;

import java.util.List;
import java.util.Map;

public interface UserService {

    public List<Map<String, Object>> getUserList();

    public List<Map<String, Object>> getUser(String name);
}
