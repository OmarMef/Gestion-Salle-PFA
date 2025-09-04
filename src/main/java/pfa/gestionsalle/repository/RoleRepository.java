package pfa.gestionsalle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pfa.gestionsalle.entities.Role;

import java.util.List;


public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByNomRole(String nomRole);
    void deleteById(Long id);
}
