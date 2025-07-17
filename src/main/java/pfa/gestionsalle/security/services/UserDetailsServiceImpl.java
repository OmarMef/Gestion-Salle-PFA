package pfa.gestionsalle.security.services;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pfa.gestionsalle.entities.Utilisateur;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private AccountService accountService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Utilisateur user = accountService.LoadUserByUsername(username);
        if(user == null) throw new UsernameNotFoundException(String.format("User %s not found", username));

        UserDetails userDetails = User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole().getNomRole())
                .build();

        return userDetails;
    }

}
