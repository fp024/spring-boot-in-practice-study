INSERT INTO COURSES(NAME, CATEGORY, RATING, DESCRIPTION)
VALUES ('Rapid Spring Boot Application Development', 'Spring', 4,
        'Learn Enterprise Application Development with Spring Boot');
INSERT INTO COURSES(NAME, CATEGORY, RATING, DESCRIPTION)
VALUES ('Getting Started with Spring Security DSL', 'Spring', 5, 'Learn Spring Security DSL in Easy Steps');
INSERT INTO COURSES(NAME, CATEGORY, RATING, DESCRIPTION)
VALUES ('Getting Started with Spring Cloud Kubernetes', 'Spring', 3,
        'Master Spring Boot Application Deployment with Kubernetes');


INSERT INTO CT_USERS(CT_USER, CT_PASSWORD, CT_ENABLED)
VALUES ('user', '{noop}pw00', true);
INSERT INTO CT_USERS(CT_USER, CT_PASSWORD, CT_ENABLED)
VALUES ('admin', '{noop}pw00', true);

INSERT INTO CT_AUTHORITIES(CT_USER, CT_AUTHORITY)
VALUES ('user', 'ROLE_USER');
INSERT INTO CT_AUTHORITIES(CT_USER, CT_AUTHORITY)
VALUES ('admin', 'ROLE_ADMIN');