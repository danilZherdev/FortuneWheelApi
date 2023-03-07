package ru.mif.fortunewheel.security.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.mif.fortunewheel.security.Identified;
import ru.mif.fortunewheel.security.mapper.UserJWTMapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public final class UserAuthenticationFilter extends AbstractAuthenticationFilter {

    private final UserJWTMapper mapper;

    public UserAuthenticationFilter(UserJWTMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        super.doFilterInternal(request, response, filterChain);

        if (!mapper.isValid(getToken())) {
            logger.warn("Token {} is not valid user token in request {}", getToken(), request);
            filterChain.doFilter(request, response);
            return;
        }

        Identified identified = mapper.parse(getToken());
        /**
         * Add identified user to SecurityContextHolder
         */
        SecurityContextHolder.getContext().setAuthentication(identified);
        filterChain.doFilter(request, response);
    }
}
