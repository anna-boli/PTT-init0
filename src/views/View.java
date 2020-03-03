package views;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import controllers.Controller;
import controllers.Validator;
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
  // private int year;
  // private int semester;
  private String newCourse;
  private String newteacher;
  private String makeApproval;
  private String name;
  private Header header;
  private DisplayInfo displayInfo;
  private MenuView menuView;
  private UserSystemView userSystemView;

  // constructor
  public View(Model model, Controller controller) {
    this.model = model;
    this.controller = controller;
    this.header = new Header(model, controller);
    this.displayInfo = new DisplayInfo(model, controller, this);
    this.menuView = new MenuView(model, controller, this);
    this.userSystemView = new UserSystemView(model, controller, this);
  }

  /**
   * Let user input an integer and pass it to controller.validateInt() for
   * validation.
   */
  public int inputInt(int lowerBound, int upperBound) {
    int input = -1;
    do {
      input = Validator.validateInt(lowerBound, upperBound);
      // this.backOneLine();
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
   * Clear console screen according to the current running OS.
   */
  public void clearScreen() {
    try {
      if (this.os.contains("Windows")) {
        // Runtime.getRuntime().exec("cls");
      } else {
        Runtime.getRuntime().exec("clear");
        System.out.print("\033[H\033[2J");
        System.out.flush();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
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

  // cd add course to list
  public String addCourseToList() {
    System.out.print("Input new course name: ");
    Scanner s = new Scanner(System.in);
    String courseName = s.nextLine();
    return courseName;
  }

  public void backOneLine() {
    System.out.print("\033[F");
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

  // ad print teacher list
  public void printTeacherList(ArrayList<Teacher> teachers) {
    System.out.println("Teacher list: ");
    System.out.println(String.format("%-15s\t%-15s\t%-15s", "Name", "Status", "Courses"));
    System.out.println("-----------------------------------------");
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
      System.out.println(String.format("%-15s\t%-15s\t%-15s", teachers.get(i).getName(), status, courseStatus));

    }
    this.click2Continue();
  }

  // print teacher info
  public void printinfo(String teacherName) {
    System.out.println("Your Name: " + teacherName);
    System.out.println("You was trained: " + model.teacherTrained(teacherName));
    this.click2Continue();

  }

  // read teacher courses
  public void printTeacherCourse(ArrayList<String> courses) {
    if (courses == null || courses.isEmpty()) {
      System.out.println("You do not have any course.");
    } else {
      System.out.println("The courses are as follows: ");
      for (String course : courses) {
        System.out.println(course);
      }
    }
    this.click2Continue();
  }

  public Course inputCourseName(RequirementList rl) {
    Course course = null;
    System.out.print("Please enter course name: ");
    do {
      course = Validator.validateCourse(rl);
      if (course == null) {
        System.out.println("Cannot find this course in the requirement list.");
        System.out.print("Please try again: ");
      } else {
        return course;
      }
    } while (course == null);
    return null;
  }

  public Teacher inputTeacherName() {
    Teacher teacher = null;
    System.out.print("Please enter teacher name: ");
    do {
      teacher = Validator.validateTeacher();
      if (teacher == null) {
        System.out.println("Cannot find this teacher in the requirement list.");
        System.out.print("Please try again: ");
      } else {
        return teacher;
      }
    } while (teacher == null);
    return null;
  }

  // print specific list
  public void printSpecificList(RequirementList list) {
    if (list != null) {
      System.out.println(list.readList());
    } else {
      System.out.println("List does not exist.");
    }
    this.click2Continue();
  }

  // print all lists or lists with the same year/semester
  public void printLists(ArrayList<RequirementList> lists) {
    if (lists != null && lists.size() != 0) {
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

  /**
   * @return the header
   */
  public Header getHeader() {
    return header;
  }

  /**
   * @return the displayInfo
   */
  public DisplayInfo getDisplayInfo() {
    return displayInfo;
  }

  /**
   * @return the menuView
   */
  public MenuView getMenuView() {
    return menuView;
  }

  /**
   * @return the userSystemView
   */
  public UserSystemView getUserSystemView() {
    return userSystemView;
  }

}