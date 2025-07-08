package pfa.gestionsalle.web;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import pfa.gestionsalle.entities.Utilisateur;
import pfa.gestionsalle.repository.UserRepository;

import java.util.List;


@RestController
@AllArgsConstructor
public class UserController {

    private UserRepository userRepository;


    @GetMapping("/index")
    public Page index(
            @RequestParam(defaultValue = "0") int page ,
            @RequestParam(defaultValue = "3") int size){
        return userRepository.findAll(PageRequest.of(page,size));
    }
//-----------------------------------------------------//

    @GetMapping("/users")
    public List<Utilisateur> findAll() {
        return userRepository.findAll();
    }

    @GetMapping("/id")
    public Utilisateur findById(@PathVariable("id") long id) {
        return userRepository.findById(id);
    }

    @GetMapping("/name/{name}")
    public List<Utilisateur> findByNom(@PathVariable("name") String nom){
        return userRepository.findByNom(nom);
    }

    @GetMapping("/email/{email}")
    public Utilisateur findByEmail(@PathVariable String email){
        return userRepository.findByEmail(email);
    }
}
