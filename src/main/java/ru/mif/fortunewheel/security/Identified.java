package ru.mif.fortunewheel.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import ru.mif.fortunewheel.domain.APIClient;
import ru.mif.fortunewheel.enums.UserRole;

import java.util.List;

public class Identified extends User implements Authentication {

    public Identified(APIClient apiClient) {
        super(
                apiClient.getAgent(),
                apiClient.getSecret(),
                List.of(new SimpleGrantedAuthority(UserRole.API_CLIENT_ROLE))
        );
    }

    public Identified(ru.mif.fortunewheel.domain.User user) {
        super(
                user.getEmail(),
                user.getHash(),
                List.of(new SimpleGrantedAuthority(user.getRole().name()))
        );
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.setAuthenticated(isAuthenticated);
    }

    @Override
    public String getName() {
        return null;
    }
}
