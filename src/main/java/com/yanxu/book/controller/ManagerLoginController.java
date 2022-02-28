package com.yanxu.book.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("managerLogin")
public class ManagerLoginController {

    @GetMapping("index")
    public String login(HttpServletRequest request) {
        request.getCookies();
        return "managerIndex";
    }


}
