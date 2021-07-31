package com.irme.admin.mvc.controllers;

import com.irme.common.dto.AuthUserDto;
import com.irme.server.bll.UserBusinessLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    private UserBusinessLogic userBusinessLogic;

    @GetMapping({"/user"})
    public ModelAndView usersPage(
            @RequestParam("userId") Integer userId,
            Map<String, Object> model) {

        AuthUserDto user = userBusinessLogic.getUserById(userId);

        if (user == null) {
            return new ModelAndView("error");
        }

        model.put("user", user);
        return new ModelAndView("user", model);
    }


}
