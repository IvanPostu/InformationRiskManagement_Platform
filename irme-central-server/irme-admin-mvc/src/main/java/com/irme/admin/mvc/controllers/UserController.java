package com.irme.admin.mvc.controllers;

import com.irme.common.dto.AuthUserDto;
import com.irme.common.dto.OrganisationDto;
import com.irme.server.business_entities.OrganisationBusinessLogic;
import com.irme.server.business_entities.UserBusinessLogic;
import org.javatuples.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    private UserBusinessLogic userBusinessLogic;

    @Autowired
    private OrganisationBusinessLogic organisationBusinessLogic;

    @GetMapping({"/user"})
    public ModelAndView userPage(
            @RequestParam("userId") Integer userId,
            Map<String, Object> model) {

        AuthUserDto user = userBusinessLogic.getUserById(userId);
        List<Pair<OrganisationDto, Boolean>> organisations = organisationBusinessLogic
                .selectAllOrganisationsIncludingRelatedToTheUser(userId);

        if (user == null || organisations == null) {
            return new ModelAndView("error");
        }

        model.put("organisations", organisations);
        model.put("user", user);
        model.put("userId", userId);

        return new ModelAndView("user", model);
    }

    @PostMapping({"/assignOrganisations"})
    public String assignUserTo(
            @RequestParam(required = true) int[] assignedOrganisations,
            @RequestParam(required = true) int userId,
            Map<String, Object> model) {


        boolean assignedWithSuccess = organisationBusinessLogic
                .assignUserToOrganisations(userId, assignedOrganisations);

        if (assignedWithSuccess) {
            return String
                    .format("redirect:/user?userId=%d&assignedToOrganisationsSuccessfully=1",
                            userId);
        }

        return String
                .format("redirect:/user?userId=%d&assignedToOrganisationsSuccessfully=0", userId);
    }

}
