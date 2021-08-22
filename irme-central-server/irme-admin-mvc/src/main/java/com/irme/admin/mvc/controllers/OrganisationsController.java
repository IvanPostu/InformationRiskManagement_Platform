package com.irme.admin.mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import java.util.Map;

@Controller
public class OrganisationsController {

    @GetMapping({"/organisations"})
    public ModelAndView usersPage(Map<String, Object> model) {
        return new ModelAndView("organisations", model);
    }

    @PostMapping({"/organisations/addOrganisation"})
    public String addOrganisation(
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam(value = "base64logo") String logo,
            Map<String, Object> model) {


        return String.valueOf("redirect:/organisations?createdSuccessfully=1");
    }

}
