 /*
CREATE DATABASE caderneta
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Portuguese_Brazil.1252'
    LC_CTYPE = 'Portuguese_Brazil.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

COMMENT ON DATABASE caderneta
    IS 'default administrative connection database';
*/
 
CREATE SCHEMA IF NOT EXISTS SGCP AUTHORIZATION postgres;
GRANT ALL ON SCHEMA SGCP TO postgres;