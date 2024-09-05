-- Create the schema if it does not exist
CREATE SCHEMA IF NOT EXISTS project2Andrew;

-- Set the schema search path
SET
    search_path TO project2Andrew;

-- Drop sequences if they exist
DROP SEQUENCE IF EXISTS discussionforums_id_seq CASCADE;

DROP SEQUENCE IF EXISTS lesson_course_id_seq CASCADE;

DROP SEQUENCE IF EXISTS teachers_id_seq CASCADE;

-- Create sequences
CREATE SEQUENCE IF NOT EXISTS discussionforums_id_seq;

CREATE SEQUENCE IF NOT EXISTS lesson_course_id_seq;

-- Drop tables if they exist
DROP TABLE IF EXISTS user_lesson_status CASCADE;

DROP TABLE IF EXISTS lesson_courses CASCADE;

DROP TABLE IF EXISTS lesson_plan_course CASCADE;

DROP TABLE IF EXISTS lesson_plan CASCADE;

DROP TABLE IF EXISTS lesson_plans CASCADE;

DROP TABLE IF EXISTS forum_posts CASCADE;

DROP TABLE IF EXISTS enrollments CASCADE;

DROP TABLE IF EXISTS discussionforums CASCADE;

DROP TABLE IF EXISTS courses CASCADE;

DROP TABLE IF EXISTS users CASCADE;

DROP TABLE IF EXISTS programs CASCADE;

-- Create tables
CREATE TABLE
    IF NOT EXISTS programs (
        program_id SERIAL PRIMARY KEY,
        program_name VARCHAR NOT NULL
    );

CREATE TABLE
    IF NOT EXISTS users (
        user_id SERIAL PRIMARY KEY,
        email VARCHAR(255) NOT NULL UNIQUE,
        username VARCHAR(50) NOT NULL UNIQUE,
        password_hash VARCHAR(255) NOT NULL,
        first_name VARCHAR(255) NOT NULL,
        last_name VARCHAR(255) NOT NULL,
        user_created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
        user_updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
        role VARCHAR(255) NOT NULL,
        program_id INT REFERENCES programs (program_id)
    );

CREATE TABLE
    IF NOT EXISTS courses (
        course_id SERIAL PRIMARY KEY,
        course_name VARCHAR NOT NULL,
        created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
        updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
        description TEXT,
        teacher_id INTEGER,
        program_id INTEGER,
        CONSTRAINT fk_program FOREIGN KEY (program_id) REFERENCES programs (program_id),
        CONSTRAINT fk_teacher FOREIGN KEY (teacher_id) REFERENCES users (user_id)
    );

CREATE TABLE
    IF NOT EXISTS discussionforums (
        forum_id INTEGER NOT NULL DEFAULT nextval ('discussionforums_id_seq') PRIMARY KEY,
        course_id INTEGER NOT NULL,
        title VARCHAR NOT NULL,
        forum_created_at TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP,
        forum_updated_at TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP,
        CONSTRAINT fk_course FOREIGN KEY (course_id) REFERENCES courses (course_id)
    );

CREATE TABLE
    IF NOT EXISTS enrollments (
        enroll_id SERIAL PRIMARY KEY,
        enrollment_status VARCHAR(255) NOT NULL,
        payment_status VARCHAR(255) NOT NULL,
        course_id INTEGER,
        user_id INTEGER,
        CONSTRAINT fk_course FOREIGN KEY (course_id) REFERENCES courses (course_id),
        CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users (user_id)
    );

CREATE TABLE
    IF NOT EXISTS forum_posts (
        forum_post_id SERIAL PRIMARY KEY,
        created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
        updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
        post_text TEXT NOT NULL,
        forum_id INTEGER,
        user_id INTEGER,
        CONSTRAINT fk_forum FOREIGN KEY (forum_id) REFERENCES discussionforums (forum_id),
        CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users (user_id)
    );

CREATE TABLE
    IF NOT EXISTS lesson_plans (
        lesson_plan_id SERIAL PRIMARY KEY,
        content TEXT NOT NULL,
        created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
        updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
        title VARCHAR NOT NULL
    );

<<<<<<< HEAD
=======
CREATE UNIQUE INDEX IF NOT EXISTS lesson_plan_pkey ON lesson_plans (lesson_plan_id);

>>>>>>> 7f8f54475e504848ace76c02eaa7113b6011097c
CREATE TABLE
    IF NOT EXISTS lesson_plan_course (
        lesson_course_id INTEGER NOT NULL DEFAULT nextval ('lesson_course_id_seq') PRIMARY KEY,
        lesson_plan_id INTEGER NOT NULL,
        course_id INTEGER NOT NULL,
        CONSTRAINT fk_lesson_plan FOREIGN KEY (lesson_plan_id) REFERENCES lesson_plans (lesson_plan_id),
        CONSTRAINT fk_course FOREIGN KEY (course_id) REFERENCES courses (course_id)
    );

<<<<<<< HEAD
=======
CREATE UNIQUE INDEX IF NOT EXISTS lesson_plan_course_pkey ON lesson_plan_course (lesson_course_id);

>>>>>>> 7f8f54475e504848ace76c02eaa7113b6011097c
CREATE TABLE
    IF NOT EXISTS user_lesson_status (
        user_lesson_id SERIAL PRIMARY KEY,
        complete BOOLEAN NOT NULL,
        lesson_plan_id INTEGER,
        user_id INTEGER,
        CONSTRAINT fk_lesson_plan FOREIGN KEY (lesson_plan_id) REFERENCES lesson_plans (lesson_plan_id),
        CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users (user_id)
    );