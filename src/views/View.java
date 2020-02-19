package views;

import java.io.IOException;
import java.util.Scanner;

import controllers.Controller;
import models.Model;

public class View {
  private Model model;
  private Controller controller;
  private String os = System.getProperty("os.name");
  private boolean userWantToQuit = false;

  public View(Model model, Controller controller) {
    this.model = model;
    this.controller = controller;
  }

  /**
   * Clear console screen according to the current running OS.
   */
  public void clearScreen() {
    try {
      if (this.os.contains("Windows")) {
        Runtime.getRuntime().exec("cls");
      } else {
        Runtime.getRuntime().exec("clear");
        System.out.print("\033[H\033[2J");
        System.out.flush();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void StartScreen() {
    Scanner scanner = new Scanner(System.in);
    String username, password;

    System.out.println("----------------------------------------");
    System.out.println("Welcome to PPT system");
    System.out.println("----------------------------------------");

    System.out.print("Login required. Please enter username: ");
    username = scanner.nextLine();
    System.out.print("Please enter your password: ");
    password = scanner.nextLine();
  }

}