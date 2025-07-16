package pfa.gestionsalle.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Role {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_role;
    @Column(name = "nom_role")
    private String nomRole;
    private String description;

    public Role(String nomRole) {
        this.nomRole = nomRole;
    }

    @OneToMany(mappedBy = "role")
    @JsonIgnore
    private List<Utilisateur> utilisateurs = new ArrayList<>();
}
