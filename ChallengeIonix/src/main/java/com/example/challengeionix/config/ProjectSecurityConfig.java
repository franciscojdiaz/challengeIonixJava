package com.example.challengeionix.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig {


    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

        http.csrf((csrf) -> csrf.disable())
                .authorizeHttpRequests((requests)->requests
                        .requestMatchers("/api/v1/user/create", "/api/v1/user/delete/**").authenticated()
                        .requestMatchers("/api/v1/user/all",
                                "/api/v1/user/byEmail/**",
                                "/api/v1/ext/askApiExt","/v3/api-docs/**","/swagger-ui/**","swagger-ui.html").permitAll())
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());
        return http.build();


    }

}
