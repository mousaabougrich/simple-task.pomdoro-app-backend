package stage_test.taskmmanagerapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import stage_test.taskmmanagerapp.entities.AppRole;
import stage_test.taskmmanagerapp.services.RoleSer;

@RestController
public class AppRoleController {
    @Autowired
    private RoleSer roleSer;

    @PostMapping("/addrole")
    public AppRole addAppRole(@RequestBody AppRole appRole) {
        return roleSer.addRole(appRole);
    }
}
