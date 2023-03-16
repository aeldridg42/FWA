-- DROP TABLE IF EXISTS users CASCADE;
-- DROP TABLE IF EXISTS usersLog;

CREATE TABLE IF NOT EXISTS users (
    id INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    email VARCHAR(30) NOT NULL ,
    firstName VARCHAR(20) NOT NULL,
    lastName VARCHAR(20) NOT NULL,
    phoneNumber VARCHAR(12) NOT NULL,
    password VARCHAR(80) NOT NULL,
    picName VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS usersLog (
    id INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    user_id INT REFERENCES users(id) ON DELETE CASCADE,
    date DATE,
    time TIME,
    ip VARCHAR(16)
);