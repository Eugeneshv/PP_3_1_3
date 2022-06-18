/*DROP TABLE IF EXISTS t_users_roles;
DROP TABLE IF EXISTS t_roles;
DROP TABLE IF EXISTS t_users;

CREATE TABLE t_users (id int AUTO_INCREMENT, username varchar(15), password varchar(100), firstname varchar(15), lastname varchar(15),
email varchar(100), primary key (id, username));
CREATE TABLE t_roles (id int, name varchar(15));
CREATE TABLE t_users_roles(users_id int, roles_id int);
INSERT INTO t_users (id, username, password, firstname, lastname, email)
VALUES (1, 'User1', '{noop}User1', 'Ivan', 'Ivanoff', 'ivan@gmail.com'),
       (2, 'User2', '{noop}User2', 'Marya', 'Maryanova', 'marya@gmail.com'),
       (3, 'User3', '{noop}User3', 'Mike', 'Mikoff', 'mike@gmail.com'),
       (4, 'Admin', '{noop}Admin', 'Pheophan', 'Pheophanoff', 'pheophan@gmail.com');
INSERT INTO t_roles (id, name)
VALUES (1, 'ROLE_USER'),(2, 'ROLE_ADMIN');
INSERT INTO t_users_roles (users_id, roles_id)
VALUES (1, 1), (2, 1),(3, 1), (4, 2);*/