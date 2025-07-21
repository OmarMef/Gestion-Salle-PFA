package pfa.gestionsalle.web;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pfa.gestionsalle.entities.Role;
import pfa.gestionsalle.service.RoleService;

import java.util.List;

@RestController
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("/roles")
@AllArgsConstructor
public class RoleController {

    private RoleService roleService;

    @PostMapping("/save")
    public Role AddNewRole(String nomRole ,String desc){
        return roleService.addNewRole(nomRole, desc);
    }

    @DeleteMapping("/delete")
    public void deleteRole(String nomRole){
        roleService.deleteRole(nomRole);
    }

    @GetMapping("/all")
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

}
