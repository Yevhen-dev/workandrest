package org.example.finalproject.config;

import org.example.finalproject.util.CustomSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomSuccessHandler successHandler;
    private final UserDetailsService userDetailsService;

    public SecurityConfig(CustomSuccessHandler successHandler, UserDetailsService userDetailsService) {
        this.successHandler = successHandler;
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers( "/index", "/", "/style/**").permitAll()
                        .requestMatchers("/userDashboard","/userDashboard/start", "/userDashboard/end", "/holiday", "/menu", "/confirmOrder", "/send").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                        .requestMatchers("/addUser", "/adminDashboard", "/allShifts", "/editShift", "/updateShift", "/allHolidays", "/allHolidays/updateStatus", "/allOrders").hasAuthority("ROLE_ADMIN")
                        .requestMatchers("/userDashboard").hasAuthority("ROLE_USER")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/")
                        .loginProcessingUrl("/login")
                        .usernameParameter("email")
                        .successHandler(successHandler)
                        .permitAll()
                )
                .logout(LogoutConfigurer::permitAll
                )
                .csrf(csrf -> csrf.disable()
                );
        return http.build();
    }



}
