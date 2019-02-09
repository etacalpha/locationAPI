DROP TABLE IF EXISTS US_STATES;
CREATE TABLE US_STATES (
                         ID serial PRIMARY KEY,
                         STATE_CODE char(2) NOT NULL,
                         STATE_NAME varchar(50) NOT NULL
);

DROP TABLE IF EXISTS US_CITIES;
CREATE TABLE US_CITIES (
                         ID serial PRIMARY KEY,
                         ID_STATE integer NOT NULL,
                         CITY varchar(50) NOT NULL,
                         COUNTY varchar(50) NOT NULL,
                         LATITUDE double precision NOT NULL,
                         LONGITUDE double precision NOT NULL,
                         FOREIGN KEY (ID_STATE) REFERENCES US_STATES(ID)
);