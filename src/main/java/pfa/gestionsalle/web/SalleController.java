package pfa.gestionsalle.web;


import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pfa.gestionsalle.entities.Salle;
import pfa.gestionsalle.repository.SalleRepository;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/salle")
public class SalleController {

    private SalleRepository salleRepository;
        //**************GERER LES SALLES***********************

    @PostMapping("/save")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Salle createSalle(@RequestBody Salle salle) {
        Salle salleMMnom = salleRepository.findSalleByNom(salle.getNom());
        if (salleMMnom != null) throw new RuntimeException("Salle deja existente");
        Salle savedSalle = salleRepository.save(salle);
            return salleRepository.save(savedSalle);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Salle updateSalle(@PathVariable Long id, @RequestBody Salle salleModifiee) {
        Salle salle = salleRepository.findById(id).orElse(null);
        if (salle != null) {
            salle.setNom(salleModifiee.getNom());
            salle.setCapacite(salleModifiee.getCapacite());
            salle.setLocalisation(salleModifiee.getLocalisation());
            return salleRepository.save(salle);
        }
        return null;
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteSalle(@PathVariable Long id) {
        if (salleRepository.findSalleById(id) != null) {
            salleRepository.deleteById(id);
            System.out.println("Salle deleted successfully");
        } else {
            System.out.println("Failed to delete salle");
        }
    }

//---------------------------------------------------------------------------------------------
    @GetMapping("/salles")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Salle> findAll() { return salleRepository.findAll(); }

    @GetMapping("/id/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Salle findSalleById(@PathVariable("id")long id) { return salleRepository.findSalleById(id); }

    @GetMapping("/search/nom/{nom}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Salle findSalleByNom(@PathVariable("nom")String nom) { return salleRepository.findSalleByNom(nom); }

    @GetMapping("/capacite/{capacite}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Salle findSalleByCapacite(@PathVariable("capacite")int capacite) {return  salleRepository.findSalleByCapacite(capacite); }

    @GetMapping("/nom/contains/{KW}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Salle> findByNomContainingIgnoreCase(@PathVariable("KW") String KW) { return salleRepository.findByNomContainingIgnoreCase(KW); }


    @GetMapping("/search")
    public List<Salle> findByCapaciteGreaterThanEqualAndLocalisationContainingIgnoreCase(
            @RequestParam(required = false , defaultValue = "0") int capacite,
            @RequestParam(required = false , defaultValue = "") String localisation
    ){ return salleRepository.findByCapaciteGreaterThanEqualAndLocalisationContainingIgnoreCase(capacite, localisation);}

    /*
    @GetMapping("/disponibles")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER') or hasRole('ROLE_RESPONSABLE')")
    public List<Salle> getSallesDisponibles(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam("heureDebut") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime heureDebut,
            @RequestParam("heureFin") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime heureFin
    ) { return salleRepository.findSallesDisponibles(date, heureDebut, heureFin); }
    */
}
