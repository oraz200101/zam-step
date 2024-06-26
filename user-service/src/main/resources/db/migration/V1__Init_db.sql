CREATE SCHEMA user_schema;
CREATE TABLE user_schema.user_role
(
    user_id BIGINT NOT NULL,
    roles   VARCHAR(255)
);

CREATE TABLE user_schema.users
(
    id         BIGINT,
    firstname  VARCHAR(255),
    lastname   VARCHAR(255),
    patronymic VARCHAR(255),
    email      VARCHAR(255) unique,
    password   VARCHAR(255),
    birth_date date,
    CONSTRAINT pk_users PRIMARY KEY (id)
);

ALTER TABLE user_schema.user_role
    ADD CONSTRAINT fk_user_role_on_user_entity FOREIGN KEY (user_id) REFERENCES user_schema.users (id);