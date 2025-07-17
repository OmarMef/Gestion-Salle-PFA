package pfa.gestionsalle.security.services;

import org.springframework.security.access.prepost.PreAuthorize;
import pfa.gestionsalle.entities.Role;
import pfa.gestionsalle.entities.Utilisateur;

public interface AccountService {
    Utilisateur addNewUser(String nom ,String prenom ,String username ,String password ,String ConfirmPassword);
    Role addNewRole(String role);

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    void addRoleToUser(String username , String role);

    void deleteRoleFromUser(String username , String role);
    Utilisateur LoadUserByUsername(String Username);


}
