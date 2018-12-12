package com.zhc.mscauth.controller;

import java.security.Principal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>必须要有，做验证</p>
 * Created by jingxian on 2018/7/13.
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @RequestMapping("/current")
    public Principal user(Principal user) {
        return user;
    }

}
