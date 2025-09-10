package pfa.gestionsalle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pfa.gestionsalle.entities.Historique;

import java.time.LocalDateTime;
import java.util.List;

public interface HistoriqueRepository extends JpaRepository<Historique, Long> {

    List<Historique> findByReservationId(Long id);
    List<Historique> findByUtilisateurId(Long id);
    List<Historique> findByActionIgnoreCase(String action);

    @Query("SELECT h.action AS action, COUNT(h) AS total FROM Historique h GROUP BY h.action")
    List<Object[]> countActions();

    @Query("SELECT h.utilisateur.nom AS nom, COUNT(h) AS total FROM Historique h GROUP BY h.utilisateur.nom")
    List<Object[]> countActionsByUser();

    @Query("SELECT h.reservation.salle.nom AS nomSalle, COUNT(h) AS total FROM Historique h GROUP BY h.reservation.salle.nom")
    List<Object[]> countReservationsBySalle();

}
