package sessionmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfig
{
    @Autowired
    MyUserDetailsService myUserDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        //return new BCryptPasswordEncoder();
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception
    {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);

        authenticationManagerBuilder.userDetailsService(myUserDetailsService);
        return authenticationManagerBuilder.build();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception
    {
        // We don't need CSRF for this example
        httpSecurity.csrf().disable()
            .authorizeHttpRequests()
            .requestMatchers("/signup/", "/signin").permitAll()
            // all other requests need to be authenticated
            .anyRequest()
            .authenticated()
            .and().httpBasic()
            .and().formLogin()
            .and()
            // make sure we use stateless session; session won't be used to store user's state.
            .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED); // create session if required.

        httpSecurity.cors();

        return httpSecurity.build();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {

        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://127.0.0.1:5500/")
                        .allowCredentials(true)
                        .allowedMethods("*");
            }
        };
    }
}
