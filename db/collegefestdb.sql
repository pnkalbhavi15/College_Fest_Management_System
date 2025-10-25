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


CREATE TABLE task (
    task_id INT AUTO_INCREMENT PRIMARY KEY,
    event_id INT NOT NULL,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    scheduled_time DATETIME,
    claimed_by INT DEFAULT NULL, -- links to volunteer.volunteer_id when claimed
    status VARCHAR(20) DEFAULT 'To Do',
    FOREIGN KEY (event_id) REFERENCES event(event_id)
);
