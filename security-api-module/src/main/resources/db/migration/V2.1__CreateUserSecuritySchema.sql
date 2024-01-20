
CREATE TABLE security_role
(
    id          SERIAL PRIMARY KEY,
    role_name   varchar(100) DEFAULT NULL,
    description varchar(100) DEFAULT NULL
);

CREATE TABLE security_user
(
    id         SERIAL PRIMARY KEY,
    email      varchar(255) NOT NULL,
    password   varchar(255) NOT NULL,
    first_name varchar(255) NOT NULL,
    last_name  varchar(255) NOT NULL
);

CREATE TABLE user_role
(
    user_id SERIAL NOT NULL,
    role_id SERIAL NOT NULL,
    CONSTRAINT FK_SECURITY_USER_ID FOREIGN KEY (user_id) REFERENCES security_user (id),
    CONSTRAINT FK_SECURITY_ROLE_ID FOREIGN KEY (role_id) REFERENCES security_role (id)
);
