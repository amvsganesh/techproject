
# TataEazyProject

Welcome to TataEazyProject! This  is a Backend project of a Spring Boot application  with JWT configurations .

## Prerequisites

- **Java Development Kit (JDK)** version 17 installed.
- **ImportantNote**  The java version in local storage  must and should be either 17 or 17+ Version
- **H2-database** is used to avoid any hassle.
- **Git** for cloning the repository.
- **Postman** for API testing.

## Getting Started

### Clone the Repository

1. Create a new folder and open Git Bash.
2. Clone the repository:
   ```bash
   git clone https://github.com/amvsganesh/techproject.git
**Import and Run in IDE**<br>
1.Open your preferred IDE (e.g., Eclipse, IntelliJ).<br>
2.Import the cloned project into your IDE.<br>
3.Start the Spring Boot application.<br>
### Running the Application
Ensure Java 17 is configured and note the username and password for h2-Database  .<br>
The endpoint are given below with details which are  not secured and secured endpoint with the GrantedRoles .<br>

- **PostMapping**  http://localhost:8080/user/save.       Not Secured Endpoint so the entity can be saved<br>
- **PostMapping**  http://localhost:8080/user/jwttoken.    Not Secured Endpoint to Generate Token<br>
- **GetMapping**   http://localhost:8080/user/getstudent.   Secured with 'admin' authority<br>
- **GetMapping**   http://localhost:8080/user/gettokens.   Secured with 'admin' authority<br>
- **GetMapping**   http://localhost:8080/user/getsubject.    Secured with 'admin' and 'user' authority<br>

<h4> NOW FOLLOW GIVEN STEPS BELOW CAREFULLY</h4><br>

### 1.Student Registration
To register a student, use the following endpoint:<br>

**POST Method:**  http://localhost:8080/user/save

**Body (JSON raw):**
 <pre>
 {
 
  "studentname": "John Doe",
  "username": "johndoe",
  "password": "password123",
  "address": "123 Main St, Anytown, USA",
  "roles":"user",
  "subjects": [
    {
      "subjectname": "Mathematics"
    },
    {
      "subjectname": "Physics"
    }
  ]
}
 </pre>
 
<h4> Important Notes:</h4>
 1.Ensure all fields (username, password,studentname ) are unique and not null.<br>
 2 Ensure the roles must be either (user or admin ) not both if not u will be unauthorized.<br>
 3. Remember the roles feild  you used , Other feilds  can be null .<br>
 
### 2.Token Generation
To Generate the token , use the following endpoint:<br>
**POST Method:**   http://localhost:8080/user/jwttoken
**Body (JSON raw):**
 <pre>
 {
  "username":"johndoe",
  "password":"password123"
}
 </pre>
 <h4> Important Notes:</h4>
 1.Ensure the username and password are same as when u have saved ur details .<br>
 2 After giving the correct credentials the token will be generated which will look like given below .<br>
 3. eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb2huZG9lIiwiaWF0IjoxNzIyODU1MzA0LCJleHAiOjE3MjI4NTcxMDR9.s2PB6cbar5UuS4-iae2F6D5qxo1x-Yu_9KZlKEle6Sg .<br>
 4. Now copy the generated token. <br>
 

### 3.Access the secured endpoints with Generated Token

**GET Method:**    http://localhost:8080/user/getstudent       Secured with 'admin' authority

**GET Method:**   http://localhost:8080/user/gettokens         Secured with 'admin' authority

**GET Method:**   http://localhost:8080/user/getsubject         Secured with 'user' and 'admin' authority
<h4> Follow the below steps carefully</h4>
Now below the  Get Method there is Authorization click on it and click on BearerToken.<br>
Now paste the copied token in it and send the request to any of the above endpoints.<br>
You will Authenticated and Authorized and will be given access to the above endpoints as the Authority Roles mentioned besides of the endpoints.  with the 'user' or 'admin' while you have registerd in step 1 .<br>
Try the same process for the above Three Endpoints.<br>
You will breif about how it works.<br> 


 ### Additional Notes
 1 .Frontend: Since there is no frontend, use Postman for API testing.<br>
 2 .Error and Exception Handling: Detailed error handling is provided .<br>
 3 . For every exception or error handling Response is in String format <br>
 4 .Contact: For support or issues, contact :amvsganesh@gmail.com <br>
