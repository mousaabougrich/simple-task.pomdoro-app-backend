package stage_test.taskmmanagerapp.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import stage_test.taskmmanagerapp.Dtos.LoginDto;
import stage_test.taskmmanagerapp.Dtos.RegistreDto;
import stage_test.taskmmanagerapp.entities.AppRole;
import stage_test.taskmmanagerapp.entities.Person;
import stage_test.taskmmanagerapp.reposotries.AppRoleRep;
import stage_test.taskmmanagerapp.reposotries.PersonRep;

import java.util.Collections;

@RestController
@RequestMapping("/api")
public class RegistreandLogin {

    private final PasswordEncoder passwordEncoder;
    private final AppRoleRep appRoleRep;
    private final PersonRep personRep;
    private final AuthenticationManager authenticationManager;

    public RegistreandLogin(AppRoleRep appRoleRep, AuthenticationManager authenticationManager, PersonRep personRep, PasswordEncoder passwordEncoder) {
        this.appRoleRep = appRoleRep;
        this.authenticationManager = authenticationManager;
        this.personRep = personRep;
        this.passwordEncoder = passwordEncoder;
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto) {
        Authentication authentication=authenticationManager.
                authenticate(new
                        UsernamePasswordAuthenticationToken(loginDto.getUsername(),
                        loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>("user signed in sucess!",HttpStatus.OK);


    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegistreDto registreDto) throws Exception {

        if (personRep.existsByUsername(registreDto.getUsername())) {
            return new ResponseEntity<>("Username already exists", HttpStatus.BAD_REQUEST);
        }
        Person person = new Person();
        person.setUsername(registreDto.getUsername());
        person.setUsername(registreDto.getUsername());
        person.setLastName(registreDto.getLastName());
        person.setPassword(passwordEncoder.encode(registreDto.getPassword()));
        AppRole role = appRoleRep.findByRolename("User");
        person.setRoles(Collections.singletonList(role));

        personRep.save(person);

        return new ResponseEntity<>("User created", HttpStatus.CREATED);
    }
}
