-- Insert demo data into programs
INSERT INTO
    programs (program_name)
VALUES
    ('Computer Science'),
    ('Mathematics'),
    ('History');

-- Insert demo data into users
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
        'alice@example.com',
        'alice123',
        'hashedpassword1',
        'Alice',
        'Smith',
        'Student',
        1
    ),
    (
        'bob@example.com',
        'bob456',
        'hashedpassword2',
        'Bob',
        'Johnson',
        'Teacher',
        2
    ),
    (
        'carol@example.com',
        'carol789',
        'hashedpassword3',
        'Carol',
        'Williams',
        'Teacher',
        3
    );

-- Insert demo data into courses
INSERT INTO
    courses (course_name, description, teacher_id, program_id)
VALUES
    (
        'Intro to Computer Science',
        'An introduction to the basics of Computer Science.',
        2,
        1
    ),
    (
        'Calculus 101',
        'Basic principles of calculus.',
        3,
        2
    ),
    (
        'World History',
        'A comprehensive overview of world history.',
        3,
        3
    );

-- Insert demo data into discussionforums
INSERT INTO
    discussionforums (course_id, title)
VALUES
    (1, 'CS101 General Discussion'),
    (2, 'Calculus Questions and Answers'),
    (3, 'History Study Group');

-- Insert demo data into enrollments
INSERT INTO
    enrollments (
        enrollment_status,
        payment_status,
        course_id,
        user_id
    )
VALUES
    ('Enrolled', 'Paid', 1, 1),
    ('Enrolled', 'Paid', 2, 1),
    ('Enrolled', 'Pending', 3, 1);

-- Insert demo data into forum_posts
INSERT INTO
    forum_posts (post_text, forum_id, user_id)
VALUES
    (
        'What are the key topics covered in this course?',
        1,
        1
    ),
    (
        'Can someone help me with problem #5 in the homework?',
        2,
        1
    ),
    (
        'Looking for study partners for the final exam!',
        3,
        1
    );

-- Insert demo data into lesson_plans
INSERT INTO
    lesson_plans (content, title)
VALUES
    (
        'Lesson 1 Content: Introduction to Programming',
        'Introduction to Programming'
    ),
    (
        'Lesson 2 Content: Data Structures Basics',
        'Data Structures Basics'
    ),
    (
        'Lesson 3 Content: History of World War II',
        'History of World War II'
    );

-- Insert demo data into lesson_plan_course
INSERT INTO
    lesson_plan_course (lesson_plan_id, course_id)
VALUES
    (1, 1),
    (2, 1),
    (3, 3);

-- Insert demo data into user_lesson_status
INSERT INTO
    user_lesson_status (complete, lesson_plan_id, user_id)
VALUES
    (TRUE, 1, 1),
    (FALSE, 2, 1),
    (TRUE, 3, 1);