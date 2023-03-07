package ru.mif.fortunewheel.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import ru.mif.fortunewheel.domain.User;
import ru.mif.fortunewheel.enums.UserRole;
import ru.mif.fortunewheel.repository.ApiClientRepository;
import ru.mif.fortunewheel.repository.UserRepository;

import java.util.Optional;

@Component
public class AuthenticatedUser {

    private final UserRepository userRepository;

    public AuthenticatedUser(UserRepository userRepository,
                             ApiClientRepository apiClientRepository) {
        this.userRepository = userRepository;
    }

    private Optional<Authentication> getAuthentication() {
        SecurityContext context = SecurityContextHolder.getContext();
        return Optional.ofNullable(context.getAuthentication());
    }

    public Optional<User> get() {
        return getAuthentication().flatMap(authentication -> {
            Identified identified = (Identified) authentication;
            var role = identified.getAuthorities().stream().findAny()
                    .orElseThrow(() -> new AuthenticationException("Authentication failed."));
            if (!role.getAuthority().equals(UserRole.API_CLIENT_ROLE)) {
                return userRepository.findByEmailAndHash(identified.getUsername(), identified.getPassword());
            }
            return Optional.empty();
        });
    }

}