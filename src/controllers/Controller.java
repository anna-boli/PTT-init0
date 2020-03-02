package controllers;

import java.util.ArrayList;
import java.util.Scanner;

import models.Course;
import models.Model;
import models.RequirementList;
import models.UserSystem;
import models.database.Database;
import models.users.Teacher;
import models.users.User;
import views.View;

public class Controller {
  Model model;
  View view;
  // attributes
  private int year;
  private int semester;
  private String menuSelect;
  private String newCourse;
  private String newTeacher;
  private RequirementList list;
  private ArrayList<RequirementList> lists;
  private ArrayList<Teacher> teachers;
  private String teacherName;
  private String teacherCourses;

  private int MIN_YEAR = 1451;
  private int MAX_YEAR = 9999;
  private int MIN_SEMESTER = 1;
  private int MAX_SEMESTER = 3;

  public Controller(Model model) {
    this.model = model;
  }

  public void addView(View view) {
    this.view = view;
  }

  public void initialized() {
    this.year = 0;
    this.semester = 0;
    menuSelect = "";
  }

  // after cd log in
  public void cd_login() {
    view.text_cdLogin(); // print welcome to cd page
    cd_MainMenu();
  }

  // after ptt log in
  public void ptt_login() {
    view.text_pttLogin();
    ptt_mainMenu();
  }

  // after administrator log in
  public void ad_login() {
    view.text_adLogin();
    ad_mainMenu();
  }

  // after teacher log in
  public void t_login() {
    view.text_tLogin();
    t_mainMenu();
  }

  // cd main menu
  public void cd_MainMenu() {
    Boolean selected = false;
    while (!selected) {
      int menuSelect = view.cd_selectMenu(); // show cd main menu and input option
      switch (menuSelect) {
        case 1:
          cd_createList();
          break;

        case 2:
          readList();
          break;

        case 3:
          checkRequest();
          if (list != null) {
            lists.clear();
          }
          break;

        case 4:
          view.text_logOut();
          Database.save(this.model);
          selected = true;
          break;

        default:
          view.text_invalidInput();
          break;
      }
    }
  }

  // cd add course menu
  public void cd_addCourseMenu() {
    Boolean keepAdding = false;
    while (!keepAdding) {
      int menuSelect = view.addCourseMenu();
      switch (menuSelect) {
        case 1:
          newCourse = view.addCourseToList();
          model.addCourseToList(newCourse);
          break;

        case 2:
          view.text_submitList();
          keepAdding = true;
          break;

        default:
          view.text_invalidInput();
          break;
      }
    }
  }

  // ad add new teacher
  public void ad_addNewTeacher() {
    this.view.ask2InputTeacherName();
    newTeacher = this.view.inputString(); // input teacher's name
    model.addTeacherToList(newTeacher);
  }

  // cd_ main menu select 1 - add list
  public void cd_createList() {
    this.view.ask2InputYear();
    this.year = view.inputInt(MIN_YEAR, MAX_YEAR); // input year
    this.view.ask2InputSemester();
    this.semester = view.inputInt(MIN_SEMESTER, MAX_SEMESTER); // input semester
    view.ListisBuild(); // print list title
    model.createRequirementList(year, semester);
    // System.out.println("list is build");
    cd_addCourseMenu();
  }

  // PTT main menu
  public void ptt_mainMenu() {
    Boolean selected = false;
    while (!selected) {
      int menuSelect = view.ptt_selectMenu(); // show cd main menu and input option
      switch (menuSelect) {
        case 1:
          // check request
          checkRequest();
          ptt_toApprove();
          if (list != null) {
            lists.clear();
          }
          // System.out.println("check request not yet");
          break;

        case 2:
          readList();
          break;

        case 3:
          view.text_logOut();
          Database.save(this.model);
          selected = true;
          break;

        default:
          view.text_invalidInput();
          break;
      }
    }
  }

