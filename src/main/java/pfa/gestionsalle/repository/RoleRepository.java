package pfa.gestionsalle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pfa.gestionsalle.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
