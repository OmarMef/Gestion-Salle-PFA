package pfa.gestionsalle.web;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.*;
import pfa.gestionsalle.entities.Historique;
import pfa.gestionsalle.entities.Utilisateur;
import pfa.gestionsalle.service.HistoriqueService;
import pfa.gestionsalle.service.ReservationService;
import pfa.gestionsalle.service.SalleService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/hist")
public class HistoriqueController {

    private HistoriqueService historiqueService;


    @GetMapping("/all")
    public List<Historique> AllHistorique() {
        return historiqueService.getAllHistoriques();
    }

    @GetMapping("/reservation/id/{id}")
    public List<Historique> getHistoriqueByReservationId(@PathVariable Long id) {
        return historiqueService.getHistoriquesByReservationId(id);
    }

    @GetMapping("/user/id/{id}")
    public List<Historique> getHistoriqueByUtilisateurId(@PathVariable Long id) {
        return historiqueService.getHistoriquesByUtilisateurId(id);
    }

    @GetMapping("/action")
    public List<Historique> getHistoriqueByAction(@RequestParam String action) {
        return historiqueService.getHistoriquesByAction(action);
    }
}
