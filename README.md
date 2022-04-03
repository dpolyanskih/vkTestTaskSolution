# vkTestTaskSolution
My humble solution of the test assignment from VK

## Usage

Fill config.properties with username, password, client_id and client_secret

then run Gradle task
```sh
gradle clean build test
```

## Result report

I choose ReportNG (please don't confuse with the TestNG report).

After execution, tests report could be found here
```sh
/build/reports/tests/test/html/index.html
```
you should see smth like this
![ReportNG](https://i.ibb.co/SNWMnKs/2022-04-02-23-37-58.png)

