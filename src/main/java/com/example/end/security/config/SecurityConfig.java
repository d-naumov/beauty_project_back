//package com.example.end.security.config;
//
//import com.example.end.security.sec_filter.TokenFilter;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.AuthenticationEntryPoint;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.access.AccessDeniedHandler;
//import org.springframework.security.web.authentication.AuthenticationFailureHandler;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
//
//@Configuration
//@EnableWebSecurity
//@EnableMethodSecurity
//public class SecurityConfig {
//
//
//    private TokenFilter filter;
//
//    public SecurityConfig(TokenFilter filter) {
//        this.filter = filter;
//    }
//    @Bean
//    public AuthenticationEntryPoint authenticationEntryPoint() {
//        return SecurityExceptionHandlers.ENTRY_POINT;
//    }
//
//    @Bean
//    public AccessDeniedHandler accessDeniedHandler() {
//        return SecurityExceptionHandlers.ACCESS_DENIED_HANDLER;
//    }
//
//    @Bean
//    public AuthenticationSuccessHandler authenticationSuccessHandler() {
//        return SecurityExceptionHandlers.LOGIN_SUCCESS_HANDLER;
//    }
//
//    @Bean
//    public AuthenticationFailureHandler authenticationFailureHandler() {
//        return SecurityExceptionHandlers.LOGIN_FAILURE_HANDLER;
//    }
//
//    @Bean
//    public LogoutSuccessHandler logoutSuccessHandler() {
//        return SecurityExceptionHandlers.LOGOUT_SUCCESS_HANDLER;
//    }
//
//    @Bean
//    public BCryptPasswordEncoder encoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        return http
//                .httpBasic(AbstractHttpConfigurer::disable)
//                .csrf(AbstractHttpConfigurer::disable)
//                .sessionManagement(x -> x.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .authorizeHttpRequests(x -> x
//                        .requestMatchers("/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs", "/v3/api-docs/**").permitAll()
//                        .requestMatchers(HttpMethod.POST, "api/auth/login", "api/auth/access").permitAll()
//                        .requestMatchers(HttpMethod.GET, "api/procedures").permitAll()//getAll,getById,getByCategory
//                        .requestMatchers(HttpMethod.PUT, "api/procedures").hasRole("ADMIN")//update
//                        .requestMatchers(HttpMethod.POST, "api/procedures").hasRole("ADMIN")//create
//                        .requestMatchers(HttpMethod.DELETE, "api/procedures").hasRole("ADMIN")//delete
//                        .requestMatchers(HttpMethod.GET, "api/users/masters").permitAll()//allMasters
//                        .requestMatchers(HttpMethod.GET, "api/users/by-category").permitAll()//MastersByCategory
//                        .requestMatchers(HttpMethod.POST, "api/users").permitAll()//register
//                        .requestMatchers(HttpMethod.POST, "api/users/confirm").hasRole("ADMIN")//confirm master by email
//                        .requestMatchers(HttpMethod.DELETE, "api/users").hasRole("ADMIN")//addProfileImage
//                        .requestMatchers(HttpMethod.POST, "api/metadata").hasRole("MASTER")//addPortfolioImages
//                        .requestMatchers(HttpMethod.PUT, "api/bookings").hasRole("ADMIN")//updateBookingStatus
//                        .requestMatchers(HttpMethod.GET, "api/categories").permitAll()//getAll
//                        .requestMatchers(HttpMethod.PUT, "api/categories").hasRole("ADMIN")//update
//                        .requestMatchers(HttpMethod.DELETE, "api/categories").hasRole("ADMIN")
//                        .requestMatchers(HttpMethod.POST, "api/categories").hasRole("ADMIN")//create
//                        .requestMatchers(HttpMethod.GET, "api/reviews").permitAll()//getRating, getReviews
//                        .requestMatchers(HttpMethod.DELETE, "api/reviews").hasRole("ADMIN")
//
//
//                        .anyRequest().authenticated())
//                .addFilterAfter(filter, UsernamePasswordAuthenticationFilter.class)
//                .exceptionHandling(configurer -> configurer
//                        .authenticationEntryPoint(authenticationEntryPoint())
//                        .accessDeniedHandler(accessDeniedHandler()))
//                .formLogin(configurer -> configurer
//                        .successHandler(authenticationSuccessHandler())
//                        .failureHandler(authenticationFailureHandler()))
//                .logout(configurer -> configurer
//                        .logoutSuccessHandler(logoutSuccessHandler()))
//                .build();
//    }
//}
package com.example.end.security.config;

import com.example.end.security.sec_filter.TokenFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    private final TokenFilter filter;

    public SecurityConfig(TokenFilter filter) {
        this.filter = filter;
    }

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        x -> x
                                .requestMatchers("/**").permitAll())
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }
}