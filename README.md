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

6. Added URLs to access/perform CRUD operations on Users and Notes Table. 

7. Added validations to validate title,note columns of Notes Table for null and blank cases.
   
8. Added validations to validate emailId from Users table for unique/valid case.

9. Added feature to allow logged in user to view/update/delete their own notes only.

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
 a)To get list of all users in database:-
   URL: http://localhost:8090/UserNotesApp/getUsers
  
 b)To get user details on the basis of EmailId:-
   URL: http://localhost:8090/UserNotesApp/getUser/{emailId} 
   Example: http://localhost:8090/UserNotesApp/getUser/akhilgarg11@gmail.com

ii) Notes Table Operations URLs:-
 a)To get all notes assigned to a particular user on the basis of emailId:-
	URL:-http://localhost:8090/UserNotesApp/users/{emailId}/notes
	Request Method Type: GET
	Example:- http://localhost:8090/UserNotesApp/users/akhilgarg11@gmail.com/notes
	
 b)To store a note in database to be assigned to a particular user:-
	URL:-http://localhost:8090/UserNotesApp/users/{emailId}/notes 
	Request Method Type: POST
	Example:- http://localhost:8090/UserNotesApp/users/akhilgarg11@gmail.com/notes
	
	Add Body of Note in Content Type- Application/JSON to create Notes Object.
	Example:- 
	{
	"title": "Sample Note",
	"note": "Sample Note inserted in database"
	}
	NOTE: On save operation, Newly created Note object is returned in response.
	
 c)To update existing note stored in database against a use:-
	URL:-http://localhost:8090/UserNotesApp/users/{emailId}/notes/{noteId}
	Request Method Type: PUT
	Example:- http://localhost:8090/UserNotesApp/users/akhilgarg11@gmail.com/notes/1
	Add Body of Note in Content Type- Application/JSON to create Notes Object.
	Example:- 
	{
	"title": "Sample Note",
	"note": "Sample Note inserted in database"
	}
	NOTE: On update operation, Newly created Note object is returned in response.
	
 d)To update existing note stored in database against a use:-
	URL:-http://localhost:8090/UserNotesApp/users/{emailId}/notes/{noteId}
	Request Method Type: DELETE
	Example:- http://localhost:8090/UserNotesApp/users/akhilgarg11@gmail.com/notes/1
	