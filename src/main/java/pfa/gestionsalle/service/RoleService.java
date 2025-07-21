package pfa.gestionsalle.service;

import pfa.gestionsalle.entities.Role;

import java.util.List;

public interface RoleService {
    Role addNewRole(String nomRole , String desc);
    void deleteRole(String nomRole);
    List<Role> getAllRoles();
}
