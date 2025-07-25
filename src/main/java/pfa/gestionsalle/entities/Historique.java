package pfa.gestionsalle.entities;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
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
public class Historique {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;

    private String action;
    private LocalDate date_action;
    private LocalTime H_action;

    @ManyToOne
    @JoinColumn(name = "id_utilisateur")
    @JsonIncludeProperties({"nom","prenom"})
    private Utilisateur utilisateur;

    @ManyToOne
    @JoinColumn(name = "id_reservation")
    @JsonIncludeProperties({"id","status","H_debut","H_fin"})
    private Reservation reservation;


}
