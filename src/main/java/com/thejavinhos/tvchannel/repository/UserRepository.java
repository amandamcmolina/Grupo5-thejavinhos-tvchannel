package com.thejavinhos.tvchannel.repository;

import com.thejavinhos.tvchannel.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

//    Optional<User> findByUsername(String username);
    User findByUsername(String username);
}
