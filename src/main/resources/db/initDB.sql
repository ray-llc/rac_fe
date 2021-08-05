DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS gpio;
DROP TABLE IF EXISTS equipments;
DROP TABLE IF EXISTS tasks;

DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START WITH 1000;

CREATE TABLE users
(
    id         integer                     NOT NULL DEFAULT nextval('global_seq'),
    login      character varying(255)      NOT NULL,
    password   character varying(255)      NOT NULL,
    email      character varying(255)      NOT NULL,
    name       character varying(255)      NOT NULL,
    registered timestamp without time zone NOT NULL DEFAULT now(),
    enabled    boolean                     NOT NULL DEFAULT true,
    CONSTRAINT users_pkey PRIMARY KEY (id)
);
CREATE UNIQUE INDEX users_unique_email_idx ON users (email);


CREATE TABLE user_roles
(
    user_id INTEGER NOT NULL,
    role    VARCHAR,
    CONSTRAINT user_roles_idx UNIQUE (user_id, role),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);


CREATE TABLE equipments
(
    id          integer                     NOT NULL DEFAULT nextval('global_seq'),
    name        character varying(255)      NOT NULL,
    address     character varying(255)      NOT NULL,
    ip_address  character varying(255)      NOT NULL,
    longitude   double precision            NOT NULL DEFAULT 0,
    latitude    double precision            NOT NULL DEFAULT 0,
    state       boolean                     NOT NULL DEFAULT true,
    description character varying(255),
    registered  timestamp without time zone NOT NULL DEFAULT now(),
    enabled     boolean                     NOT NULL DEFAULT true,
    CONSTRAINT equipments_pkey PRIMARY KEY (id)
);
CREATE UNIQUE INDEX equipments_unique_ip_address_idx ON equipments (ip_address);

CREATE TABLE gpio
(
    id            integer                NOT NULL DEFAULT nextval('global_seq'),
    equipments_id INTEGER                NOT NULL,
    name          character varying(255) NOT NULL,
    direction     character varying(50)  NOT NULL,
    value         INTEGER                NOT NULL,
    trigger       character varying(50)  NOT NULL,
    action        character varying(50)  NOT NULL DEFAULT 'none',
    parameter     character varying(255),
    debounce      INTEGER                NOT NULL DEFAULT 100,
    enabled       boolean                NOT NULL DEFAULT true,
    CONSTRAINT gpio_unique_equipments_name_idx UNIQUE (equipments_id, name),
    FOREIGN KEY (equipments_id) REFERENCES equipments (id) ON DELETE CASCADE
);


CREATE TABLE tasks
(
    id          integer                NOT NULL DEFAULT nextval('global_seq'),
    name        character varying(255) NOT NULL DEFAULT 'task',
    address     character varying(255) NOT NULL,
    phone       character varying(255) NOT NULL,
    number_auto character varying(50)  NOT NULL,
    enabled     boolean                NOT NULL DEFAULT true,
    longitude   double precision       NOT NULL DEFAULT 0,
    latitude    double precision       NOT NULL DEFAULT 0,
    registered  timestamp without time zone NOT NULL DEFAULT now(),
    CONSTRAINT tasks_pkey PRIMARY KEY (id),
    CONSTRAINT tasks_unique_number_auto_address_idx UNIQUE (number_auto, address)
);
