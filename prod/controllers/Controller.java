package controllers;

import java.util.ArrayList;
import java.util.Scanner;

import models.Course;
import models.Model;
import models.RequirementList;
import models.UserSystem;
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
  private String guid;
  private String teacherName;
  private String teacherCourses;

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
      menuSelect = view.cd_selectMenu(); // show cd main menu and input option
      if (menuSelect.equals("1")) {
        cd_createList();
      } else if (menuSelect.equals("2")) {
        readList();
      } else if (menuSelect.equals("3")) {
        checkRequest();
        lists.clear();
        // System.out.println("check approval not yet");
      } else if (menuSelect.equals("4")) {
        view.text_logOut();
        selected = true;
      } else {
        view.text_invalidInput();
      }
    }
  }

  // cd add course menu
  public void cd_addCourseMenu() {
    Boolean keepAdding = false;
    while (!keepAdding) {
      this.menuSelect = view.addCourseMenu();
      if (menuSelect.equals("1")) {
        newCourse = view.addCourseToList();
        model.addCourseToList(newCourse);
      } else if (menuSelect.equals("2")) {
        view.text_submitList();
        keepAdding = true;
      } else {
        view.text_invalidInput();
      }
    }
  }

  // ad add new teacher
  public void ad_addNewTeacher() {
    newTeacher = view.invalidName(); // input teacher's name
    model.addTeacherToList(newTeacher);
  }

  // cd_ main menu select 1 - add list
  public void cd_createList() {
    this.year = view.invalidYear(); // input year
    this.semester = view.invalidSemester(); // input semester
    view.ListisBuild(); // print list title
    model.createRequirementList(year, semester);
    // System.out.println("list is build");
    cd_addCourseMenu();
  }

  // PTT main menu
  public void ptt_mainMenu() {
    Boolean selected = false;
    while (!selected) {
      menuSelect = view.ptt_selectMenu(); // show cd main menu and input option
      if (menuSelect.equals("1")) {
        // check request
        checkRequest();
        ptt_toApprove();
        lists.clear();
        // System.out.println("check request not yet");
      } else if (menuSelect.equals("2")) {
        readList();
      } else if (menuSelect.equals("3")) {
        view.text_logOut();
        selected = true;
      } else {
        view.text_invalidInput();
      }
    }
  }

  // ptt approval menu (y/n)
  public void ptt_toApprove() {
    Boolean approval = false;
    if (lists.size() != 0) {
      System.out.println("\nInput year and semester to select list.");
      this.year = view.invalidYear(); // input year
      this.semester = view.invalidSemester(); // input semester
      this.list = model.getSpecificList(year, semester);
      System.out.println("\nYou have selected the following list.");
      view.printUnapprovedList(list);
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
      menuSelect = view.ad_selectMenu();
      if (menuSelect.equals("1")) {
        System.out.println("Set teacher to course"); // // loop - keep setting teacher to course menu
        System.out.println("Print specific list");
        this.year = view.invalidYear(); // input year
        this.semester = view.invalidSemester(); // input semester
        list = model.getSpecificList(year, semester);
        view.printSpecificList(list);
        // teachers = model.getTeacherList(); // ask if need to print teacher list?
        System.out.println("Input course ID and teacher's name to set teacher to course.");
        guid = view.invalidGuid();
        teacherName = view.invalidName();
        model.setTeacherToCourse(list, guid, teacherName);
        System.out.println("Is set.");

      } else if (menuSelect.equals("2")) {
        readList();
      } else if (menuSelect.equals("3")) {
        System.out.println("show teacher list");
        // teachers = model.getTeacherList();
        view.printTeacherList(teachers);
      } else if (menuSelect.equals("4")) {
        ad_addNewTeacher();
      } else if (menuSelect.equals("5")) {
        view.text_logOut();
        selected = true;
      } else {
        view.text_invalidInput();
      }
    }
  }

  // ad set teacher - ad main menu select 1
  public void ad_setTeacher() {

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
    String teacherGuid = null;// get from teacher list
    String teacherName = null;// get from teacher list
    while (!selected) {
      menuSelect = view.t_selectMenu(); // show teacher main menu and input option
      if (menuSelect.equals("1")) {
        view.printinfo(teacherGuid, teacherName);
      } else if (menuSelect.equals("2")) {
        System.out.println("NOT YET");
        this.year = view.invalidYear(); // input year
        this.semester = view.invalidSemester(); // input semester
        teacherCourses = model.getTeacherCourse(year, semester, teacherName);
        view.printTeacherCourse(teacherCourses);
      } else if (menuSelect.equals("3")) {
        view.text_logOut();
        selected = true;
      } else {
        view.text_invalidInput();
      }
    }
  }

  // get unapproval lists ( PTT _ main menu select 1 or cd main menu select 3)
  public void checkRequest() {
    System.out.println("The following are the unapproved lists\n");
    this.lists = model.getUnapprovedLists();
    view.printLists(lists);
  }

  // read list
  public void readList() {
    Boolean selected = false;
    while (!selected) {
      menuSelect = view.readListMenu(); // show menu & input option
      if (menuSelect.equals("1")) {
        this.year = view.invalidYear(); // input year
        this.semester = view.invalidSemester(); // input semester
        list = model.getSpecificList(year, semester);
        view.printSpecificList(list);
        selected = true;
      } else if (menuSelect.equals("2")) {
        this.year = view.invalidYear(); // input year
        lists = model.getSameYearLists(year);
        view.printLists(lists);
        selected = true;
      } else if (menuSelect.equals("3")) {
        this.semester = view.invalidSemester(); // input semester
        lists = model.getSameSemesterLists(semester);
        view.printLists(lists);
        selected = true;
      } else if (menuSelect.equals("4")) {
        lists = model.getAllLists();
        view.printLists(lists);
        selected = true;
      } else {
        view.text_invalidInput();
      }
    }
    lists.clear();
  }

  /**
   * Scan input and validate it according to # of choices. The choices is valid
   * when it's between 0(included) amd nChoices(excluded). Print "invalid input"
   * and ask user to input again when the input is not within the range.
   * 
   * @param nChoices an int, # of choices shown in view
   * @return an int, the valid input of user
   */
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
        scanner.close();
        return intInput;
      }
      System.out.println("Invalid input. Please enter a number between 1 to " + Integer.toString(nChoices) + ".");
      System.out.print("Please enter your choice again: ");
    }
    scanner.close();
    return -1;
  }

  public boolean validateLogin(String username, String password) {
    User user = new User(username, password);
    return UserSystem.login(user);
  }

}