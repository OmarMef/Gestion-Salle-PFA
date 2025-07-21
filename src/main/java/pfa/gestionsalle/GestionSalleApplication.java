package pfa.gestionsalle;


import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pfa.gestionsalle.security.services.AccountService;


@SpringBootApplication
@AllArgsConstructor
public class GestionSalleApplication implements CommandLineRunner {



	public static void main(String[] args) {
		SpringApplication.run(GestionSalleApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

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

		};
	}

	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
}
