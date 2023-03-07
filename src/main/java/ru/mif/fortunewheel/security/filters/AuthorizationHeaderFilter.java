package ru.mif.fortunewheel.security.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthorizationHeaderFilter extends OncePerRequestFilter {

    private final Logger logger = LoggerFactory.getLogger(AuthorizationHeaderFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        //check is header exist
        var authorizationHeader = request.getHeader(FilterConstants.AUTHORIZATION_HEADER_KEY);
        if (!StringUtils.hasText(authorizationHeader)) {
            logger.warn("Authorization header in request {} not found", request);
            filterChain.doFilter(request, response);
            return;
        }
        //check that token start with TOKEN_PREFIX
        if (!authorizationHeader.startsWith(FilterConstants.TOKEN_PREFIX)) {
            logger.warn("Authorization header not starts with {} in request {}", FilterConstants.TOKEN_PREFIX, request);
            filterChain.doFilter(request, response);
            return;
        }
        //check that token value has text.
        var token = authorizationHeader.substring(FilterConstants.TOKEN_PREFIX.length());
        if (!StringUtils.hasText(token)) {
            logger.warn("Authorization header {} has not token in request {}", authorizationHeader, request);
            filterChain.doFilter(request, response);
            return;
        }
        //put extracted token to request attributes.
        request.setAttribute(FilterConstants.ACCESS_TOKEN_KEY, token);
        filterChain.doFilter(request, response);
    }
}
