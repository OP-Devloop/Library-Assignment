CREATE TABLE games (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255),
    make VARCHAR(255),
    genre VARCHAR(255),
    age INT
)