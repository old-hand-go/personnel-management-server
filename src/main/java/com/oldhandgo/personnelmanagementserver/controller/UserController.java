package com.oldhandgo.personnelmanagementserver.controller;

import com.oldhandgo.personnelmanagementserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @author dormir
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;
}
