-- Create schema if it does not exist
CREATE SCHEMA IF NOT EXISTS project2;

-- Set the schema search path
SET search_path TO project2;

-- Drop sequences if they exist
DROP SEQUENCE IF EXISTS discussionforums_id_seq CASCADE;
DROP SEQUENCE IF EXISTS lesson_course_id_seq CASCADE;

-- Create sequences
CREATE SEQUENCE IF NOT EXISTS discussionforums_id_seq;
CREATE SEQUENCE IF NOT EXISTS lesson_course_id_seq;

-- Drop tables if they exist
DROP TABLE IF EXISTS user_lesson_status CASCADE;
DROP TABLE IF EXISTS lesson_plan_course CASCADE;
DROP TABLE IF EXISTS lesson_plans CASCADE;
DROP TABLE IF EXISTS discussionforums CASCADE;
DROP TABLE IF EXISTS forum_posts CASCADE;
DROP TABLE IF EXISTS enrollments CASCADE;
DROP TABLE IF EXISTS courses CASCADE;
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS programs CASCADE;

-- Create tables
CREATE TABLE IF NOT EXISTS programs (
    program_id SERIAL PRIMARY KEY,
    program_name VARCHAR NOT NULL
);

CREATE TABLE IF NOT EXISTS users (
    user_id SERIAL PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    username VARCHAR(50) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    user_created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    user_updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    role VARCHAR(255) NOT NULL,
    program_id INT REFERENCES programs (program_id) ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS courses (
    course_id SERIAL PRIMARY KEY,
    course_name VARCHAR NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    description TEXT,
    teacher_id INTEGER REFERENCES users (user_id) ON DELETE SET NULL,
    program_id INTEGER REFERENCES programs (program_id) ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS discussionforums (
    forum_id INTEGER NOT NULL DEFAULT nextval('discussionforums_id_seq') PRIMARY KEY,
    course_id INTEGER NOT NULL REFERENCES courses (course_id) ON DELETE CASCADE,
    title VARCHAR NOT NULL,
    forum_created_at TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    forum_updated_at TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS enrollments (
    enroll_id SERIAL PRIMARY KEY,
    enrollment_status VARCHAR(255) NOT NULL,
    payment_status VARCHAR(255) NOT NULL,
    course_id INTEGER REFERENCES courses (course_id) ON DELETE CASCADE,
    user_id INTEGER REFERENCES users (user_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS forum_posts (
    forum_post_id SERIAL PRIMARY KEY,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    post_text TEXT NOT NULL,
    forum_id INTEGER REFERENCES discussionforums (forum_id) ON DELETE CASCADE,
    user_id INTEGER REFERENCES users (user_id) ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS lesson_plans (
    lesson_plan_id SERIAL PRIMARY KEY,
    content TEXT NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    title VARCHAR NOT NULL
);

CREATE UNIQUE INDEX IF NOT EXISTS lesson_plan_pkey ON lesson_plans (lesson_plan_id);

CREATE TABLE IF NOT EXISTS lesson_plan_course (
    lesson_course_id INTEGER NOT NULL DEFAULT nextval('lesson_course_id_seq') PRIMARY KEY,
    lesson_plan_id INTEGER NOT NULL REFERENCES lesson_plans (lesson_plan_id) ON DELETE CASCADE,
    course_id INTEGER NOT NULL REFERENCES courses (course_id) ON DELETE CASCADE
);

CREATE UNIQUE INDEX IF NOT EXISTS lesson_plan_course_pkey ON lesson_plan_course (lesson_course_id);

CREATE TABLE IF NOT EXISTS user_lesson_status (
    user_lesson_id SERIAL PRIMARY KEY,
    complete BOOLEAN NOT NULL,
    lesson_plan_id INTEGER REFERENCES lesson_plans (lesson_plan_id) ON DELETE CASCADE,
    user_id INTEGER REFERENCES users (user_id) ON DELETE CASCADE
);
