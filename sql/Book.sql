CREATE TABLE book
(
    book_id BIGINT PRIMARY KEY NOT NULL,
    isbn    BIGINT UNIQUE      NOT NULL,
    title   VARCHAR(20)        NOT NULL,
    author  VARCHAR(20)        NOT NULL,
    genre   VARCHAR(20)        NOT NULL
);