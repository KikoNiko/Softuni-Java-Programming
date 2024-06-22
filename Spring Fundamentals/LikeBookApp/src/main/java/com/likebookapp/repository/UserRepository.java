package com.likebookapp.repository;

import com.likebookapp.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByUsernameOrEmail(String username, String email);

    Optional<User> findUserByUsername(String username);
}
