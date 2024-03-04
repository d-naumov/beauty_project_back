package com.example.end.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        authorizeRequests ->
                                authorizeRequests
                                        .antMatchers(HttpMethod.GET, "/car/all").permitAll()
                                        .antMatchers(HttpMethod.POST, "/registration/register").permitAll()
                                        .antMatchers(HttpMethod.GET, "/car/by_id/{id}").hasRole("USER")
                                        .antMatchers(HttpMethod.POST, "/car/save").hasRole("ADMIN")
                                        .anyRequest().hasRole("ADMIN")
                )
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}



//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.AuthenticationEntryPoint;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.access.AccessDeniedHandler;
//import org.springframework.security.web.authentication.AuthenticationFailureHandler;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//import org.springframework.security.web.authentication.HttpStatusEntryPoint;
//import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true, proxyTargetClass = true)
//public class SecurityConfig {
//    private static final AuthenticationEntryPoint ENTRY_POINT = new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED);
//    private static final AccessDeniedHandler ACCESS_DENIED_HANDLER = new CustomAccessDeniedHandler();
//    private static final AuthenticationSuccessHandler LOGIN_SUCCESS_HANDLER = new CustomLoginSuccessHandler();
//    private static final AuthenticationFailureHandler LOGIN_FAILURE_HANDLER = new CustomLoginFailureHandler();
//    private static final LogoutSuccessHandler LOGOUT_SUCCESS_HANDLER = new CustomLogoutSuccessHandler();
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.csrf().disable();
//        httpSecurity.headers().frameOptions().disable();
//
//        httpSecurity
//                .authorizeRequests()
//                .antMatchers("/swagger-ui/**").permitAll()
//                .antMatchers(org.springframework.http.HttpMethod.POST, "/api/users/register/**").permitAll()
//                .antMatchers("/api/users/confirm/**").permitAll()
//                .antMatchers("/api/files/**").permitAll()
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

