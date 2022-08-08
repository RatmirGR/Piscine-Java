INSERT INTO chat.users (login, password) VALUES ('Tom', 'tom1');
INSERT INTO chat.users (login, password) VALUES ('Tim', 'tim2');
INSERT INTO chat.users (login, password) VALUES ('Mike', 'mike3');
INSERT INTO chat.users (login, password) VALUES ('John', 'john4');
INSERT INTO chat.users (login, password) VALUES ('Bob', 'bob5');

INSERT INTO chat.chatrooms (name, owner) VALUES ('travels', 2);
INSERT INTO chat.chatrooms (name, owner) VALUES ('sport', 1);
INSERT INTO chat.chatrooms (name, owner) VALUES ('coding', 1);
INSERT INTO chat.chatrooms (name, owner) VALUES ('auto', 5);
INSERT INTO chat.chatrooms (name, owner) VALUES ('book', 5);

INSERT INTO chat.messages (author, room, message_text, message_time) VALUES (4, 2, 'Very good', current_timestamp);
INSERT INTO chat.messages (author, room, message_text, message_time) VALUES (3, 1, 'Hi', current_timestamp);
INSERT INTO chat.messages (author, room, message_text, message_time) VALUES (3, 1, 'How are you', current_timestamp);
INSERT INTO chat.messages (author, room, message_text, message_time) VALUES (1, 5, 'Fine, thanks', current_timestamp);
INSERT INTO chat.messages (author, room, message_text, message_time) VALUES (1, 5, 'Goodbye', current_timestamp);
