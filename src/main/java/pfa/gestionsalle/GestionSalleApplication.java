package pfa.gestionsalle;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pfa.gestionsalle.entities.Role;
import pfa.gestionsalle.entities.Utilisateur;
import pfa.gestionsalle.repository.RoleRepository;
import pfa.gestionsalle.repository.UserRepository;

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

		Role admin_role = roleRepository.save(new Role("admin"));
		Role user_role = roleRepository.save(new Role("user"));
		Role responsable_role = roleRepository.save(new Role("responsable"));


		userRepository.save(new Utilisateur("Mef","Omar","omar@gmail.com","1234", admin_role));
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

	}

	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
}
