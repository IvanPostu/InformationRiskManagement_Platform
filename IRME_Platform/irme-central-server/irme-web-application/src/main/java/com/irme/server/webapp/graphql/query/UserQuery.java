package com.irme.server.webapp.graphql.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.irme.common.dto.AuthUserDto;
import com.irme.server.business_entities.UserBusinessLogic;
import com.irme.server.webapp.graphql.GraphQLDomainError;
import com.irme.server.webapp.graphql.GraphQLDomainErrorStatusCode;
import com.irme.server.webapp.graphql.model.UserDataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

@Component
public class UserQuery implements GraphQLQueryResolver {

    @Autowired
    private UserBusinessLogic userBusinessLogic;

    @PreAuthorize("!isAnonymous()")
    public UserDataResult userInfo(final int userId) {

        if (userId == 666) {
            throw new GraphQLDomainError("123",
                    GraphQLDomainErrorStatusCode.ACCESS_DENIED);
        }

        AuthUserDto userDto = userBusinessLogic.getUserById(userId);

        UserDataResult result = null;
        if (userDto != null) {
            result = new UserDataResult();

            result.setBanned(userDto.isBanned());
            result.setCountryCode(userDto.getCountryCode());
            result.setBase64Picture(userDto.getBase64Picture());
            result.setCreated(userDto.getCreated());
            result.setEmail(userDto.getEmail());
            result.setFirstName(userDto.getFirstName());
            result.setLastName(userDto.getLastName());
            result.setId(userDto.getId());
            result.setPhone(userDto.getPhone());
            result.setStatus(userDto.getStatus());
            result.setRoles(userDto.getRoles());
        }

        return result;
    }
}
