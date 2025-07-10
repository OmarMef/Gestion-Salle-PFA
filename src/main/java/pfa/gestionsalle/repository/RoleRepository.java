package pfa.gestionsalle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pfa.gestionsalle.entities.Role;
import pfa.gestionsalle.entities.Utilisateur;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
