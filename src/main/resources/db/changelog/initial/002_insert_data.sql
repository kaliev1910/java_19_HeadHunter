INSERT INTO categories (parent_id, name)
VALUES (1, 'IT'),
       (1, 'Programming'),
       (1, 'Web Development'),
       (1, 'Mobile Development'),
       (2, 'Finance'),
       (2, 'Accounting'),
       (2, 'Investment Banking');

INSERT INTO contact_types (type)
VALUES ('Phone'),
       ('Email'),
       ('LinkedIn'),
       ('Telegram');

INSERT INTO users (name, surname, age, email, password, avatar, account_type, enabled)
VALUES ('John', 'Doe', 30, 'john@example.com', '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2',
        'avatar1.jpg', 'APPLICANT', true),
       ('Jane', 'Smith', 28, 'jane@example.com', '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2',
        'avatar2.jpg', 'EMPLOYER', true);

INSERT INTO resumes (applicant_email, name, category_id, expected_salary, is_active)
VALUES ('john@example.com', 'Full Stack Developer', 2, 70000, true),
       ('john@example.com', 'Financial Analyst', 6, 60000, true),
       ('jane@example.com', 'Frontend Developer', 3, 65000, true);

INSERT INTO contacts_info (resume_id, type_id, contact_value)
VALUES (1, 1, '+1234567890'),
       (1, 2, 'john@example.com'),
       (1, 3, 'https://www.linkedin.com/in/johndoe'),
       (2, 2, 'jane@example.com'),
       (2, 4, 'kaliev1910');

INSERT INTO education_info (resume_id, institution, program, degree, start_date, end_date)
VALUES (1, 'University of IT', 'Computer Science', 'Bachelor', '2010-09-01', '2014-06-30'),
       (2, 'Finance University', 'Finance', 'Bachelor', '2012-09-01', '2016-06-30'),
       (3, 'Web Development Institute', 'Web Development', 'Bachelor', '2014-09-01', '2018-06-30');

INSERT INTO work_experience_info (resume_id, company_name, position, responsibilities, years , START_DATE, END_DATE)
VALUES (1, 'Tech Company', 'Full Stack Developer', 'Developed web applications using JavaScript, HTML, CSS.', 5, '2010-09-01', '2014-06-30'),
       (2, 'Finance Firm', 'Financial Analyst', 'Conducted financial analysis and prepared reports.', 4, '2010-09-01', '2014-06-30'),
       (3, 'Web Development Agency', 'Frontend Developer', 'Designed and implemented user interfaces.', 3, '2010-09-01', '2014-06-30');
INSERT INTO vacancies (author_email, name, description, category_id, salary, exp_from, exp_to)
VALUES ('jane@example.com', 'Full Stack Developer Position',
        'We are looking for a talented Full Stack Developer to join our team.', 2, 80000, 3, 5),
       ('jane@example.com', 'Financial Analyst Internship',
        'Exciting opportunity for a Financial Analyst Intern to gain hands-on experience.', 6, 30000, 1, 2),
       ('jane@example.com', 'Frontend Developer Position',
        'Join our team as a Frontend Developer and help us build amazing web experiences.', 3, 75000, 2, 4);

INSERT INTO responded_applicants (resume_id, vacancy_id, confirmation)
VALUES (1, 1, true),
       (2, 2, false),
       (3, 3, true);
