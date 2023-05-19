package securityoauth2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration
{
    @Bean
    SecurityFilterChain customOAuthFilterChain(HttpSecurity http) throws Exception
    {
        return http
                .authorizeHttpRequests(auth -> {
                        auth.requestMatchers("/Public").permitAll();
                        auth.anyRequest().authenticated(); } )
                .oauth2Login(withDefaults())
                .formLogin(withDefaults())
                .build();
    }
}
