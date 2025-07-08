package pfa.gestionsalle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pfa.gestionsalle.entities.Equipement;

public interface EquipementReposiroty extends JpaRepository<Equipement, Long> {
}
