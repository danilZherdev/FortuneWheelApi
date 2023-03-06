package ru.mif.fortunewheel.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.mif.fortunewheel.security.mapper.UserJWTMapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class UserAuthenticationFilter extends OncePerRequestFilter {

    private final Logger logger = LoggerFactory.getLogger(UserAuthenticationFilter.class);

    private final static String AUTHORIZATION_HEADER_KEY = "Authorization";
    private final static String TOKEN_PREFIX = "Bearer";

    private final UserJWTMapper userJWTMapper;

    public UserAuthenticationFilter(UserJWTMapper userJWTMapper) {
        this.userJWTMapper = userJWTMapper;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        var authorizationHeader = request.getHeader(AUTHORIZATION_HEADER_KEY);
        if (!StringUtils.hasText(authorizationHeader)) {
            logger.warn("Authorization header in request {} not found", request);
            filterChain.doFilter(request, response);
            return;
        }
        if (!authorizationHeader.startsWith(TOKEN_PREFIX)) {
            logger.warn("Authorization header not starts with {} in request {}", TOKEN_PREFIX, request);
            filterChain.doFilter(request, response);
            return;
        }
        var token = authorizationHeader.substring(TOKEN_PREFIX.length());
        if (!StringUtils.hasText(token)) {
            logger.warn("Authorization header {} has not token in request {}", authorizationHeader, request);
            filterChain.doFilter(request, response);
            return;
        }
        if (!userJWTMapper.isValid(token)) {
            logger.warn("Token {} is not valid user token in request {}", token, request);
            filterChain.doFilter(request, response);
            return;
        }

        var identified = userJWTMapper.parse(token);
        SecurityContextHolder.getContext().setAuthentication(identified);
        filterChain.doFilter(request, response);

    }
}
