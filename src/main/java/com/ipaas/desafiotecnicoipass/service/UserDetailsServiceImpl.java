package com.ipaas.desafiotecnicoipass.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Implementação em memória para o desafio.
        if ("user".equals(username)) {
            return new User(
                "user",
                new BCryptPasswordEncoder().encode("password"),
                Collections.emptyList()
            );
        }
        throw new UsernameNotFoundException("Usuário não encontrado: " + username);
    }
}