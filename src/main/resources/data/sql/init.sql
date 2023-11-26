-- Erstellen des Practitioner-Eintrags
INSERT INTO practitioner (id, active, gender, birth_date) VALUES
    (2, true, 'MALE', '1980-01-01');

-- Erstellen eines HumanName-Eintrags
INSERT INTO human_name (id, practitioner_id, use, text, family, "start", "end") VALUES
    (2, 2, 'OFFICIAL', 'Dr. John Doe', 'Doe', '2023-01-01', '2023-12-31');

-- Erstellen eines ContactPoint-Eintrags für den Practitioner
INSERT INTO practitioner_contact_point (practitioner_id, system, value, use, rank, "start", "end") VALUES
    (2, 'EMAIL', 'johndoe@example.com', 'WORK', 1, '2023-01-01', '2023-12-31');

-- Erstellen eines Address-Eintrags (ohne line)
INSERT INTO address (id, practitioner_id, use, type, text, city, district, state, postal_code, country, "start", "end") VALUES
    (2, 2, 'HOME', 'BOTH', '123 Street, Big City', 'Big City', 'Big District', 'State', '12345', 'Country', '2023-01-01', '2023-12-31');
-- Erstellen von Einträgen in der address_line Tabelle
INSERT INTO address_line (address_id, line) VALUES (1, '123 Street');
