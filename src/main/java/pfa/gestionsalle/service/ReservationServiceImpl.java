package pfa.gestionsalle.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pfa.gestionsalle.entities.Reservation;
import pfa.gestionsalle.entities.Status;
import pfa.gestionsalle.repository.ReservationRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private ReservationRepository reservationRepository;

    @Override
    public Reservation createReservation(Reservation reservation) {

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
        return reservationRepository.save(reservation);
    }

    @Override
    public boolean isSalleAvailable(Long salleId, LocalDate date, LocalTime startTime, LocalTime endTime) {
        List<Reservation> conflicts = reservationRepository.checkSalleAvailability(salleId, date, startTime, endTime);
        return conflicts.isEmpty();
    }

    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public List<Reservation> getReservationsBySalleAndDate(Long salleId, LocalDate date) {
        return reservationRepository.findBySalleIdAndDate(salleId, date);
    }

    @Override
    public List<Reservation> getReservationsByStatus(String status) {
        return reservationRepository.findByStatus(Status.valueOf(status));
    }

    @Override
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

    @Override
    public void deleteReservation(Long id , Reservation reservation) {
        if (reservation.getStatus() == Status.VALIDEE) {
            throw new RuntimeException("Impossible de supprimer une réservation validée.");
        }
        if (!reservationRepository.existsById(id)) {
            throw new RuntimeException("Réservation introuvable");
        }
        reservationRepository.deleteById(id);
    }
}
