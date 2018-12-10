# todolist-rest

Restful API for a TODO application. It includes the following endpoints:

1) Path: "http://localhost:8080/todo/rest/list" list all the current tasks
    Method: GET

2) Path: "http://localhost:8080/todo/rest/details/{taskId}"
    show details of a specific task, retrieved by Id
    Method: GET

3) Path: "http://localhost:8080/todo/rest/add"
    adds a new task to the TODO list. 
    
    Method: POST

    Example of the payload of the POST request (sent with the Unix command "curl"):
    curl -v -d '{"name":"This is a test 2","category":"Test","description":"Example of adding a new todo task 2."}' -H 'Content-Type: application/json' http://localhost:8080/todo/rest/add


TO LAUNCH IT: use the Maven Spring Boot plugin, as shown below: 
mvn spring-boot:run