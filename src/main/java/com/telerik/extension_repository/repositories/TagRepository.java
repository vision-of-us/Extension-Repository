package com.telerik.extension_repository.repositories;

import com.telerik.extension_repository.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Integer> {

    Tag findByName(String name);
}
