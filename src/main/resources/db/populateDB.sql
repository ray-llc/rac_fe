DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM tasks;
DELETE FROM equipments;
ALTER SEQUENCE global_seq RESTART WITH 1000;

INSERT INTO users (name, email, login, password)
VALUES ('User', 'user@yandex.ru', 'login1', '{noop}password'),
       ('Admin', 'admin@yandex.ru', 'login2', '{noop}password2');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 1000),
       ('ADMIN', 1001);

INSERT INTO equipments (name, address, ip_address, longitude, latitude, description)
VALUES ('name1', 'address1', '192.168.1.2', 11.4545, 12.4453, 'description1');

INSERT INTO equipments (name, address, ip_address, longitude, latitude, description)
VALUES ('name2', 'address2', '192.168.1.3', 11.4545, 12.4453, 'description2');

INSERT INTO equipments (name, address, ip_address, longitude, latitude, description)
VALUES ('name3', 'address3', '192.168.1.4', 11.4545, 12.4453, 'description3');

INSERT INTO equipments (name, address, ip_address, longitude, latitude, description)
VALUES ('name4', 'address4', '192.168.1.5', 11.4545, 12.4453, 'description4');

INSERT INTO equipments (name, address, ip_address, longitude, latitude, description)
VALUES ('name5', 'address5', '192.168.1.6', 11.4545, 12.4453, 'description5');

INSERT INTO gpio (equipments_id, name, direction, value, trigger)
VALUES (1002, 'gpio_name', 'direction_gpio', 1, 'rising');


INSERT INTO tasks (address, phone, number_auto,longitude, latitude)
VALUES ('address1', '89182498619', 'a222ac123', 45.03547, 38.975313);
INSERT INTO tasks (address, phone, number_auto,longitude, latitude)
VALUES ('address2', '89182498619', 'a333ac123', 45.03507, 38.975013);
