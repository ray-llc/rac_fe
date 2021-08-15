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
VALUES ('RU22w', 'г Краснодар, ул Коммунаров, д 213', '192.168.1.2', 38.981718, 45.047482, 'description1');

INSERT INTO equipments (name, address, ip_address, longitude, latitude, description)
VALUES ('RU22w', 'г Краснодар, ул им. Чкалова, д 136', '192.168.1.3', 38.964686, 45.039948, 'description2');

INSERT INTO equipments (name, address, ip_address, longitude, latitude, description)
VALUES ('RU22w', 'г Краснодар, ул Брянская, д 53', '192.168.1.4', 38.9759788, 45.0573308, 'description3');

INSERT INTO equipments (name, address, ip_address, longitude, latitude, description)
VALUES ('RU22w', 'г Краснодар, ул Красных Партизан, д 565', '192.168.1.5', 38.974783, 45.049373, 'description4');

INSERT INTO equipments (name, address, ip_address, longitude, latitude, description)
VALUES ('RU22w', 'г Краснодар, ул Новокузнечная, д 166', '192.168.1.6', 39.0020334, 45.0373452, 'description5');

INSERT INTO equipments (name, address, ip_address, longitude, latitude, description)
VALUES ('RU22w', 'г Краснодар, ул Березанская, д 50', '192.168.1.7', 38.997358, 45.034617, 'description5');

INSERT INTO equipments (name, address, ip_address, longitude, latitude, description)
VALUES ('RU22w', 'г Краснодар, ул Иркутская, д 43', '192.168.1.8', 39.03595, 45.03084, 'description5');

INSERT INTO equipments (name, address, ip_address, longitude, latitude, description)
VALUES ('RU22w', 'г Краснодар, ул им. Селезнева, д 177', '192.168.1.9', 39.039929, 45.024622, 'description5');


INSERT INTO equipments (name, address, ip_address, longitude, latitude, description)
VALUES ('RU22w', 'г Краснодар, ул Алтайская, д 18', '192.168.1.10', 39.04114, 45.03131, 'description5');

INSERT INTO equipments (name, address, ip_address, longitude, latitude, description)
VALUES ('RU22w', 'г Краснодар, ул им. Селезнева, д 179', '192.168.1.11', 39.04001, 45.024361, 'description5');

INSERT INTO equipments (name, address, ip_address, longitude, latitude, description)
VALUES ('RU22w', 'г Краснодар, ул Сормовская, д 12/6', '192.168.1.12', 39.068531, 45.024195, 'description5');

INSERT INTO equipments (name, address, ip_address, longitude, latitude, description)
VALUES ('RU22w', 'г Краснодар, 2-й Онежский проезд, д 12', '192.168.1.13', 39.072483, 45.02728, 'description5');

INSERT INTO equipments (name, address, ip_address, longitude, latitude, description)
VALUES ('RU22w', 'г Краснодар, 2-й Сормовский проезд, д 6', '192.168.1.14', 39.07631, 45.028973, 'description5');


INSERT INTO gpio (equipments_id, name, direction, value, trigger)
VALUES (1002, 'gpio6', 'direction_gpio', 1, 'rising');


INSERT INTO tasks (address, phone, number_auto,longitude, latitude)
VALUES ('г Краснодар, ул Старокубанская, д 127А', '89182498619', 'a222ac123', 39.049038, 45.02001);
INSERT INTO tasks (address, phone, number_auto,longitude, latitude)
VALUES ('г Краснодар, 1-й Звездный проезд, д 26', '89182498619', 'a333ac123', 39.08169, 45.02914);
