package pfa.gestionsalle.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
public class Reservation {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_reservation;

    private LocalDate date_reservation;
    private LocalTime H_debut;
    private LocalTime H_fin;

    @Enumerated(EnumType.STRING)
    private Status status;
    @Enumerated(EnumType.STRING)
    private Evenement type_evenement;

    @ManyToOne
    @JoinColumn(name = "id_salle")
    @JsonIncludeProperties("nom")
    private Salle salle;

    @ManyToOne
    @JoinColumn(name = "id_utilisateur")
    @JsonIncludeProperties({"nom","prenom"})
    private Utilisateur utilisateur;

    //@Version
    //private Long version;


    public Reservation(LocalDate date_reservation , LocalTime H_debut ,
                       LocalTime H_fin ,Status status, Evenement type_evenement,
                       Salle salle , Utilisateur utilisateur) {
        this.date_reservation = date_reservation;
        this.H_debut = H_debut;
        this.H_fin = H_fin;
        this.status = status;
        this.type_evenement = type_evenement;
        this.salle = salle;
        this.utilisateur = utilisateur;
    }

}
