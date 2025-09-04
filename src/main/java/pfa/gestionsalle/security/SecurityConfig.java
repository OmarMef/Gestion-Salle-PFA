package pfa.gestionsalle.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import pfa.gestionsalle.security.services.UserDetailsServiceImpl;

import static javax.management.Query.and;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
@AllArgsConstructor
public class SecurityConfig {

    private PasswordEncoder passwordEncoder;
    private UserDetailsServiceImpl userDetailsServiceImpl;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity

                .csrf(csrf -> csrf.disable())
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/error", "/static/**", "/assets/**").permitAll()
                        .requestMatchers("/reserv/save").permitAll()
                        .requestMatchers("/api/**").authenticated()
                        .anyRequest().authenticated()


                )
                .formLogin(form -> form
                        .defaultSuccessUrl("http://localhost:3000/home", true) // redirection après login
                        .permitAll()
                )
                .logout(logout -> logout
                                .logoutUrl("/logout")
                        .logoutSuccessUrl("/login")
                );

        httpSecurity.userDetailsService(userDetailsServiceImpl);

        return httpSecurity.build();

        /*
        httpSecurity.csrf(csrf -> csrf.disable());
        httpSecurity.authorizeHttpRequests(auth -> auth
                //.requestMatchers("").permitAll()
                .anyRequest().authenticated()
        );
        httpSecurity.formLogin(form -> form
                .defaultSuccessUrl("/home", true)
        );
        httpSecurity.userDetailsService(userDetailsServiceImpl);

        return httpSecurity.build();
        */
    }

}
