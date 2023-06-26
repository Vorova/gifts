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
    is_enabled        BOOLEAN DEFAULT FALSE,
    type              VARCHAR(32)
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