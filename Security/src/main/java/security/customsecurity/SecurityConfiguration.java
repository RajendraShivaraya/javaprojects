package security.customsecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.security.cert.Extension;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration
{
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails raj = User.withUsername("raj")
                .password(passwordEncoder().encode("1234"))
                .roles("USER")
                .build();
        UserDetails aks = User.withUsername("askhata")
                .password(passwordEncoder().encode("test"))
                .roles("USER")
                .build();
        UserDetails admin = User.withUsername("root")
                .password(passwordEncoder().encode("1234"))
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(raj, aks, admin);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity rajHttpSecurity) throws Exception
    {
        rajHttpSecurity
                .authorizeHttpRequests() // Authorize incoming http requests
                .requestMatchers("/**") // define such "/**" -> for all endpoint, "/user/**" -> only for users endoints
                .hasRole("USER") // define above rule for set of roles, we can deifne multiple roles here
                .and().formLogin() // Supports Login by form in UI
                .and().httpBasic(); // Supports login by basic auth using postman, curl etc

        return rajHttpSecurity.build();
    }
    */
    @Bean
    SecurityFilterChain allEndPointsSecurityFilterChain(HttpSecurity rajHttpSecurity) throws Exception
    {
        rajHttpSecurity.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/").permitAll()
                .requestMatchers("/signup/").permitAll()
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/user/**").hasRole("USER")
                .anyRequest()
                .authenticated()
                .and().formLogin()
                .and().httpBasic();

        return rajHttpSecurity.build();
    }

}
