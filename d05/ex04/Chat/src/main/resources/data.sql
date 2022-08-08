INSERT INTO chat.users (login, password) VALUES ('Tom', 'tom1');
INSERT INTO chat.users (login, password) VALUES ('Tim', 'tim2');
INSERT INTO chat.users (login, password) VALUES ('Mike', 'mike3');
INSERT INTO chat.users (login, password) VALUES ('John', 'john4');
INSERT INTO chat.users (login, password) VALUES ('Bob', 'bob5');

INSERT INTO chat.chatrooms (name, owner) VALUES ('travels', 1);
INSERT INTO chat.chatrooms (name, owner) VALUES ('sport', 2);
INSERT INTO chat.chatrooms (name, owner) VALUES ('coding', 3);
INSERT INTO chat.chatrooms (name, owner) VALUES ('auto', 4);
INSERT INTO chat.chatrooms (name, owner) VALUES ('book', 5);

INSERT INTO chat.messages (author, room, message_text, message_time) VALUES (1, 1, 'Very good', current_timestamp);
INSERT INTO chat.messages (author, room, message_text, message_time) VALUES (2, 2, 'Hi', current_timestamp);
INSERT INTO chat.messages (author, room, message_text, message_time) VALUES (3, 3, 'How are you', current_timestamp);
INSERT INTO chat.messages (author, room, message_text, message_time) VALUES (4, 4, 'Fine, thanks', current_timestamp);
INSERT INTO chat.messages (author, room, message_text, message_time) VALUES (5, 5, 'Goodbye', current_timestamp);
