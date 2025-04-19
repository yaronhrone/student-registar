CREATE TABLE students(
id INT AUTO_INCREMENT,
name VARCHAR(20) NOT NULL,
age INT,
email VARCHAR(50) UNIQUE NOT NULL,
phone VARCHAR(20),
PRIMARY KEY(id)
);

CREATE TABLE courses(
id INT AUTO_INCREMENT,
name VARCHAR(20) UNIQUE NOT NULL,
start_date DATE NOT NULL,
PRIMARY KEY(id)
);

CREATE TABLE student_course(
student_id INT,
course_id INT,
FOREIGN KEY(student_id) REFERENCES students(id),
FOREIGN KEY(course_id) REFERENCES courses(id),
PRIMARY KEY(student_id, course_id)
);

INSERT INTO courses(name, start_date)
VALUES('HTML', '2025-06-01'),
('CSS', '2025-07-01'),
('JS', '2025-08-01'),
('React', '2025-09-01'),
('Angular', '2025-10-01'),
('QA', '2025-11-01'),
('UI-UX', '2025-12-01');