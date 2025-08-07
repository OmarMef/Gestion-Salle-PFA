package pfa.gestionsalle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pfa.gestionsalle.entities.Equipement;
import pfa.gestionsalle.entities.Salle;
import pfa.gestionsalle.entities.SalleEquipement;

import java.util.List;
import java.util.Optional;

public interface SalleEquipementRepository extends JpaRepository<SalleEquipement, Long> {

    @Query("SELECT COUNT(se) > 0 FROM SalleEquipement se WHERE se.equipement.id = :equipementId")
    boolean isEquipementUsed(@Param("equipementId") Long equipementId);


    @Query("SELECT se.salle FROM SalleEquipement se WHERE se.equipement.id = :equipementId")
    List<Salle> findSallesByEquipementId(@Param("equipementId") Long equipementId);


    @Query("SELECT COALESCE(SUM(se.quantite), 0) FROM SalleEquipement se WHERE se.equipement.id = :equipementId")
    int totalQuantiteAffectee(@Param("equipementId") Long equipementId);



    // Obtenir tous les équipements d’une salle
    List<SalleEquipement> findBySalleId(Long salleId);

    // Obtenir la liste des salles qui utilisent un équipement donné
    List<SalleEquipement> findByEquipementId(Long equipementId);

    // Optionnel : vérifier si un équipement est déjà affecté à une salle spécifique
    Optional<SalleEquipement> findBySalleIdAndEquipementId(Long salleId, Long equipementId);
}
