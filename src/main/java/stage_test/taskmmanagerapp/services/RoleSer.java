package stage_test.taskmmanagerapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stage_test.taskmmanagerapp.entities.AppRole;
import stage_test.taskmmanagerapp.reposotries.AppRoleRep;

@Service
public class RoleSer {
    @Autowired
    private AppRoleRep appRoleRep;

    public AppRole addRole(AppRole appRole) {
        return appRoleRep.save(appRole);
    }
}
