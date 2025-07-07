package pfa.gestionsalle.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalleEquipement {

    @EmbeddedId
    private SalleEquipementID id;

    @ManyToOne
    @MapsId("idSalle")
    @JoinColumn(name = "id_salle")
    private Salle salle;

    @ManyToOne
    @MapsId("idEquipement")
    @JoinColumn(name = "id_equipement")
    private Equipement equipment;


    private int quantite;
}
