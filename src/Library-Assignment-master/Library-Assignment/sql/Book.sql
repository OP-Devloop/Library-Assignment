CREATE TABLE book
(
    id     LONG AUTO_INCREMENT NOT NULL UNIQUE PRIMARY KEY,
    isbn   LONG UNIQUE         NOT NULL,
    title  VARCHAR(20)         NOT NULL,
    author VARCHAR(20)         NOT NULL,
    genre  VARCHAR(20)         NOT NULL
);