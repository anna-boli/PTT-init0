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
  Model model;
  View view;

  Validator validator;
  MenuController menuController;

  public Controller(Model model) {
    this.model = model;
  }

  public void addView(View view) {
    this.view = view;
    this.initialize();
  }

  public void initialize() {
    this.validator = new Validator(model, view);
    this.menuController = new MenuController(model, view, this);
  }

  // after cd log in
  public void courseDirectorLogin() {
    DisplayInfo.courseDirectorLogin(); // print welcome to cd page
    MenuController.courseDirectorMainMenu();
  }

  // after ptt log in
  public void ptt_login() {
    DisplayInfo.text_pttLogin();
    MenuController.ptt_mainMenu();
  }

  // after administrator log in
  public void ad_login() {
    DisplayInfo.text_adLogin();
    MenuController.ad_mainMenu();
  }

  // after teacher log in
  public void t_login() {
    DisplayInfo.text_tLogin();
    MenuController.t_mainMenu();
  }

  // ad add new teacher
  public void ad_addNewTeacher() {
    System.out.print("Please enter teacher name: ");
    Scanner scanner = new Scanner(System.in);
    String stringInput = scanner.nextLine();
    Teacher teacher = new Teacher(stringInput, stringInput, stringInput);
    this.model.getData().getTeachers().add(teacher);
    UserSystem.addUser((User) teacher);
    DisplayInfo.newTeacher(teacher.getName());
  }

  // cd_ main menu select 1 - add list
  public void createList() {
    RequirementList list = Validator.validateRequirementList();
    if (!list.isNew()) {
      DisplayInfo.requirementListExist();
    } else {
      list.setNew(false);
      this.model.getData().addToData(list);
      this.cd_addCourseMenu(list);
    }

  }

  // cd add course menu
  public void cd_addCourseMenu(RequirementList list) {
    Boolean keepAdding = true;
    while (keepAdding) {
      int menuSelect = MenuView.addCourseMenu();
      switch (menuSelect) {
        case 1:
          Course course = Validator.validateCourse(list);
          if (course == null) {
            this.model.addCourseToList(list, course);
          } else {
            DisplayInfo.courseExist();
          }
          break;

        case 2:
          DisplayInfo.submitList();
          keepAdding = false;
          break;

        default:
          DisplayInfo.invalidInput();
          break;
      }
    }
  }

  // ptt approval menu (y/n)
  public void ptt_toApprove() {
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
      view.printUnapprovedList(list);
      String makeApproval = this.view.makeApproval();
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
      DisplayInfo.text_noRequest();
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