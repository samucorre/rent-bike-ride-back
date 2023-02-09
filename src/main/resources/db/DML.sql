-- USUARIOS INICIALES
INSERT INTO users (ID, LOGIN, NAME, SURNAME1, PASSWORD) VALUES (DEFAULT, 'demo', 'Demo', 'Demo', 'C5rCRzh9s2DPHYrnnLS/eg==');

-- PERFILES INICIALES
INSERT INTO profiles (ID, NAME, DESCRIPTION) VALUES (DEFAULT, 'Administrador', 'Acceso general');

-- SECCIONES INICIALES
INSERT INTO sections (ID, NAME, DESCRIPTION, ALIAS) VALUES(DEFAULT, 'Contactos', 'Perfiles y secciones a los que puede acceder cada perfil.', 'CONTACTS');

-- RELACIONES PERFILES-SECCIONES INICIALES
INSERT INTO profiles_sections_map (PROFILE_ID, SECTION_ID) VALUES (1,1);

-- RELACIONES USUARIOS-PERFILES INICIALES
INSERT INTO users_profiles_map (USER_ID, PROFILE_ID) VALUES (1,1);