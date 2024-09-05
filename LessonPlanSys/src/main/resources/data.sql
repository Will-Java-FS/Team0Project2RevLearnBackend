-- Insert demo data into programs table
INSERT INTO
    programs (program_name)
VALUES
    ('Computer Science');

INSERT INTO
    programs (program_name)
VALUES
    ('Mathematics');

INSERT INTO
    programs (program_name)
VALUES
    ('Literature');

-- Insert demo data into users table
INSERT INTO
    users (
        email,
        username,
        password_hash,
        first_name,
        last_name,
        role,
        program_id
    )
VALUES
    (
        'john.doe@example.com',
        'johndoe',
        'hashedpassword1',
        'John',
        'Doe',
        'student',
        1
    ),
    (
        'jane.smith@example.com',
        'janesmith',
        'hashedpassword2',
        'Jane',
        'Smith',
        'teacher',
        2
    ),
    (
        'admin@example.com',
        'admin',
        'hashedpassword3',
        'Admin',
        'User',
        'admin',
        NULL
    );

-- Insert demo data into courses table
INSERT INTO
    courses (course_name, description, teacher_id, program_id)
VALUES
    (
        'Introduction to Programming',
        'Learn the basics of programming using Python.',
        2,
        1
    ),
    (
        'Calculus I',
        'Introduction to differential and integral calculus.',
        2,
        2
    );

-- Insert demo data into discussionforums table
INSERT INTO
    discussionforums (course_id, title)
VALUES
    (1, 'Introduction to Programming Forum'),
    (2, 'Calculus I Forum');

-- Insert demo data into enrollments table
INSERT INTO
    enrollments (
        enrollment_status,
        payment_status,
        course_id,
        user_id
    )
VALUES
    ('active', 'paid', 1, 1),
    ('active', 'unpaid', 2, 1);

-- Insert demo data into forum_posts table
INSERT INTO
    forum_posts (post_text, forum_id, user_id)
VALUES
    (
        'Welcome to the Introduction to Programming Forum!',
        1,
        2
    ),
    ('When is the first assignment due?', 1, 1),
    ('How do I solve problem 3?', 2, 1);

-- Insert demo data into lesson_plans table
INSERT INTO
    lesson_plans (content, title)
VALUES
    (
        'Lesson 1: Introduction to Variables and Data Types',
        'Variables and Data Types'
    ),
    (
        'Lesson 2: Control Structures in Python',
        'Control Structures'
    );

-- Insert demo data into lesson_courses table
INSERT INTO
    lesson_courses (lesson_plan_id, course_id)
VALUES
    (1, 1),
    (2, 1);

-- Insert demo data into user_lesson_status table
INSERT INTO
    user_lesson_status (complete, lesson_plan_id, user_id)
VALUES
    (false, 1, 1),
    (true, 2, 1);