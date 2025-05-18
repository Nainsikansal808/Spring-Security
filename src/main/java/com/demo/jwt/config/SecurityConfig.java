package com.demo.jwt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.session.HttpSessionEventPublisher;

/**
 * SecurityConfig configures session-based authentication using Spring Security.
 *
 * ✅ Authentication: Form-based login (Session-based)
 * ✅ Session Strategy: One session per user, prevents multiple logins
 */

@Configuration
@Profile("stateful") // Only active when 'stateful' profile is active
@EnableWebSecurity // Enables Spring Security’s web security support
public class SecurityConfig {

    @Bean
    public SecurityFilterChain getSecurityFilterChain(HttpSecurity http, SessionRegistry sessionRegistry) throws Exception {

        http
                // Enables default form-based login at /login with basic username/password fields
                .formLogin(Customizer.withDefaults())

                // Requires authentication for any HTTP request
                .authorizeHttpRequests(request -> request.anyRequest().authenticated())

                // Configures session management
                .sessionManagement(session ->
                        session
                                // Prevents session fixation by creating a new session upon login
                                .sessionFixation(SessionManagementConfigurer.SessionFixationConfigurer::newSession)

                                // Allows only 1 active session per user
                                .maximumSessions(1)

                                // If user is already logged in, prevent a second login
                                .maxSessionsPreventsLogin(true)

                                // Tracks active sessions using the SessionRegistry bean
                                .sessionRegistry(sessionRegistry)
                )

                // Configures logout behavior
                .logout(logout ->
                        logout
                                // Invalidates the HTTP session on logout
                                .invalidateHttpSession(true)

                                // Clears authentication from the SecurityContext
                                .clearAuthentication(true)

                                // Deletes the JSESSIONID cookie from the browser
                                .deleteCookies("JSESSIONID")
                );

        // Finalizes and builds the security filter chain
        return http.build();
    }

    /**
     * Registers a listener that publishes HttpSession events (like session creation/destruction).
     * This is essential for session concurrency control to work correctly.
     */
    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }

    /**
     * Bean that holds session information (user to session mapping).
     * Required for tracking how many sessions each user has.
     */
    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }
}
