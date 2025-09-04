package pfa.gestionsalle.security.services;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pfa.gestionsalle.entities.Role;
import pfa.gestionsalle.entities.Utilisateur;
import pfa.gestionsalle.repository.RoleRepository;
import pfa.gestionsalle.repository.UserRepository;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    //SAVE
    @Override
    public Utilisateur addNewUser(String nom, String prenom,String username , String password, String ConfirmPassword) {
        Utilisateur user = userRepository.findByUsername(username);
        if(user != null) throw new RuntimeException("User already exists");
        if(!password.equals(ConfirmPassword)) throw new RuntimeException("Passwords do not match");
        user = Utilisateur.builder()
                .nom(nom)
                .prenom(prenom)
                .username(username)
                .password(passwordEncoder.encode(password))
                //.role(roleRepository.findByNomRole("USER"))
                .build();
        Utilisateur savedUser = userRepository.save(user);
        return savedUser;
    }

    //UPDATE
    public Utilisateur editUser(Long id, Utilisateur userDetails) {
        Utilisateur user = userRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));

        user.setNom(userDetails.getNom());
        user.setPrenom(userDetails.getPrenom());
        user.setUsername(userDetails.getUsername());
        user.setRole(userDetails.getRole());
        return userRepository.save(user);
    }

    //DELETE
    public void deleteUser(Long id) {
        if(!userRepository.existsById(id)) {throw new RuntimeException("Utilisateur introuvable");}
        userRepository.deleteById(id);
    }
//*******************************************************************************

    @Override
    public Page<Utilisateur> findAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public List<Utilisateur> findAllUsers(){
         return userRepository.findAll();
    }

    public Utilisateur findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public List<Utilisateur> findByNom(String nom){
        return userRepository.findByNom(nom);
    }

    public List<Utilisateur> findByPrenom(String prenom){
        return userRepository.findByPrenom(prenom);
    }

    public List<Utilisateur> findByNomContainsIgnoreCase(String KW){
        return userRepository.findByNomContainsIgnoreCase(KW);
    }

    public List<Utilisateur> findByPrenomContainsIgnoreCase(String prenom){
        return userRepository.findByPrenomContainsIgnoreCase(prenom);
    }

    public Utilisateur findById(long id){
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void addRoleToUser(String username, String role) {
        Utilisateur user = userRepository.findByUsername(username);
        Role role1 = roleRepository.findByNomRole(role);
        if(user == null) throw new RuntimeException("User does not exist");
        if(role1 == null) throw new RuntimeException("Role does not exist");
        user.setRole(role1);
        userRepository.save(user);
    }

    @Override
    public void deleteRoleFromUser(String username, String role) {
        Utilisateur user = userRepository.findByUsername(username);
        Role role1 = roleRepository.findByNomRole(role);
        if(user == null) throw new RuntimeException("User does not exist");
        if(role1 == null) throw new RuntimeException("Role does not exist");
        user.setRole(null);
        userRepository.save(user);
    }

    @Override
    public Utilisateur LoadUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Role LoadRoleByUsername(String Username) {
        Utilisateur user1 =userRepository.findByUsername(Username);
        if(user1 == null) throw new RuntimeException("User does not exist");
        return user1.getRole();
    }

    @Override
    public String getRoleOfCurrentUser(String username) {
        Utilisateur user = userRepository.findByUsername(username);
        if (user != null && user.getRole() != null) {
            return user.getRole().getNomRole(); // renvoie "ADMIN" ou "USER"
        }
        return "USER"; // fallback si pas de rôle défini
    }

}
