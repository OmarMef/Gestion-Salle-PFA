package pfa.gestionsalle;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestClient;
import pfa.gestionsalle.entities.Historique;
import pfa.gestionsalle.entities.Reservation;
import pfa.gestionsalle.entities.Role;
import pfa.gestionsalle.entities.Utilisateur;
import pfa.gestionsalle.repository.RoleRepository;
import pfa.gestionsalle.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class GestionSalleApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(GestionSalleApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Role admin_role = new Role("admin");
		roleRepository.save(admin_role);

		userRepository.save(new Utilisateur("Omar","Meftah","omar@gmail.com","password",null));
		userRepository.save(new Utilisateur("khalil","hamimouch","khalil@gmail.com","password",null));

		Utilisateur user1 = Utilisateur.builder()
				.nom("Ahmed")
				.prenom("Benar")
				.email("ahmed@gmail.com")
				.password("password")
				.role(admin_role)
				.build();
		userRepository.save(user1);

	}
}
