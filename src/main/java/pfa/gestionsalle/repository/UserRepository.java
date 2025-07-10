package pfa.gestionsalle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pfa.gestionsalle.entities.Reservation;
import pfa.gestionsalle.entities.Utilisateur;

import java.util.List;


public interface UserRepository extends JpaRepository<Utilisateur, Long> {
    List<Utilisateur> findByNom(String nom);
    List<Utilisateur> findByPrenom(String prenom);
    List<Utilisateur> findByNomContainsIgnoreCase(String KW);
    List<Utilisateur> findByPrenomContainsIgnoreCase(String prenom);
    //-------------------------------------------------------//
    Utilisateur findByEmail(String email);
    Utilisateur findById(long id);
    //-------------------------------------------------------//


}
