package pfa.gestionsalle.service;

import pfa.gestionsalle.entities.Historique;

import java.time.LocalDate;
import java.util.List;

public interface HistoriqueService {
    List<Historique> getAllHistoriques();
    List<Historique> getHistoriquesByReservationId(Long id);
    List<Historique> getHistoriquesByUtilisateurId(Long id);
    List<Historique> getHistoriquesByAction(String action);
}
