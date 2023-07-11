package xyz.lechi.classbook.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import xyz.lechi.classbook.dto.mapper.UserMapper;
import xyz.lechi.classbook.repository.UserRepository;
import xyz.lechi.classbook.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    UserDetailsService userDetailsService(UserRepository userRepository, UserMapper userMapper) {
        return new UserService(userRepository, userMapper);
    }

    @Bean
    AuthenticationProvider authenticationProvider(
        UserDetailsService userDetailsService,
        PasswordEncoder passwordEncoder
    ) {
        var authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    SecurityFilterChain securityFilterChain(
        HttpSecurity httpSecurity,
        AuthenticationProvider authenticationProvider,
        JwtTokenAuthFilter jwtAuthFilter
    ) throws Exception {
       httpSecurity
           .csrf(AbstractHttpConfigurer::disable)
           .securityMatcher("/api/**")
           .authenticationProvider(authenticationProvider)
           .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

       httpSecurity.authorizeHttpRequests(requests -> {
           requests
               .requestMatchers("/api/auth/register", "/api/auth/login")
               .permitAll();

           requests
               .anyRequest()
               .authenticated();
       });

       return httpSecurity.build();
    }
}
