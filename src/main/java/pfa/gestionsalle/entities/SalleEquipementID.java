package pfa.gestionsalle.entities;


import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalleEquipementID implements Serializable {
    private long idSalle;
    private long idEquipement;
}
