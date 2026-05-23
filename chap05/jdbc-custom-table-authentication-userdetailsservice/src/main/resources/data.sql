INSERT INTO COURSES(NAME, CATEGORY, RATING, DESCRIPTION)
VALUES ('Rapid Spring Boot Application Development', 'Spring', 4,
        'Learn Enterprise Application Development with Spring Boot');
INSERT INTO COURSES(NAME, CATEGORY, RATING, DESCRIPTION)
VALUES ('Getting Started with Spring Security DSL', 'Spring', 5, 'Learn Spring Security DSL in Easy Steps');
INSERT INTO COURSES(NAME, CATEGORY, RATING, DESCRIPTION)
VALUES ('Getting Started with Spring Cloud Kubernetes', 'Spring', 3,
        'Master Spring Boot Application Deployment with Kubernetes');


INSERT INTO CT_USERS(ID, FIRST_NAME, LAST_NAME, USERNAME, PASSWORD, EMAIL, VERIFIED, LOCKED, ACC_CRED_EXPIRED)
VALUES (1, 'John', 'Socket', 'jsocket', '{noop}pw01', 'jsocket@example.com', TRUE, FALSE, FALSE);
INSERT INTO CT_USERS(ID, FIRST_NAME, LAST_NAME, USERNAME, PASSWORD, EMAIL, VERIFIED, LOCKED, ACC_CRED_EXPIRED)
VALUES (2, 'Steve', 'Smith', 'smith', '{noop}pw01', 'smith@example.com', FALSE, FALSE, FALSE);