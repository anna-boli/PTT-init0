package controllers;

import java.util.ArrayList;
import java.util.Scanner;

import models.Course;
import models.Model;
import models.RequirementList;
import models.UserSystem;
import models.users.Teacher;
import models.users.User;
import views.DisplayInfo;
import views.MenuView;
import views.View;

public class Controller {
  private Model model;
  private View view;

  public Controller(Model model) {
    this.model = model;
  }

  /**
   * Add view into controller
   * 
   * @param view a View, the view in the MVC pattern
   */
  public void addView(View view) {
    this.view = view;
    this.initialize();
  }

  /**
   * Initialize Validator and MenuController.
   */
  public void initialize() {
    new Validator(this.model);
    new MenuController(this.model, this);
  }

  // after cd log in
  public void courseDirectorLogin() {
    DisplayInfo.courseDirectorLogin(); // print welcome to cd page
    MenuController.courseDirectorMainMenu();
  }

  // after ptt log in
  public void pttDirectorLogin() {
    DisplayInfo.pttDirectorLogin();
    MenuController.pttDirectorMainMenu();
  }

  // after administrator log in
  public void administratorLogin() {
    DisplayInfo.administratorLogin();
    MenuController.administratorMainMenu();
  }

  // after teacher log in
  public void teacherLogin() {
    DisplayInfo.teacherLogin();
    MenuController.teacherMainMenu();
  }

  /**
   * Create new teacher according to user input.
   */
  public void addNewTeacher() {
    System.out.print("Please enter teacher name: ");
    Scanner scanner = new Scanner(System.in);
    String stringInput = scanner.nextLine();
    Teacher teacher = View.inputTeacherName();
    if (teacher == null) {
      teacher = new Teacher(stringInput, stringInput, stringInput);
      this.model.getData().getTeachers().add(teacher);
      UserSystem.addUser((User) teacher);
      DisplayInfo.newTeacher(teacher.getName());
    } else {
      DisplayInfo.teacherExisted();
    }

  }

  // cd_ main menu select 1 - add list
  public void createList() {
    RequirementList list = Validator.validateRequirementList();
    if (!list.isNew()) {
      DisplayInfo.requirementListExist();
    } else {
      DisplayInfo.buildingRequirementList(list);
      list.setNew(false);
      this.model.getData().addToData(list);
      this.addCourseMenu(list);
    }

  }

  // cd add course menu
  public void addCourseMenu(RequirementList list) {
    Boolean keepAdding = true;
    while (keepAdding) {
      int menuSelect = MenuView.addCourseMenu();
      switch (menuSelect) {
        // Add new course to this requirement list
        case 1:
          Course course = View.inputNewCourse(list);
          if (course != null) {
            this.model.addCourseToList(list, course);
            DisplayInfo.courseAdded();
          } else {
            DisplayInfo.courseExist();
          }
          break;

        // Submit this requirement list
        case 2:
          DisplayInfo.submitList();
          keepAdding = false;
          break;

        // When input is not between 1 - 2
        default:
          DisplayInfo.invalidInput();
          break;
      }
    }
  }

  // ptt approval menu (y/n)
  public void approve() {
    Boolean approval = false;
    ArrayList<RequirementList> lists = this.model.getUnapprovedLists();
    if (lists.isEmpty()) {
      DisplayInfo.requirementListAllApproved();
      return;
    }
    if (lists.size() != 0) {
      System.out.println("Input year and semester to select list.");
      // DisplayInfo.ask2InputYear();
      // int year = view.inputInt(GlobalVariable.MIN_YEAR, GlobalVariable.MAX_YEAR);
      // // input year
      // DisplayInfo.ask2InputSemester();
      // int semester = view.inputInt(GlobalVariable.MIN_SEMESTER,
      // GlobalVariable.MAX_SEMESTER); // input semester
      // RequirementList list = model.validateSpecificList(year, semester);
      RequirementList list = Validator.validateRequirementList();
      if (list == null) {
        DisplayInfo.requirementListNotExist();
        return;
      }
      if (list.getApproval()) {
        DisplayInfo.requirementListIsApproved();
        return;
      }
      System.out.println("You have selected the following list.");
      DisplayInfo.makeApproval();
      View.printUnapprovedList(list);
      Scanner s = new Scanner(System.in);
      String makeApproval = s.nextLine();
      while (!approval) {
        if (makeApproval.equals("y")) {
          model.setApproval(list);
          System.out.println("You have set << " + list.getYear() + " semester " + list.getSemester()
              + " Requirement List >> to approved.");
          approval = true;
        } else if (makeApproval.equals("n")) {
          approval = true;
        } else {
          DisplayInfo.invalidInput();
          approval = true;
        }
      }
    } else {
      DisplayInfo.noRequest();
    }
  }

  /**
   * 
   */
  public void checkRequest() {
    ArrayList<RequirementList> lists = model.getUnapprovedLists();
    if (lists.isEmpty()) {
      System.out.println("No unapproved lists exist.");
    } else {
      System.out.println("The following are the unapproved lists:");
      view.printLists(lists);
    }
  }

}