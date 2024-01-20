-- USER
-- hashed password: letmein
INSERT INTO security_user (id, email, password, first_name, last_name)
VALUES (1, 'secure.admin@example.com', '$2a$12$ZhGS.zcWt1gnZ9xRNp7inOvo5hIT0ngN7N.pN939cShxKvaQYHnnu', 'Secure', 'Administrator'),
       (2, 'admin1@example.com', '$2a$12$ZhGS.zcWt1gnZ9xRNp7inOvo5hIT0ngN7N.pN939cShxKvaQYHnnu', 'Admin1', 'Cardoso'),
       (3, 'admin2@example.com', '$2a$12$ZhGS.zcWt1gnZ9xRNp7inOvo5hIT0ngN7N.pN939cShxKvaQYHnnu', 'Admin2', 'Cardao'),
       (4, 'user1@example.com', '$2a$12$ZhGS.zcWt1gnZ9xRNp7inOvo5hIT0ngN7N.pN939cShxKvaQYHnnu', 'User1', 'Marques'),
       (5, 'user2@example.com', '$2a$12$ZhGS.zcWt1gnZ9xRNp7inOvo5hIT0ngN7N.pN939cShxKvaQYHnnu', 'User2', 'Martins');

-- ROLES
INSERT INTO security_role (id, role_name, description)
VALUES (1, 'ROLE_SECURE_ADMIN', 'Secure Administrator');
INSERT INTO security_role (id, role_name, description)
VALUES (2, 'ROLE_ADMIN', 'Administrator');
INSERT INTO security_role (id, role_name, description)
VALUES (3, 'ROLE_USER', 'Normal User');

INSERT INTO user_role(user_id, role_id)
VALUES (1, 1), -- give Secure Admin ROLE_SECURE_ADMIN
       (2, 1), -- give Admin1 ROLE_SECURE_ADMIN
       (3, 1), -- give Admin2 ROLE_SECURE_ADMIN
       (4, 3), -- give User1 ROLE_USER
       (5, 3); -- give User2 ROLE_USER
