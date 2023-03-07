package ru.mif.fortunewheel.security.filters;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import ru.mif.fortunewheel.security.AuthenticationException;
import ru.mif.fortunewheel.security.mapper.ApiClientJWTMapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 */
@Component
public final class ApiClientAuthenticationFilter extends AbstractAuthenticationFilter {

    private final ApiClientJWTMapper mapper;

    public ApiClientAuthenticationFilter(ApiClientJWTMapper mapper) {
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

        var identified = mapper.parse(getToken());
        /**
         * Add identified user to SecurityContextHolder
         */
        SecurityContextHolder.getContext().setAuthentication(identified);
        filterChain.doFilter(request, response);
    }
}
