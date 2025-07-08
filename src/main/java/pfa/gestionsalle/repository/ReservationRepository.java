package pfa.gestionsalle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pfa.gestionsalle.entities.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
