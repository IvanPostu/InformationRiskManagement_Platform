package com.irme.server.webapp.graphql.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.irme.common.dto.OrganisationDto;
import com.irme.server.business_entities.OrganisationBusinessLogic;
import com.irme.server.webapp.jwt.JwtUser;
import org.javatuples.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrganisationQuery implements GraphQLQueryResolver {

    @Autowired
    private OrganisationBusinessLogic organisationBll;

    @PreAuthorize("!isAnonymous()")
    public List<OrganisationDto> userOrganisations() {

        AbstractAuthenticationToken auth = (AbstractAuthenticationToken) SecurityContextHolder.getContext()
                .getAuthentication();
        JwtUser user = (JwtUser) auth.getPrincipal();

        List<Pair<OrganisationDto, Boolean>> organisations = organisationBll
                .selectAllOrganisationsIncludingRelatedToTheUser(user.getId());

        List<OrganisationDto> userOrganisations = organisations
                .stream()
                .filter(o -> o.getValue1())
                .map(o -> o.getValue0())
                .collect(Collectors.toList());

        return userOrganisations;
    }
}