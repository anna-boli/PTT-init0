# PTT-init0

According to SOLID design principle, the MVC (Model–view–controller) design pattern was applied and each class has single responsibilities.For views package, the classes are design as static singletons. They are design purely as user interaction for separation of concerns (SoC). The user system and user data are separated into independent to be scaled up for more secure user management system.
For database package, the models are converted into JSON format to be stored into a single JSON file. The database is designed as static classes without coupling so that it could be scaled up into NoSQL document database for a distributed system in the second release. For example, MongoDB supports data stored as JSON format for large system design.


- For controllers packages:
  - Controller contains general methods for controllers packages.
  - Validator contains methods for input validation and scanners.
  - MenuController controls the overall procedures according to user inputs.

- For database package:
  - Database: Database object write and reads a file into model.json.
  - Object2Json is called by database object to convert objects to JSON format when saving data into the database.
  - Json2Object is called by database object to convert JSON and call constructors to recreate objects when loading data from the database.

- For views package:
  - DisplayInfo prints simple print method to display information for users.
  - MenuView prints the user menu.
  - Header prints the header of each menu.
  - UserSystem prints information for UserSystem in the model.
  - View contains some general method called by other objects in views package.

- For models package:
  - Model contains general methods for models package, as well as PTT system object and user system object.
  - PttSystem contains teachers' data and a list of RequirementList
  - UserSystem contains a list of users and is responsible for login, logout and registration.
  - RequirementList contains courses of the requirement set by course directors.

- For models.users package:
  - User stores user identification information such as username, password and user role in PTT system (i.e. The user is administrator, PTT director, course director or teacher.)
  - Teacher is inherited from user class, which contains the teacher's training status and courses.