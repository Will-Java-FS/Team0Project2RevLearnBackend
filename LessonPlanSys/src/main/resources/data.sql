--data.sql
INSERT INTO
    programs (program_id, program_name)
VALUES
    (1, 'Computer Science'),
    (2, 'Data Science'),
    (3, 'Cybersecurity');

-- Insert demo data into users table
INSERT INTO
    users (
        user_id,
        email,
        first_name,
        last_name,
        password_hash,
        user_created_at,
        user_updated_at,
        username,
        role,
        program_id
    )
VALUES
    (
        1,
        'john.doe@example.com',
        'John',
        'Doe',
        'hashedpassword1',
        NOW (),
        NOW (),
        'johndoe',
        'teacher',
        1
    ),
    (
        2,
        'jane.smith@example.com',
        'Jane',
        'Smith',
        'hashedpassword2',
        NOW (),
        NOW (),
        'janesmith',
        'student',
        1
    ),
    (
        3,
        'sam.wilson@example.com',
        'Sam',
        'Wilson',
        'hashedpassword3',
        NOW (),
        NOW (),
        'samwilson',
        'student',
        3
    );

-- Insert demo data into courses table
INSERT INTO
    courses (
        course_id,
        course_name,
        created_at,
        updated_at,
        description,
        teacher_id,
        program_id
    )
VALUES
    (
        1,
        'Introduction to Programming',
        NOW (),
        NOW (),
        'Learn the basics of programming.',
        1,
        1
    ),
    (
        2,
        'Data Science 101',
        NOW (),
        NOW (),
        'Introduction to Data Science concepts.',
        1,
        2
    ),
    (
        3,
        'Cybersecurity Fundamentals',
        NOW (),
        NOW (),
        'Basics of cybersecurity.',
        1,
        3
    );

-- Insert demo data into discussionforums table
INSERT INTO
    discussionforums (forum_id, course_id, title, forum_created_at, forum_updated_at)
VALUES
    (
        nextval ('discussionforums_id_seq'),
        1,
        'Programming Basics Discussion',
        NOW (),
        NOW ()
    ),
    (
        nextval ('discussionforums_id_seq'),
        2,
        'Data Science Tips and Tricks',
        NOW (),
        NOW ()
    ),
    (
        nextval ('discussionforums_id_seq'),
        3,
        'Cybersecurity News and Updates',
        NOW (),
        NOW ()
    );

-- Insert demo data into enrollments table
INSERT INTO
    enrollments (enroll_id, enrollment_status, payment_status, course_id, user_id)
VALUES
    (1, 'active', 'paid', 1, 1),
    (2, 'completed', 'paid', 2, 2),
    (3, 'active', 'pending', 3, 3);

-- Insert demo data into forum_posts table
INSERT INTO
    forum_posts (
        forum_post_id,
        created_at,
        updated_at,
        post_text,
        forum_id,
        user_id
    )
VALUES
    (
        1,
        NOW (),
        NOW (),
        'I have a question about variables in Python.',
        1,
        1
    ),
    (
        2,
        NOW (),
        NOW (),
        'What libraries are essential for data science?',
        2,
        2
    ),
    (
        3,
        NOW (),
        NOW (),
        'How to secure a server against DDoS attacks?',
        3,
        3
    );

-- Insert demo data into lesson_plans table
INSERT INTO
    lesson_plans (
        lesson_plan_id,
        content,
        created_at,
        updated_at,
        title
    )
VALUES
    (
        1,
        'Lesson content for Introduction to Programming.',
        NOW (),
        NOW (),
        'Programming Basics'
    ),
    (
        2,
        'Lesson content for Data Science 101.',
        NOW (),
        NOW (),
        'Data Science Overview'
    ),
    (
        3,
        'Lesson content for Cybersecurity Fundamentals.',
        NOW (),
        NOW (),
        'Cybersecurity Introduction'
    );

-- Insert demo data into lesson_courses table
INSERT INTO
    lesson_courses (lesson_course_id, lesson_plan_id, course_id)
VALUES
    (
        nextval ('lesson_courses_lesson_course_id_seq'),
        1,
        1
    ),
    (
        nextval ('lesson_courses_lesson_course_id_seq'),
        2,
        2
    ),
    (
        nextval ('lesson_courses_lesson_course_id_seq'),
        3,
        3
    );

-- Insert demo data into user_lesson_status table
INSERT INTO
    user_lesson_status (user_lesson_id, complete, lesson_plan_id, user_id)
VALUES
    (1, TRUE, 1, 1),
    (2, FALSE, 2, 2),
    (3, TRUE, 3, 3);