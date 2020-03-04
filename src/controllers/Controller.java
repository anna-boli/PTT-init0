package controllers;

import java.util.ArrayList;
import java.util.Scanner;

import models.GlobalVariable;
import models.Model;
import models.RequirementList;
import models.users.Teacher;
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
  public void cd_login() {
    DisplayInfo.text_cdLogin(); // print welcome to cd page
    MenuController.cd_MainMenu();
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
          String newCourse = this.view.addCourseToList();
          model.addCourseToList(list, newCourse);
          break;

        case 2:
          DisplayInfo.text_submitList();
          keepAdding = false;
          break;

        default:
          DisplayInfo.text_invalidInput();
          break;
      }
    }
  }

  // ptt approval menu (y/n)
  public void ptt_toApprove() {
    Boolean approval = false;
    ArrayList<RequirementList> lists = this.model.getUnapprovedLists();
    if (lists.size() != 0) {
      System.out.println("Input year and semester to select list.");
      DisplayInfo.ask2InputYear();
      int year = view.inputInt(GlobalVariable.MIN_YEAR, GlobalVariable.MAX_YEAR); // input year
      DisplayInfo.ask2InputSemester();
      int semester = view.inputInt(GlobalVariable.MIN_SEMESTER, GlobalVariable.MAX_SEMESTER); // input semester
      RequirementList list = model.validateSpecificList(year, semester);
      if (list == null) {
        DisplayInfo.requirementListNotExist();
        return;
      }
      System.out.println("You have selected the following list.");
      view.printUnapprovedList(list);
      String makeApproval = this.view.makeApproval();
      while (!approval) {
        if (makeApproval.equals("y")) {
          model.setApproval(list);
          System.out.println("You have set << " + year + " semester " + semester + " Requirement List >> to approved.");
          approval = true;
        } else if (makeApproval.equals("n")) {
          approval = true;
        } else {
          DisplayInfo.text_invalidInput();
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
    System.out.println("The following are the unapproved lists");
    ArrayList<RequirementList> lists = model.getUnapprovedLists();
    if (lists.isEmpty()) {
      System.out.println("No unapproved lists exist.");
    } else {
      view.printLists(lists);
    }
  }

}