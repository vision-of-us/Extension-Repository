package com.telerik.extension_repository.repositories;


import com.telerik.extension_repository.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findOneByUsername(String username);
}
