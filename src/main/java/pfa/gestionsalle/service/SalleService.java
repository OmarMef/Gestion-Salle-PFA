package pfa.gestionsalle.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import pfa.gestionsalle.entities.Salle;

import java.util.List;

@Service
@Transactional
public interface SalleService {

    Salle createSalle(String nomSalle ,int capacite ,String localisation);
    Salle updateSalle(Long id, Salle salleModifiee);
    void deleteSalle(Long id);
//*************************************************************************
    Salle findById(long id);
    List<Salle> findAll();
    Salle findSalleByNom(String nom);
    Salle findSalleByCapacite(int capacite);
    List<Salle> findByNomContainingIgnoreCase(String KW);
    List<Salle> findByCapaciteGreaterThanEqualAndLocalisationContainingIgnoreCase(int capacite, String localisation);



}
