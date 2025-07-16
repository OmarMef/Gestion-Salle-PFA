package pfa.gestionsalle.security.service;

import pfa.gestionsalle.entities.Role;
import pfa.gestionsalle.entities.Utilisateur;

public interface AccountService {
    Utilisateur addNewUser(String nom ,String prenom ,String username ,String email ,String password ,String ConfirmPassword);
    Role addNewRole(String role);
    void addRoleToUser(String email , String role);
    void deleteRoleFromUser(String email , String role);
    Utilisateur LoadUserByUsername(String Username);


}
