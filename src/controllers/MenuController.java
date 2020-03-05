package controllers;

import java.util.ArrayList;

import models.Course;
import models.Model;
import models.RequirementList;
import models.UserSystem;
import models.database.Database;
import models.users.Teacher;
import views.DisplayInfo;
import views.MenuView;
import views.View;

public class MenuController {
  private static Model model;
  private static Controller controller;

  public MenuController(Model model, Controller controller) {
    MenuController.model = model;
    MenuController.controller = controller;
  }

  public static void courseDirectorMainMenu() {
    Boolean selected = false;
    while (!selected) {
      int menuSelect = MenuView.courseDirectorMenu(); // show cd main menu and input option
      switch (menuSelect) {
        // Create new requirement list
        case 1:
          MenuController.controller.createList();
          break;

        // Print requirement list
        case 2:
          MenuController.readList();
          break;

        case 3:
          MenuController.controller.checkRequest();
          break;

        // Log out and save to database
        case 4:
          Database.save(MenuController.model);
          DisplayInfo.logout();
          selected = true;
          break;

        // When input is not between 1 - 4
        default:
          DisplayInfo.invalidInput();
          break;
      }
    }
  }

  /**
   * Administrator's main menu (Controller)
   */
  public static void administratorMainMenu() {
    Boolean selected = false;
    Teacher teacher;
    ArrayList<Teacher> teachers = model.getTeacherList();
    RequirementList list;
    while (!selected) {
      int menuSelect = MenuView.administratorMenu();
      switch (menuSelect) {
        // Add new requirement list
        case 1:
          list = Validator.validateRequirementList();
          if (list.isNew()) {
            DisplayInfo.requirementListNotExist();
            break;
          }
          if (!list.getApproval()) {
            DisplayInfo.requirementListNotApproved();
            break;
          }
          View.printSpecificList(list);
          Course course = View.inputCourseName(list);
          if (course == null) {
            break;
          }
          teacher = View.inputTeacherName();
          if (teacher == null) {
            break;
          }
          if (!teacher.isTrained()) {
            DisplayInfo.teacherNotTrained();
            break;
          }
          course.setTeacher(teacher);
          teacher.addCourse(list.getYear(), list.getSemester(), course);
          DisplayInfo.trinedSuccessfully();
          break;

        // Show requirement list
        case 2:
          MenuController.readList();
          break;

        // Show teacher's list
        case 3:
          System.out.println("show teacher list");
          View.printTeacherList(teachers);
          break;

        // Add/Create new teacher
        case 4:
          MenuController.controller.addNewTeacher();
          break;

        // Train teacher
        case 5:
          teacher = View.inputTeacherName();
          if (teacher != null) {
            MenuController.model.trainTeacher(teacher);
            DisplayInfo.trainingCompleted();
          } else {
            DisplayInfo.teacherNotExists();
            DisplayInfo.click2Continue();
          }
          break;

        // Log out and save to database
        case 6:
          Database.save(MenuController.model);
          DisplayInfo.logout();
          selected = true;
          break;

        // When input is not between 1 - 6
        default:
          DisplayInfo.invalidInput();
          break;
      }
    }
  }

  /**
   * Teacher's main menu (Controller)
   */
  public static void teacherMainMenu() {
    Boolean selected = false;
    Teacher teacher;
    while (!selected) {
      int menuSelect = MenuView.teacherSelectMenu(); // show teacher main menu and input option
      switch (menuSelect) {
        // Check the teacher's personal information
        case 1:
          String userName = UserSystem.getCurrentUser().getUserName();
          teacher = MenuController.model.getData().getTeacher(userName);
          View.printTeacherInfo(teacher);
          break;

        // Print the teacher's courses
        case 2:
          String teacherName = UserSystem.getCurrentUser().getUserName();
          teacher = MenuController.model.getData().getTeacher(teacherName);
          View.printTeacherCourse(teacher.getCourses());
          break;

        // Log out and save to database
        case 3:
          Database.save(MenuController.model);
          DisplayInfo.logout();
          selected = true;
          break;

        // When input is not between 1 - 3
        default:
          DisplayInfo.invalidInput();
          break;
      }
    }
  }

  /**
   * PTT Director's main menu (Controller)
   */
  public static void pttDirectorMainMenu() {
    Boolean selected = false;
    while (!selected) {
      int menuSelect = MenuView.pttDirectorMenu(); // show cd main menu and input option
      switch (menuSelect) {

        // Show unapproved list and approve the chosen requirement list
        case 1:
          MenuController.controller.checkRequest();
          MenuController.controller.approve();
          break;

        // Print requirement list
        case 2:
          MenuController.readList();
          break;

        // Log out and save to database
        case 3:
          Database.save(MenuController.model);
          DisplayInfo.logout();
          selected = true;
          break;

        // When input is not between 1 - 3
        default:
          DisplayInfo.invalidInput();
          break;
      }
    }
  }

  // read list
  public static void readList() {
    Boolean selected = false;
    int menuSelect;
    ArrayList<RequirementList> lists;
    RequirementList list;
    while (!selected) {
      menuSelect = MenuView.readListMenu(); // show menu & input option
      switch (menuSelect) {
        // Print requirement list according to input year and semester
        case 1:
          list = Validator.validateRequirementList();
          if (list.isNew()) {
            DisplayInfo.requirementListNotExist();
            break;
          }
          View.printSpecificList(list);
          selected = true;
          break;

        // Print all requirement lists
        case 2:
          lists = model.getData().getData();
          View.printLists(lists);
          selected = true;
          break;

        // When input is not between 1 - 2
        default:
          DisplayInfo.invalidInput();
          break;
      }

    }

  }
}