package com.irme.admin.mvc.controllers;

import com.irme.common.dto.OrganisationDto;
import com.irme.server.business_entities.BusinessLogicException;
import com.irme.server.business_entities.OrganisationBusinessLogic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class OrganisationsController {
    private static final Logger logger = LoggerFactory.getLogger(OrganisationsController.class);

    @Autowired
    private OrganisationBusinessLogic organisationBusinessLogic;

    @GetMapping({"/organisation/{organisationId}"})
    public ModelAndView organisationInfo(@PathVariable(required = true) Integer organisationId,
            Map<String, Object> model) {


        try {
            Optional<OrganisationDto> organisationDto = organisationBusinessLogic
                    .getOrganisationById(organisationId);

            model.put("organisation", organisationDto.orElseThrow(() -> new Exception()));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ModelAndView("error");
        }

        return new ModelAndView("organisation", model);
    }

    @GetMapping({"/organisations"})
    public ModelAndView organisations(Map<String, Object> model) {

        List<OrganisationDto> organisations;

        try {
            organisations = organisationBusinessLogic.selectAllOrganisations();
            if (organisations == null) {
                organisations = new ArrayList<>();
            }
        } catch (BusinessLogicException e) {
            organisations = new ArrayList<>();
            logger.error(e.getMessage(), e);
        }

        organisations = organisations.stream()
                .collect(() -> new LinkedList<>(), (list, e) -> {
                    list.addFirst(e);
                }, (a, b) -> {
                });


        List<List<OrganisationDto>> partitionedOrganisationsList =
                splitOrganisationsInChunks(organisations);

        model.put("partitionedOrganisationsList", partitionedOrganisationsList);

        return new ModelAndView("organisations", model);
    }

    private List<List<OrganisationDto>> splitOrganisationsInChunks(
            List<OrganisationDto> organisations) {
        final int PARTITION_SIZE = 3;

        List<List<OrganisationDto>> result =
                new ArrayList<>(organisations.size() / PARTITION_SIZE);

        List<OrganisationDto> partition = null;
        int indexInPartition = 0;

        for (OrganisationDto o : organisations) {
            if (indexInPartition == 0) {
                partition = new ArrayList<>(PARTITION_SIZE);
                result.add(partition);
            }

            partition.add(o);

            indexInPartition = indexInPartition == PARTITION_SIZE - 1
                    ? 0
                    : indexInPartition + 1;
        }

        return result;
    }

    @PostMapping({"/organisations/addOrganisation"})
    public String saveOrganisation(
            @RequestParam(required = false) Integer organisationId,
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam(value = "base64logo") String logo,
            Map<String, Object> model) {



        OrganisationDto organisation = new OrganisationDto();
        organisation.setId(organisationId);
        organisation.setBase64ImageLogo(logo);
        organisation.setName(name);
        organisation.setDescription(description);


        boolean isSuccess = organisationBusinessLogic.saveOrganisation(organisation);

        return isSuccess
                ? String.valueOf("redirect:/organisations?createdSuccessfully=1")
                : String.valueOf("redirect:/organisations?createdSuccessfully=0");

    }

}
