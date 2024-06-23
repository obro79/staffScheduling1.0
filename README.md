# My Personal Project

## Staff Scheduler (Catered towards Resturants)

A *bulleted* list:

- I will be designing a staff scheduling software. The 'manager' will be able to enter a list of employees where each
employee has different attributes like occupation, name, availibility, days off, as well as preferred hours. 
It also allows the manager to decide how many people they want working at a time. 
software will take all these parameters into consideration and make a schedule that fits these criteria. If no schedule 
can be made it will give a message saying why it's impossible like "No one put availibilty for Mondays."

- The users will be managers of different workplacs that wants to schedule their employees automatically so they can
free up time doing more important things

- I'm want to do this project because I feel like this matches the project description closely as well as this will an
ineffiency in my workplace and give me practice finding a good algorithm so that this can scale decently well when
adding more employees.

- As a user, I want to be able to create a new employee and add it to a list of employees
- As a user, I want to be able to select a employee and add a update their availability player to the team
- As a user, I want to be able to select a employee and view their weekly availability {list of availabilities}
- As a user, I want to specify the times and the number of employees I need at a time.
- As a user, I want to be able to save my list of employees if i choose to
- As a user, I want to be able to be able to load my employee list if i choose to


Phase 4: Task 2 

Examples of Logged Events: 

- "Sat Apr 06 16:03:10 PDT 2024
EmployeeTest Need was added for Monday12:0014:00with 4 employees"
- "Sat Apr 06 16:03:10 PDT 2024
  New EmployeeTest added with name alex and job job"
- "Sat Apr 06 16:03:10 PDT 2024
  Daily Availability was added: Thursday,00:00-23:30"
- "Sat Apr 06 16:05:03 PDT 2024
  Employees were sorted alphabetically"
- "Sat Apr 06 16:05:04 PDT 2024
  Employees without availability were filtered"

//
Phase 4: Task 3
![ProjectUML](ProjectUML.jpg "UML Diagram")

after looking at the UML diagram for my project I see some things that could be changed to help improve the structure.

1. The employee list class is uneccesary. It just stores a list of Employees. It does implement the singleton pattern,
but it doesn't need to do that. Instead I can just have a feild in store hold a list of employees.
2. The schedule class does not need an association with the employeeNeeds class it just needs access to the Store app.

3. Also i have 2 classes that have a 0 ... * relationship with DailyAvailability. I'm not entierly sure what fix would
be best, but some type of heirarchy with an interface could help this.
4. I don't think the event log class is really neccessary. It just makes sure that the GUI implements the printLog
method.



