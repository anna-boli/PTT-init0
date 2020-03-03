package controllers;

import java.util.ArrayList;
import java.util.Scanner;

import models.Course;
import models.GlobalVariable;
import models.Model;
import models.RequirementList;
import models.UserSystem;
import models.users.Teacher;
import models.users.User;
import views.DisplayInfo;
import views.View;

public class Validator {
  private static Model model;
  private static View view;

  public Validator(Model model, View view) {
    Validator.model = model;
    Validator.view = view;
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
  public static int validateInput(int nChoices) {
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

  public static int validateInt(int lowerBound, int upperBound) {
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
  @Deprecated
  public static String validateString() {
    // Use scanner for input
    String input = "";
    Scanner scanner = new Scanner(System.in);
    input = scanner.nextLine();
    return input;
  }

  public static Teacher validateTeacher() {
    Scanner scanner = new Scanner(System.in);
    String input = scanner.nextLine();
    ArrayList<Teacher> teachers = Validator.model.getData().getTeachers();
    for (Teacher teacher : teachers) {
      if (teacher.getName().equals(input)) {
        return teacher;
      }
    }
    return null;
  }

  public static boolean isRequirementListExist(int year, int semester) {
    ArrayList<RequirementList> lists = Validator.model.getData().getData();
    for (RequirementList list : lists) {
      if (list.getYear() == year && list.getSemester() == semester) {
        return true;
      }
    }
    return false;
  }

  public static Course validateCourse(RequirementList rl) {
    Scanner scanner = new Scanner(System.in);
    String input = scanner.nextLine();
    ArrayList<Course> courses = rl.getCourses();
    for (Course course : courses) {
      if (course.getName().equals(input)) {
        return course;
      }
    }
    return null;
  }

  public static boolean validateLogin(String username, String password) {
    User user = new User(username, password);
    return UserSystem.login(user);
  }

  public static RequirementList validateRequirementList(int MIN_YEAR, int MAX_YEAR, int MIN_SEMESTER,
      int MAX_SEMESTER) {
    DisplayInfo.ask2InputYear();
    int year = view.inputInt(GlobalVariable.MIN_YEAR, GlobalVariable.MAX_YEAR); // input year
    DisplayInfo.ask2InputSemester();
    int semester = view.inputInt(GlobalVariable.MIN_SEMESTER, GlobalVariable.MAX_SEMESTER); // input semester
    ArrayList<RequirementList> lists = Validator.model.getData().getData();
    for (RequirementList list : lists) {
      if (list.getYear() == year && list.getSemester() == semester) {
        return list;
      }
    }
    return null;
  }
}