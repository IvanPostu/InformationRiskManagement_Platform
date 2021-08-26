package com.irme.admin.mvc.models;

import com.irme.common.dto.AuthUserDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.stream.Collectors;

public class AdminUserModel extends AuthUserDto implements UserDetails {

    public AdminUserModel(AuthUserDto userDto) {
        super(userDto.getId(), userDto.getEmail(), userDto.getPasswordHash(), userDto.isBanned(),
                userDto.getStatus(), userDto.getFirstName(), userDto.getLastName(),
                userDto.getCreated(), userDto.getPhone(), userDto.getCountryCode(),
                userDto.getRoles(), userDto.getBase64Picture());
    }

    public Integer getId() {
        return super.getId();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return super.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return super.getPasswordHash();
    }

    @Override
    public String getUsername() {
        return super.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return Boolean.TRUE;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
