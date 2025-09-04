package pfa.gestionsalle.web;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import pfa.gestionsalle.dto.UserDto;
import pfa.gestionsalle.entities.Role;
import pfa.gestionsalle.entities.Utilisateur;
import pfa.gestionsalle.repository.UserRepository;
import pfa.gestionsalle.security.services.AccountService;
import pfa.gestionsalle.service.RoleService;

import java.security.Principal;
import java.util.List;
import java.util.Map;


@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    public AccountService accountService;

    @GetMapping("/me")
    @PreAuthorize("permitAll()")
    public ResponseEntity<UserDto> getCurrentUser(Principal principal) {
        if (principal == null) {
            // Spring ne voit pas la session
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Utilisateur user = accountService.findByUsername(principal.getName());
        return ResponseEntity.ok(new UserDto(user.getNom(), user.getUsername()));
    }

    @GetMapping("/me/id")
    @PreAuthorize("permitAll()")
    public ResponseEntity<Long> getCurrentUserId(Principal principal) {
        if (principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Utilisateur user = accountService.findByUsername(principal.getName());
        return ResponseEntity.ok(user.getId()); // renvoie l'ID
    }

//----------------------------------------------------------------------------------------------------------

    //*************************METHODES ADMIN****************************//

    @PostMapping("/save")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Utilisateur SaveUser(@RequestParam String nom ,
                                  @RequestParam String prenom,
                                  @RequestParam String username,
                                  @RequestParam String password ,
                                  @RequestParam String CPassword){

        return accountService.addNewUser(nom, prenom, username, password, CPassword);
    }

    @PutMapping("/edit/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Utilisateur EditUser(@PathVariable("id") Long id ,@RequestBody Utilisateur userDetails) {
        return accountService.editUser(id, userDetails);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteUser(@PathVariable("id") Long id) { accountService.deleteUser(id); }

    //------------------------------------------------------------------------------------------------

    @GetMapping("/admin/index")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Page index(
            @RequestParam(defaultValue = "0") int page ,
            @RequestParam(defaultValue = "2") int size){
        return accountService.findAllUsers(PageRequest.of(page,size));
    }


    @GetMapping("/admin/users")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Utilisateur> findAll() {
        return accountService.findAllUsers();
    }

    @GetMapping("/username/{username}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Utilisateur findByUsername(@PathVariable("username")String username) {
        return accountService.findByUsername(username);
    }

    @GetMapping("/Search/nom/{nom}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Utilisateur> findByNom(@PathVariable("nom") String nom){
        return accountService.findByNom(nom);
    }

    @GetMapping("/Search/prenom/{prenom}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Utilisateur> findByPrenom(@PathVariable("prenom") String prenom){ return accountService.findByPrenom(prenom); }

    @GetMapping("/nom/contains/{KW}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Utilisateur> findByNomContainsIgnoreCase(@PathVariable("KW") String KW) { return accountService.findByNomContainsIgnoreCase(KW); }

    @GetMapping("/prenom/contains/{KW}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Utilisateur> findByPrenomContainsIgnoreCase(@PathVariable("KW") String KW) { return accountService.findByPrenomContainsIgnoreCase(KW); }

    @GetMapping("/user/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Utilisateur findById(@PathVariable("id") long id) { return accountService.findById(id); }


    //------------------------------------------------------------------------------------------------------------------
    @PostMapping("/addRole")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void addRoleToUser(@RequestParam String username,
                              @RequestParam String role) {
        accountService.addRoleToUser(username, role);
    }

    @PostMapping("/removeRole")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void removeRoleFromUser(@RequestParam String username,
                                   @RequestParam String role) {
        accountService.deleteRoleFromUser(username, role);
    }

    @GetMapping("/current-role")
    public Map<String, String> getCurrentUserRole(Principal principal) {
        String username = principal.getName(); // username de l'utilisateur connecté
        String role = accountService.getRoleOfCurrentUser(username);
        return Map.of("role", role); // renvoie JSON { "role": "ADMIN" } ou { "role": "USER" }
    }


}
