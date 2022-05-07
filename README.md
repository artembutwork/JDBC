
An application which stores data in a relational database for university with JDBC

Application data stored in scheme as depicted below: 
![scheme](https://github.com/artembutwork/JDBC/blob/main/DatabaseConnectivity/data/mssql/scheme.png)

The database is to contain data on all students, lecturers, subjects taught and classrooms at the school. The information contained in the database is to allow for preparation of a timetable and registration of grades obtained by students in individual subjects.

The application allows you to perform CRUD (Create-Read-Update-Delete) operations i.e:
1) Inserting new entities records into database
2) Reading entities records from database based on query
3) Updating entities records stored in database
4) Deleting entities records from database

Unique searching functions for entities:
1) Grade - find grades which value is greater than the parameter value
2) Group – find group which type is the same as the parameter value
3) Room – find rooms which capacity within lower and upper bounds
4) Subject – find subjects which acronym length equals 3
5) Person – find people whose names starts with the parameter
6) Student – find students whose date of enrollment earlier than the parameter value
7) Teacher – find teachers without academic rank
8) Lesson – find lessons which duration (expressed in minutes) equals the parameter value



Compiling the project:

1. Connect to the server via the software.
2. Run the data/mssql/setup.sql file to create a test database and add linked entities to it.
3. Change the link to connect to the server, remembering to specify your login and password.

Now you can start testing methods which will work with the entities in the database. Each method, which performs the basic functions (CRUD), as well as the unique functions of the entities, has a unit test. The sequential execution of test methods ( add() -> update() -> delete() ) for any of the entities will result in adding a record -> its updating -> its removing from the entity. Method with unique entity function and findById() method add test data -> test it -> delete added information.
