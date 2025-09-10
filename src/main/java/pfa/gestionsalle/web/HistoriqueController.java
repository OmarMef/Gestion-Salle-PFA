package pfa.gestionsalle.web;

import lombok.AllArgsConstructor;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pfa.gestionsalle.entities.Historique;
import pfa.gestionsalle.entities.Utilisateur;
import pfa.gestionsalle.service.HistoriqueService;
import pfa.gestionsalle.service.ReservationService;
import pfa.gestionsalle.service.SalleService;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/hist")
public class HistoriqueController {

    private HistoriqueService historiqueService;


    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
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

    @GetMapping("/analytics/actions")
    @PreAuthorize("hasRole('ADMIN')")
    public Map<String, Long> getActionAnalytics() {
        return historiqueService.getActionCounts();
    }

    @GetMapping("/analytics/users")
    @PreAuthorize("hasRole('ADMIN')")
    public Map<String, Long> getUserAnalytics() {
        return historiqueService.getTopUsers();
    }

    @GetMapping("/analytics/salles")
    @PreAuthorize("hasRole('ADMIN')")
    public Map<String, Long> getSalleAnalytics() {
        return historiqueService.getTopSalles();
    }
}
