package pfa.gestionsalle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pfa.gestionsalle.entities.Reservation;
import pfa.gestionsalle.entities.Salle;
import pfa.gestionsalle.entities.Status;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findBySalleId(Long salleId);
    List<Reservation> findBySalleNom(String SalleNom);
    List<Reservation> findByUtilisateurId(Long id);
    List<Reservation> findByUtilisateurNom(String nom);
    List<Reservation> findByStatus(Status status);


//**************************************************************************************************

    @Query("SELECT r FROM Reservation r " +
            "WHERE r.salle.id = :salleId " +
            "AND r.date_reservation = :date " +
            "AND r.id <> :currentId " +
            "AND (r.H_debut < :newH_fin AND r.H_fin > :newH_debut)")
    List<Reservation> findConflits(@Param("salleId") Long salleId,
                                   @Param("date") LocalDate date,
                                   @Param("newH_debut") LocalTime newH_debut,
                                   @Param("newH_fin") LocalTime newH_fin,
                                   @Param("currentId") Long currentId);

    @Query("SELECT r FROM Reservation r WHERE r.H_debut = :hDebut AND r.H_fin = :hFin")
    List<Reservation> findByHeureDebutAndHeureFin(@Param("hDebut") LocalTime hDebut, @Param("hFin") LocalTime hFin);


    @Query("SELECT r FROM Reservation r WHERE r.salle.id = :salleId AND r.date_reservation = :date")
    List<Reservation> findBySalleIdAndDate(@Param("salleId") Long salleId, @Param("date") LocalDate date);


    @Query("SELECT r FROM Reservation r WHERE r.salle.id = :salleId AND r.date_reservation = :date " +
            "AND ((:startTime BETWEEN r.H_debut AND r.H_fin) OR (:endTime BETWEEN r.H_debut AND r.H_fin) " +
            "OR (r.H_debut BETWEEN :startTime AND :endTime))")
    List<Reservation> checkSalleAvailability(@Param("salleId") Long salleId,
                                             @Param("date") LocalDate date,
                                             @Param("startTime") LocalTime startTime,
                                             @Param("endTime") LocalTime endTime);
}
