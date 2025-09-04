package pfa.gestionsalle.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import pfa.gestionsalle.entities.Evenement;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
public class ReservationDto {

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date_reservation;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime H_debut;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime H_fin;

    private Evenement type_evenement;
    private Long salleId;
    private Long utilisateurId;

    // Getters et setters
    public LocalDate getDate_reservation() { return date_reservation; }
    public void setDate_reservation(LocalDate date_reservation) { this.date_reservation = date_reservation; }
    public LocalTime getH_debut() { return H_debut; }
    public void setH_debut(LocalTime H_debut) { this.H_debut = H_debut; }
    public LocalTime getH_fin() { return H_fin; }
    public void setH_fin(LocalTime H_fin) { this.H_fin = H_fin; }
    public Evenement getType_evenement() { return type_evenement; }
    public void setType_evenement(Evenement type_evenement) { this.type_evenement = type_evenement; }
    public Long getSalleId() { return salleId; }
    public void setSalleId(Long salleId) { this.salleId = salleId; }
    public Long getUtilisateurId() { return utilisateurId; }
    public void setUtilisateurId(Long utilisateurId) { this.utilisateurId = utilisateurId; }
}

