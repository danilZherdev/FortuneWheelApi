package ru.mif.fortunewheel.security.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.mif.fortunewheel.security.AuthenticationException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



public abstract class AbstractAuthenticationFilter extends OncePerRequestFilter {

    protected final Logger logger = LoggerFactory.getLogger(AbstractAuthenticationFilter.class);

    private String token;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        var token = request.getAttribute(FilterConstants.ACCESS_TOKEN_KEY);
        if (token == null) {
            logger.warn("Token is empty user token in request {}", request);
            filterChain.doFilter(request, response);
            return;
        }
        this.token = token.toString();
    }

    protected String getToken() {
        return token;
    }
}
