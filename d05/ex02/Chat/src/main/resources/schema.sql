DROP SCHEMA IF EXISTS chat CASCADE;

CREATE SCHEMA IF NOT EXISTS chat;

CREATE TABLE IF NOT EXISTS chat.users
(
    id       serial PRIMARY KEY,
    login    VARCHAR(20) UNIQUE NOT NULL,
    password VARCHAR(20)        NOT NULL
);

CREATE TABLE IF NOT EXISTS chat.chatrooms
(
    id serial PRIMARY KEY,
    name VARCHAR(20) UNIQUE NOT NULL,
    owner INTEGER REFERENCES chat.users(id) NOT NULL
);

CREATE TABLE IF NOT EXISTS chat.messages
(
    id SERIAL PRIMARY KEY,
    author INTEGER REFERENCES chat.users(id) NOT NULL,
    room INTEGER REFERENCES chat.chatrooms(id) NOT NULL,
    message_text VARCHAR(200) NOT NULL,
    message_time TIMESTAMP
);