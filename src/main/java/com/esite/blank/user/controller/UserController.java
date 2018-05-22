package com.esite.blank.user.controller;

import com.esite.blank.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

//    @Autowired
//    private UserService userService;
    @RequestMapping(value="/list",method = RequestMethod.GET)
    public List<Map<String, Object>> getUserList(){
        return null;
    }

}
