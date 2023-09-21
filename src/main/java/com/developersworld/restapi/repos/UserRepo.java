package com.developersworld.restapi.repos;

import com.developersworld.restapi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    public Optional<User> findByUserName(String userName);
}
