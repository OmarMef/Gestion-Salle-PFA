package pfa.gestionsalle.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pfa.gestionsalle.entities.Equipement;
import pfa.gestionsalle.entities.Salle;
import pfa.gestionsalle.entities.SalleEquipement;
import pfa.gestionsalle.entities.SalleEquipementID;
import pfa.gestionsalle.repository.EquipementReposiroty;
import pfa.gestionsalle.repository.SalleEquipementRepository;
import pfa.gestionsalle.repository.SalleRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class SalleEquipementServiceImpl implements SalleEquipementService {

    private final SalleEquipementRepository salleEquipementRepository;
    private final SalleRepository salleRepository;
    private final EquipementReposiroty equipementReposiroty;


    //SAVE
    @Override
    public SalleEquipement addEquipementToSalle(Long salleId, Long equipementId, int quantite) {

        if (quantite <= 0) {
            throw new IllegalArgumentException("La quantité doit être supérieure à zéro");
        }
        Salle salle = salleRepository.findById(salleId)
                .orElseThrow(() -> new RuntimeException("Salle introuvable"));
        Equipement equipement = equipementReposiroty.findById(equipementId)
                .orElseThrow(() -> new RuntimeException("Equipement introuvable"));

        // Vérifier si equipement déjà affecté
        Optional<SalleEquipement> existing = salleEquipementRepository.findBySalleIdAndEquipementId(salleId, equipementId);
        if (existing.isPresent()) {
            throw new RuntimeException("Cet équipement est déjà affecté à cette salle");
        }
        // Vérifier stock disponible globalement
        int quantiteAffectee = salleEquipementRepository.totalQuantiteAffectee(equipementId);

        if (quantite + quantiteAffectee > equipement.getQuantite()) {
            throw new RuntimeException("Quantité demandée dépasse la quantité disponible en stock");
        }

        SalleEquipement salleEquipement = new SalleEquipement();
        SalleEquipementID id = new SalleEquipementID(salle.getId(), equipement.getId());
        salleEquipement.setId(id);
        salleEquipement.setSalle(salle);
        salleEquipement.setEquipement(equipement);
        salleEquipement.setQuantite(quantite);

        return salleEquipementRepository.save(salleEquipement);
    }

    //UPDATE
    @Override
    public SalleEquipement updateSalleEquipement(Long salleEquipementId, Long nouvelleSalleId, int newQuantite) {
        if (newQuantite <= 0) {
            throw new IllegalArgumentException("La quantité doit être supérieure à zéro");
        }
        if (nouvelleSalleId == null) {
            throw new IllegalArgumentException("Nouvelle salle ID ne peut pas être null");
        }

        SalleEquipement salleEquipement = salleEquipementRepository.findById(salleEquipementId)
                .orElseThrow(() -> new RuntimeException("Attribution introuvable"));

        Salle nouvelleSalle = salleRepository.findById(nouvelleSalleId)
                .orElseThrow(() -> new RuntimeException("Salle introuvable"));

        // Si la salle change, vérifier que cet équipement n'est pas déjà affecté à la nouvelle salle
        Long currentSalleId = salleEquipement.getSalle() != null ? salleEquipement.getSalle().getId() : null;

        if (!nouvelleSalleId.equals(currentSalleId)) {
            Optional<SalleEquipement> existing = salleEquipementRepository
                    .findBySalleIdAndEquipementId(nouvelleSalleId, salleEquipement.getEquipement().getId());

            if (existing.isPresent()) {
                throw new RuntimeException("Cet équipement est déjà affecté à la salle cible");
            }
            salleEquipement.setSalle(nouvelleSalle);
        }

        salleEquipement.setQuantite(newQuantite);
        return salleEquipementRepository.save(salleEquipement);
    }

    //DELETE
    @Override
    public void deleteEquipementFromSalle(Long salleId, Long equipementId) {
        SalleEquipement salleEquipement = salleEquipementRepository
                .findBySalleIdAndEquipementId(salleId, equipementId)
                .orElseThrow(() -> new RuntimeException("L'équipement n'est pas affecté à cette salle"));

        salleEquipementRepository.delete(salleEquipement);
    }

    //*****************************************************************************************************

    @Override
    public boolean isEquipementUsed(Long equipementId) {
        Equipement equipement = equipementReposiroty.findById(equipementId)
                .orElseThrow(() -> new RuntimeException("Équipement avec l'ID " + equipementId + " introuvable"));
        return salleEquipementRepository.isEquipementUsed(equipementId);
    }

    @Override
    public List<SalleEquipement> getEquipementsBySalle(Long salleId) {
        Salle salle = salleRepository.findById(salleId)
                .orElseThrow(() -> new RuntimeException("Salle avec l'ID " + salleId + " introuvable"));
        return salleEquipementRepository.findBySalleId(salleId);
    }

    @Override
    public List<SalleEquipement> getSallesByEquipement(Long equipementId) {

        Equipement equipement = equipementReposiroty.findById(equipementId)
                .orElseThrow(() -> new RuntimeException("Équipement avec l'ID " + equipementId + " introuvable"));
        return salleEquipementRepository.findByEquipementId(equipementId);
    }

    @Override
    public List<Salle> getSallesUsingEquipement(Long equipementId) {
        return salleEquipementRepository.findSallesByEquipementId(equipementId);
    }

}
