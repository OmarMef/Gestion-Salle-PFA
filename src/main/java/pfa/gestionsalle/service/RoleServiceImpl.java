package pfa.gestionsalle.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pfa.gestionsalle.entities.Role;
import pfa.gestionsalle.repository.RoleRepository;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;


    @Override
    public Role addNewRole(String nomRole ,String desc){
        Role newRole = roleRepository.findByNomRole(nomRole);
        if(newRole != null) throw new RuntimeException("Role already exists");
        newRole = Role.builder()
                .nomRole(nomRole)
                .description(desc)
                .build();
        return roleRepository.save(newRole);
    }

    @Override
    public void deleteRoleById(Long id) {
        roleRepository.deleteById(id);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role findById(Long id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rôle non trouvé avec l'id : " + id));
    }

}
