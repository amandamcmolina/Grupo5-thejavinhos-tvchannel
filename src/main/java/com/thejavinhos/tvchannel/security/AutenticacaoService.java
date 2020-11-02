package com.thejavinhos.tvchannel.security;

import com.thejavinhos.tvchannel.entity.User;
import com.thejavinhos.tvchannel.repository.UserRepository;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.User; Não tem mais essa classe?
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Transactional //teste
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isPresent()){
            return user.get();
        }
//        if(user.isPresent()){
//            return new User(user.get().getUsername(), user.get().getPassword(), true, true, true, true, user.get().getAuthorities());
//        }
        throw new UsernameNotFoundException("Dados inválidos");
    }
}
