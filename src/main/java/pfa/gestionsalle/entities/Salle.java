package pfa.gestionsalle.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Salle {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nom;
    private int capacite;
    private String localisation;

    public Salle(String nom, int capacite, String localisation) {
        this.nom = nom;
        this.capacite = capacite;
        this.localisation = localisation;
    }

    @OneToMany(mappedBy = "salle" , cascade = CascadeType.ALL)
    private List<Reservation> reservations = new ArrayList<>();
}
