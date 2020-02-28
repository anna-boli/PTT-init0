package views;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import controllers.Controller;
import models.Model;
import models.RequirementList;
import models.UserSystem;
import models.users.Teacher;

public class View {
  private Model model;
  private Controller controller;
  private String os = System.getProperty("os.name");
  private boolean userWantToQuit = false;
  private String menuSelect;
  private Scanner s = new Scanner(System.in);
  private int year;
  private int semester;
  private String newCourse;
  private String newteacher;
  private String info;
  private String makeApproval;
  private String guid;
  private String name;

  // constructor
  public View(Model model, Controller controller) {
    this.model = model;
    this.controller = controller;
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
      this.startScreen();
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
      System.out.println("1. Login");
      System.out.println("2. Register");
      System.out.print("Please enter your choice: ");
      int input = this.controller.validateInput(2);
      switch (input) {
        case 1:
          this.loginScreen();
          exitStartScreen = true;
          break;
        case 2:
          this.registration();
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
        isRegisterSuccessfully = UserSystem.register(username, password, role);
      }
      if (!isRegisterSuccessfully) {
        System.out.println("Registration failed. Possible reasons are as follows:");
        System.out.println("1. There is an empty input(s).");
        System.out.println("2. That username is taken. Try another.");
        System.out.println("3. The input role was invalid.");
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
  public String cd_selectMenu() {
    this.clearScreen();
    System.out.println("\n*** CD MENU ***");
    System.out.print("(1)Add new requirement list  (2)Read history  (3)Check approval  (4)Log out\nInput selection: ");
    menuSelect = s.nextLine();
    return menuSelect;
  }

  // cd add course menu
  public String addCourseMenu() {
    System.out.print("\n(1)Add new course  (2)Submit, Input selection: ");
    menuSelect = s.nextLine();
    return menuSelect;
  }

  // cd add course to list
  public String addCourseToList() {
    System.out.print("Input new course name: ");
    this.newCourse = s.nextLine();
    return this.newCourse;
  }

  // ptt selectMenu
  public String ptt_selectMenu() {
    this.clearScreen();
    System.out.println("\n*** PTT MENU ***");
    System.out.print("(1)Check request  (2)Read history  (3)Log out\nInput selection: ");
    menuSelect = s.nextLine();
    return menuSelect;
  }

  // PTT make approval select (y/n)
  public String makeApproval() {
    System.out.print("Do you want to make approval to this list? (y/n): ");
    makeApproval = s.nextLine();
    return makeApproval;
  }

  // ad keep setting teacher to course?
  public String keepSetting() {
    System.out.print("Do you want to set another teacher to course? (y/n): ");
    makeApproval = s.nextLine();
    return makeApproval;
  }

  // ad selectMenu
  public String ad_selectMenu() {
    this.clearScreen();
    System.out.println("\n*** Administrator MENU ***");
    System.out.print(
        "(1)Set teacher to course  (2)Read history  (3)Read teacher list (4)Add new teacher (5)Log out (6) Train teacher\nInput selection: ");
    menuSelect = s.nextLine();
    return menuSelect;
  }

  // ad print teacher list
  public void printTeacherList(ArrayList<Teacher> teachers) {
    System.out.println("\nTeacher list: ");
    System.out.println("Guid\tName\t Status\t\tCourses");
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
      if (teachers.get(i).isHasClass()) {
        courseStatus = "Has Class";
      } else {
        courseStatus = "No Class";
      }
      printList = String.format("%s\t%s", teachers.get(i).get_Guid(), teachers.get(i).getName());
      System.out.print(printList);
      System.out.println("\t " + status + "\t" + courseStatus);
    }
  }

  // teacher selectMenu
  public String t_selectMenu() {
    this.clearScreen();
    System.out.println("\n*** Teacher MENU ***");
    System.out.print("(1)Check self information  (2)Check course  (3)Log out\nInput selection: ");
    menuSelect = s.nextLine();
    return menuSelect;
  }

  // print teacher info
  public void printinfo(String teacherGuid, String teacherName) {
    System.out.println("\nGUID: " + teacherGuid);
    System.out.println("\nYour Name: " + teacherName);
    System.out.println("\nYou was trained: " + model.teacherTrained(teacherGuid));
  }

  // read teacher courses
  public void printTeacherCourse(String teacherCourses) {
    System.out.println("\nThe courses you have: " + teacherCourses);
  }

  // read list menu
  public String readListMenu() {
    System.out.print(
        "\n(1)Read specific list  (2)Read same year lists  (3)Read same semester lists  (4)Read all lists\nInput selection: ");
    menuSelect = s.nextLine();
    return menuSelect;
  }

  // input year
  public int invalidYear() {
    System.out.print("Please enter year: ");
    this.year = s.nextInt();
    s.nextLine();
    Boolean valid = false;
    while (!valid) {
      if (year <= 0 || String.valueOf(year).length() != 4) {
        this.year = s.nextInt();
        valid = false;
        System.out.println("invalid input.");
        System.out.print("Please enter year: ");
      } else {
        valid = true;
      }
    }
    return this.year;
  }

  // input semsester
  public int invalidSemester() {
    System.out.print("Please enter semester: ");
    this.semester = s.nextInt();
    s.nextLine();
    return this.semester;
  }

  // input guid
  public String invalidGuid() {
    System.out.print("Please enter course guid: ");
    this.guid = s.nextLine();
    return this.guid;
  }

  // input teacher name // add teacher to list
  public String invalidName() {
    System.out.print("Please enter the teacher's name: ");
    Boolean invalid = false;
    while (!invalid) {
      this.name = s.nextLine();
      if (name.length() <= 7) {
        invalid = true;
      } else {
        System.out.println("Name should be less than seven characters.");
        System.out.print("Please enter the teacher's name: ");
      }
    }
    return this.name;
  }

  // **************** text ****************

  public void ListisBuild() {
    System.out.print("\n\n<< Build requirement list - " + year + ", semester " + semester + " >>\n");
  }

  // print specific list
  public void printSpecificList(RequirementList list) {
    if (list != null) {
      System.out.println(list.readList());
    } else {
      System.out.println("\nList does not exist.");
    }
  }

  // print all lists or lists with the same year/semester
  public void printLists(ArrayList<RequirementList> lists) {
    if (lists.size() != 0) {
      for (RequirementList list : lists) {
        System.out.println(list.readList());
      }
    } else {
      System.out.println("\nList does not exist.");
    }
  }

  // print unapproved list
  public void printUnapprovedList(RequirementList list) {
    if (list != null) {
      System.out.println(list.readList());
    } else {
      System.out.println("\nNo Request.");
    }
  }

  public void text_invalidInput() {
    System.out.println("Invalid input!");
  }

  public void text_submitList() {
    System.out.println("List is submitted!");
  }

  public void text_noRequest() {
    System.out.println("No requests");
  }

  public void text_logOut() {
    System.out.println("Log out successfully.\n");
    this.click2Continue();
    this.startScreen();
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