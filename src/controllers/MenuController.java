package controllers;

import java.util.ArrayList;

import models.Course;
import models.GlobalVariable;
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
  private static View view;
  private static Controller controller;

  public MenuController(Model model, View view, Controller controller) {
    MenuController.model = model;
    MenuController.view = view;
    MenuController.controller = controller;
  }

  public static void cd_MainMenu() {
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
          DisplayInfo.text_invalidInput();
          break;
      }
    }
  }

  // ad main menu
  public static void ad_mainMenu() {
    Boolean selected = false;
    ArrayList<Teacher> teachers = model.getTeacherList();
    RequirementList list;
    while (!selected) {
      int menuSelect = MenuView.administratorMenu();
      switch (menuSelect) {
        case 1:
          // System.out.println("Set teacher to course"); // // loop - keep setting
          // teacher to course menu
          // System.out.println("Print specific list");
          DisplayInfo.ask2InputYear();
          int year = view.inputInt(GlobalVariable.MIN_YEAR, GlobalVariable.MAX_YEAR); // input year
          DisplayInfo.ask2InputSemester();
          int semester = view.inputInt(GlobalVariable.MIN_SEMESTER, GlobalVariable.MAX_SEMESTER); // input semester
          list = model.validateSpecificList(year, semester);
          if (list == null) {
            DisplayInfo.requirementListNotExist();
            break;
          }
          if (!list.getApproval()) {
            DisplayInfo.requirementListNotApproved();
            break;
          }
          view.printSpecificList(list);
          // teachers = model.getTeacherList(); // ask if need to print teacher list?
          if (list != null) {
            DisplayInfo.ask2SetTeacher();
            // this.view.ask2InputCourseName();
            // String courseName = this.view.inputString();
            // this.view.ask2InputTeacherName();
            // String teacherName = this.view.inputString();
            Course course = MenuController.view.inputCourseName(list);
            Teacher teacher = MenuController.view.inputTeacherName();
            if (!teacher.isTrained()) {
              DisplayInfo.teacherNotTrained();
              break;
            }
            course.setTeacher(teacher); // can't input correctly
            teacher.addCourse(list.getYear(), list.getSemester(), course);
            DisplayInfo.trinedSuccessfully();
            // model.setTeacherToCourse(list, courseName, teacherName);
            // System.out.println("Is set.");
          }
          break;

        case 2:
          readList();
          break;

        case 3:
          System.out.println("show teacher list");
          // teachers = model.getTeacherList();
          view.printTeacherList(teachers);
          break;

        case 4:
          MenuController.controller.ad_addNewTeacher();
          break;

        case 5:
          Teacher teacher = MenuController.view.inputTeacherName();
          // TODO
          if (teacher != null) {
            MenuController.model.trainTeacher(teacher);
            DisplayInfo.trainingCompleted();
          } else {
            MenuController.view.click2Continue();
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
          DisplayInfo.text_invalidInput();
          break;
      }
    }
  }

  /**
   * Teacher's main menu (Controller)
   */
  public static void t_mainMenu() {
    Boolean selected = false;
    while (!selected) {
      int menuSelect = MenuView.teacherSelectMenu(); // show teacher main menu and input option
      switch (menuSelect) {
        // Check the teacher's personal information
        case 1:
          String userName = UserSystem.getCurrentUser().getUserName();
          MenuController.view.printTeacherInfo(userName);
          break;

        // Print the teacher's courses
        case 2:
          String teacherName = UserSystem.getCurrentUser().getUserName();
          Teacher teacher = MenuController.model.getData().getTeacher(teacherName);
          view.printTeacherCourse(teacher.getCourses());
          break;

        // Log out and save to database
        case 3:
          Database.save(MenuController.model);
          DisplayInfo.logout();
          selected = true;
          break;

        // When input is not between 1 - 3
        default:
          DisplayInfo.text_invalidInput();
          break;
      }
    }
  }

  /**
   * PTT Director's main menu (Controller)
   */
  public static void ptt_mainMenu() {
    Boolean selected = false;
    while (!selected) {
      int menuSelect = MenuView.pttDirectorMenu(); // show cd main menu and input option
      switch (menuSelect) {

        case 1:
          MenuController.controller.checkRequest();
          MenuController.controller.ptt_toApprove();
          break;

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
          DisplayInfo.text_invalidInput();
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
    int year, semester;
    while (!selected) {
      menuSelect = MenuView.readListMenu(); // show menu & input option
      switch (menuSelect) {
        case 1:
          DisplayInfo.ask2InputYear();
          year = view.inputInt(GlobalVariable.MIN_YEAR, GlobalVariable.MAX_YEAR); // input year
          DisplayInfo.ask2InputSemester();
          semester = view.inputInt(GlobalVariable.MIN_SEMESTER, GlobalVariable.MAX_SEMESTER); // input semester
          list = model.validateSpecificList(year, semester);
          if (list == null) {
            DisplayInfo.requirementListNotExist();
            break;
          }
          view.printSpecificList(list);
          selected = true;
          break;

        case 2:
          lists = model.getData().getData();
          view.printLists(lists);
          selected = true;
          break;

        // When input is not between 1 - 4
        default:
          DisplayInfo.text_invalidInput();
          break;
      }

    }

  }
}