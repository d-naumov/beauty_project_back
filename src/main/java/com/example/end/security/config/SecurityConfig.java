package com.example.end.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .antMatchers("/**").permitAll() // Разрешить все запросы
                );
    }
    //    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(
//                        authorizeRequests ->
//                                authorizeRequests
//                                        .antMatchers(HttpMethod.GET, "/*+").permitAll()
//                                        .antMatchers(HttpMethod.GET, "/api/users/").permitAll()
//                                        .antMatchers(HttpMethod.GET, "/api/users").permitAll()
//                                        .antMatchers(HttpMethod.POST, "/registration/register").permitAll()
//                                        .antMatchers(HttpMethod.GET, "/master/by_id/{id}").hasRole("MASTER")
//                                        .antMatchers(HttpMethod.POST, "/master/save").hasRole("ADMIN")
//                                        .anyRequest().hasRole("ADMIN")
//                )
//                .httpBasic(Customizer.withDefaults());
//
//        return http.build();
//    }

}
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//import static de.ait.template.security.config.SecurityExceptionHandlers.*;
//
///**
// * 10/13/2023
// * education-center
// *
// * @author Marsel Sidikov (AIT TR)
// */
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true, proxyTargetClass = true)
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.csrf().disable();
//        httpSecurity.headers().frameOptions().disable();
//
//        httpSecurity
//                .authorizeRequests()
//                .antMatchers("/swagger-ui/**").permitAll()
//                .antMatchers(HttpMethod.POST, "/api/users/register/**").permitAll()
//                .antMatchers("/api/users/confirm/**").permitAll()
//                .antMatchers("/api/**").authenticated();
//
//        httpSecurity
//                .exceptionHandling()
//                .defaultAuthenticationEntryPointFor(ENTRY_POINT, new AntPathRequestMatcher("/api/**"))
//                .accessDeniedHandler(ACCESS_DENIED_HANDLER);
//
//        httpSecurity
//                .formLogin()
//                .loginProcessingUrl("/api/login")
//                .successHandler(LOGIN_SUCCESS_HANDLER)
//                .failureHandler(LOGIN_FAILURE_HANDLER);
//
//        httpSecurity
//                .logout()
//                .logoutUrl("/api/logout")
//                .logoutSuccessHandler(LOGOUT_SUCCESS_HANDLER);
//
//        return httpSecurity.build();
//    }
//
//    @Autowired
//    public void bindUserDetailsServiceAndPasswordEncoder(UserDetailsService userDetailsServiceImpl,
//                                                         PasswordEncoder passwordEncoder,
//                                                         AuthenticationManagerBuilder builder) throws Exception {
//        builder.userDetailsService(userDetailsServiceImpl)
//                .passwordEncoder(passwordEncoder);
//    }
//}
