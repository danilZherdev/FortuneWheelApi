package ru.mif.fortunewheel.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.mif.fortunewheel.security.filters.ApiClientAuthenticationFilter;
import ru.mif.fortunewheel.security.filters.AuthorizationHeaderFilter;
import ru.mif.fortunewheel.security.filters.UserAuthenticationFilter;

@Configuration
public class SecurityConfiguration {

    private final AuthorizationHeaderFilter authorizationHeaderFilter;
    private final UserAuthenticationFilter userAuthenticationFilter;
    private final ApiClientAuthenticationFilter apiClientAuthenticationFilter;

    public SecurityConfiguration(AuthorizationHeaderFilter authorizationHeaderFilter,
                                 UserAuthenticationFilter userAuthenticationFilter,
                                 ApiClientAuthenticationFilter apiClientAuthenticationFilter) {
        this.authorizationHeaderFilter = authorizationHeaderFilter;
        this.userAuthenticationFilter = userAuthenticationFilter;
        this.apiClientAuthenticationFilter = apiClientAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
        // third filter
//        security.addFilterBefore(apiClientAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        // second filter
        security.addFilterBefore(userAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        // first filter
        security.addFilterBefore(authorizationHeaderFilter, UserAuthenticationFilter.class);

        return security
                .csrf().disable()
                .cors().disable()
                .authorizeRequests()
                .mvcMatchers("/user/auth**","/swagger-ui/**", "/v3/**").permitAll() //ignore this Urls.
                .anyRequest().authenticated() // all others request authenticate.
                .and().build();
    }
}
