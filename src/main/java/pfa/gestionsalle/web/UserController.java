package pfa.gestionsalle.web;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pfa.gestionsalle.entities.Utilisateur;
import pfa.gestionsalle.repository.UserRepository;

import java.util.List;


@RestController
@AllArgsConstructor
public class UserController {

    private UserRepository userRepository;

    @GetMapping("/home")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public String home(){
        return "Bonjour Page D acceuil";
    }
//----------------------------------------------------------------------------------------------------------

    //*************************METHODES ADMIN****************************//

    @PostMapping("/save")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Utilisateur AddNewUtilisateur(String nom , String prenom, String username, String password , String CPassword){
        Utilisateur user = userRepository.findByUsername(username);
        if(user != null) throw new RuntimeException("User already exists");
        if(!password.equals(CPassword)) throw new RuntimeException("Password does not match");
        user = Utilisateur.builder()
                .nom(nom)
                .prenom(prenom)
                .username(username)
                .password(password)
                .build();
        Utilisateur savedUser = userRepository.save(user);
        return savedUser;
    }

    @PutMapping("/edit/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Utilisateur EditUser(@PathVariable("id") Long id ,@RequestBody Utilisateur userDetails) {
        Utilisateur utilisateur = userRepository.findById(id).get();
        utilisateur.setNom(userDetails.getNom());
        utilisateur.setPrenom(userDetails.getPrenom());
        utilisateur.setUsername(userDetails.getUsername());
        return userRepository.save(utilisateur);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void DeleteUser(@PathVariable("id") Long id) { userRepository.deleteById(id); }

    //------------------------------------------------------------------------------------------------

    @GetMapping("/admin/index")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Page index(
            @RequestParam(defaultValue = "0") int page ,
            @RequestParam(defaultValue = "2") int size){
        return userRepository.findAll(PageRequest.of(page,size));
    }

    @GetMapping("/admin/users")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Utilisateur> findAll() {
        return userRepository.findAll();
    }

    @GetMapping("/username/{username}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Utilisateur findByUsername(@PathVariable("username")String username) {
        return userRepository.findByUsername(username);
    }

    @GetMapping("/Search/nom/{nom}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Utilisateur> findByNom(@PathVariable("nom") String nom){
        return userRepository.findByNom(nom);
    }

    @GetMapping("/Search/prenom/{prenom}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Utilisateur> findByPrenom(@PathVariable("prenom") String prenom){ return userRepository.findByPrenom(prenom); }

    @GetMapping("/nom/contains/{KW}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Utilisateur> findByNomContainsIgnoreCase(@PathVariable("KW") String KW) { return userRepository.findByNomContainsIgnoreCase(KW); }

    @GetMapping("/prenom/contains/{KW}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Utilisateur> findByPrenomContainsIgnoreCase(@PathVariable("KW") String KW) { return userRepository.findByPrenomContainsIgnoreCase(KW); }

    @GetMapping("/user/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Utilisateur findById(@PathVariable("id") long id) { return userRepository.findById(id); }

    //------------------------------------------------------------------------------------------------------------------



}
