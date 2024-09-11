-- Insert data into programs
INSERT INTO programs (program_name) VALUES 
('Program A'),
('Program B'),
('Program C'),
('Program D'),
('Program E');

-- Insert data into users
INSERT INTO users (email, username, password_hash, first_name, last_name, role, program_id) VALUES 
('johndoe@example.com', 'johndoe', 'hashed_password1', 'John', 'Doe', 'student', 1),
('janedoe@example.com', 'janedoe', 'hashed_password2', 'Jane', 'Doe', 'student', 2),
('bobsmith@example.com', 'bobsmith', 'hashed_password3', 'Bob', 'Smith', 'teacher', 1),
('alicejones@example.com', 'alicejones', 'hashed_password4', 'Alice', 'Jones', 'teacher', 3),
('charliesmith@example.com', 'charliesmith', 'hashed_password5', 'Charlie', 'Smith', 'admin', NULL);

-- Insert data into courses
INSERT INTO courses (course_name, description, teacher_id, program_id) VALUES 
('Introduction to Programming', 'Learn the basics of programming.', 3, 1),
('Advanced Programming', 'Deep dive into advanced programming topics.', 3, 2),
('Database Systems', 'Introduction to database management systems.', 4, 3),
('Web Development', 'Learn how to build web applications.', 4, 4),
('Data Science', 'Learn data analysis and machine learning.', 5, 1);

-- Insert data into discussionforums
INSERT INTO discussionforums (course_id, title) VALUES 
(1, 'General Discussion for Intro to Programming'),
(2, 'Advanced Programming Techniques'),
(3, 'Database Systems Q&A'),
(4, 'Web Development Tips and Tricks'),
(5, 'Data Science Challenges');

-- Insert data into enrollments
INSERT INTO enrollments (enrollment_status, payment_status, course_id, user_id) VALUES 
('active', 'paid', 1, 1),
('active', 'paid', 2, 2),
('completed', 'paid', 3, 3),
('active', 'unpaid', 4, 4),
('active', 'paid', 5, 1);

-- Insert data into forum_posts
INSERT INTO forum_posts (post_text, forum_id, user_id) VALUES 
('What is the best way to start programming?', 1, 1),
('Can anyone recommend resources for advanced programming?', 2, 2),
('How do I optimize database queries?', 3, 3),
('What tools do you use for web development?', 4, 4),
('What are the latest trends in data science?', 5, 1);

-- Insert data into lesson_plans
INSERT INTO lesson_plans (content, title) VALUES 
('Introduction to variables, data types, and control structures.', 'Lesson 1: Basics of Programming'),
('Deep dive into algorithms and data structures.', 'Lesson 2: Advanced Programming Concepts'),
('Overview of SQL and relational databases.', 'Lesson 1: Introduction to Databases'),
('Hands-on project: Building a web app from scratch.', 'Lesson 1: Web Development Project'),
('Exploring machine learning models and their applications.', 'Lesson 1: Introduction to Data Science');

-- Insert data into lesson_plan_course
INSERT INTO lesson_plan_course (lesson_plan_id, course_id) VALUES 
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5);

-- Insert data into user_lesson_status
INSERT INTO user_lesson_status (complete, lesson_plan_id, user_id) VALUES 
(TRUE, 1, 1),
(FALSE, 2, 2),
(TRUE, 3, 3),
(TRUE, 4, 4),
(FALSE, 5, 1);
