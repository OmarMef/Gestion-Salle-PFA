package pfa.gestionsalle.service;

import org.springframework.security.access.prepost.PreAuthorize;
import pfa.gestionsalle.entities.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ReservationService {

    Reservation createReservation(LocalDate date_reservation , LocalTime H_debut , LocalTime H_fin,
                                  Evenement type_evenement, Salle salle , Utilisateur utilisateur);
    Reservation updateReservation(Long id, LocalDate newDate , LocalTime newH_debut, LocalTime newH_fin, Status newStatus , Evenement newtypeEvenement);
    void deleteReservation(Long id , Reservation reservation);

    //**************************************************************************
    Reservation updateReservationStatus(Long id, String newStatus);
    boolean isSalleAvailable(Long salleId, LocalDate date, LocalTime startTime, LocalTime endTime);
    List<Reservation> getAllReservations();
    List<Reservation> getReservationsBySalleAndDate(Long salleId, LocalDate date);
    List<Reservation> getReservationsByStatus(String status);





    //  HISTORIQUE  ->  List<Reservation> getReservationsByUser(Long utilisateurId);
}
