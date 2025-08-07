package pfa.gestionsalle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pfa.gestionsalle.entities.Equipement;

import java.util.List;

public interface EquipementReposiroty extends JpaRepository<Equipement, Long> {

    @Query("SELECT COUNT(se) > 0 FROM SalleEquipement se WHERE se.equipement.id = :equipementId")
    boolean isEquipementUsed(@Param("equipementId") Long equipementId);

    @Query("SELECT e FROM Equipement e WHERE NOT EXISTS (" +
            "SELECT se FROM SalleEquipement se WHERE se.equipement.id = e.id)")
    List<Equipement> findEquipementsNonUtilises();

    boolean existsByNom(String nom);

    Equipement findByNom(String nom);
    List<Equipement> findByNomContains(String KW);
    List<Equipement> findByQuantiteGreaterThan(int seuil);
}
