CREATE TABLE book
(
    isbn   LONG UNIQUE NOT NULL,
    title  VARCHAR(20) NOT NULL,
    author VARCHAR(20) NOT NULL,
    SAB    VARCHAR(5),
    DDC    VARCHAR(5)
);