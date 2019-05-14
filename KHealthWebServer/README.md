
# Running Web Service (Linux Ubuntu)

 ### - Install Java 
 ### - Install sbt (Scala build tool)
 ### - cd into  WebServer , and type : ```sbt testProd``` || ```sbt run ```


## To Run CLI: 
### cd into cli & Type the following:

1.```javac -cp "/libs/gson.jar" *.java```
2.```java -cp .:libs/gson.jar Graph```

Input parameters should be seperated by comma, ie: ```Tired,Hungry```

-- I've tested these running instruction under Linux Ubuntu and it seemed to work perfectly, please let me know if  you tackle any issues running it

# HTTTP INTERFACE 

#### GET localhost:9000/next-question/:username
200 OK If all went well and returns the questions & it's possible Answers(s)
If username doesn't exist, creates a new one and starts iterating from root.

#### PUT localhost:9000/answer-question/:username
BODY = Raw Input, Examples:
```"Yes"``` | ```"No"``` | ```"Tired,Hungry"``` (Multiple as String, separated by comma)

Returns 200 OK If processed successfully

Returns 404 if an Error occurred during processing answer (Can and should be handled to behave better in such cases)


## PLEASE READ
I have chosen to implement both tasks, a simple CLI with the  traversing engine & a Web Server for handling multiple users and storing session's state for long term.
I have assumed all inputs are correct (did not protect code to handle invalid inputs etc.)
I have invested in writing as clean as possible solution to handle the state and traverse the graph easily.

Regardless, this Implementation can be further extended, for example:

1.Have a more fault-tolerant persistent storage such as DB
2.Add dynamic traversing start point, that is, root node for a possible run is determined upon's user's first input, unlike current Implementation which starts from same root for all incoming new requests.
3.Add Code documentation, Error handling, Logging, Unit Tests.


## Protocol:

I have chosen to represent the chatbot's data structure system as a directed graph, assuming it cannot have inner cycles.

I've represented the graph by Vertices and Edges:

Vertices are the Questions which include the question and it's possible answers.
Edges are the user's possible answers, where each set of answers represents the next Vertex ( = Question ) to jump to.

Each user's session is stored internally in a hashMap of <String,Integer> where the Integer is the vertex number (i.e: next question to ask the user in this session).

When a user asks for a question, we fetch it's current vertex from HashMap, and return the Vertex's question + possible Answer(s)

When a user answers a question, we check if the answer maps to any Edge, and if it does, we only update the user's session to step onto the new vertex it has just moved to.


** It is totaly possible to build a JSON file having the structure I've defined along with the data that appears in the diagram of the exercise, my current graph is just a random JSON used to have some run contexts during development **

# Tech

##### The java code is wrapped with Play Framework 2.7 (latest) for HTTP purposes.
##### I used Gson for serializing stream objects into POJO.


