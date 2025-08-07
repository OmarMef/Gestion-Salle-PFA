package pfa.gestionsalle.service;

import pfa.gestionsalle.entities.Salle;
import pfa.gestionsalle.entities.SalleEquipement;

import java.util.List;

public interface SalleEquipementService {

    SalleEquipement addEquipementToSalle(Long salleId, Long equipementId, int quantite);
    SalleEquipement updateSalleEquipement(Long salleEquipementId,Long nouvelleSalleId, int newQuantite);
    void deleteEquipementFromSalle(Long salleId, Long equipementId);


    boolean isEquipementUsed(Long equipementId);
    List<SalleEquipement> getEquipementsBySalle(Long salleId);
    List<SalleEquipement> getSallesByEquipement(Long equipementId);
    List<Salle> getSallesUsingEquipement(Long equipementId);


}

