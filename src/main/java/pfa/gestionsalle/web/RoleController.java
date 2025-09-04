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
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Role AddNewRole(String nomRole ,String desc){
        return roleService.addNewRole(nomRole, desc);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id){
        roleService.deleteRoleById(id);
    }


    @GetMapping("/all")
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

}
