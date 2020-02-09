# Lendico challenge

## Testing
testing is very important and it is one of the main bottle nicks which slow teams. 
when you are looking to testing please notice the following:
- tests are provided as BDD test and has no relation to implementation.
- code quality of these tests. this is a good example for code quality.
- it is easy to add more tests by just adding data files.
- the use of facade design pattern to decouple implementation code from testing code.
- the use of json to provide input and expected results data.
- how integration, unit and test cases are implemented. and how easy it is to implement end to end testing by just adding an EndToEndSystem class for example.
- number of unit tests related to integration tests.
- test code coverage numbers. even though code converge was not the purpose, but got it for free by using BDD.
- edge cases tests provided by single, two-months and three-months tests as an example.

## development
please notice that I am not sure about calculation. to verify that we need to add more tests.
verify calculation tests:
- test data could be provided by product owner.
- 5000-24-5 test. this is the provided example test. 
- I am not that confident about calculation, and I will ask my product owner to provide more examples to help me write more calculation tests specially to cover case "(if, calculated amount exceeds the initial outstanding principal amount, take initial outstanding principal amount instead >> can happen principal in the very last installment".

## running product
### build and run tests
- assuming you have maven installed.
mvn clean install

- no maven installed use mvnw provided command file.

### run product
mvn spring-boot:run

you can use the application by
- command line this is a curl example which i did not test.
curl --location --request POST 'localhost:8080/generate-plan' \
--header 'Content-Type: application/json' \
--data-raw '{
 "loanAmount": "5000",
 "nominalRate": "5.0",
 "duration": 3,
 "startDate": "2018-01-01T00:00:01Z"
}
'

- using postman by importing file: Lendico.postman_collection.json

- using swagger: http://localhost:8080/swagger-ui.html


