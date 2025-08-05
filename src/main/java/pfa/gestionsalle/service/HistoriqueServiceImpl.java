package pfa.gestionsalle.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pfa.gestionsalle.entities.Historique;
import pfa.gestionsalle.repository.HistoriqueRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class HistoriqueServiceImpl implements HistoriqueService {

    private HistoriqueRepository historiqueRepository;

    @Override
    public List<Historique> getAllHistoriques() {
        return historiqueRepository.findAll();
    }

    @Override
    public List<Historique> getHistoriquesByReservationId(Long id) {
        return historiqueRepository.findByReservationId(id);
    }

    @Override
    public List<Historique> getHistoriquesByUtilisateurId(Long id) {
        return historiqueRepository.findByUtilisateurId(id);
    }

    @Override
    public List<Historique> getHistoriquesByAction(String action) {
        return historiqueRepository.findByActionIgnoreCase(action);
    }

}
