package com.tarabut.retriever.dto.repository;

import com.tarabut.retriever.dto.Preferences;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PreferencesRepository extends JpaRepository<Preferences, Long> {

    Optional<Preferences> findFirstByUserIdentifierOrderByIdDesc(String userIdentifier);
}
