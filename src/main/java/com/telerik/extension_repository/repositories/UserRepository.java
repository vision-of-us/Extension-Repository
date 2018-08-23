package com.telerik.extension_repository.repositories;


import com.telerik.extension_repository.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    // TODO documentation
    User findOneByUsername(String username);

    User findByUsernameAndPassword(String username, String password);

    Optional<User> findByUsername(String username);

}
