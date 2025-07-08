package pfa.gestionsalle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pfa.gestionsalle.entities.Historique;

public interface HistoriqueRepository extends JpaRepository<Historique, Long> {
}
