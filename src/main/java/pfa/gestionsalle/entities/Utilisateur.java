package pfa.gestionsalle.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_utilisateur;

    private String nom;
    private String prenom;

    @Column(unique = true)
    private String email;
    private String password;

    @ManyToOne
    @JoinColumn(name = "id_role")
    private Role role;

    public Utilisateur(String nom, String prenom, String email, String password, Role role) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    @OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL)
    private List<Reservation> reservations = new ArrayList<>();

    @OneToMany(mappedBy = "utilisateur" , cascade = CascadeType.ALL)
    private List<Historique> historiques = new ArrayList<>();

}