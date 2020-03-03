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

  // read list menu
  public static int readListMenu() {
    System.out.println("(1) Read specific list");
    System.out.println("(2) Read same year lists");
    System.out.println("(3) Read same semester lists");
    System.out.println("(4) Read all lists");
    System.out.print("Input selection: ");
    return MenuView.view.inputInt(1, 4);
  }

  // teacher selectMenu
  public static int t_selectMenu() {
    // this.clearScreen();
    Header.teacherHeader();
    System.out.println("(1) Check self information");
    System.out.println("(2) Check course");
    System.out.println("(3) Log out");
    System.out.print("Input selection: ");
    return MenuView.view.inputInt(1, 3);
  }

  // ad selectMenu
  public static int ad_selectMenu() {
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

  // ptt selectMenu
  public static int ptt_selectMenu() {
    // this.clearScreen();
    Header.pttDirectorHeader();
    System.out.println("(1) Check request");
    System.out.println("(2) Read history");
    System.out.println("(3) Log out");
    System.out.print("Input selection: ");
    return MenuView.view.inputInt(1, 3);
  }

  // cd selectMenu
  public static int cd_selectMenu() {
    // this.clearScreen();
    Header.classDirectorHeader();
    System.out.println("(1) Add new requirement list");
    System.out.println("(2) Read history");
    System.out.println("(3) Check approval  ");
    System.out.println("(4) Log out");
    System.out.print("Input selection: ");
    return MenuView.view.inputInt(1, 4);
  }

  // cd add course menu
  public static int addCourseMenu() {
    System.out.println("(1) Add new course");
    System.out.println("(2) Submit");
    System.out.print("Input selection: ");
    return MenuView.view.inputInt(1, 2);
  }
}