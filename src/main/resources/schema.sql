CREATE TABLE IF NOT EXISTS instructors (
    instructor_id SERIAL8 PRIMARY KEY,
    instructor_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS students (
    student_id SERIAL8 PRIMARY KEY,
    student_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    phone_number VARCHAR(15)
);

CREATE TABLE IF NOT EXISTS courses (
    course_id SERIAL8 PRIMARY KEY,
    course_name VARCHAR(100) NOT NULL,
    description VARCHAR,
    instructor_id INT8 NOT NULL,
    CONSTRAINT fk_instructor
        FOREIGN KEY (instructor_id)
        REFERENCES instructors(instructor_id)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS student_courses(
    id SERIAL8 PRIMARY KEY,
    student_id INT8 NOT NULL,
    course_id INT8 NOT NULL,
    CONSTRAINT fk_student FOREIGN KEY (student_id) REFERENCES students (student_id) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk_instructor FOREIGN KEY (course_id) REFERENCES courses (course_id) ON UPDATE CASCADE ON DELETE CASCADE
);