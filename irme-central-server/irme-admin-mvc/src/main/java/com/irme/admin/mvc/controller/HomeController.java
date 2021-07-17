package com.irme.admin.mvc.controller;

import com.irme.common.dto.AuthUserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import java.util.Date;
import java.util.Map;

@Controller
public class HomeController {

    private final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @GetMapping({"/"})
    public String homeRedierct() {
        return "redirect:/home";
    }

    @GetMapping({"/home"})
    public ModelAndView home(Map<String, Object> model) {
        logger.debug("Welcome to 1.com...");
        model.put("msg", getMessage());
        model.put("today", new Date());

        AuthUserDto u = new AuthUserDto();
        u.setEmail("qrqereqr");


        return new ModelAndView("home", model);
    }

    private String getMessage() {
        return "Hello World";
    }

}
