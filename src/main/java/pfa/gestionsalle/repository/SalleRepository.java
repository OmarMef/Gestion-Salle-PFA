package pfa.gestionsalle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pfa.gestionsalle.entities.Salle;

public interface SalleRepository extends JpaRepository<Salle, Long> {

}
