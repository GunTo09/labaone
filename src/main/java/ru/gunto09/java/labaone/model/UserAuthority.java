package ru.gunto09.java.labaone.model;

import org.springframework.security.core.GrantedAuthority;

public enum UserAuthority implements GrantedAuthority {

    PLACE_JOKE,
    MANAGE_JOKE,
    FULL;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
