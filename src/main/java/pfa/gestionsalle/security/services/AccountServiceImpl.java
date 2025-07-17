package pfa.gestionsalle.security.services;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pfa.gestionsalle.entities.Role;
import pfa.gestionsalle.entities.Utilisateur;
import pfa.gestionsalle.repository.RoleRepository;
import pfa.gestionsalle.repository.UserRepository;

@Service
@Transactional
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;


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
                .build();
        Utilisateur savedUser = userRepository.save(user);
        return savedUser;
    }

    @Override
    public Role addNewRole(String role){
        Role newRole = roleRepository.findByNomRole(role);
        if(newRole != null) throw new RuntimeException("Role already exists");
        newRole = Role.builder()
                .nomRole(role)
                .build();
        return roleRepository.save(newRole);
    }

    @Override
    public void addRoleToUser(String username, String role) {
        Utilisateur user = userRepository.findByUsername(username);
        Role role1 = roleRepository.findByNomRole(role);
        if(user == null) throw new RuntimeException("User does not exist");
        if(role1 == null) throw new RuntimeException("Role does not exist");
        user.setRole(role1);
    }

    @Override
    public void deleteRoleFromUser(String username, String role) {
        Utilisateur user = userRepository.findByUsername(username);
        Role role1 = roleRepository.findByNomRole(role);
        if(user == null) throw new RuntimeException("User does not exist");
        if(role1 == null) throw new RuntimeException("Role does not exist");
        user.setRole(null);
    }

    @Override
    public Utilisateur LoadUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }


}
