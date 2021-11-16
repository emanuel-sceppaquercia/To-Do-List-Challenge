package com.example.challenge.repository;

import com.example.challenge.model.Folder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FolderRepository extends JpaRepository<Folder, Long> {
    boolean existsByName(String name);
}
