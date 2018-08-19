package com.telerik.extension_repository.repositories;


import com.telerik.extension_repository.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    // TODO documentation
    User findOneByUsername(String username);
}
