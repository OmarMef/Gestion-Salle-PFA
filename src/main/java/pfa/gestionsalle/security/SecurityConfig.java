package pfa.gestionsalle.security;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import pfa.gestionsalle.security.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
@AllArgsConstructor
public class SecurityConfig {

    private PasswordEncoder passwordEncoder;
    private UserDetailsServiceImpl userDetailsServiceImpl;

    /*
    @Bean
    public InMemoryUserDetailsManager InMemoryUserDetailsManager() {
        return new InMemoryUserDetailsManager(
                User.withUsername("omar").password(passwordEncoder.encode("password")).roles("ADMIN").build(),
                User.withUsername("khalil").password(passwordEncoder.encode("password")).roles("RESPONSABLE").build(),
                User.withUsername("ahmed").password(passwordEncoder.encode("password")).roles("USER").build()
        );
    }*/

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(csrf -> csrf.disable());
        httpSecurity.authorizeHttpRequests(auth -> auth
                        //.requestMatchers("").permitAll()
                        .anyRequest().authenticated()
        );
        httpSecurity.formLogin(form -> form
                        .defaultSuccessUrl("/admin/index",true)
        );
        httpSecurity.userDetailsService(userDetailsServiceImpl);

        return httpSecurity.build() ;
    }

}
