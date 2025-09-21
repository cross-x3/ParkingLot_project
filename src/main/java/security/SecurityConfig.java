package security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

	 @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        http
	            .csrf(csrf -> csrf.disable())
	            .headers(headers -> headers
	                    .frameOptions(frameOptions -> frameOptions.disable())
	                )
	            .authorizeHttpRequests(auth -> auth
	                .requestMatchers("/api/admin/**").hasRole("ADMIN")
	                .requestMatchers("/api/parking/**").hasAnyRole("USER", "ADMIN")
	                .requestMatchers("/h2-console/**").permitAll()
	                .anyRequest().authenticated()
	            )
	            .httpBasic(customizer -> {})
	            .formLogin(customizer -> {})
	            .oauth2Login(customizer -> {});

	        return http.build();
	    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Expose AuthenticationManager for login flows if needed
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}