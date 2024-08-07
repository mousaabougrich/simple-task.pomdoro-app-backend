package stage_test.taskmmanagerapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import stage_test.taskmmanagerapp.entities.Person;
import stage_test.taskmmanagerapp.reposotries.PersonRep;

import java.util.ArrayList;
import java.util.Collection;

@Configuration
@EnableWebSecurity
public class SecConfig {

    @Autowired
    private PersonRep personRep;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http

                .csrf((csrf) -> csrf.disable())//for diasbling the srcf

                .authorizeRequests()//for autorzingrequeste
                .anyRequest()
                .authenticated()//must be authenticated
                .and().
                httpBasic(Customizer.withDefaults());//enable basic http*/
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                Person person = personRep.findByUsername(username);
                if (person == null) {
                    throw new UsernameNotFoundException("User not found");
                }
                Collection<GrantedAuthority> authorities = new ArrayList<>();
                person.getRoles().forEach(r -> {
                    authorities.add(new SimpleGrantedAuthority(r.getRolename()));
                });

                return new User(person.getUsername(), person.getPassword(), authorities);
            }
        };
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService());
    }
}
