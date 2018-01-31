package com.laohans.redis.session.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author laohans 2018-01-31 09:56
 */
@Controller
public class IndexController {
    @RequestMapping("")
    @ResponseBody
    public String index(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("sessionId = " + request.getSession().getId());
        return "success";
    }
}
