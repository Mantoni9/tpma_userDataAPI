package com.m2olie.userDataService.config;

import com.m2olie.userDataService.model.Language;
import com.m2olie.userDataService.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class LanguageDataLoader implements CommandLineRunner {

    @Autowired
    private LanguageRepository languageRepository;

    @Override
    public void run(String... args) {
        for (String languageCode : Locale.getISOLanguages()) {
            Locale locale = new Locale(languageCode);
            Language language = new Language();
            language.setCode(languageCode);
            language.setName(locale.getDisplayLanguage());
            languageRepository.save(language);
        }
    }
}
