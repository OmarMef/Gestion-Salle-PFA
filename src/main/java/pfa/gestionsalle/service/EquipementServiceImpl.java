package pfa.gestionsalle.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pfa.gestionsalle.entities.Equipement;
import pfa.gestionsalle.entities.Salle;
import pfa.gestionsalle.repository.EquipementReposiroty;
import pfa.gestionsalle.repository.SalleEquipementRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@AllArgsConstructor
public class EquipementServiceImpl implements EquipementService {

    private EquipementReposiroty equipementRepository;
    private SalleEquipementRepository salleEquipementRepository;

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

    @Override
    public List<Map<String, Object>> getDisponibiliteEquipements() {
        List<Equipement> equipements = equipementRepository.findAll();
        List<Map<String, Object>> result = new ArrayList<>();

        for (Equipement e : equipements) {
            Map<String, Object> equipementData = new HashMap<>();
            equipementData.put("nom", e.getNom());

            boolean estUtilise = equipementRepository.isEquipementUsed(e.getId());
            equipementData.put("estUtilise", estUtilise);

            int quantiteRestante = e.getQuantite() - salleEquipementRepository.totalQuantiteAffectee(e.getId());
            equipementData.put("quantiteRestante", quantiteRestante);

            // Récupérer les salles où l'équipement est affecté
            List<Map<String, Object>> salles = new ArrayList<>();
            e.getSalleEquipements().forEach(se -> {
                Map<String, Object> salleData = new HashMap<>();
                salleData.put("nomSalle", se.getSalle().getNom());
                salleData.put("quantite", se.getQuantite());
                salles.add(salleData);
            });
            equipementData.put("salles", salles);

            result.add(equipementData);
        }

        return result;
    }
}
