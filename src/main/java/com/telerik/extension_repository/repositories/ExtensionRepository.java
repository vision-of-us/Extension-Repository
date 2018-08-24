package com.telerik.extension_repository.repositories;


import com.telerik.extension_repository.entities.Extension;
import com.telerik.extension_repository.entities.User;
import com.telerik.extension_repository.entities.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExtensionRepository extends JpaRepository<Extension, Long> {

    Extension findByName(String name);

    void deleteByName(String name);

    void deleteById(Long id);

    // TODO documentation
    Extension save(Extension extension);

    @Query(value =
            "SELECT e FROM Extension AS e " +
            "ORDER BY e.name")
    List<Extension> findAll();


       @Query(value =
            "SELECT e FROM Extension AS e " +
                    "WHERE e.status = :pending")
    List<Extension> findAllByStatus(@Param("pending") Status pending);


    List<Extension> getAllByTags(String tag);

    List<Extension> getAllByNameOrderByNameAsc(String name);

    Extension findFirstByName(String name);

    @Modifying
    @Query("UPDATE Extension " +
            "SET name = :name, description = :description WHERE id = :id")
    void update(@Param("name") String name, @Param("description") String description, @Param("id") Long id);

}
