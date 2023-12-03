package com.m2olie.userDataService.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;

@Component
public class SQLRunner implements CommandLineRunner {
    private final DataSource dataSource;
    private final ResourceLoader resourceLoader;
    private final JdbcTemplate jdbcTemplate;

    public SQLRunner(DataSource dataSource, ResourceLoader resourceLoader, JdbcTemplate jdbcTemplate) {
        this.dataSource = dataSource;
        this.resourceLoader = resourceLoader;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        // Pfad zur SQL-Datei
        Resource resource = resourceLoader.getResource("classpath:data/sql/init.sql");

        // Prüfen, ob die Datenbank leer ist
        if (isDatabaseEmpty()) {
            // Ausführen des SQL-Skripts
            try (Connection conn = dataSource.getConnection()) {
                ScriptUtils.executeSqlScript(conn, resource);
            }
        }
    }

    private boolean isDatabaseEmpty() {
        String sql = "SELECT COUNT(*) FROM practitioner";
        int count = jdbcTemplate.queryForObject(sql, Integer.class);
        return count == 0;
    }
}
