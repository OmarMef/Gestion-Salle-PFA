package pfa.gestionsalle.service;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pfa.gestionsalle.entities.*;
import pfa.gestionsalle.repository.HistoriqueRepository;
import pfa.gestionsalle.repository.ReservationRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private ReservationRepository reservationRepository;
    private HistoriqueRepository historiqueRepository;

    //SAVE
    @Override
    public Reservation createReservation(LocalDate date_reservation , LocalTime H_debut , LocalTime H_fin,
                                         Evenement type_evenement, Salle salle , Utilisateur utilisateur) {

        Reservation reservation = new Reservation();

        reservation.setDate_reservation(date_reservation);
        reservation.setH_debut(H_debut);
        reservation.setH_fin(H_fin);
        reservation.setType_evenement(type_evenement);
        reservation.setSalle(salle);
        reservation.setUtilisateur(utilisateur);

        if (reservation.getH_debut().isAfter(reservation.getH_fin())) {
            throw new RuntimeException("L'heure de début doit être avant l'heure de fin");
        }
        if (reservation.getDate_reservation() == null || reservation.getH_debut() == null || reservation.getH_fin() == null) {
            throw new RuntimeException("Date et heures de réservation sont obligatoires.");
        }
        if (reservation.getSalle() == null || reservation.getUtilisateur() == null) {
            throw new RuntimeException("Salle et utilisateur sont obligatoires.");
        }
        boolean available = isSalleAvailable(
                reservation.getSalle().getId(),
                reservation.getDate_reservation(),
                reservation.getH_debut(),
                reservation.getH_fin()
        );
        if (!available) {
            throw new RuntimeException("Salle indisponible à cette date/heure.");
        }

        reservation.setStatus(Status.EN_ATTENTE);

        Historique historique = new Historique();
        historique.setDate_action(LocalDate.now());
        historique.setAction("CREATION");
        historique.setH_action(LocalTime.now());
        historique.setReservation(reservation);
        historique.setUtilisateur(reservation.getUtilisateur());

        historiqueRepository.save(historique);

        return reservationRepository.save(reservation);
    }


    //UPDATE
    @Override
    public Reservation updateReservation(Long id, LocalDate newDate , LocalTime newH_debut, LocalTime newH_fin, Status newStatus , Evenement newtypeEvenement) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Réservation introuvable avec l'ID : " + id));

        if (newH_debut.isAfter(newH_fin) || newH_debut.equals(newH_fin)) {
            throw new IllegalArgumentException("L'heure de début doit être avant l'heure de fin.");
        }
        if (newDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("La date de réservation ne peut pas être dans le passé.");
        }
        List<Reservation> conflits = reservationRepository.findConflits(
                reservation.getSalle().getId(), newDate, newH_debut, newH_fin, id);

        if (!conflits.isEmpty()) {
            throw new IllegalStateException("Conflit avec une autre réservation existante.");
        }
        Status statutEnum;
        try {
            statutEnum = Status.valueOf(newStatus.toString().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Le statut '" + newStatus +
                    "' est invalide. Valeurs possibles : " + Arrays.toString(Status.values()));
        }
        Evenement evenementEnum;
        try {
            evenementEnum = Evenement.valueOf(newtypeEvenement.toString().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Le type d'événement '" + newtypeEvenement +
                    "' est invalide. Valeurs possibles : " + Arrays.toString(Evenement.values()));
        }
        reservation.setDate_reservation(newDate);
        reservation.setH_debut(newH_debut);
        reservation.setH_fin(newH_fin);
        reservation.setStatus(newStatus);
        reservation.setType_evenement(newtypeEvenement);

        Historique historique = new Historique();
        historique.setDate_action(LocalDate.now());
        historique.setAction("MODIFICATION");
        historique.setH_action(LocalTime.now());
        historique.setReservation(reservation);
        historique.setUtilisateur(reservation.getUtilisateur());

        historiqueRepository.save(historique);

        return reservationRepository.save(reservation);
    }


    //DELETE
    @Override
    public void deleteReservation(Long id , Reservation reservation) {
        if (!reservationRepository.existsById(id)) {
            throw new RuntimeException("Réservation introuvable");
        }
        if (reservation.getStatus() == Status.VALIDEE) {
            throw new RuntimeException("Impossible de supprimer une réservation validée.");
        }

        reservation.setStatus(Status.ANNULEE);
        reservationRepository.save(reservation);

        Historique historique = new Historique();
        historique.setDate_action(LocalDate.now());
        historique.setAction("ANNULATION");
        historique.setH_action(LocalTime.now());
        historique.setReservation(reservation);
        historique.setUtilisateur(reservation.getUtilisateur());

        historiqueRepository.save(historique);

    }

    //*************************************************************************************

    @Override
    public boolean isSalleAvailable(Long salleId, LocalDate date, LocalTime startTime, LocalTime endTime) {
        List<Reservation> conflicts = reservationRepository.checkSalleAvailability(salleId, date, startTime, endTime);
        return conflicts.isEmpty();
    }

    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public List<Reservation> getReservationsBySalleAndDate(Long salleId, LocalDate date) {
        return reservationRepository.findBySalleIdAndDate(salleId, date);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public List<Reservation> getReservationsByStatus(String status) {
        return reservationRepository.findByStatus(Status.valueOf(status));
    }


    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public Reservation updateReservationStatus(Long id, String newStatus) {
        Status statutEnum;
        try {
            statutEnum = Status.valueOf(newStatus.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Le statut '" + newStatus +
                    "est invalide. Valeurs possibles : EN_ATTENTE, VALIDEE, REFUSEE, ANNULEE");
        }
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Réservation non trouvée"));

        reservation.setStatus(statutEnum);
        return reservationRepository.save(reservation);
    }

}
