CREATE TABLE if not exists categories
(
    id        INTEGER PRIMARY KEY AUTO_INCREMENT,
    parent_id INTEGER      NOT NULL,
    name      VARCHAR(255) NOT NULL
);


CREATE TABLE if not exists contact_types
(
    id   INTEGER PRIMARY KEY AUTO_INCREMENT,
    type VARCHAR(255) NOT NULL
);


CREATE TABLE if not exists users
(
    id           INTEGER PRIMARY KEY AUTO_INCREMENT,
    name         VARCHAR(255) NOT NULL,
    surname      VARCHAR(255) NOT NULL,
    age          TINYINT,
    email        VARCHAR(255) NOT NULL UNIQUE,
    password     VARCHAR(255) NOT NULL,
    avatar       VARCHAR(255),
    account_type VARCHAR(50)  NOT NULL
);

CREATE TABLE if not exists resumes
(
    id              INTEGER PRIMARY KEY AUTO_INCREMENT,
    applicant_id    INTEGER      NOT NULL,
    name            VARCHAR(255) NOT NULL,
    category_id     INTEGER      NOT NULL,
    expected_salary DECIMAL,
    is_active       BOOLEAN   DEFAULT false,
    created_date    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (applicant_id) REFERENCES users (id),
    FOREIGN KEY (category_id) REFERENCES categories (id)
);


CREATE TABLE if not exists contacts_info
(
    id            INTEGER PRIMARY KEY AUTO_INCREMENT,
    resume_id     INTEGER NOT NULL,
    type_id       INTEGER NOT NULL,
    contact_value TEXT    NOT NULL,
    FOREIGN KEY (resume_id) REFERENCES resumes (id),
    FOREIGN KEY (type_id) REFERENCES contact_types (id)
);

CREATE TABLE if not exists vacancies
(
    id           INTEGER PRIMARY KEY AUTO_INCREMENT,
    author_id    INTEGER      NOT NULL,
    name         VARCHAR(255) NOT NULL,
    description  TEXT         NOT NULL,
    category_id  INTEGER      NOT NULL,
    salary       DECIMAL,
    exp_from     INTEGER,
    exp_to       INTEGER,
    is_active    BOOLEAN   DEFAULT TRUE,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (author_id) REFERENCES users (id),
    FOREIGN KEY (category_id) REFERENCES categories (id)
);


CREATE TABLE if not exists education_info
(
    id          INTEGER PRIMARY KEY AUTO_INCREMENT,
    resume_id   INTEGER      NOT NULL,
    institution VARCHAR(255) NOT NULL,
    program     VARCHAR(255),
    degree      VARCHAR(255),
    start_date  DATE,
    end_date    DATE,
    FOREIGN KEY (resume_id) REFERENCES resumes (id)
);


CREATE TABLE if not exists work_experience_info
(
    id               INTEGER PRIMARY KEY AUTO_INCREMENT,
    resume_id        INTEGER      NOT NULL,
    company_name     VARCHAR(255) NOT NULL,
    position         VARCHAR(255) NOT NULL,
    responsibilities TEXT         NOT NULL,
    years            INTEGER      NOT NULL,
    FOREIGN KEY (resume_id) REFERENCES resumes (id)
);

CREATE TABLE if not exists responded_applicants
(
    id           INTEGER PRIMARY KEY AUTO_INCREMENT,
    resume_id    INTEGER NOT NULL,
    vacancy_id   INTEGER NOT NULL,
    confirmation BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (resume_id) REFERENCES resumes (id),
    FOREIGN KEY (vacancy_id) REFERENCES vacancies (id)
);


CREATE TABLE if not exists messages
(
    id                      INTEGER PRIMARY KEY AUTO_INCREMENT,
    responded_applicants_id INTEGER NOT NULL,
    content                 TEXT    NOT NULL,
    timestamp               TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (responded_applicants_id) REFERENCES responded_applicants (id)
);

INSERT INTO categories (parent_id, name)
VALUES (0, 'IT'),
       (1, 'Programming'),
       (1, 'Web Development'),
       (1, 'Mobile Development'),
       (0, 'Finance'),
       (5, 'Accounting'),
       (5, 'Investment Banking');

INSERT INTO contact_types (type)
VALUES ('Phone'),
       ('Email'),
       ('LinkedIn'),
       ('Skype');

INSERT INTO users (name, surname, age, email, password, avatar, account_type)
VALUES ('John', 'Doe', 30, 'john@example.com', 'password1', 'avatar1.jpg', 'applicant'),
       ('Jane', 'Smith', 28, 'jane@example.com', 'password2', 'avatar2.jpg', 'employer');

INSERT INTO resumes (applicant_id, name, category_id, expected_salary, is_active)
VALUES (1, 'Full Stack Developer', 2, 70000, true),
       (1, 'Financial Analyst', 6, 60000, true),
       (2, 'Frontend Developer', 3, 65000, true);

INSERT INTO contacts_info (resume_id, type_id, contact_value)
VALUES (1, 1, '+1234567890'),
       (1, 2, 'john@example.com'),
       (1, 3, 'https://www.linkedin.com/in/johndoe'),
       (2, 2, 'jane@example.com'),
       (2, 1, '+0987654321');
