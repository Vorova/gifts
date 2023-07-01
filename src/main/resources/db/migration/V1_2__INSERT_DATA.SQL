INSERT INTO category (id, name, parent)
VALUES (1, 'first category', NULL),
       (2, 'second category', NULL),
       (3, 'third category', NULL),
       (4, 'fourth category', 1);

INSERT INTO tag (title)
VALUES ('first tag'),
       ('second tag'),
       ('third'),
       ('fourth');

INSERT INTO gift (title, description, short_description, category, cost_price, price)
VALUES ('title 1', 'simple description', 'short description for preview', 1, 224, 3000),
       ('title 2', 'simple description', 'short description for preview', 1, 234, 3000),
       ('title 3', 'simple description', 'short description for preview', 2, 345, 3000),
       ('title 4', 'simple description', 'short description for preview', 2, 342, 3000),
       ('title 5', 'simple description', 'short description for preview', 3, 153, 3000),
       ('title 6', 'simple description', 'short description for preview', 3, 234, 3000),
       ('title 7', 'simple description', 'short description for preview', 4, 256, 3000),
       ('title 8', 'simple description', 'short description for preview', 2, 678, 3000),
       ('title 9', 'simple description', 'short description for preview', 1, 345, 3000),
       ('title 10', 'simple description', 'short description for preview', 2, 1000, 3000),
       ('title 11', 'simple description', 'short description for preview', 4, 1245, 3000),
       ('title 12', 'simple description', 'short description for preview', 3, 145, 3000),
       ('title 13', 'simple description', 'short description for preview', 2, 356, 3000),
       ('title 14', 'simple description', 'short description for preview', 4, 350, 3000),
       ('title 15', 'simple description', 'short description for preview', 2, 125, 3000),
       ('title 16', 'simple description', 'short description for preview', 3, 2345, 3000),
       ('title 17', 'simple description', 'short description for preview', 2, 1233, 3000);