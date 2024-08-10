package stage_test.taskmmanagerapp.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import stage_test.taskmmanagerapp.entities.AppRole;
import stage_test.taskmmanagerapp.entities.Person;
import stage_test.taskmmanagerapp.reposotries.AppRoleRep;
import stage_test.taskmmanagerapp.reposotries.PersonRep;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class CustomerDetailsService implements UserDetailsService {

    @Autowired
    private PersonRep personRep;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Fetch the user from the database
        Person person = personRep.findByUsername(username);
        if (person == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        // Convert the roles to granted authorities
        Collection<GrantedAuthority> authorities = authorities(person.getRoles());

        // Return the UserDetails implementation with the user's data
        return new User(person.getUsername(), person.getPassword(), authorities(person.getRoles()));
    }

    private Collection<GrantedAuthority> authorities(Collection<AppRole> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getRolename()))
                .collect(Collectors.toList());
    }
}