  // ptt approval menu (y/n)
  public void ptt_toApprove() {
    Boolean approval = false;
    if (lists.size() != 0) {
      System.out.println("Input year and semester to select list.");
      this.view.ask2InputYear();
      this.year = view.inputInt(MIN_YEAR, MAX_YEAR); // input year
      this.view.ask2InputSemester();
      this.semester = view.inputInt(MIN_SEMESTER, MAX_SEMESTER); // input semester
      this.list = model.getSpecificList(year, semester);
      System.out.println("You have selected the following list.");
      view.printUnapprovedList(list);
      if (list == null) {
        return;
      }
      String makeApproval = view.makeApproval();
      while (!approval) {
        if (makeApproval.equals("y")) {
          model.setApproval(list);
          System.out.println("You have set << " + year + " semester " + semester + "Requirement List >> to approved.");
          approval = true;
        } else if (makeApproval.equals("n")) {
          approval = true;
        } else {
          view.text_invalidInput();
          approval = true;
        }
      }
    } else {
      view.text_noRequest();
    }
  }

  // ad main menu
  public void ad_mainMenu() {
    Boolean selected = false;
    teachers = model.getTeacherList();
    while (!selected) {
      int menuSelect = view.ad_selectMenu();
      switch (menuSelect) {
        case 1:
          // System.out.println("Set teacher to course"); // // loop - keep setting
          // teacher to course menu
          // System.out.println("Print specific list");
          this.view.ask2InputYear();
          this.year = view.inputInt(MIN_YEAR, MAX_YEAR); // input year
          this.view.ask2InputSemester();
          this.semester = view.inputInt(MIN_SEMESTER, MAX_SEMESTER); // input semester
          list = model.getSpecificList(year, semester);
          view.printSpecificList(list);
          // teachers = model.getTeacherList(); // ask if need to print teacher list?
          if (list != null) {
            this.view.ask2SetTeacher();
            // this.view.ask2InputCourseName();
            // String courseName = this.view.inputString();
            // this.view.ask2InputTeacherName();
            // String teacherName = this.view.inputString();
            Course course = this.view.inputCourseName(list);
            Teacher teacher = this.view.inputTeacherName();
            course.setTeacher(teacher); // can't input correctly
            teacher.addCourse(list.getYear(), list.getSemester(), course);
            // model.setTeacherToCourse(list, courseName, teacherName);
            // System.out.println("Is set.");
          }
          break;

        case 2:
          readList();
          break;

        case 3:
          System.out.println("show teacher list");
          // teachers = model.getTeacherList();
          view.printTeacherList(teachers);
          break;

        case 4:
          ad_addNewTeacher();
          break;

        case 5:
          this.view.ask2InputYear();
          this.year = view.inputInt(MIN_YEAR, MAX_YEAR); // input year
          this.view.ask2InputSemester();
          this.semester = view.inputInt(MIN_SEMESTER, MAX_SEMESTER); // input semester
          list = model.getSpecificList(year, semester);
          if (list != null) {
            view.printSpecificList(list);
            String name = this.validateString();
            model.trainTeacher(list, name);
          }
          break;

        case 6:
          view.text_logOut();
          Database.save(this.model);
          selected = true;
          break;

        default:
          view.text_invalidInput();
          break;
      }
      this.view.click2Continue();
    }
  }

  // // ad setting teacher menu (y/n)
  // public void ad_setTeacher_menu() {
  // Boolean keepSetting = false;
  // String set = view.keepSetting();
  // while (!keepSetting) {
  // if (set.equals("y")) {
  // // keepSetting
  // keepSetting = true;
  // } else if (set.equals("n")) {
  // keepSetting = true;
  // } else {
  // view.text_invalidInput();
  // keepSetting = true;
  // }
  // }
  // }

  // Teacher main menu
  public void t_mainMenu() {
    Boolean selected = false;
    while (!selected) {
      int menuSelect = view.t_selectMenu(); // show teacher main menu and input option
      switch (menuSelect) {
        case 1:
          view.printinfo(UserSystem.getCurrentUser().getUserName());
          break;

        case 2:
          view.printTeacherCourse(model.getTeacherCourse(teacherName));
          break;

        case 3:
          view.text_logOut();
          Database.save(this.model);
          selected = true;
          break;

        default:
          view.text_invalidInput();
          break;
      }
    }
  }

  // get unapproval lists ( PTT _ main menu select 1 or cd main menu select 3)
  public void checkRequest() {
    System.out.println("The following are the unapproved lists");
    this.lists = model.getUnapprovedLists();
    view.printLists(lists);
  }

