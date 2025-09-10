package pfa.gestionsalle.service;

import pfa.gestionsalle.entities.Historique;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface HistoriqueService {
    List<Historique> getAllHistoriques();
    List<Historique> getHistoriquesByReservationId(Long id);
    List<Historique> getHistoriquesByUtilisateurId(Long id);
    List<Historique> getHistoriquesByAction(String action);
    Map<String, Long> getActionCounts();
    Map<String, Long> getTopUsers();
    Map<String, Long> getTopSalles();
}
