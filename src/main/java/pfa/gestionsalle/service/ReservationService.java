package pfa.gestionsalle.service;

import org.springframework.security.access.prepost.PreAuthorize;
import pfa.gestionsalle.entities.Reservation;
import pfa.gestionsalle.entities.Status;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ReservationService {

    Reservation createReservation(Reservation reservation);
    boolean isSalleAvailable(Long salleId, LocalDate date, LocalTime startTime, LocalTime endTime);
    List<Reservation> getAllReservations();
    List<Reservation> getReservationsBySalleAndDate(Long salleId, LocalDate date);
    List<Reservation> getReservationsByStatus(String status);
    Reservation updateReservationStatus(Long id, String newStatus);
    void deleteReservation(Long id , Reservation reservation);



    //  HISTORIQUE  ->  List<Reservation> getReservationsByUser(Long utilisateurId);
}
