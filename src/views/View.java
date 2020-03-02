package views;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import controllers.Controller;
import models.Course;
import models.Model;
import models.RequirementList;
import models.users.Teacher;

public class View {
  private Model model;
  private Controller controller;
  private String os = System.getProperty("os.name");
  private boolean userWantToQuit = false;
  private String menuSelect;
  private int year;
  private int semester;
  private String newCourse;
  private String newteacher;
  private String info;
  private String makeApproval;
  private String name;

  // constructor
  public View(Model model, Controller controller) {
    this.model = model;
    this.controller = controller;
  }

  /**
   * Let user input an integer and pass it to controller.validateInt() for
   * validation.
   */
  public int inputInt(int lowerBound, int upperBound) {
    int input = -1;
    do {
      input = this.controller.validateInt(lowerBound, upperBound);
      this.backOneLine();
      if (input != -1) {
        break;
      }
      System.out
          .println(String.format("Invalid input. Please enter a number between %d to %d.", lowerBound, upperBound));
      System.out.print("Please enter your choice again: ");
    } while (input == -1);
    return input;
  }

  /**
   * Let user input string without validation.
   */
  public String inputString() {
    String input = this.controller.validateString();
    // this.backOneLine();
    return input;
  }

  /**
   * Clear console screen according to the current running OS.
   */
  public void clearScreen() {
    try {
      if (this.os.contains("Windows")) {
        Runtime.getRuntime().exec("cls");
      } else {
        Runtime.getRuntime().exec("clear");
        System.out.print("\033[H\033[2J");
        System.out.flush();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void loginScreen() {
    String username, password;
    this.clearScreen();
    Scanner scanner = new Scanner(System.in);
    System.out.println("----------------------------------------");
    System.out.println("Login to PTT system");
    System.out.println("----------------------------------------");
    while (true) {
      System.out.print("Login required. Please enter username: ");
      username = scanner.nextLine();
      System.out.print("Please enter your password: ");
      password = scanner.nextLine();
      if (this.controller.validateLogin(username, password)) {
        System.out.println("Login successfully.");
        this.click2Continue();
        break;
      }
      System.out.println("Invalid user information. Please try again.");
      this.click2Continue();
      break;
    }
    // scanner.close();
  }

  public void startScreen() {
    boolean exitStartScreen = false;
    do {
      this.clearScreen();
      System.out.println("----------------------------------------");
      System.out.println("Welcome to PTT system");
      System.out.println("----------------------------------------");
      System.out.println("(1) Login");
      System.out.println("(2) Register");
      System.out.print("Please enter your choice: ");
      int input = this.controller.validateInt(1, 2);
      switch (input) {
        case 1:
          this.loginScreen();
          exitStartScreen = true;
          break;
        case 2:
          this.registration();
          break;
        default:
          this.text_invalidInput();
          break;
      }
    } while (!exitStartScreen);
  }

  public void registration() {
    String username, password, role;
    boolean isRegisterSuccessfully = false;
    Scanner scanner = new Scanner(System.in);
    while (!isRegisterSuccessfully) {
      this.clearScreen();
      System.out.println("----------------------------------------");
      System.out.println("Sign up for PTT");
      System.out.println("----------------------------------------");
      System.out.print("Please enter username: ");
      username = scanner.nextLine();
      System.out.print("Please enter your password: ");
      password = scanner.nextLine();
      System.out.print("Please enter your role in PTT: ");
      role = scanner.nextLine();

      if (username.equals("") || password.equals("") || role.equals("")) {
        isRegisterSuccessfully = false;
      } else {
        isRegisterSuccessfully = this.model.getUserSystem().register(username, password, role);
      }
      if (!isRegisterSuccessfully) {
        System.out.println("Registration failed. Possible reasons are as follows:");
        System.out.println("(1) There is an empty input(s).");
        System.out.println("(2) That username is taken. Try another.");
        System.out.println("(3) The input role was invalid.");
        System.out.println("Please try again.");
        this.click2Continue();
      }
    }
    System.out.println("Register Successfully.");
    this.click2Continue();
  }

  /**
   * Before clean the screen, let user see the current game information and then
   * the user can input anything to continue.
   */
  public void click2Continue() {
    System.out.print("Press ENTER to continue.");
    Scanner scanner = new Scanner(System.in);
    scanner.nextLine();
  }

  // cd selectMenu
  public int cd_selectMenu() {
    this.clearScreen();
    System.out.println("----------------------------------------");
    System.out.println("Class Director menu");
    System.out.println("----------------------------------------");
    System.out.println("(1) Add new requirement list");
    System.out.println("(2) Read history");
    System.out.println("(3) Check approval  ");
    System.out.println("(4) Log out");
    System.out.print("Input selection: ");
    return this.inputInt(1, 4);
  }

  // cd add course menu
  public int addCourseMenu() {
    System.out.println("(1) Add new course");
    System.out.println("(2) Submit");
    System.out.print("Input selection: ");
    return this.inputInt(1, 2);
  }

  // cd add course to list
  public String addCourseToList() {
    System.out.print("Input new course name: ");
    Scanner s = new Scanner(System.in);
    this.newCourse = s.nextLine();
    return this.newCourse;
  }

  public void backOneLine() {
    System.out.print("\033[F");
  }

  // ptt selectMenu
  public int ptt_selectMenu() {
    this.clearScreen();
    System.out.println("----------------------------------------");
    System.out.println("PTT Director menu");
    System.out.println("----------------------------------------");
    System.out.println("(1) Check request");
    System.out.println("(2) Read history");
    System.out.println("(3) Log out");
    System.out.print("Input selection: ");
    return this.inputInt(1, 3);
  }

  // PTT make approval select (y/n)
  public String makeApproval() {
    System.out.print("Do you want to make approval to this list? (y/n): ");
    Scanner s = new Scanner(System.in);
    makeApproval = s.nextLine();
    return makeApproval;
  }

  // ad keep setting teacher to course?
  public String keepSetting() {
    System.out.print("Do you want to set another teacher to course? (y/n): ");
    Scanner s = new Scanner(System.in);
    makeApproval = s.nextLine();
    return makeApproval;
  }

  // ad selectMenu
  public int ad_selectMenu() {
    this.clearScreen();
    System.out.println("----------------------------------------");
    System.out.println("Administrator menu");
    System.out.println("----------------------------------------");
    System.out.println("(1) Set teacher to course");
    System.out.println("(2) Read history");
    System.out.println("(3) Read teacher list");
    System.out.println("(4) Add new teacher");
    System.out.println("(5) Train teacher");
    System.out.println("(6) Log out");
    System.out.print("Input selection: ");
    return this.inputInt(1, 6);
  }

  // ad print teacher list
  public void printTeacherList(ArrayList<Teacher> teachers) {
    System.out.println("Teacher list: ");
    System.out.println("Name\t Status\t\tCourses");
    System.out.println("-----------------------------------------");
    String printList = "";
    String status = "";
    String courseStatus = "";
    for (int i = 0; i < teachers.size(); i++) {
      if (!teachers.get(i).isTrained()) {
        status = "Not Trained";
      } else {
        status = "Trained";
      }
      if (!teachers.get(i).getCourses().isEmpty()) {
        courseStatus = "Has Class";
      } else {
        courseStatus = "No Class";
      }
      printList = String.format("%s", teachers.get(i).getName());
      System.out.print(printList);
      System.out.println("\t " + status + "\t" + courseStatus);
    }
    this.click2Continue();
  }

  // teacher selectMenu
  public int t_selectMenu() {
    this.clearScreen();
    System.out.println("----------------------------------------");
    System.out.println("Teacher menu");
    System.out.println("----------------------------------------");
    System.out.println("(1) Check self information");
    System.out.println("(2) Check course");
    System.out.println("(3) Log out");
    System.out.print("Input selection: ");
    return this.inputInt(1, 3);
  }

  // print teacher info
  public void printinfo(String teacherName) {
    System.out.println("Your Name: " + teacherName);
    System.out.println("You was trained: " + model.teacherTrained(teacherName));
    this.click2Continue();

  }

  // read teacher courses
  public void printTeacherCourse(ArrayList<String> courses) {
    if (courses == null) {
      System.out.println("You do not have any course.");
    } else {
      System.out.println("The courses are as follows: ");
      for (String course : courses) {
        System.out.println(course);
      }
    }
    this.click2Continue();
  }

  // read list menu
  public int readListMenu() {
    System.out.println("(1) Read specific list");
    System.out.println("(2) Read same year lists");
    System.out.println("(3) Read same semester lists");
    System.out.println("(4) Read all lists");
    System.out.print("Input selection: ");
    return this.inputInt(1, 4);
  }

  public Course inputCourseName(RequirementList rl) {
    Course course = null;
    System.out.print("Please enter course name: ");
    do {
      course = this.controller.validateCourse(rl);
      if (course == null) {
        System.out.println("Cannot find this course in the requirement list.");
        System.out.print("Please input again:");
      } else {
        return course;
      }
    } while (course != null);
    return null;
  }

  public Teacher inputTeacherName() {
    Teacher teacher = null;
    System.out.print("Please enter teacher name: ");
    do {
      teacher = this.controller.validateTeacher();
      if (teacher == null) {
        System.out.println("Cannot find this course in the requirement list.");
        System.out.print("Please input again:");
      } else {
        return teacher;
      }
    } while (teacher != null);
    return null;
  }

  public void ask2InputYear() {
    System.out.print("Please enter year: ");
  }

  public void ask2InputSemester() {
    System.out.print("Please enter semester: ");
  }

  public void ask2SetTeacher() {
    System.out.println("Input course name and teacher's name to set teacher to course.");
  }

  // **************** text ****************

  public void ListisBuild() {
    System.out.println("<< Build requirement list - " + year + ", semester " + semester + " >>");
  }

  // print specific list
  public void printSpecificList(RequirementList list) {
    if (list != null) {
      System.out.println(list.readList());
    } else {
      System.out.println("List does not exist.");
    }
    // this.click2Continue();

  }

  // print all lists or lists with the same year/semester
  public void printLists(ArrayList<RequirementList> lists) {
    if (lists.size() != 0) {
      for (RequirementList list : lists) {
        System.out.println(list.readList());
      }
      this.click2Continue();
    } else {
      System.out.println("List does not exist.");
      this.click2Continue();

    }
  }

  // print unapproved list
  public void printUnapprovedList(RequirementList list) {
    if (list != null) {
      System.out.println(list.readList());
    } else {
      System.out.println("No Request.");
    }
    this.click2Continue();
  }

  public void text_invalidInput() {
    System.out.println("Invalid input. Please try again.");
    this.click2Continue();
  }

  public void text_submitList() {
    System.out.println("List is submitted!");
    this.click2Continue();

  }

  public void text_noRequest() {
    System.out.println("No requests");
    this.click2Continue();

  }

  public void text_logOut() {
    System.out.println("Log out successfully.");
    this.click2Continue();
  }

  public void text_cdLogin() {
    System.out.println("----------------------------------------");
    System.out.println("Course Director log in");
    System.out.println("----------------------------------------");
  }

  public void text_pttLogin() {
    System.out.println("----------------------------------------");
    System.out.println("PTT Director log in");
    System.out.println("----------------------------------------");
  }

  public void text_adLogin() {
    System.out.println("----------------------------------------");
    System.out.println("Administrator log in");
    System.out.println("----------------------------------------");
  }

  public void text_tLogin() {
    System.out.println("----------------------------------------");
    System.out.println("Teacher log in");
    System.out.println("----------------------------------------");
  }

}