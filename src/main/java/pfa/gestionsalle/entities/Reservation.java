package pfa.gestionsalle.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_reservation;

    private LocalDate date_reservation;
    private LocalTime H_debut;
    private LocalTime H_fin;
    private String status;
    private String type_evenement;

    @ManyToOne
    @JoinColumn(name = "id_salle")
    private Salle salle;

    @ManyToOne
    @JoinColumn(name = "id_utilisateur")
    private Utilisateur utilisateur;

}
