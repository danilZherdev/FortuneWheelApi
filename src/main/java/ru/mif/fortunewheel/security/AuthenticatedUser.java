package ru.mif.fortunewheel.security;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import ru.mif.fortunewheel.domain.User;
import ru.mif.fortunewheel.repository.UserRepository;
import ru.mif.fortunewheel.service.ServiceException;

import java.util.Optional;

@Component
public class AuthenticatedUser {

    private final UserRepository userRepository;

    public AuthenticatedUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private Optional<Authentication> getAuthentication() {
        SecurityContext context = SecurityContextHolder.getContext();
        return Optional.ofNullable(context.getAuthentication())
                .filter(authentication -> !(authentication instanceof AnonymousAuthenticationToken));
    }

    public Optional<User> get() {
        return getAuthentication().flatMap(authentication -> userRepository.findByHash(authentication.getName()));
    }

    public User getWithThrow() {
        return getAuthentication().flatMap(authentication -> userRepository.findByHash(authentication.getName()))
                .orElseThrow(() -> {
                    return new AuthenticationException("СЮДА СОЗДАТЬ И ПОСТАВИТЬ AUTHENTICATION EXCEPTION");
                });
    }

}