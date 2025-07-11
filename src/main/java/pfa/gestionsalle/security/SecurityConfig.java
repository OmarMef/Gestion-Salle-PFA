package pfa.gestionsalle.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Bean
    public InMemoryUserDetailsManager InMemoryUserDetailsManager() {
        return new InMemoryUserDetailsManager(
                User.withUsername("omar").password(passwordEncoder.encode("password")).roles("ADMIN").build(),
                User.withUsername("khalil").password(passwordEncoder.encode("password")).roles("RESPONSABLE").build(),
                User.withUsername("ahmed").password(passwordEncoder.encode("password")).roles("USER").build()
        );
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(csrf -> csrf.disable())
                .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/h2-console/**").permitAll()
                        .anyRequest().authenticated()
                )
                //.exceptionHandling(ex -> ex.accessDeniedPage("/notAutorized"))
                .formLogin(form -> form
                        .defaultSuccessUrl("/index", true)
                );

        return httpSecurity.build() ;
    }

}
