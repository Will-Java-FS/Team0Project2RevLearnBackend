-- Fix the schema to use discussionforums instead of discussion_forums or make the forum model use discussion_forums
-- Add valid entries to discussionforums table to match forum_posts table
-- Add role column to users table

-- Create the schema if it does not exist
CREATE SCHEMA IF NOT EXISTS project2;

-- Set the schema search path
SET
    search_path TO project2;

-- Drop sequences if they exist
DROP SEQUENCE IF EXISTS discussionforums_id_seq CASCADE;

DROP SEQUENCE IF EXISTS lesson_courses_lesson_course_id_seq CASCADE;

DROP SEQUENCE IF EXISTS teachers_id_seq CASCADE;

-- Create sequences
CREATE SEQUENCE IF NOT EXISTS discussionforums_id_seq;

CREATE SEQUENCE IF NOT EXISTS lesson_courses_lesson_course_id_seq;

CREATE SEQUENCE IF NOT EXISTS teachers_id_seq;

-- Drop tables if they exist
DROP TABLE IF EXISTS user_lesson_status CASCADE;

DROP TABLE IF EXISTS teachers CASCADE;

DROP TABLE IF EXISTS lesson_courses CASCADE;

DROP TABLE IF EXISTS lesson_plans CASCADE;

DROP TABLE IF EXISTS forum_posts CASCADE;

DROP TABLE IF EXISTS enrollments CASCADE;

DROP TABLE IF EXISTS discussion_forums CASCADE;

DROP TABLE IF EXISTS discussionforums CASCADE;

DROP TABLE IF EXISTS courses CASCADE;

DROP TABLE IF EXISTS users CASCADE;

DROP TABLE IF EXISTS programs CASCADE;

-- Create tables
CREATE TABLE
    IF NOT EXISTS programs (
        program_id INTEGER NOT NULL PRIMARY KEY,
        program_name VARCHAR NOT NULL
    );

CREATE UNIQUE INDEX IF NOT EXISTS programs_pkey ON programs (program_id);

CREATE TABLE
    IF NOT EXISTS users (
        user_id INTEGER NOT NULL PRIMARY KEY,
        email VARCHAR NOT NULL UNIQUE,
        first_name VARCHAR NOT NULL,
        last_name VARCHAR NOT NULL,
        password_hash VARCHAR NOT NULL,
        user_created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
        user_updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
        username VARCHAR NOT NULL UNIQUE,
        program_id INTEGER,
        role VARCHAR NOT NULL,
        CONSTRAINT fk_program FOREIGN KEY (program_id) REFERENCES programs (program_id)
    );

CREATE UNIQUE INDEX IF NOT EXISTS users_pkey ON users (user_id);

CREATE TABLE
    IF NOT EXISTS courses (
        course_id INTEGER NOT NULL PRIMARY KEY,
        course_name VARCHAR NOT NULL,
        created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
        updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
        description TEXT,
        teacher_id INTEGER,
        program_id INTEGER,
        CONSTRAINT fk_program FOREIGN KEY (program_id) REFERENCES programs (program_id),
        CONSTRAINT fk_teacher FOREIGN KEY (teacher_id) REFERENCES users (user_id)
    );

CREATE UNIQUE INDEX IF NOT EXISTS courses_pkey ON courses (course_id);

CREATE TABLE
    IF NOT EXISTS discussionforums (
        forum_id INTEGER NOT NULL DEFAULT nextval ('discussionforums_id_seq') PRIMARY KEY,
        course_id INTEGER NOT NULL,
        title VARCHAR NOT NULL,
        forum_created_at TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP,
        forum_updated_at TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP,
        CONSTRAINT fk_course FOREIGN KEY (course_id) REFERENCES courses (course_id)
    );

CREATE UNIQUE INDEX IF NOT EXISTS discussionforums_title_key ON discussionforums (title);

CREATE UNIQUE INDEX IF NOT EXISTS discussionforums_pkey ON discussionforums (forum_id);

CREATE TABLE
    IF NOT EXISTS enrollments (
        enroll_id INTEGER NOT NULL PRIMARY KEY,
        status VARCHAR NOT NULL,
        course_id INTEGER,
        user_id INTEGER,
        CONSTRAINT fk_course FOREIGN KEY (course_id) REFERENCES courses (course_id),
        CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users (user_id)
    );

CREATE UNIQUE INDEX IF NOT EXISTS enrollments_pkey ON enrollments (enroll_id);

CREATE TABLE
    IF NOT EXISTS forum_posts (
        forum_post_id INTEGER NOT NULL PRIMARY KEY,
        created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
        updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
        post_text TEXT NOT NULL,
        forum_id INTEGER,
        user_id INTEGER,
        CONSTRAINT fk_forum FOREIGN KEY (forum_id) REFERENCES discussionforums (forum_id),
        CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users (user_id)
    );

CREATE UNIQUE INDEX IF NOT EXISTS forum_posts_pkey ON forum_posts (forum_post_id);

CREATE TABLE
    IF NOT EXISTS lesson_plans (
        lesson_plan_id INTEGER NOT NULL PRIMARY KEY,
        content TEXT NOT NULL,
        created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
        updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
        title VARCHAR NOT NULL
    );

CREATE UNIQUE INDEX IF NOT EXISTS lesson_plans_pkey ON lesson_plans (lesson_plan_id);

CREATE TABLE
    IF NOT EXISTS lesson_courses (
        lesson_course_id INTEGER NOT NULL DEFAULT nextval ('lesson_courses_lesson_course_id_seq') PRIMARY KEY,
        lesson_plan_id INTEGER NOT NULL,
        course_id INTEGER NOT NULL,
        CONSTRAINT fk_lesson_plan FOREIGN KEY (lesson_plan_id) REFERENCES lesson_plans (lesson_plan_id),
        CONSTRAINT fk_course FOREIGN KEY (course_id) REFERENCES courses (course_id)
    );

CREATE UNIQUE INDEX IF NOT EXISTS lesson_courses_pkey ON lesson_courses (lesson_course_id);

CREATE TABLE
    IF NOT EXISTS teachers (
        id INTEGER NOT NULL DEFAULT nextval ('teachers_id_seq') PRIMARY KEY,
        first_name VARCHAR NOT NULL,
        last_name VARCHAR NOT NULL,
        email VARCHAR NOT NULL UNIQUE
    );

CREATE TABLE
    IF NOT EXISTS user_lesson_status (
        id INTEGER NOT NULL PRIMARY KEY,
        complete BOOLEAN NOT NULL,
        lesson_plan_id INTEGER,
        user_id INTEGER,
        CONSTRAINT fk_lesson_plan FOREIGN KEY (lesson_plan_id) REFERENCES lesson_plans (lesson_plan_id),
        CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users (user_id)
    );

CREATE UNIQUE INDEX IF NOT EXISTS user_lesson_status_pkey ON user_lesson_status (id);