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
import pfa.gestionsalle.service.*;

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
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner commandLineRunner(AccountService accountService, RoleService roleService ,
										SalleService salleService, ReservationService reservationService,
										EquipementService equipementService, SalleEquipementService salleEquipementService)
	{
		return args -> {/*
			roleService.addNewRole("ADMIN", "ROLE_ADMIN");
			roleService.addNewRole("USER", "ROLE_USER");
			roleService.addNewRole("RESPONSABLE", "ROLE_RESPONSABLE");

			Utilisateur user1 = accountService.addNewUser("user1","user1","user1@gmail.com","1234","1234");
			Utilisateur user2 = accountService.addNewUser("user2","user2","user2@gmail.com","1234","1234");
			Utilisateur user3 = accountService.addNewUser("admin1","admin1","admin1@gmail.com","1234","1234");
			Utilisateur user4 = accountService.addNewUser("admin2","admin2","admin2@gmail.com","1234","1234");


			accountService.addRoleToUser("user1@gmail.com","USER");
			accountService.addRoleToUser("user2@gmail.com","USER");
			accountService.addRoleToUser("admin1@gmail.com","ADMIN");
			accountService.addRoleToUser("admin2@gmail.com","ADMIN");



			accountService.addNewUser("Meftah","Omar","omar@gmail.com","1234","1234");
			accountService.addRoleToUser("omar@gmail.com","ADMIN");
			Salle salle1 = salleService.createSalle("salle1",100,"CASABLANCA");
			Salle salle2 = salleService.createSalle("salle2",250,"RABAT");


			reservationService.createReservation(LocalDate.of(2022,10,13)
					, LocalTime.of(12,30),LocalTime.of(13,00), Evenement.REUNION
					,salle1.getId() , user1.getId());

			reservationService.createReservation(LocalDate.of(2025,8,30)
					, LocalTime.of(10,00),LocalTime.of(11,00), Evenement.REUNION
					,salle1.getId() , user1.getId());

			reservationService.createReservation(LocalDate.of(2022,10,13)
					, LocalTime.of(12,30),LocalTime.of(13,00), Evenement.REUNION
					,salle2.getId() , user2.getId());

			reservationService.updateReservation(1L,LocalDate.of(2026,12,14),
					LocalTime.of(12,30),LocalTime.of(13,00), Status.EN_ATTENTE, Evenement.REUNION);


			reservationService.createReservation(LocalDate.of(2029,12,14),
					LocalTime.of(12,30),LocalTime.of(13,00), Evenement.REUNION
					,salle2.getId() , user2.getId());
			reservationService.deleteReservation(1L);

			Equipement equipement1 = equipementService.saveEquipement("table","table",100);
			Equipement equipement2 =equipementService.saveEquipement("chaise","chaise",100);

			salleEquipementService.addEquipementToSalle(salle1.getId(),equipement1.getId(),50);
			salleEquipementService.addEquipementToSalle(salle1.getId(),equipement2.getId(),60);


			salleEquipementService.addEquipementToSalle(salle2.getId(),equipement1.getId(),50);
			salleEquipementService.addEquipementToSalle(salle2.getId(),equipement2.getId(),40);
*/

		};
	}

}
