package pfa.gestionsalle.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pfa.gestionsalle.entities.Equipement;
import pfa.gestionsalle.repository.EquipementReposiroty;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class EquipementServiceImpl implements EquipementService {

    private EquipementReposiroty equipementRepository;

    //SAVE
    @Override
    public Equipement saveEquipement(String nom, String description, int quantite) {

        if (nom == null || nom.trim().isEmpty()) {
            throw new IllegalArgumentException("Le nom de l'équipement est obligatoire.");
        }
        // Vérification d'unicité du nom
        if (equipementRepository.existsByNom(nom)) {
            throw new IllegalStateException("Un équipement avec ce nom existe déjà.");
        }

        Equipement equipement = new Equipement();
        equipement.setNom(nom.trim());
        equipement.setDescription(description);
        equipement.setQuantite(quantite);

        return equipementRepository.save(equipement);
    }


    //UPDATE
    @Override
    public Equipement updateEquipement(Long id, String nom, String description, int quantite) {
        Equipement equipement = equipementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Equipement non trouvé"));
        if (nom == null || nom.trim().isEmpty()) {
            throw new IllegalArgumentException("Le nom de l'équipement est obligatoire.");
        }
        if (!equipement.getNom().equals(nom.trim()) && equipementRepository.existsByNom(nom.trim())) {
            throw new IllegalStateException("Un équipement avec ce nom existe déjà.");
        }

        equipement.setNom(nom.trim());
        equipement.setDescription(description);
        equipement.setQuantite(quantite);

        return equipementRepository.save(equipement);
    }


    //DELETE
    @Override
    public void deleteEquipement(Long id) {
        Equipement equipement = equipementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Equipement non trouvé"));

        boolean isUsed = equipementRepository.isEquipementUsed(id);
        if (isUsed) {
            throw new IllegalStateException("Impossible de supprimer cet équipement car il est utilisé dans une salle.");
        }
        equipementRepository.deleteById(id);
    }

//**********************************************************************************************************


    @Override
    public List<Equipement> getAllEquipements() {
        return equipementRepository.findAll();
    }

    @Override
    public Equipement getEquipementById(Long id) {
        return equipementRepository.findById(id)
                  .orElseThrow(() -> new RuntimeException("Equipement introuvable"));
    }

    @Override
    public Equipement getEquipementByNom(String nom) {
        return equipementRepository.findByNom(nom);
    }

    @Override
    public List<Equipement> findByNomContains(String KW) {
        return equipementRepository.findByNomContains(KW);
    }

    @Override
    public List<Equipement> findByQuantiteGreaterThan(int seuil) {
        return equipementRepository.findByQuantiteGreaterThan(seuil);
    }

    @Override
    public List<Equipement> getEquipementsDisponibles() {
        return equipementRepository.findEquipementsNonUtilises();
    }
}
