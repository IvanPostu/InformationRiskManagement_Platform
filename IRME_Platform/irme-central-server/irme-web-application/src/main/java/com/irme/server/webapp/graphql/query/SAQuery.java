package com.irme.server.webapp.graphql.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.irme.common.dto.SACategoryDto;
import com.irme.server.business_entities.SABusinessLogic;
import com.irme.server.webapp.graphql.model.SACategoryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SAQuery implements GraphQLQueryResolver {

    @Autowired
    private SABusinessLogic sABusinessLogic;

    @PreAuthorize("!isAnonymous()")
    public List<SACategoryResult> getSecurityAssessmentCategories() {
        List<SACategoryResult> categories = new ArrayList<>();

        List<SACategoryDto> categoryDtos = sABusinessLogic.getCategories();

        if (categoryDtos != null && categoryDtos.size() > 0) {
            categoryDtos.forEach(c -> {
                SACategoryResult item = new SACategoryResult();
                item.setCategroyId(c.getCategroyId());
                item.setDescription(c.getDescription());
                item.setName(c.getName());
                categories.add(item);
            });
        }

        return categories;
    }
}
