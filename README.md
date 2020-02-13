# UserNotesApp
User Notes application to perform CRUD operation on Notes linked to user

I) Key Points:-
1. Used SOLID Principle Design Pattern to create application.
   -All user related and notes related CRUD operations are segregated.
   -Created packages according to the class functionality.

2. Used Hibernate as a ORM tool to intereact with DB for CRUD Operations.

3. Used @Autowired annotation for dependency injection of Service and DAO classes.

4. Made use of Basic Authentication to authenticate user using emailId and password.

5. Added authorities table to provide to the user having emailId.

II) Instructions for database setup:-
1. Create Database:- 
  command:- 
  CREATE DATABASE  IF NOT EXISTS `user_application`;
  USE `user_application`;

2. After starting the UserNotesApplication, users and notes table will be created in database 'user_application'.

3. Add entries in users table.

Insert script: 

insert into users(user_Id,username,password,enabled,create_time,last_updated_time) values(1,'akhilgarg11@gmail.com','$2a$10$kZbD8qS.9/hKuT.qKzBcIOmEpzYk24RPyPt2leeNPCB/PmuZaHshq',1,now(),now());

insert into users(user_Id,username,password,enabled,create_time,last_updated_time) values(2,'akki10garg@gmail.com','$2a$10$kZbD8qS.9/hKuT.qKzBcIOmEpzYk24RPyPt2leeNPCB/PmuZaHshq',1,now(),now());
 
NOTE: the password is encoded. The plain text password is "password". 
Use "password" as password to authenticate application.
	
4. Create authorities table to store roles of logged in user:-
i) CREATE Script:-

DROP TABLE IF EXISTS authorities;
CREATE TABLE authorities (
  username varchar(50) NOT NULL,
  authority varchar(50) NOT NULL,
  UNIQUE KEY authorities_idx_1 (username,authority),
  CONSTRAINT authorities_ibfk_1 FOREIGN KEY (username) REFERENCES users (username)
)  ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

ii) Insert Script:-

INSERT INTO `authorities` 
VALUES 
('akhilgarg11@gmail.com','ROLE_EMPLOYEE'),
('akki10garg@gmail.com','ROLE_EMPLOYEE');
 
III) Resource URLs:-

i) Users Table operations URls:-
  -To get list of all users in database:-
   URL: http://localhost:8090/UserNotes/getUsers
  
  -To get user details on the basis of EmailId:-
   URL: http://localhost:8090/UserNotes/getUser/{emailId} 
   Example: http://localhost:8090/UserNotes/getUser/akhilgarg11@gmail.com
