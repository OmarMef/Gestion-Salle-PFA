package pfa.gestionsalle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pfa.gestionsalle.entities.Historique;

import java.time.LocalDateTime;
import java.util.List;

public interface HistoriqueRepository extends JpaRepository<Historique, Long> {

    List<Historique> findByReservationId(Long id);
    List<Historique> findByUtilisateurId(Long id);
    List<Historique> findByActionIgnoreCase(String action);

}
