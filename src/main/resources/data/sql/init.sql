-- Erstellen des Practitioner-Eintrags
INSERT INTO practitioner (id, active, gender, birth_date) VALUES
    (1, true, 'MALE', '1980-01-01');

-- Erstellen eines HumanName-Eintrags
INSERT INTO human_name (id, practitioner_id, use, text, family, "start", "end") VALUES
    (1, 1, 'OFFICIAL', 'Dr. John Doe', 'Doe', '2023-01-01', '2023-12-31');

-- Erstellen eines ContactPoint-Eintrags für den Practitioner
INSERT INTO practitioner_contact_point (practitioner_id, system, value, use, rank, "start", "end") VALUES
    (1, 'EMAIL', 'johndoe@example.com', 'WORK', 1, '2023-01-01', '2023-12-31');

-- Erstellen eines Address-Eintrags (ohne line)
INSERT INTO address (id, practitioner_id, use, type, text, city, district, state, postal_code, country, "start", "end") VALUES
    (1, 1, 'HOME', 'BOTH', '123 Street, Big City', 'Big City', 'Big District', 'State', '12345', 'Country', '2023-01-01', '2023-12-31');
-- Erstellen von Einträgen in der address_line Tabelle
INSERT INTO address_line (address_id, line) VALUES (1, '123 Street');

-- Contact-Eintrag erstellen
INSERT INTO contact (id, patient_id, relationship) VALUES
    (1, NULL, 'Familienangehöriger');

-- HumanName-Einträge für den Contact erstellen
INSERT INTO human_name (id, contact_id, use, text, family, "start", "end") VALUES
    (3, 1, 'OFFICIAL', 'Maria Musterfrau', 'Musterfrau', '2023-01-01', '2023-12-31');

-- ContactPoint-Einträge für den Contact erstellen
INSERT INTO contact_contact_point (contact_id, system, value, use, rank, "start", "end") VALUES
    (1, 'EMAIL', 'maria.musterfrau@example.com', 'HOME', 1, '2023-01-01', '2023-12-31');

-- Address-Einträge für den Contact erstellen
INSERT INTO address (id, contact_id, use, type, text, city, district, state, postal_code, country, "start", "end") VALUES
    (3, 1, 'HOME', 'POSTAL', '456 Musterweg, Musterstadt', 'Musterstadt', 'Musterbezirk', 'Musterstaat', '45678', 'Musterland', '2023-01-01', '2023-12-31');

-- Einträge in der address_line Tabelle für die Adresse des Contact erstellen
INSERT INTO address_line (address_id, line) VALUES (3, '456 Musterweg');

-- Patienten-Eintrag erstellen und den Contact zuweisen
INSERT INTO patient (id, active, gender, birth_date, marital_status) VALUES
    (2, true, 'FEMALE', '1995-01-01', false);

-- HumanName-Einträge für den Patienten erstellen
INSERT INTO human_name (id, patient_id, use, text, family, "start", "end") VALUES
    (4, 2, 'OFFICIAL', 'Anna Beispiel', 'Beispiel', '2023-01-01', '2023-12-31');

-- ContactPoint-Einträge für den Patienten erstellen
INSERT INTO patient_contact_point (patient_id, system, value, use, rank, "start", "end") VALUES
    (2, 'EMAIL', 'anna.beispiel@example.com', 'HOME', 1, '2023-01-01', '2023-12-31');

-- Address-Einträge für den Patienten erstellen
INSERT INTO address (id, patient_id, use, type, text, city, district, state, postal_code, country, "start", "end") VALUES
    (4, 2, 'HOME', 'POSTAL', '789 Beispielallee, Beispielort', 'Beispielort', 'Beispielkreis', 'Beispielbundesland', '78901', 'Beispielrepublik', '2023-01-01', '2023-12-31');

-- Einträge in der address_line Tabelle für die Adresse des Patienten erstellen
INSERT INTO address_line (address_id, line) VALUES (4, '789 Beispielallee');

-- Verknüpfung zwischen Patient und Contact herstellen
UPDATE contact SET patient_id = 2 WHERE id = 1;

-- Verknüpfung zwischen Patient und Sprache herstellen
INSERT INTO patient_language (patient_id, language_code) VALUES (2, 'en');