  // read list
  public void readList() {
    Boolean selected = false;
    int menuSelect;
    while (!selected) {
      menuSelect = view.readListMenu(); // show menu & input option
      switch (menuSelect) {
        case 1:
          this.view.ask2InputYear();
          this.year = view.inputInt(MIN_YEAR, MAX_YEAR); // input year
          this.view.ask2InputSemester();
          this.semester = view.inputInt(MIN_SEMESTER, MAX_SEMESTER); // input semester
          list = model.getSpecificList(year, semester);
          view.printSpecificList(list);
          selected = true;
          break;

        case 2:
          this.view.ask2InputYear();
          this.year = view.inputInt(MIN_YEAR, MAX_YEAR); // input year
          lists = model.getSameYearLists(year);
          view.printLists(lists);
          selected = true;
          break;

        case 3:
          this.view.ask2InputSemester();
          this.semester = view.inputInt(MIN_SEMESTER, MAX_SEMESTER); // input semester
          lists = model.getSameSemesterLists(semester);
          view.printLists(lists);
          selected = true;
          break;

        case 4:
          lists = model.getAllLists();
          view.printLists(lists);
          selected = true;
          break;

        default:
          view.text_invalidInput();

          break;
      }

    }
    if (list != null) {
      lists.clear();
    }
  }

  /**
   * Scan input and validate it according to # of choices. The choices is valid
   * when it's between 0(included) amd nChoices(excluded). Print "invalid input"
   * and ask user to input again when the input is not within the range.
   * 
   * @param nChoices an int, # of choices shown in view
   * @return an int, the valid input of user
   */
  @Deprecated
  public int validateInput(int nChoices) {
    // Use scanner for input
    Scanner scanner = new Scanner(System.in);
    // the input value as valid integer
    int intInput = -1;
    // infinite loop which break when the input is validated
    boolean isInvalidInput = true;
    while (isInvalidInput) {
      // use nextLine() rather than nextLine(nextInt) to exclude exception raised
      String stringInput = scanner.nextLine();
      // if the input cannot be converted to int, then it's invalid
      try {
        intInput = Integer.parseInt(stringInput);
      } catch (NumberFormatException e) {
      }
      // When input can be converted to integer & input is within acceptable range
      if (intInput > 0 && intInput <= nChoices) {
        System.out.println();
        // scanner.close();
        return intInput;
      }
      System.out.println("Invalid input. Please enter a number between 1 to " + Integer.toString(nChoices) + ".");
      System.out.print("Please enter again: ");
    }
    // scanner.close();
    return -1;
  }

  public int validateInt(int lowerBound, int upperBound) {
    // Use scanner for input
    Scanner scanner = new Scanner(System.in);
    // the input value as valid integer
    int intInput = -1;
    // use nextLine() rather than nextLine(nextInt) to exclude exception raised
    String stringInput = scanner.nextLine();
    // if the input cannot be converted to int, then it's invalid
    try {
      intInput = Integer.parseInt(stringInput);
    } catch (NumberFormatException e) {
      intInput = -1;
    }
    // When input can be converted to integer & input is within acceptable range
    if (intInput >= lowerBound && intInput <= upperBound) {
      System.out.println();
      // scanner.close();
      return intInput;
    }
    // scanner.close();
    return -1;
  }

  /**
   * Validate String user input. No validation rule has been implemented yet.
   * 
   * @return a String, the input String
   */
  public String validateString() {
    // Use scanner for input
    String input = "";
    Scanner scanner = new Scanner(System.in);
    input = scanner.nextLine();
    return input;
  }

  public Teacher validateTeacher() {
    Scanner scanner = new Scanner(System.in);
    String input = scanner.nextLine();
    ArrayList<Teacher> teachers = this.model.getData().getTeachers();
    for (Teacher teacher : teachers) {
      if (teacher.getName().equals(input)) {
        return teacher;
      }
    }
    return null;
  }

  public Course validateCourse(RequirementList rl) {
    Scanner scanner = new Scanner(System.in);
    String input = scanner.nextLine();
    ArrayList<Course> courses = list.getCourses();
    for (Course course : courses) {
      if (course.getName().equals(input)) {
        return course;
      }
    }
    return null;
  }

  public boolean validateLogin(String username, String password) {
    User user = new User(username, password);
    return UserSystem.login(user);
  }

}