package views;

import controllers.Controller;
import models.Model;

public class DisplayInfo {
  private static Model model;
  private static Controller controller;
  private static View view;

  public DisplayInfo(Model model, Controller controller, View view) {
    DisplayInfo.model = model;
    DisplayInfo.controller = controller;
    DisplayInfo.view = view;
  }

  public static void ask2InputYear() {
    System.out.print("Please enter year: ");
  }

  public static void ask2InputSemester() {
    System.out.print("Please enter semester: ");
  }

  public static void ask2SetTeacher() {
    System.out.println("Input course name and teacher's name to set teacher to course.");
  }

  public static void requirementListIsApproved() {
    System.out.println("The chosen requirement list has been approved.");
  }

  public static void requirementListAllApproved() {
    System.out.println("All requirement list have been approved.");
  }

  public static void requirementListNotApproved() {
    System.out.println("The chosen requirement list has not been approved.");
    DisplayInfo.view.click2Continue();
  }

  public static void requirementListExist() {
    System.out.println("The chosen requirement list already exists.");
    DisplayInfo.view.click2Continue();
  }

  public static void requirementListNotExist() {
    System.out.println("The chosen requirement list does not exists.");
    DisplayInfo.view.click2Continue();
  }

  public static void teacherNotExists() {
    System.out.println("The chosen teacher does not exists.");
    DisplayInfo.view.click2Continue();
  }
  public static void courseExist(){
    System.out.println("The chosen course existed.");
  }

  public static void teacherNotTrained() {
    System.out.println("The chosen teacher has not been trained, so the teacher cannot be assigned to courses.");
    DisplayInfo.view.click2Continue();
  }

  public static void newTeacher(String teacherName) {
    System.err.println(String.format("The new teacher \"%s\" was created.", teacherName));
    DisplayInfo.view.click2Continue();
  }

  public static void trainingCompleted() {
    System.out.println("The teacher has been trained.");
    DisplayInfo.view.click2Continue();
  }

  public static void buildingRequirementList(int year, int semester) {
    System.out.println("<< Building requirement list - " + year + ", semester " + semester + " >>");
  }

  public static void trinedSuccessfully() {
    System.out.println("The teacher has been trained.");
  }

  public static void invalidInput() {
    System.out.println("Invalid input. Please try again.");
    DisplayInfo.view.click2Continue();
  }

  public static void submitList() {
    System.out.println("List is submitted!");
    DisplayInfo.view.click2Continue();

  }

  public static void text_noRequest() {
    System.out.println("No requests");
    DisplayInfo.view.click2Continue();

  }

  /**
   * Print logout and saving info.
   */
  public static void logout() {
    System.out.println("Logout successfully.");
    System.out.println("The data are saved.");
    DisplayInfo.view.click2Continue();
  }

  public static void courseDirectorLogin() {
    System.out.println("<< Course Director was logged in >>");
  }

  public static void text_pttLogin() {
    System.out.println("<< PTT Director was logged in >>");
  }

  public static void text_adLogin() {
    System.out.println("<< Administrator was logged in >>");
  }

  public static void text_tLogin() {
    System.out.println("<< Teacher was logged in >>");
  }
}