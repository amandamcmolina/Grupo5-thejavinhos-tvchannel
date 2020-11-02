package com.thejavinhos.tvchannel.security;

import com.thejavinhos.tvchannel.entity.MyUserDetails;
import com.thejavinhos.tvchannel.entity.Perfil;
import com.thejavinhos.tvchannel.entity.User;
import com.thejavinhos.tvchannel.repository.UserRepository;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;
import org.springframework.stereotype.Service;


//@Transactional //teste
//@Component
@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user != null){
//            return user;

//          return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), true,
//              true, true, true, user.getAuthorities());
          MyUserDetails userUm = new MyUserDetails();
          userUm.setUser(user);
          userUm.setId(user.getId());
          userUm.setAuthorities(user.getAuthorities());
          return userUm;
        }


        throw new UsernameNotFoundException("Dados inválidos");


    }
}
