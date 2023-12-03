package com.m2olie.userDataService.repository;

import com.m2olie.userDataService.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepository extends JpaRepository<Language, String> {

}
