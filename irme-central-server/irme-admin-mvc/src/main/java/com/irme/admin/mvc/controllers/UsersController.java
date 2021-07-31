package com.irme.admin.mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import java.util.Map;

@Controller
public class UsersController {
    @GetMapping({"/users"})
    public ModelAndView homeRedierct(Map<String, Object> model) {
        return new ModelAndView("users", model);
    }


}
