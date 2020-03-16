# theScore "the Rush" Interview Challenge
At theScore, we are always looking for intelligent, resourceful, full-stack developers to join our growing team. To help us evaluate new talent, we have created this take-home interview question. This question should take you no more than a few hours.

**All candidates must complete this before the possibility of an in-person interview. During the in-person interview, your submitted project will be used as the base for further extensions.**

### Why a take-home challenge?
In-person coding interviews can be stressful and can hide some people's full potential. A take-home gives you a chance work in a less stressful environment and showcase your talent.

We want you to be at your best and most comfortable.

### A bit about our tech stack
As outlined in our job description, you will come across technologies which include a server-side web framework (like Elixir/Phoenix, Ruby on Rails or a modern Javascript framework) and a front-end Javascript framework (like ReactJS)

### Challenge Background
We have sets of records representing football players' rushing statistics. All records have the following attributes:
* `Player` (Player's name)
* `Team` (Player's team abbreviation)
* `Pos` (Player's postion)
* `Att/G` (Rushing Attempts Per Game Average)
* `Att` (Rushing Attempts)
* `Yds` (Total Rushing Yards)
* `Avg` (Rushing Average Yards Per Attempt)
* `Yds/G` (Rushing Yards Per Game)
* `TD` (Total Rushing Touchdowns)
* `Lng` (Longest Rush -- a `T` represents a touchdown occurred)
* `1st` (Rushing First Downs)
* `1st%` (Rushing First Down Percentage)
* `20+` (Rushing 20+ Yards Each)
* `40+` (Rushing 40+ Yards Each)
* `FUM` (Rushing Fumbles)

In this repo is a sample data file [`rushing.json`](/rushing.json).

##### Challenge Requirements
1. Create a web app. This must be able to do the following steps
    1. Create a webpage which displays a table with the contents of [`rushing.json`](/rushing.json)
    2. The user should be able to sort the players by _Total Rushing Yards_, _Longest Rush_ and _Total Rushing Touchdowns_
    3. The user should be able to filter by the player's name
    4. The user should be able to download the sorted data as a CSV, as well as a filtered subset
    
2. The system should be able to potentially support larger sets of data on the order of 10k records.

3. Update the section `Installation and running this solution` in the README file explaining how to run your code

### Submitting a solution
1. Download this repo
2. Complete the problem outlined in the `Requirements` section
3. In your personal public GitHub repo, create a new public repo with this implementation
4. Provide this link to your contact at theScore

We will evaluate you on your ability to solve the problem defined in the requirements section as well as your choice of frameworks, and general coding style.

### Help
If you have any questions regarding requirements, do not hesitate to email your contact at theScore for clarification.

## Installation and running this solution
This solution was built in Java 8 using Maven. So both JDK 1.8 and Maven 3 (used 3.6.3) should be installed to build and run the application.

As my experience with front-end is very limited, the solution was built fully as a backend application as discussed with the Technical Recruiter.

Two options for running the application. Directly through the terminal or as a Web Springboot application with a couple of endpoints.

### Terminal application
1. Build the application using `mvn clean install`.
2. Run the application passing the JSON file as first argument.  
    `java -jar target/scoreAssignment-1.0-SNAPSHOT.jar rushing.json`
3. The application will provide further instructions regarding sorting, filtering and saving the CSV.

### Springboot web application
1. Build and run the application using `mvn spring-boot:run`.
2. There will be two endpoints for getting the data in JSON or CSV formats.  
    `http://localhost:8080/rushing` - This endpoint will provide the Players Data as a JSON list.  
    `http://localhost:8080/download` - This endpoint will provide the Players Data as a CSV file.
3. Both endpoints have 2 optional parameters: `sort` and `filter`.  
    `sort` accepts 3 sorting params: `Yds` for Total Rushing Yards, `Lgn` for Longest Rush, `Td` for Total Touchdowns  
    `filter` is a parameter to provide a filter for the name of the players.  
    `http://localhost:8080/rushing?sort=Yds&filter=Mat` is an example of usage of these parameters    


