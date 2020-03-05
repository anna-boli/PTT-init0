package views;

import java.util.Scanner;

import models.Course;
import models.RequirementList;
import models.users.Teacher;

public class DisplayInfo {
  /**
   * Before clean the screen, let user see the current game information and then
   * the user can input anything to continue.
   */
  public static void click2Continue() {
    System.out.print("Press ENTER to continue.");
    Scanner scanner = new Scanner(System.in);
    scanner.nextLine();
  }

  public static void ask2InputYear() {
    System.out.print("Please enter year: ");
  }

  public static void ask2InputSemester() {
    System.out.print("Please enter semester: ");
  }

  public static void ask2SetTeacher() {
    System.out.println("Input course name and teacher's name to set teacher to course.");
    DisplayInfo.click2Continue();
  }

  public static void requirementListIsApproved() {
    System.out.println("The chosen requirement list has been approved.");
    DisplayInfo.click2Continue();
  }

  public static void requirementListAllApproved() {
    System.out.println("All requirement list have been approved.");
    DisplayInfo.click2Continue();

  }

  public static void requirementListNotApproved() {
    System.out.println("The chosen requirement list has not been approved.");
    DisplayInfo.click2Continue();
  }

  public static void requirementListExist() {
    System.out.println("The chosen requirement list already exists.");
    DisplayInfo.click2Continue();
  }

  public static void courseAdded() {
    System.out.println("The course is added.");
    DisplayInfo.click2Continue();
  }

  public static void requirementListNotExist() {
    System.out.println("The chosen requirement list does not exists.");
    DisplayInfo.click2Continue();
  }

  public static void teacherNotExists() {
    System.out.println("The chosen teacher does not exists.");
    DisplayInfo.click2Continue();
  }

  public static void teacherExisted() {
    System.out.println("The chosen teacher already existed.");
    DisplayInfo.click2Continue();
  }

  public static void courseExist() {
    System.out.println("The chosen course existed.");
    DisplayInfo.click2Continue();
  }

  public static void teacherNotTrained() {
    System.out.println("The chosen teacher has not been trained, so the teacher cannot be assigned to courses.");
    DisplayInfo.click2Continue();
  }

  public static void newTeacher(String teacherName) {
    System.err.println(String.format("The new teacher \"%s\" was created.", teacherName));
    DisplayInfo.click2Continue();
  }

  public static void trainingCompleted() {
    System.out.println("The teacher has been trained.");
    DisplayInfo.click2Continue();
  }

  public static void buildingRequirementList(RequirementList list) {
    System.out.println("<< Building requirement list - " + list.getYear() + ", semester " + list.getSemester() + " >>");
  }

  public static void approveRequirementList(RequirementList list) {
    System.out
        .println("<< Approving requirement list - " + list.getYear() + ", semester " + list.getSemester() + " >>");
  }

  public static void trinedSuccessfully() {
    System.out.println("The teacher has been trained.");
    DisplayInfo.click2Continue();
  }

  public static void ask2InputRequirementList() {
    System.out.println("Please select the requirement list.");
  }

  public static void makeApproval() {
    System.out.print("Do you want to make approval to this list? (y/n): ");
  }

  public static void ask2approve() {
    System.out.println("Please select the requirement list you'd like to approve.");
  }

  public static void approved() {
    System.out.println("The chosen requirement list is approved.");
  }

  public static void invalidInput() {
    System.out.println("Invalid input. Please try again.");
    DisplayInfo.click2Continue();
  }

  public static void submitList() {
    System.out.println("List is submitted!");
    DisplayInfo.click2Continue();

  }

  public static void noRequest() {
    System.out.println("No requests");
    DisplayInfo.click2Continue();

  }

  /**
   * Print logout and saving info.
   */
  public static void logout() {
    System.out.println("Logout successfully.");
    System.out.println("The data are saved.");
    DisplayInfo.click2Continue();
  }

  public static void courseDirectorLogin() {
    System.out.println("<< Course Director was logged in >>");
  }

  public static void pttDirectorLogin() {
    System.out.println("<< PTT Director was logged in >>");
  }

  public static void administratorLogin() {
    System.out.println("<< Administrator was logged in >>");
  }

  public static void teacherLogin() {
    System.out.println("<< Teacher was logged in >>");
  }

  public static void setTeacherSuccessfully(Teacher teacher, Course course) {
    System.out.println(
        String.format("The teacher \"%s\" had been set to course \"%s\".", teacher.getName(), course.getName()));
  }

}