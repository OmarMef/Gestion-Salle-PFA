package pfa.gestionsalle.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pfa.gestionsalle.entities.Historique;
import pfa.gestionsalle.repository.HistoriqueRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public Map<String, Long> getActionCounts() {
        List<Object[]> results = historiqueRepository.countActions();
        Map<String, Long> map = new HashMap<>();
        for (Object[] row : results) {
            map.put((String) row[0], (Long) row[1]);
        }
        return map;
    }

    public Map<String, Long> getTopUsers() {
        List<Object[]> results = historiqueRepository.countActionsByUser();
        Map<String, Long> map = new HashMap<>();
        for (Object[] row : results) {
            map.put((String) row[0], (Long) row[1]);
        }
        return map;
    }

    public Map<String, Long> getTopSalles() {
        List<Object[]> results = historiqueRepository.countReservationsBySalle();
        Map<String, Long> map = new HashMap<>();
        for (Object[] row : results) {
            map.put((String) row[0], (Long) row[1]);
        }
        return map;
    }

}
