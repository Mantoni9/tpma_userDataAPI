package com.m2olie.userDataService.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;

@Component
public class SQLRunner implements CommandLineRunner {
    private final DataSource dataSource;
    private final ResourceLoader resourceLoader;

    public SQLRunner(DataSource dataSource, ResourceLoader resourceLoader) {
        this.dataSource = dataSource;
        this.resourceLoader = resourceLoader;
    }

    @Override
    public void run(String... args) throws Exception {
        // Pfad zur SQL-Datei
        Resource resource = resourceLoader.getResource("classpath:data/sql/init.sql");

        // Ausführen des SQL-Skripts
        try (Connection conn = dataSource.getConnection()) {
            ScriptUtils.executeSqlScript(conn, resource);
        }
    }
}
