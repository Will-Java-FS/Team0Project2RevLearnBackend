-- Insert sample data
-- Insert data into programs
INSERT INTO
    programs (program_id, program_name)
VALUES
    (1, 'Computer Science'),
    (2, 'Data Science');

-- Insert data into users
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
        program_id
    )
VALUES
    (
        1,
        'john.doe@example.com',
        'John',
        'Doe',
        'hashedpassword123',
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP,
        'johndoe',
        1
    ),
    (
        2,
        'jane.smith@example.com',
        'Jane',
        'Smith',
        'hashedpassword456',
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP,
        'janesmith',
        2
    );

-- Insert data into courses
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
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP,
        'Learn the basics of programming.',
        1,
        1
    ),
    (
        2,
        'Advanced Data Analysis',
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP,
        'In-depth data analysis techniques.',
        2,
        2
    );

-- Insert data into discussionforums
INSERT INTO
    discussionforums (
        forum_id,
        course_id,
        title,
        forum_created_at,
        forum_updated_at
    )
VALUES
    (
        1,
        1,
        'General Discussion',
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP
    ),
    (
        2,
        1,
        'Homework Help',
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP
    ),
    (
        3,
        2,
        'Project Ideas',
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP
    );

-- Insert data into forum_posts
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
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP,
        'What’s the best way to start learning Python?',
        1,
        1
    ),
    (
        2,
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP,
        'Does anyone have tips for the upcoming homework?',
        2,
        1
    ),
    (
        3,
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP,
        'I have an idea for a data visualization project.',
        3,
        2
    );

-- Insert data into enrollments
INSERT INTO
    enrollments (enroll_id, status, course_id, user_id)
VALUES
    (1, 'Enrolled', 1, 1),
    (2, 'Completed', 2, 2);

-- Insert data into lesson_plans
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
        'Introduction to variables and data types.',
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP,
        'Lesson 1: Basics'
    ),
    (
        2,
        'Advanced data manipulation techniques.',
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP,
        'Lesson 2: Data Analysis'
    );

-- Insert data into lesson_courses
INSERT INTO
    lesson_courses (lesson_course_id, lesson_plan_id, course_id)
VALUES
    (1, 1, 1),
    (2, 2, 2);

-- Insert data into teachers
INSERT INTO
    teachers (id, first_name, last_name, email)
VALUES
    (
        1,
        'Alice',
        'Johnson',
        'alice.johnson@example.com'
    ),
    (2, 'Bob', 'Brown', 'bob.brown@example.com');

-- Insert data into user_lesson_status
INSERT INTO
    user_lesson_status (id, complete, lesson_plan_id, user_id)
VALUES
    (1, TRUE, 1, 1),
    (2, FALSE, 2, 2);