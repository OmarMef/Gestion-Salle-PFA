package pfa.gestionsalle.security.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pfa.gestionsalle.entities.Role;
import pfa.gestionsalle.entities.Utilisateur;

import java.util.List;

public interface AccountService {

    Utilisateur addNewUser(String nom ,String prenom ,String username ,String password ,String ConfirmPassword);
    Utilisateur editUser(Long id,Utilisateur user);
    void deleteUser(Long id);
//**************************************************************************************
    List<Utilisateur> findAllUsers();
    Page<Utilisateur> findAllUsers(Pageable pageable);
    Utilisateur findByUsername(String username);
    List<Utilisateur> findByNom(String nom);
    List<Utilisateur> findByPrenom(String prenom);
    List<Utilisateur> findByNomContainsIgnoreCase(String KW);
    List<Utilisateur> findByPrenomContainsIgnoreCase(String prenom);
    Utilisateur findById(long id);
//******************************************************************************************
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    void addRoleToUser(String username , String role);
    void deleteRoleFromUser(String username , String role);
    Utilisateur LoadUserByUsername(String Username);
    Role LoadRoleByUsername(String Username);


}
