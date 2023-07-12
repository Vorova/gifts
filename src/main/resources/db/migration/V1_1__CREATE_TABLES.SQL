CREATE TABLE category
(
    id     SERIAL PRIMARY KEY,
    name   VARCHAR(64),
    parent INT REFERENCES category (id)
);

CREATE TABLE gift
(
    id                BIGSERIAL PRIMARY KEY,
    title             VARCHAR(128)  NOT NULL,
    description       VARCHAR(128)  NOT NULL,
    short_description VARCHAR(128)  NOT NULL,
    category          INT REFERENCES category (id),
    cost_price        NUMERIC(7, 2) NOT NULL,
    price             NUMERIC(7, 2) NOT NULL,
    date_added        TIMESTAMP,
    is_enabled        BOOLEAN     DEFAULT FALSE,
    type              VARCHAR(32),
    tag_for           VARCHAR(32) DEFAULT 'unisex'
);

CREATE TABLE image
(
    id      BIGSERIAL PRIMARY KEY,
    gift_id BIGINT REFERENCES gift (id),
    link    VARCHAR(128) NOT NULL,
    is_main BOOLEAN DEFAULT FALSE
);

CREATE TABLE fast_order
(
    id      BIGSERIAL PRIMARY KEY,
    gift_id BIGINT REFERENCES gift (id),
    box_id  BIGINT REFERENCES gift (id),
    costs   NUMERIC(7, 2),
    price   NUMERIC(7, 2),
    date    TIMESTAMP DEFAULT now(),
    revenue NUMERIC(7, 2),
    address VARCHAR(256),
    tel     BIGINT,
    email   VARCHAR(128),
    name    VARCHAR(256),
    status  VARCHAR(32)
);

CREATE TABLE tag
(
    id    BIGSERIAL PRIMARY KEY,
    title VARCHAR(64)
);

CREATE TABLE gift_tag
(
    gift_id BIGINT REFERENCES gift (id),
    tag_id  BIGINT REFERENCES tag (id),
    PRIMARY KEY (gift_id, tag_id)
);

CREATE TABLE users
(
    id       BIGSERIAL PRIMARY KEY,
    username VARCHAR(128),
    password VARCHAR(128)
);

CREATE TABLE role
(
    id        BIGSERIAL PRIMARY KEY,
    authority VARCHAR(64)
);

CREATE TABLE users_role
(
    user_id BIGINT REFERENCES users (id),
    role_id BIGINT REFERENCES role (id)
);

CREATE TABLE action
(
    id      BIGSERIAL PRIMARY KEY,
    user_id BIGINT REFERENCES users (id),
    date    TIMESTAMP    NOT NULL,
    type    VARCHAR(128) NOT NULL,
    subject BIGINT
);