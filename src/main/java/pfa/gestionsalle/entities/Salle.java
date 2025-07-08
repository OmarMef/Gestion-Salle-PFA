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
    private long id_salle;

    private String nom_salle;
    private int capacite;
    private String localisation;

    @OneToMany(mappedBy = "salle" , cascade = CascadeType.ALL)
    private List<Reservation> reservations = new ArrayList<>();
}
