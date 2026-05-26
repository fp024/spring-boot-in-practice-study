INSERT INTO COURSES(NAME, CATEGORY, RATING, DESCRIPTION)
VALUES ('Rapid Spring Boot Application Development', 'Spring', 4,
        'Learn Enterprise Application Development with Spring Boot');
INSERT INTO COURSES(NAME, CATEGORY, RATING, DESCRIPTION)
VALUES ('Getting Started with Spring Security DSL', 'Spring', 5, 'Learn Spring Security DSL in Easy Steps');
INSERT INTO COURSES(NAME, CATEGORY, RATING, DESCRIPTION)
VALUES ('Getting Started with Spring Cloud Kubernetes', 'Spring', 3,
        'Master Spring Boot Application Deployment with Kubernetes');


INSERT INTO USERS(username, password, enabled)
VALUES ('user', '{noop}pw00', true);
INSERT INTO USERS(username, password, enabled)
VALUES ('admin', '{noop}pw00', true);

INSERT INTO AUTHORITIES(username, authority)
VALUES ('user', 'ROLE_USER');
INSERT INTO AUTHORITIES(username, authority)
VALUES ('admin', 'ROLE_ADMIN');