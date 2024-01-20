drop table if exists hunter;

CREATE TABLE hunter
(
    id          UUID         NOT NULL,
    name        VARCHAR(255) NOT NULL,
    sur_name    VARCHAR(255) NOT NULL,
    passport_no VARCHAR(255) NOT NULL,
    CONSTRAINT pk_hunter PRIMARY KEY (id)
);

ALTER TABLE hunter
    ADD CONSTRAINT uc_hunter_passport_no UNIQUE (passport_no);