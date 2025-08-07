package pfa.gestionsalle.web;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pfa.gestionsalle.entities.Salle;
import pfa.gestionsalle.entities.SalleEquipement;
import pfa.gestionsalle.service.SalleEquipementService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/Salle-Equipement")
@PreAuthorize("hasRole('ADMIN')")
public class SalleEquipementController {

    private SalleEquipementService salleEquipementService;


    @PostMapping("/add")
    public SalleEquipement addEquipementToSalle(
                            @RequestParam Long salleId,
                            @RequestParam Long equipementId,
                            @RequestParam int quantite) {
        return salleEquipementService.addEquipementToSalle(salleId, equipementId, quantite);
    }

    @PutMapping("/update/id/{salleEquipementId}")
    public SalleEquipement updateSalleEquipement(
                        @PathVariable Long salleEquipementId,
                        @RequestParam Long nouvelleSalleId,
                        @RequestParam int newQuantite) {
        return salleEquipementService.updateSalleEquipement(salleEquipementId, nouvelleSalleId, newQuantite);
    }

    @DeleteMapping("/delete")
    public void deleteEquipementFromSalle(
            @RequestParam Long salleId,
            @RequestParam Long equipementId) {
        salleEquipementService.deleteEquipementFromSalle(salleId, equipementId);
    }

    //***************************************************************************************************

    @GetMapping("/salle/{salleId}")
    public List<SalleEquipement> getEquipementsBySalle(@PathVariable Long salleId) {
        return salleEquipementService.getEquipementsBySalle(salleId);
    }

    @GetMapping("/equipement/{equipementId}")
    public List<SalleEquipement> getSallesByEquipement(@PathVariable Long equipementId) {
        return salleEquipementService.getSallesByEquipement(equipementId);
    }

    @GetMapping("/equipements/{id}/usage")
    public Map<String, Object> getEquipementUsage(@PathVariable Long id) {
        boolean used = salleEquipementService.isEquipementUsed(id);
        Map<String, Object> response = new HashMap<>();
        response.put("used", used);
        if (used) {
            List<Salle> salles = salleEquipementService.getSallesUsingEquipement(id);
            response.put("salles", salles);
        }
        return response;
    }
}
