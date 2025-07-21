package pfa.gestionsalle.web;


import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pfa.gestionsalle.entities.Salle;
import pfa.gestionsalle.repository.SalleRepository;
import pfa.gestionsalle.service.SalleService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/salle")
public class SalleController {

    private SalleService salleService;
        //**************GERER LES SALLES***********************

    @PostMapping("/save")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Salle createSalle(@RequestBody Salle salle) {
        return salleService.createSalle(salle);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Salle updateSalle(@PathVariable Long id, @RequestBody Salle salleModifiee) {
        return salleService.updateSalle(id, salleModifiee);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteSalle(@PathVariable Long id) {
         salleService.deleteSalle(id);
    }

//---------------------------------------------------------------------------------------------
    @GetMapping("/salles")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Salle> findAll() { return salleService.findAll(); }

    @GetMapping("/id/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Salle findSalleById(@PathVariable("id")long id) { return salleService.findById(id); }

    @GetMapping("/search/nom/{nom}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Salle findSalleByNom(@PathVariable("nom")String nom) { return salleService.findSalleByNom(nom); }

    @GetMapping("/capacite/{capacite}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Salle findSalleByCapacite(@PathVariable("capacite")int capacite) {return  salleService.findSalleByCapacite(capacite); }

    @GetMapping("/nom/contains/{KW}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Salle> findByNomContainingIgnoreCase(@PathVariable("KW") String KW) { return salleService.findByNomContainingIgnoreCase(KW); }


    @GetMapping("/search")
    public List<Salle> findByCapaciteGreaterThanEqualAndLocalisationContainingIgnoreCase(
            @RequestParam(required = false , defaultValue = "0") int capacite,
            @RequestParam(required = false , defaultValue = "") String localisation
    )
    { return salleService.findByCapaciteGreaterThanEqualAndLocalisationContainingIgnoreCase(capacite, localisation);}


}
