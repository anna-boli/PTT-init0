package views;

import java.util.ArrayList;

import controllers.Validator;
import models.Course;
import models.RequirementList;
import models.users.Teacher;

public class View {

  /**
   * Let user input an integer and pass it to controller.validateInt() for
   * validation.
   */
  public static int inputInt(int lowerBound, int upperBound) {
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
  // public void clearScreen() {
  // try {
  // if (this.os.contains("Windows")) {
  // // Runtime.getRuntime().exec("cls");
  // } else {
  // Runtime.getRuntime().exec("clear");
  // System.out.print("\033[H\033[2J");
  // System.out.flush();
  // }
  // } catch (IOException e) {
  // e.printStackTrace();
  // }
  // }

  // ad print teacher list
  public static void printTeacherList(ArrayList<Teacher> teachers) {
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
    DisplayInfo.click2Continue();
  }

  /**
   * Print teacher's personal info according to the input teacher name
   * 
   * @param teacherName a String, the teacher's username
   */
  public static void printTeacherInfo(Teacher teacher) {
    if (teacher != null) {
      System.out.println("Your name: " + teacher.getName());
      System.out.println("You was trained: " + teacher.isTrained());
    } else {
      System.out.println("The teacher are not register in teacher list in PTT system.");
    }
    DisplayInfo.click2Continue();
  }

  /**
   * Print teacher's courses ArrayList.
   * 
   * @param courses an ArrayList, the courses ArrayList to be printed.
   */
  public static void printTeacherCourse(ArrayList<String> courses) {
    if (courses == null || courses.isEmpty()) {
      System.out.println("You do not have any course.");
    } else {
      System.out.println("Your course(s): ");
      for (String course : courses) {
        System.out.println(course);
      }
    }
    DisplayInfo.click2Continue();
  }

  public static Course inputCourseName(RequirementList rl) {
    System.out.print("Please enter course name: ");
    Course course = null;
    course = Validator.validateCourse(rl);
    if (course != null) {
      return course;
    }
    System.out.println("Cannot find this course in the requirement list.");
    System.out.println("Please try again.");
    return null;
  }

  public static Course inputNewCourse(RequirementList rl) {
    System.out.print("Please enter the new course name: ");
    Course course = null;
    course = Validator.validateExistCourse(rl);
    if (course == null) {
      System.out.println("Course already existed.");
      System.out.println("Please try again.");
    }
    return course;
  }

  public static Teacher inputTeacherName() {
    System.out.print("Please enter teacher name: ");
    Teacher teacher = Validator.validateTeacher();
    if (teacher != null) {
      return teacher;
    }
    System.out.println("Cannot find this teacher.");
    System.out.println("Please try again.");
    return null;
  }

  public static Teacher inputNewTeacher() {
    System.out.print("Please enter teacher name: ");
    Teacher teacher = Validator.validateExistTeacher();
    if (teacher != null) {
      return teacher;
    }
    System.out.println("The input teacher name already existed.");
    System.out.println("Please try again.");
    return null;
  }

  // print specific list
  public static void printSpecificList(RequirementList list) {
    if (list != null) {
      System.out.println(list.readList());
    } else {
      System.out.println("List does not exist.");
    }
    DisplayInfo.click2Continue();
  }

  // print all lists or lists with the same year/semester
  public static void printLists(ArrayList<RequirementList> lists) {
    if (lists != null && lists.size() != 0) {
      for (RequirementList list : lists) {
        System.out.println(list.readList());
      }
      DisplayInfo.click2Continue();
    } else {
      System.out.println("List does not exist.");
      DisplayInfo.click2Continue();
    }
  }

  // print unapproved list
  public static void printUnapprovedList(RequirementList list) {
    if (list != null) {
      System.out.println(list.readList());
    } else {
      System.out.println("No Request.");
    }
    DisplayInfo.click2Continue();
  }

}