# NotesTaker

REST API for users to save notes. It supports CRUD operations for notes. User can signup then login to generate a web token for using this API. It is based on monolithic architecture.

## Tech Used

#### Java

#### Spring 
The Spring Framework is an application framework and inversion of control container for the Java platform.

#### Spring Boot
Spring Boot Tutorial provides basic and advanced concepts of Spring Framework. It also comes with Tomcat embedded.

### Database

#### MySQL

MySQL is an open-source relational database management system

## Tools

Postman - Postman is an API(application programming interface) development tool which helps to build, test and modify APIs

## How to use?

* Signup

With parameters "username" and "password". Also keep username and password between 1 to 20 otherwise server will return 400.

| Request | URL                                   | 
| ------- | ------------------------------------- | 
| POST    | http://localhost:8080/notesapi/signup |

* Login

With parameters "username" and "password"

| Request | URL                                  |
|---------|------------------------------------- |
| POST    | http://localhost:8080/notesapi/login |


You will get a JWT token in response after successfull login. Add the authorization header and add your token (add "Bearer" to token) for all below requests.

* Get all notes

| Request | URL                                       |
|---------|------------------------------------------ |
| GET     | http://localhost:8080/notesapi/user/notes |

* Get a note

| Request | URL                                            |
|---------|----------------------------------------------- |
| GET     | http://localhost:8080/notesapi/user/notes/{id} |

* Add a new note

With parameters "title" and "content". Skip the id otherwise server will return 400 Status code. Also keep title between 1 to 70 characters and content between 1 to 7500 characters otherwise server will return 400.

| Request | URL                                       |
|---------|------------------------------------------ |
| POST    | http://localhost:8080/notesapi/user/notes |

* Update a note

With parameters "title" and "content". Skip the id otherwise server will return 400 Status code. Also keep title between 1 to 70 characters and content between 1 to 7500 characters otherwise server will return 400.

| Request | URL                                            |
|---------|----------------------------------------------- |
| PUT     | http://localhost:8080/notesapi/user/notes/{id} |

* Delete a note

| Request | URL                                            |
|---------|----------------------------------------------- |
| DELETE  | http://localhost:8080/notesapi/user/notes/{id} |

* Delete user

| Request | URL                                 |
|---------|------------------------------------ |
| DELETE  | http://localhost:8080/notesapi/user |

### Status codes

| Status code | Meaning               |
| ----------- | --------------------- |
| 200         | Success               |
| 500         | Internal Server Error |
| 404         | Not Found             |
| 201         | Created               |
| 400         | Bad Request           |
| 501         | Not Implemented       |
| 401         | Unauthorized          |
