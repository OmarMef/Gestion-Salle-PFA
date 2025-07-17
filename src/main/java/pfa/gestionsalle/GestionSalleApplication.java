package pfa.gestionsalle;


import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pfa.gestionsalle.entities.Salle;
import pfa.gestionsalle.repository.RoleRepository;
import pfa.gestionsalle.repository.SalleRepository;
import pfa.gestionsalle.repository.UserRepository;
import pfa.gestionsalle.security.services.AccountService;


@SpringBootApplication
@AllArgsConstructor
public class GestionSalleApplication implements CommandLineRunner {

	private UserRepository userRepository;
    private RoleRepository roleRepository;
	private SalleRepository salleRepository;


	public static void main(String[] args) {
		SpringApplication.run(GestionSalleApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		/*
		Role admin_role = roleRepository.save(new Role("admin"));
		Role user_role = roleRepository.save(new Role("user"));
		Role responsable_role = roleRepository.save(new Role("responsable"));


		userRepository.save(new Utilisateur("Mef","Omar","omar@gmail.com","1234",admin_role));
		userRepository.save(new Utilisateur("Ham","Khalil","khalil@gmail.com","1234",admin_role));
		userRepository.save(new Utilisateur("Ben","Ahmed","ahmed@gmail.com","1234",responsable_role));
		userRepository.save(new Utilisateur("Bad","Saad","saad@gmail.com","1234",responsable_role));
		userRepository.save(new Utilisateur("Diaz","Hind","hind@gmail.com","1234",user_role));
		userRepository.save(new Utilisateur("Diaz","Zineb","zineb@gmail.com","1234",user_role));
		userRepository.save(new Utilisateur("Ham","Faiza","faiza@gmail.com","1234",admin_role));
		userRepository.save(new Utilisateur("Bad","Mohamed","mohamed@gmail.com","1234",responsable_role));
		userRepository.save(new Utilisateur("Bad","Meryem","meryem@gmail.com","1234",user_role));
		userRepository.save(new Utilisateur("Hour","Rajaa","rajaa@gmail.com","1234",user_role));
		userRepository.save(new Utilisateur("Ar","Souad","souad@gmail.com","1234", admin_role));
		userRepository.save(new Utilisateur("Amrani","Mouad","mouad@gmail.com","1234", admin_role));
		userRepository.save(new Utilisateur("Bel","Olaya","olaya@gmail.com","1234", admin_role));
		*/


	}

	@Bean
	CommandLineRunner commandLineRunner(AccountService accountService) {
		return args -> {
			accountService.addNewRole("ADMIN");
			accountService.addNewRole("USER");

			accountService.addNewUser("Meftah","Omar","user1@gmail.com","1234","1234");
			accountService.addNewUser("Ben","Ahmed","user2@gmail.com","1234","1234");
			accountService.addNewUser("Ham","Khalil","admin1@gmail.com","1234","1234");
			accountService.addNewUser("Test","Test","admin2@gmail.com","1234","1234");

			accountService.addRoleToUser("user1@gmail.com","USER");
			accountService.addRoleToUser("user2@gmail.com","USER");
			accountService.addRoleToUser("admin1@gmail.com","ADMIN");
			accountService.addRoleToUser("admin2@gmail.com","ADMIN");

			accountService.deleteRoleFromUser("admin2@gmail.com","ADMIN");

			accountService.addRoleToUser("admin2@gmail.com","USER");

			salleRepository.save(new Salle("manger",100,"ghandi"));



		};
	}

	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
}
