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

    this.clearScreen();
    System.out.println("----------------------------------------");
    System.out.println("Welcome to PTT system");
    System.out.println("----------------------------------------");

    while (true) {
      System.out.print("Login required. Please enter username: ");
      username = scanner.nextLine();
      System.out.print("Please enter your password: ");
      password = scanner.nextLine();
      if (this.controller.validateLogin(username, password)) {
        break;
      }
      System.out.println("Invalid user information. Please try again.");
    }
  }

  public void registration() {
    Scanner scanner = new Scanner(System.in);
    String username, password, role;

    this.clearScreen();
    System.out.println("----------------------------------------");
    System.out.println("Sign up for PTT");
    System.out.println("----------------------------------------");

    System.out.print("Please enter username: ");
    username = scanner.nextLine().replaceAll("\\s", "");
    System.out.print("Please enter your password: ");
    password = scanner.nextLine().replaceAll("\\s", "");
    System.out.print("Please enter your role in PTT: ");
    role = scanner.nextLine().replaceAll("\\s", "");
  }

  public void endScreen() {
    System.out.println("----------------------------------------");
    System.out.println("End of Program");
    System.out.println("----------------------------------------");
  }

}