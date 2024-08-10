package stage_test.taskmmanagerapp.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import stage_test.taskmmanagerapp.reposotries.PersonRep;

@Configuration
@EnableWebSecurity
public class SecConfig {

    private final PersonRep personRep;
    private final CustomerDetailsService customerDetailsService;

    public SecConfig(CustomerDetailsService customerDetailsService, PersonRep personRep) {
        this.customerDetailsService = customerDetailsService;
        this.personRep = personRep;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF protection
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/api/register").permitAll()// Secure specific requests
                                .anyRequest().authenticated() // Secure all other requests
                )
                .httpBasic(Customizer.withDefaults()); // Enable basic HTTP authentication
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /*@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }*/
}
