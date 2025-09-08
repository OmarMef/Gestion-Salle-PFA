package pfa.gestionsalle.service;

import pfa.gestionsalle.entities.Equipement;

import java.util.List;
import java.util.Map;

public interface EquipementService {

    Equipement saveEquipement(String nom, String description, int quantite );
    Equipement updateEquipement(Long id, String nom, String description, int quantite);
    void deleteEquipement(Long id);

    //****************************************************************************

    List<Equipement> getAllEquipements();
    Equipement getEquipementById(Long id);
    Equipement getEquipementByNom(String nom);
    List<Equipement> findByNomContains(String KW);
    List<Equipement> findByQuantiteGreaterThan(int seuil);
    List<Equipement> getEquipementsDisponibles();
    List<Map<String, Object>> getDisponibiliteEquipements();



}
