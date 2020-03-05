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
    Teacher teacher = View.inputNewTeacher();
    if (teacher != null) {
      this.model.getData().getTeachers().add(teacher);
      UserSystem.addUser((User) teacher);
      DisplayInfo.newTeacher(teacher.getName());
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
    Boolean done = false;
    ArrayList<RequirementList> lists = this.model.getUnapprovedLists();
    if (lists.isEmpty()) {
      DisplayInfo.requirementListAllApproved();
      return;
    }
    if (lists.size() != 0) {
      DisplayInfo.ask2approve();
      RequirementList list = Validator.validateRequirementList();
      if (list.isNew()) {
        DisplayInfo.requirementListNotExist();
        return;
      }
      if (list.getApproval()) {
        DisplayInfo.requirementListIsApproved();
        return;
      }
      // DisplayInfo.approveRequirementList(list);
      DisplayInfo.makeApproval();
      // View.printUnapprovedList(list);
      Scanner s = new Scanner(System.in);
      String makeApproval = s.nextLine();
      while (!done) {
        if (makeApproval.equals("y")) {
          this.model.setApproval(list);
          DisplayInfo.approved();
          done = true;
        } else if (makeApproval.equals("n")) {
          done = true;
        } else {
          DisplayInfo.invalidInput();
          done = true;
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