package com.ftn.isa.config;

import com.ftn.isa.model.Administrator;
import com.ftn.isa.model.RegisteredUser;
import com.ftn.isa.repository.AdministratorRepository;
import com.ftn.isa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    private final UserRepository userRepository;
    private final AdministratorRepository administratorRepository;

    // @Bean
    // public UserDetailsService userDetailsService() {
    // return username -> repository.findOneByUsername(username)
    // .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    // }
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            // Check for user in UserRepository
            RegisteredUser userEntity = userRepository.findOneByUsername(username).orElse(null);

            // Check for administrator in AdministratorRepository
            Administrator administratorEntity = administratorRepository.findOneByUsername(username).orElse(null);

            if (userEntity == null && administratorEntity == null) {
                throw new UsernameNotFoundException("User not found");
            } else if (userEntity != null) {
                return userEntity;
            } else {
                return administratorEntity;
            }
        };
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}