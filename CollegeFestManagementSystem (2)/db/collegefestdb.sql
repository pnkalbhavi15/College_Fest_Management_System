CREATE DATABASE IF NOT EXISTS collegefestdb;
USE collegefestdb;

CREATE TABLE event (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255),
    description TEXT,
    date DATE,
    location VARCHAR(255),
    PRIMARY KEY (id)
);