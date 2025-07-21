package pfa.gestionsalle.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pfa.gestionsalle.entities.Salle;
import pfa.gestionsalle.repository.SalleRepository;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class SalleServiceImpl implements SalleService {

    private SalleRepository salleRepository;

    //SAVE
    @Override
    public Salle createSalle(Salle salle) {
        Salle salleMMnom = salleRepository.findSalleByNom(salle.getNom());
        if (salleMMnom != null) throw new RuntimeException("Salle deja existente");
        Salle savedSalle = salleRepository.save(salle);
        return salleRepository.save(savedSalle);
    }

    //UPDATE
    @Override
    public Salle updateSalle(Long id, Salle salleModifiee) {
        if (salleModifiee == null) {
        throw new IllegalArgumentException("Les données de la salle sont nulles");
    }
        Salle salle = salleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Salle introuvable avec ID : " + id));

        salle.setNom(salleModifiee.getNom());
        salle.setCapacite(salleModifiee.getCapacite());
        salle.setLocalisation(salleModifiee.getLocalisation());

        return salleRepository.save(salle);
    }

    //DELETE
    @Override
    public void deleteSalle(Long id) {
        if (!salleRepository.existsById(id)) {
            throw new EntityNotFoundException("Salle introuvable avec ID : " + id);
        }
        salleRepository.deleteById(id);
    }

  //********************************************************************************

    @Override
    public Salle findById(long id) {
        return salleRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("Salle introuvable avec ID : " + id));
    }

    @Override
    public List<Salle> findAll(){
        return salleRepository.findAll();
    }

    public Salle findSalleByNom(String nom) {
        Salle salle = salleRepository.findSalleByNom(nom);
        if (salle == null) {
            throw new EntityNotFoundException("Salle introuvable avec le nom : " + nom);
        }
        return salle;
    }

    @Override
    public Salle findSalleByCapacite(int capacite) {
        Salle salle = salleRepository.findSalleByCapacite(capacite);
        if (salle == null){
            throw new EntityNotFoundException("Salle introuvable avec la capacite : " + capacite);
        }
        return salle;
    }

    @Override
    public List<Salle> findByNomContainingIgnoreCase(String KW) {
        List<Salle> salles = salleRepository.findByNomContainingIgnoreCase(KW);
        if (salles.isEmpty()) {
            throw new EntityNotFoundException("Aucune salle trouvée contenant : " + KW);
        }
        return salles;
    }

    @Override
    public List<Salle> findByCapaciteGreaterThanEqualAndLocalisationContainingIgnoreCase(int capacite, String localisation) {
        if (capacite < 0) {
            throw new IllegalArgumentException("La capacité doit être positive ou nulle");
        }
        if (localisation == null || localisation.trim().isEmpty()) {
            throw new IllegalArgumentException("La localisation ne peut pas être vide");
        }
        List<Salle> salles = salleRepository.findByCapaciteGreaterThanEqualAndLocalisationContainingIgnoreCase(capacite, localisation);
        if (salles.isEmpty()) {
            throw new EntityNotFoundException("Aucune salle trouvée avec capacité ≥ " + capacite + " et localisation contenant : " + localisation);
        }
        return salles;
    }

}
