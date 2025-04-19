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
-- Add this to the existing collegefestdb.sql file
CREATE TABLE attendance (
    id BIGINT NOT NULL AUTO_INCREMENT,
    volunteer_id BIGINT,
    check_in_time DATETIME,
    check_out_time DATETIME,
    status VARCHAR(50),
    PRIMARY KEY (id),
    FOREIGN KEY (volunteer_id) REFERENCES volunteer(id)
);