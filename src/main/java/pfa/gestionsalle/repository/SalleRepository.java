package pfa.gestionsalle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import pfa.gestionsalle.entities.Salle;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface SalleRepository extends JpaRepository<Salle, Long> {
    Salle findSalleByNom(String nom);
    Salle findSalleByCapacite(int capacite);
    List<Salle> findByNomContainingIgnoreCase(String KW);
    List<Salle> findByCapaciteGreaterThanEqualAndLocalisationContainingIgnoreCase(int capacite, String localisation);
}
