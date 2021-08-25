package com.irme.admin.mvc.controllers;

import com.irme.common.dto.OrganisationDto;
import com.irme.server.business_entities.OrganisationBusinessLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import java.util.Map;

@Controller
public class OrganisationsController {

    @Autowired
    private OrganisationBusinessLogic organisationBusinessLogic;


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

        OrganisationDto organisation = new OrganisationDto();
        organisation.setBase64ImageLogo(logo);
        organisation.setName(name);
        organisation.setDescription(description);


        boolean isSuccess = organisationBusinessLogic.saveOrganisation(organisation);

        return isSuccess
                ? String.valueOf("redirect:/organisations?createdSuccessfully=1")
                : String.valueOf("redirect:/organisations?createdSuccessfully=0");
    }

}
