-- Insert STEM programs
INSERT INTO programs (program_name) VALUES
('Computer Science'),
('Mechanical Engineering'),
('Electrical Engineering'),
('Civil Engineering'),
('Biotechnology'),
('Physics'),
('Mathematics'),
('Data Science');

-- Insert Users
INSERT INTO users (email, username, password_hash, first_name, last_name, role, program_id)
VALUES
('alice.cs@university.edu', 'alicecs', 'hashedpassword1', 'Alice', 'Smith', 'student', 1),
('bob.eng@university.edu', 'bobeng', 'hashedpassword2', 'Bob', 'Jones', 'student', 2),
('charlie.phys@university.edu', 'charliephys', 'hashedpassword3', 'Charlie', 'Brown', 'student', 6),
('david.bio@university.edu', 'davidbio', 'hashedpassword4', 'David', 'Johnson', 'student', 5),
('emma.math@university.edu', 'emmamath', 'hashedpassword5', 'Emma', 'Davis', 'student', 7),
('frank.ds@university.edu', 'frankds', 'hashedpassword6', 'Frank', 'Miller', 'student', 8),
('grace.ee@university.edu', 'graceee', 'hashedpassword7', 'Grace', 'Wilson', 'teacher', 3),
('hannah.ce@university.edu', 'hannahce', 'hashedpassword8', 'Hannah', 'Moore', 'teacher', 4),
('ivan.me@university.edu', 'ivanme', 'hashedpassword9', 'Ivan', 'Taylor', 'teacher', 2),
('julia.ds@university.edu', 'juliads', 'hashedpassword10', 'Julia', 'Anderson', 'teacher', 8);

-- Insert Courses
INSERT INTO courses (course_name, description, teacher_id, program_id)
VALUES
('Introduction to Computer Science', 'Basics of computer science and programming.', 7, 1),
('Advanced Algorithms', 'In-depth study of algorithms.', 7, 1),
('Thermodynamics', 'Study of energy and its transformations.', 9, 2),
('Structural Analysis', 'Understanding structural engineering principles.', 8, 4),
('Quantum Physics', 'Advanced concepts in quantum mechanics.', 3, 6),
('Bioinformatics', 'Combining biology with data science techniques.', 4, 5),
('Data Mining', 'Techniques for discovering patterns in data.', 10, 8),
('Machine Learning', 'Introduction to machine learning concepts and algorithms.', 10, 8),
('Calculus I', 'Basic principles of calculus.', 5, 7),
('Digital Signal Processing', 'Techniques in digital signal processing.', 7, 3);

-- Insert Enrollments
INSERT INTO enrollments (enrollment_status, payment_status, course_id, user_id)
VALUES
('Enrolled', 'Paid', 1, 1),
('Enrolled', 'Paid', 2, 1),
('Enrolled', 'Pending', 3, 2),
('Enrolled', 'Paid', 4, 2),
('Enrolled', 'Pending', 5, 3),
('Enrolled', 'Paid', 6, 4),
('Enrolled', 'Pending', 7, 5),
('Enrolled', 'Paid', 8, 6),
('Enrolled', 'Paid', 9, 2),
('Enrolled', 'Pending', 10, 1),
('Enrolled', 'Paid', 1, 3),
('Enrolled', 'Paid', 2, 4),
('Enrolled', 'Pending', 3, 5),
('Enrolled', 'Paid', 4, 6),
('Enrolled', 'Paid', 5, 1),
('Enrolled', 'Pending', 6, 2),
('Enrolled', 'Paid', 7, 3),
('Enrolled', 'Paid', 8, 4),
('Enrolled', 'Pending', 9, 5),
('Enrolled', 'Paid', 10, 6);
