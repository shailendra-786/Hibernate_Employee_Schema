create database Employee_Schema;

Use Employee_Schema;



CREATE TABLE Employee (
    employee_id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    other_details TEXT
);


CREATE TABLE Address (
    address_id INT AUTO_INCREMENT PRIMARY KEY,
    street VARCHAR(100),
    city VARCHAR(50),
    state VARCHAR(50),
    zip_code VARCHAR(10),
    employee_id INT,
    FOREIGN KEY (employee_id) REFERENCES Employee(employee_id)
);

CREATE TABLE Task (
    task_id INT AUTO_INCREMENT PRIMARY KEY,
    task_name VARCHAR(100),
    description TEXT,
    employee_id INT,
    FOREIGN KEY (employee_id) REFERENCES Employee(employee_id)
);

INSERT INTO Employee (first_name, last_name, other_details)
VALUES
    ('John', 'Doe', 'Some details about John'),
    ('Jane', 'Smith', 'Some details about Jane'),
    ('Mike', 'Johnson', 'Some details about Mike'),
    ('Emily', 'Brown', 'Some details about Emily'),
    ('David', 'Lee', 'Some details about David'),
    ('Sarah', 'Wilson', 'Some details about Sarah'),
    ('Robert', 'Miller', 'Some details about Robert'),
    ('Emma', 'White', 'Some details about Emma'),
    ('Michael', 'Davis', 'Some details about Michael'),
    ('Olivia', 'Jones', 'Some details about Olivia');
    
    
INSERT INTO Address (street, city, state, zip_code, employee_id)
VALUES
    ('123 Main St', 'New York', 'NY', '10001', 1),
    ('456 Oak Ave', 'Los Angeles', 'CA', '90001', 2),
    ('789 Maple Rd', 'Chicago', 'IL', '60601', 3),
    ('987 Pine St', 'Houston', 'TX', '77001', 4),
    ('456 Elm St', 'San Francisco', 'CA', '94101', 5),
    ('789 Cedar Ave', 'Boston', 'MA', '02101', 6),
    ('234 Cherry Rd', 'Seattle', 'WA', '98101', 7),
    ('567 Birch St', 'Miami', 'FL', '33101', 8),
    ('890 Palm Ave', 'Denver', 'CO', '80201', 9),
    ('123 Oak Ave', 'Atlanta', 'GA', '30301', 10);
    
    
INSERT INTO Task (task_name, description, employee_id)
VALUES
    ('Task 1', 'Description for Task 1', 1),
    ('Task 2', 'Description for Task 2', 2),
    ('Task 3', 'Description for Task 3', 1),
    ('Task 4', 'Description for Task 4', 3),
    ('Task 5', 'Description for Task 5', 4),
    ('Task 6', 'Description for Task 6', 3),
    ('Task 7', 'Description for Task 7', 2),
    ('Task 8', 'Description for Task 8', 5),
    ('Task 9', 'Description for Task 9', 6),
    ('Task 10', 'Description for Task 10', 5),
    ('Task 11', 'Description for Task 11', 7),
    ('Task 12', 'Description for Task 12', 8),
    ('Task 13', 'Description for Task 13', 7),
    ('Task 14', 'Description for Task 14', 9),
    ('Task 15', 'Description for Task 15', 10);








