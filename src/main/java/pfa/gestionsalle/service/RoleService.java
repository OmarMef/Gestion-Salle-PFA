package pfa.gestionsalle.service;

import pfa.gestionsalle.entities.Role;

import java.util.List;

public interface RoleService {
    Role findById(Long id);
    Role addNewRole(String nomRole , String desc);
    void deleteRoleById(Long id);
    List<Role> getAllRoles();
}
