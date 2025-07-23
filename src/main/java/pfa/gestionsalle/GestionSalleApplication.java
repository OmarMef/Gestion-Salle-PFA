package pfa.gestionsalle;


import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pfa.gestionsalle.entities.*;
import pfa.gestionsalle.security.services.AccountService;
import pfa.gestionsalle.service.ReservationService;
import pfa.gestionsalle.service.RoleService;
import pfa.gestionsalle.service.SalleService;

import java.time.LocalDate;
import java.time.LocalTime;


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
	CommandLineRunner commandLineRunner(AccountService accountService, RoleService roleService ,
										SalleService salleService, ReservationService reservationService)
	{
		return args -> {
			roleService.addNewRole("ADMIN", "ROLE_ADMIN");
			roleService.addNewRole("USER", "ROLE_USER");
			roleService.addNewRole("RESPONSABLE", "ROLE_RESPONSABLE");
			roleService.deleteRole("RESPONSABLE");

			accountService.addNewUser("Meftah","Omar","user1@gmail.com","1234","1234");
			accountService.addNewUser("Ben","Ahmed","user2@gmail.com","1234","1234");
			accountService.addNewUser("Ham","Khalil","admin1@gmail.com","1234","1234");
			accountService.addNewUser("Test","Test","admin2@gmail.com","1234","1234");


			accountService.addRoleToUser("user1@gmail.com","USER");
			accountService.addRoleToUser("user2@gmail.com","USER");
			accountService.addRoleToUser("admin1@gmail.com","ADMIN");
			accountService.addRoleToUser("admin2@gmail.com","USER");

			accountService.deleteRoleFromUser("admin2@gmail.com","USER");
			accountService.addRoleToUser("admin2@gmail.com","ADMIN");


			Utilisateur omar = accountService.addNewUser("Meftah","Omar","omar@gmail.com","1234","1234");
			Salle salle1 = salleService.createSalle("salle1",100,"CASABLANCA");
			Salle salle2 = salleService.createSalle("salle2",250,"RABAT");


			Reservation reservation1 = new Reservation(LocalDate.of(2022,10,13)
					, LocalTime.of(12,30),LocalTime.of(13,00), Status.EN_ATTENTE, Evenement.REUNION
					,salle2 , omar);
			reservationService.createReservation(reservation1);

		};
	}

	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
}
