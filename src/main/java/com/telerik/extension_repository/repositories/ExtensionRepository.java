package com.telerik.extension_repository.repositories;


import com.telerik.extension_repository.entities.Extension;
import com.telerik.extension_repository.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExtensionRepository extends JpaRepository<Extension,Integer> {

    Extension findOneByName(String name);

    void deleteByName(String name);

    // TODO documentation
    Extension save(Extension extension);

    @Query(value = "SELECT e FROM Extension AS e")
    List<Extension> findAll();



}
