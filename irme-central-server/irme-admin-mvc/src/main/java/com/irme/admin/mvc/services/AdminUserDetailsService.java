package com.irme.admin.mvc.services;

import com.irme.admin.mvc.models.AdminUserModel;
import com.irme.common.dto.AuthUserDto;
import com.irme.server.business_entities.UserBusinessLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AdminUserDetailsService implements UserDetailsService {

    @Autowired
    private UserBusinessLogic userBusinessLogic;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        AuthUserDto user = userBusinessLogic.getUserByEmail(username);

        if (user == null) {
            throw new UsernameNotFoundException(
                    String.format("User with username: %s not found!", username));
        }

        UserDetails userDetails = new AdminUserModel(user);

        boolean containsAdminRole = false;

        for (GrantedAuthority role : userDetails.getAuthorities()) {
            if (role.getAuthority().toLowerCase().equals("role_admin")) {
                containsAdminRole = true;
                break;
            }
        }

        if (!containsAdminRole) {
            throw new UsernameNotFoundException(
                    String.format("User with username: %s has no admin role", username));

        }

        return userDetails;
    }

}
