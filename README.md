# Lottery simulator

A web application designed to show the futility of trying to win the lottery - inspired by the czech lottery game Sportka.
Also a showcase of my skills as a java developer :)

## Getting started
#### Prerequisites
 - Maven
 - JDK 8

#### Installation

Download or clone this repository, and execute the following maven goals in the directory:
```sh
mvn clean install
```

A directory named "target" will appear in the project directory, that will contain an executable JAR file "lottery-1.0.jar"

Run the file:
```sh
java -jar lottery-1.0.jar
```
The web application will start on a embedded tomcat server, with http web interface available at localhost on port 8080 
#### Usage

Submit a new ticket at http://localhost:8080/newTicket

One draw costs 20 CZK.
6 numbers from the range of 1-49 are randomly selected each draw.
If you hit three of those numbers, you win 113 CZK
If you hit four, you win 619 CZK
If you hit five, you win 24 971 CZK
If you hit all six, you win the jackpot - 15 382 198 CZK

Otherwise you win nothing

You can check the result at http://localhost:8080/statistics, where all results are available
The following information is shown:
 - Player name
 - Timestamp of finishing the lottery process
 - Your current balance
 - Number of draws
 - Your frequency of hitting the winning numbers
 - Ticket state
   - Running - the lottery hasn't finished yet, results are not complete
   - Finished
   - Error - there has been an unexpected error during the lottery process
   
You can filter the results by player name

## TODO
 - Allow player to choose set his own lottery winnings
 - Add an option to play the lottery until the player wins the jackpot
 - Allow more advanced filtering on the statistics view - filter by time, allow users to sort the results, add a paginator
 - Show the frequency of hitting the winning numbers in some nicer way, instead of showing the map from code converted to string
 - Add a maven profile that would allow the user to build the project as a WAR, without an embedded database and a web server

## Author
Zuzana Trnovská

## License
This project is licensed under the MIT License - see the LICENSE.md file for details

## My notes
- My friend, who works as a junior java developer helped me with the design of this application and with choosing the right technologies. However, all the code was written by me.
- I haven't included any unit tests, because the output of my project is random, and I wasn't sure what to do.
- I have used Thymeleaf instead of JSP to generate my views, because it was recommended by the Spring team and because I wanted to practice my HTML skills, and also because it seemed more human-readable to me and easier to get into than JSP code.
- I'm not sure that Hibernate was the right choice for persisting data in my project - I may look for other options in the future
