CREATE table product
(
    id                  BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    created             TIMESTAMP                         NOT NULL,
    updated             TIMESTAMP                         NOT NULL,
    name                VARCHAR(255)                      NOT NULL,
    description         VARCHAR(512),
    category            VARCHAR(255),
    provider            VARCHAR(255),
    price               DOUBLE PRECISION                  NOT NULL
);

CREATE table user
(
    id                  BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    created             TIMESTAMP                         NOT NULL,
    updated             TIMESTAMP                         NOT NULL,
    first_name          VARCHAR(255)                      NOT NULL,
    last_name           VARCHAR(255)
);

CREATE table credential
(
    id               BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    created          TIMESTAMP                         NOT NULL,
    updated          TIMESTAMP                         NOT NULL,
    user_id          BIGINT                            NOT NULL,
    email            VARCHAR(255)                      NOT NULL,
    password         VARCHAR(255)                      NOT NULL,
    CONSTRAINT credential_email UNIQUE (email),
    FOREIGN KEY (user_id) REFERENCES user (id) ON DELETE CASCADE
);

CREATE table role
(
    id                  BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    created             TIMESTAMP                         NOT NULL,
    updated             TIMESTAMP                         NOT NULL,
    name                VARCHAR(255)                      NOT NULL
);

CREATE table users_roles
(
    user_id     BIGINT NOT NULL,
    role_id     BIGINT NOT NULL,
    CONSTRAINT users_roles_pkey PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES user (id),
    FOREIGN KEY (role_id) REFERENCES role (id)
);

INSERT INTO role (id, created, updated, name)
values (1, now(), now(), 'ROLE_ADMIN'),
       (2, now(), now(), 'ROLE_SELLER');