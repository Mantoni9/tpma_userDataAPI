package com.m2olie.userDataService.service;
import com.m2olie.userDataService.model.Language;
import com.m2olie.userDataService.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LanguageService {

    private final LanguageRepository languageRepository;

    @Autowired
    public LanguageService(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    public List<Language> getAllLanguages() {
        return languageRepository.findAll();
    }
}
