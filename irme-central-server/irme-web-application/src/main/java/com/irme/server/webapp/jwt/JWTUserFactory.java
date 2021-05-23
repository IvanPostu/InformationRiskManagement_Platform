package com.irme.server.webapp.jwt;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import com.irme.server.dal.dao.UserDAO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public final class JWTUserFactory {

    private JWTUserFactory() {}

    public static JWTUser createJWTUserFromDataAccessLayerUserDAO(UserDAO user) {

        return new JWTUser(user.getId(), user.getFirstName(), user.getLastName(),
                user.getPasswordHash(), user.getEmail(), Boolean.TRUE,
                mapToGrantedAuthorities(new ArrayList<>(user.getRoles())));

    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<String> userRoles) {
        return userRoles.stream().map(role -> new SimpleGrantedAuthority(role))
                .collect(Collectors.toList());
    }
}

