package com.irme.admin.mvc.controller;

import com.irme.common.dto.AuthUserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.Date;

@Controller
public class IndexController {

    private final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @GetMapping("/")
    public String index(Model model) {
        logger.debug("Welcome to 1.com...");
        model.addAttribute("msg", getMessage());
        model.addAttribute("today", new Date());

        AuthUserDto u = new AuthUserDto();
        u.setEmail("qrqereqr");


        return "index";

    }

    private String getMessage() {
        return "Hello World";
    }

}
