package pfa.gestionsalle.web;

import jdk.jshell.execution.Util;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pfa.gestionsalle.entities.Reservation;
import pfa.gestionsalle.entities.Utilisateur;
import pfa.gestionsalle.repository.ReservationRepository;
import pfa.gestionsalle.repository.UserRepository;

import java.util.List;


@RestController
@AllArgsConstructor
public class UserController {

    private UserRepository userRepository;


    @GetMapping("/admin/index")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Page index(
            @RequestParam(defaultValue = "0") int page ,
            @RequestParam(defaultValue = "2") int size){
        return userRepository.findAll(PageRequest.of(page,size));
    }

    @GetMapping("/users")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Utilisateur> findAll() {
        return userRepository.findAll();
    }
//-----------------------------------------------------//

    //*************************GERER LES UTILISATEURS****************************//

    @PostMapping("/save")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Utilisateur SaveUser(Utilisateur utilisateur) { return userRepository.save(utilisateur); }

    @PutMapping("/edit/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Utilisateur EditUser(@PathVariable("id") Long id ,@RequestBody Utilisateur userDetails) {
        Utilisateur utilisateur = userRepository.findById(id).get();
        utilisateur.setNom(userDetails.getNom());
        utilisateur.setPrenom(userDetails.getPrenom());
        utilisateur.setEmail(userDetails.getEmail());
        return userRepository.save(utilisateur);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void DeleteUser(@PathVariable("id") Long id) { userRepository.deleteById(id); }

    //-------------------------------------------------------------------------------------------------------------------
    @GetMapping("/username/{username}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Utilisateur findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @GetMapping("/nom/{nom}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Utilisateur> findByNom(@PathVariable("nom") String nom){
        return userRepository.findByNom(nom);
    }

    @GetMapping("/prenom/{prenom}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Utilisateur> findByPrenom(@PathVariable("prenom") String prenom){ return userRepository.findByPrenom(prenom); }

    @GetMapping("/email/{email}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Utilisateur findByEmail(@PathVariable String email){
        return userRepository.findByEmail(email);
    }

    @GetMapping("/nom/{KW}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Utilisateur> findByNomContainsIgnoreCase(@PathVariable("KW") String KW) { return userRepository.findByNomContainsIgnoreCase(KW); }

    @GetMapping("/prenom/{KW}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Utilisateur> findByPrenomContainsIgnoreCase(@PathVariable("KW") String KW) { return userRepository.findByPrenomContainsIgnoreCase(KW); }

    //-----------------------------------------------------//

    @GetMapping("/user/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Utilisateur findById(@PathVariable("id") long id) { return userRepository.findById(id); }

    //------------------------------------------------------------------------------------------------------------------



}
