package views;

import controllers.Controller;
import models.Model;

public class MenuView {
  private static Model model;
  private static Controller controller;
  private static View view;

  public MenuView(Model model, Controller controller, View view) {
    MenuView.model = model;
    MenuView.controller = controller;
    MenuView.view = view;
  }

  /**
   * Teacher's main menu (View)
   * 
   * @return an int, the number of user input as choice
   */
  public static int teacherSelectMenu() {
    // this.clearScreen();
    Header.teacherHeader();
    System.out.println("(1) Check self information");
    System.out.println("(2) Check course");
    System.out.println("(3) Log out");
    System.out.print("Input selection: ");
    return MenuView.view.inputInt(1, 3);
  }

  /**
   * Administrator's main menu (View)
   * 
   * @return an int, the number of user input as choice
   */
  public static int administratorMenu() {
    // this.clearScreen();
    Header.administratorHeader();
    System.out.println("(1) Set teacher to course");
    System.out.println("(2) Read history");
    System.out.println("(3) Read teacher list");
    System.out.println("(4) Add new teacher");
    System.out.println("(5) Train teacher");
    System.out.println("(6) Log out");
    System.out.print("Input selection: ");
    return MenuView.view.inputInt(1, 6);
  }

  /**
   * PTT Director's main menu (View)
   * 
   * @return an int, the number of user input as choice
   */
  public static int pttDirectorMenu() {
    // this.clearScreen();
    Header.pttDirectorHeader();
    System.out.println("(1) Check request");
    System.out.println("(2) Read history");
    System.out.println("(3) Log out");
    System.out.print("Input selection: ");
    return MenuView.view.inputInt(1, 3);
  }

  /**
   * Course Director's main menu (View)
   * 
   * @return an int, the number of user input as choice
   */
  public static int courseDirectorMenu() {
    // this.clearScreen();
    Header.classDirectorHeader();
    System.out.println("(1) Add new requirement list");
    System.out.println("(2) Read history");
    System.out.println("(3) Check approval  ");
    System.out.println("(4) Log out");
    System.out.print("Input selection: ");
    return MenuView.view.inputInt(1, 4);
  }

  /**
   * Course Director's main menu when adding new course into requirement list.
   * (View)
   * 
   * @return an int, the number of user input as choice
   */
  public static int addCourseMenu() {
    System.out.println("(1) Add new course");
    System.out.println("(2) Submit");
    System.out.print("Input selection: ");
    return MenuView.view.inputInt(1, 2);
  }

  /**
   * Course Director's main menu for showing requirement list. (View)
   * 
   * @return an int, the number of user input as choice
   */
  public static int readListMenu() {
    System.out.println("(1) Read specific list");
    System.out.println("(2) Read all lists");
    System.out.print("Input selection: ");
    return MenuView.view.inputInt(1, 2);
  }
}