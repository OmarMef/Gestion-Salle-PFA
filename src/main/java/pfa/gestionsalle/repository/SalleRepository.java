package pfa.gestionsalle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pfa.gestionsalle.entities.Salle;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface SalleRepository extends JpaRepository<Salle, Long> {
    Salle findByNom(String nom);
    Salle findSalleByCapacite(int capacite);
    List<Salle> findByNomContainingIgnoreCase(String KW);
    List<Salle> findByCapaciteGreaterThanEqualAndLocalisationContainingIgnoreCase(int capacite, String localisation);

    @Query("""
    SELECT s FROM Salle s WHERE s.capacite >= :capaciteMin
    AND NOT EXISTS (
        SELECT r FROM Reservation r
        WHERE r.salle = s
        AND r.date_reservation = :date
        AND r.H_debut < :heureFin
        AND r.H_fin > :heureDebut
    )
""")
    List<Salle> findAvailableSalles(@Param("date") LocalDate date,
                                    @Param("heureDebut") LocalTime heureDebut,
                                    @Param("heureFin") LocalTime heureFin,
                                    @Param("capaciteMin") int capaciteMin);

}
