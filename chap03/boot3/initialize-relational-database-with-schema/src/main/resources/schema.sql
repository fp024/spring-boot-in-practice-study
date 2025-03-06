CREATE TABLE COURSES
(
    id           INT NOT NULL,
    name         VARCHAR(100) NOT NULL,
    category     VARCHAR(20) NOT NULL,
    rating       TINYINT NOT NULL,
    description  VARCHAR(1000) NOT NULL,
    PRIMARY KEY (id)
);