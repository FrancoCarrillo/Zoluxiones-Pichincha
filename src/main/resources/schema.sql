CREATE TABLE IF NOT EXISTS role (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS  user_account (
    id BIGINT NOT NULL AUTO_INCREMENT,
    username VARCHAR(255),
    names VARCHAR(255),
    lastnames VARCHAR(255),
    email VARCHAR(255),
    password VARCHAR(255),
    roles VARCHAR(255),
    UNIQUE (username),
    UNIQUE (email),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS  user_rol (
    user_id BIGINT NOT NULL,
    rol_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, rol_id),
    FOREIGN KEY (user_id) REFERENCES user_account(id),
    FOREIGN KEY (rol_id) REFERENCES role(id)
);

CREATE TABLE IF NOT EXISTS currency (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS exchange_type (
    id BIGINT NOT NULL AUTO_INCREMENT,
    exchange_rate DOUBLE,
    origin_currency_id BIGINT,
    destination_currency_id BIGINT,
    PRIMARY KEY (id),
    FOREIGN KEY (origin_currency_id) REFERENCES currency(id),
    FOREIGN KEY (destination_currency_id) REFERENCES currency(id)
);

CREATE TABLE IF NOT EXISTS exchange (
    id BIGINT NOT NULL AUTO_INCREMENT,
    origin_Amount DOUBLE,
    destination_amount DOUBLE,
    user_id BIGINT,
    exchange_type_id BIGINT,
    created_date DATETIME,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES user_account(id),
    FOREIGN KEY (exchange_type_id) REFERENCES exchange_type(id)
);



INSERT INTO role (name) VALUES ('ADMIN');
INSERT INTO role (name) VALUES ('USER');

INSERT INTO currency (name) VALUES ('PEN');
INSERT INTO currency (name) VALUES ('USD');
INSERT INTO currency (name) VALUES ('EUR');


