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
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UserBusinessLogic userBusinessLogic;

    @Autowired
    private OrganisationBusinessLogic organisationBusinessLogic;

    @GetMapping({"/user"})
    public ModelAndView userPage(
            @RequestParam(value = "userId", required = false) Optional<Integer> userId,
            @RequestParam(value = "userEmail", required = false) Optional<String> userEmail,
            Map<String, Object> model) {

        Optional<AuthUserDto> user = Optional.ofNullable(null);

        if (userId.isPresent()) {
            user = Optional.ofNullable(userBusinessLogic.getUserById(userId.get()));
        } else {
            if (userEmail.isPresent()) {
                user = Optional.ofNullable(userBusinessLogic.getUserByEmail(userEmail.get()));
            }
        }

        try {
            int extractedUserId = user.orElseThrow(() -> new RuntimeException()).getId();

            List<Pair<OrganisationDto, Boolean>> organisations = organisationBusinessLogic
                    .selectAllOrganisationsIncludingRelatedToTheUser(extractedUserId);

            if (organisations == null) {
                return new ModelAndView("error");
            }

            model.put("organisations", organisations);
            model.put("user", user.get());
            model.put("userId", extractedUserId);

            return new ModelAndView("user", model);
        } catch (Exception e) {
            return new ModelAndView("error");
        }

    }

    @PostMapping({"/assignOrganisations"})
    public String assignUserToOrganisations(
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
