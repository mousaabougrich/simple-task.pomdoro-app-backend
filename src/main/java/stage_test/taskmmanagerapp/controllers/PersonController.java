package stage_test.taskmmanagerapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import stage_test.taskmmanagerapp.entities.AppRole;
import stage_test.taskmmanagerapp.entities.Person;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import stage_test.taskmmanagerapp.entities.RoleUserForm;
import stage_test.taskmmanagerapp.services.PersonService;

@RestController
@AllArgsConstructor
@RequestMapping("/person")
public class PersonController {
    @Autowired
    private PersonService personService;

    @PostMapping("/add")
    public Person addPerson(@RequestBody Person person) {
        return personService.addPerson(person);
    }

    @PutMapping("/update")
    public Person updatePerson(@RequestBody Person person, @RequestParam int id) {
        return personService.updatePerson(person, id);
    }

    @DeleteMapping("/delete")
    public void deletePerson(@RequestParam int id) {
        personService.deletePersonById(id);
    }

    @GetMapping("/get")
    public Person findPersonByUsername(@RequestParam String username) {
        return personService.findPersonByUsername(username);
    }
    /// first test////
    @PostMapping("/addRoleToperson")
    public void addRoleToPerson(@RequestParam String username, String role) {
       personService.addRoleToPerson(username,role);

    }
    @PostMapping("/addroletouser")

    public void addRoleToUser(@RequestBody RoleUserForm roleuser ){

        personService.addRoleToPerson(roleuser.getRolename(),roleuser.getUsername());
    }

}
