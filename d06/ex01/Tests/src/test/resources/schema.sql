DROP TABLE IF EXISTS products CASCADE;
CREATE TABLE products
(
    id    INTEGER IDENTITY,
    name  VARCHAR(30) NOT NULL,
    price INTEGER     NOT NULL,

    PRIMARY KEY (id)
);