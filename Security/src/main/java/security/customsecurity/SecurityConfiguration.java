package security.customsecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration
{
    /*
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
    */

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
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
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/user/**").hasRole("USER")
                .requestMatchers("/").permitAll()
                .requestMatchers("/signup/").permitAll()
                .anyRequest()
                .authenticated()
                .and().formLogin() // This will pop in build login form for login
                .and().httpBasic(); // Browser pop-up for login instead of inbuilt form login. We can use this in postman and other tools.

        return rajHttpSecurity.build();
    }

    @Autowired
    MyUserDetailService myUserDetailService;
    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception
    {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(myUserDetailService);
        return authenticationManagerBuilder.build();
    }
}
