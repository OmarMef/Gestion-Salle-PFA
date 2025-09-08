package pfa.gestionsalle.web;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pfa.gestionsalle.entities.Equipement;
import pfa.gestionsalle.service.EquipementService;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/equip")
public class EquipementController {

    private EquipementService equipementService;


    //SAVE
    @PostMapping("/save")
    @PreAuthorize("hasRole('ADMIN')")
    public Equipement createEquipement(@RequestParam String nom,
                                       @RequestParam String description,
                                       @RequestParam int quantite) {
        return equipementService.saveEquipement(nom, description, quantite);
    }


    //UPDATE
    @PutMapping("/edit/id/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Object updateEquipement(@PathVariable Long id,
                                   @RequestParam String nom,
                                   @RequestParam String description,
                                   @RequestParam int quantite) {
        try {
            return equipementService.updateEquipement(id, nom, description, quantite);
        } catch (RuntimeException e) {
            return "Erreur : " + e.getMessage();
        }
    }


    //DELETE
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN_ROLE')")
    public void deleteEquipement(@PathVariable Long id) {
        equipementService.deleteEquipement(id);
    }

    //****************************************************************************************************

    @GetMapping("/all")
    public List<Equipement> getAllEquipements() {
        return equipementService.getAllEquipements();
    }

    @GetMapping("/id/{id}")
    public Equipement getEquipementById(@PathVariable Long id) {
        return equipementService.getEquipementById(id);
    }

    @GetMapping("/search/nom")
    public Equipement getEquipementByNom(@RequestParam String nom) {
        return equipementService.getEquipementByNom(nom);
    }

    @GetMapping("/search/kw")
    public List<Equipement> findByNomContaining(@RequestParam String kw) {
        return equipementService.findByNomContains(kw);
    }

    @GetMapping("/stock")
    public List<Equipement> getEquipementsAvecStock(@RequestParam int seuil) {
        return equipementService.findByQuantiteGreaterThan(seuil);
    }

    @GetMapping("/disponibles")
    public List<Equipement> getEquipementsDisponibles() {
        return equipementService.getEquipementsDisponibles();
    }

    @GetMapping("/disponible")
    public List<Map<String, Object>> getDisponibiliteEquipements() {
        return equipementService.getDisponibiliteEquipements();
    }


}
