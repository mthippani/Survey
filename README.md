
# CRUD App with Spring Boot

It features full REST compliance and an embedded database.

### Requirements

- Maven
- JDK 7

### Running

To build and start the server simply type

```sh
$ mvn spring-boot:run
```

from the root directory.

### Using

You can see what urls are available using curl:

```sh
$ curl localhost:8080
```

Survey Creation:
URL: http://localhost:8080/api/survey
METHOD: POST
Content type: Application/json
Body:
{"id":"1","name":"FriendShipQuiz","startDate":1505845543511,"endDate":1506018343511,"status":"Pending"}

Survey data retrieval:
URL: http://localhost:8080/api/survey/1
METHOD: GET
Content type: Application/json

Survey data retrieval all:
URL: http://localhost:8080/api/surveys/
METHOD: GET
Content type: Application/json

Survey update:
URL: http://localhost:8080/api/survey/1
METHOD: PUT
Content type: Application/json

{"id":"1","name":"FriendShipQuiz","startDate":1505845543511,"endDate":1506018343511,"status":"Pending"}

Delete Survey:
URL: http://localhost:8080/api/survey/1
METHOD: DELETE
Content type: Application/json
	
Question Creation:
URL: http://localhost:8080/api/question
METHOD: POST
Content type: Application/json
Body:
{"id":"1","question":"What is your Best friend name?","survey":{"id":"1","name":"FriendShipQuiz","startDate":1505845543511,"endDate":1506018343511,"status":"Pending"}
}

Question data retrieval:
URL: http://localhost:8080/api/question/1
METHOD: GET
Content type: Application/json

Question data retrieval all:
URL: http://localhost:8080/api/questions/
METHOD: GET
Content type: Application/json

Question update:
URL: http://localhost:8080/api/question/1
METHOD: PUT
Content type: Application/json

{"id":"1","question":"What is your Best friend name?","survey":{"id":"1","name":"FriendShipQuiz","startDate":1505845543511,"endDate":1506018343511,"status":"Pending"}
}
Delete Question:
URL: http://localhost:8080/api/question/1
METHOD: DELETE
Content type: Application/json


Answer Creation:
URL: http://localhost:8080/api/answer
METHOD: POST
Content type: Application/json
Body:
{"id":1,"answer":"Raju","question":{"id":"1","question":"What is your Best friend name?"}}

Retrive answer data:
URL: http://localhost:8080/api/answer/1
METHOD: GET
Content type: Application/json

Retrive all answers:
URL: http://localhost:8080/api/answers/
METHOD: GET
Content type: Application/json

Answer update:
URL: http://localhost:8080/api/answers/1
METHOD: PUT
Content type: Application/json
Body:
{"id":1,"answer":"Raju","question":{"id":"1","question":"What is your Best friend name?"}}

Delete answer:
URL: http://localhost:8080/api/answer/1
METHOD: DELETE
Content type: Application/json



