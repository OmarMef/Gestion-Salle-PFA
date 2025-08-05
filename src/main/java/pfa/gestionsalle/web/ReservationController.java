package pfa.gestionsalle.web;

import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pfa.gestionsalle.entities.*;
import pfa.gestionsalle.repository.ReservationRepository;
import pfa.gestionsalle.repository.SalleRepository;
import pfa.gestionsalle.service.ReservationService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/reserv")
public class ReservationController {

    private final ReservationRepository reservationRepository;
    private ReservationService reservationService;

    //*****************************METHODE ADMIN************************************

    //SAVE
    @PostMapping("/save")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER') or hasRole('RESPONSABLE')")
    public Reservation saveReservation(@RequestParam LocalDate date_reservation ,
                                       @RequestParam LocalTime H_debut ,
                                       @RequestParam LocalTime H_fin,
                                       @RequestParam Evenement type_evenement,
                                       @RequestParam Long salleId ,
                                       @RequestParam Long utilisateurId) {

        return reservationService.createReservation(date_reservation,H_debut,H_fin,type_evenement,salleId,utilisateurId);
    }

    //UPDATE
    @PutMapping("/edit/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER') or hasRole('RESPONSABLE')")
    public Reservation updateReservation(@PathVariable Long id,
                                         @RequestParam LocalDate newDate ,
                                         @RequestParam LocalTime newH_debut,
                                         @RequestParam LocalTime newH_fin,
                                         @RequestParam Status newStatus ,
                                         @RequestParam Evenement newtypeEvenement){

        return reservationService.updateReservation(id,newDate,newH_debut,newH_fin,newStatus,newtypeEvenement);
    }

    //DELETE
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER') or hasRole('RESPONSABLE')")
    public void deleteReservation(@RequestParam Long id){
         reservationService.deleteReservation(id);
    }

    //****************************************************************************************************

    @GetMapping("/{id}")
    public Reservation getReservationById(@PathVariable Long id) {
        return reservationService.ReservationById(id);
    }

    @GetMapping("/all")
    public List<Reservation> getAllReservations() {
        return reservationService.getAllReservations();
    }

    @GetMapping("/user/id/{id}")
    public List<Reservation> getReservationsByUserId(@PathVariable Long id) {
        return reservationService.getReservationsByUserId(id);
    }

    @GetMapping("/salle/{salleId}")
    public List<Reservation> getReservationsBySalleId(@PathVariable Long salleId) {
        return reservationService.getReservationsBySalleId(salleId);
    }

    @GetMapping("user/nom/{nom}")
    public List<Reservation> getReservationByUsername(@PathVariable String nom) {
        return reservationService.getReservationByUserName(nom);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/reservations/salle/{salleId}-{date}")
    public List<Reservation> getReservationsBySalleAndDate(
            @RequestParam Long salleId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        return reservationService.getReservationsBySalleAndDate(salleId, date);
    }

    @GetMapping("/salles-disponibles")
    public List<Salle> getSallesDisponibles(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime startTime,
            @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime endTime
    ) {
        return reservationService.getSallesDisponibles(date, startTime , endTime);
    }

}
