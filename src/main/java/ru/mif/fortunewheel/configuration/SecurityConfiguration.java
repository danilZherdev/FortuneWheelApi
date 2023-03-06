package ru.mif.fortunewheel.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.mif.fortunewheel.security.UserAuthenticationFilter;

@Configuration
public class SecurityConfiguration {

    private final UserAuthenticationFilter userAuthenticationFilter;

    public SecurityConfiguration(UserAuthenticationFilter userAuthenticationFilter) {
        this.userAuthenticationFilter = userAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
        security.addFilterBefore(userAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return security
                .csrf().disable()
                .cors().disable()
                .authorizeRequests()
                .mvcMatchers("/user/auth**","/swagger-ui/**", "/v3/**").permitAll()
                .anyRequest().authenticated()
                .and().build();
    }
}
