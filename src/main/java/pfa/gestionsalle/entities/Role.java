package pfa.gestionsalle.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_role;
    private String nom_role;
    private String description;

    public Role(String nom_role) {
        this.nom_role = nom_role;
    }

    @OneToMany(mappedBy = "role")
    private List<Utilisateur> utilisateurs = new ArrayList<>();
}
