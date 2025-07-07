package pfa.gestionsalle.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;


@Entity
@Data
public class Role {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_role;
    private String nom_role;
    private String description;
}
