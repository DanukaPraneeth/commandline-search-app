## Command Line Application to Search Json Files


##### by [Danuka Praneeth](https://danukap.com) 


## Steps to build the jar file and run the service

clone the git repository to your local server
    
    link - https://github.com/DanukaPraneeth/commandline-search-app.git
``` 
git clone https://github.com/DanukaPraneeth/commandline-search-app.git
```

Go to the local repository and update "**application.properties**" file with the location of input json files ie: users.json, tickets.json and organizations.json

Then build the executable jar with all the dependancies
``` 
mvn clean package
```

Then start the service using below command (Java 8 is a product pre-requisit to run this service)

```
java -jar target/com.swivel.searchapp-1.0.0-jar-with-dependencies.jar
```

Or else you can directly get the already build executable file inside the target folder of your local clone, and execute the above command to run the application.

##### Important:
Please make sure you have the updated "**application.properties**" file at jar file location and input json files in correct location before running the jar file.


Now you can use the search application. :) 

### Development Approaches

* This application will validate all your inputs and give feedback on accordingly.
* Exceptions and handled throughout the application and a meaningful error message will be will displayed to the end user.
* A factory design pattern is used to implement search logics of three json files
* Unit tests are implemented to test proper functionality of the application. 