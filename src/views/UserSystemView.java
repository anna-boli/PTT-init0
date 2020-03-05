package views;

import java.util.Scanner;

import controllers.Validator;
import models.UserSystem;

public class UserSystemView {

  public static void loginScreen() {
    String username, password;
    // this.clearScreen();
    Scanner scanner = new Scanner(System.in);
    Header.login();
    while (true) {
      System.out.print("Login required. Please enter username: ");
      username = scanner.nextLine();
      System.out.print("Please enter your password: ");
      password = scanner.nextLine();
      if (Validator.validateLogin(username, password)) {
        System.out.println("Login successfully.");
        DisplayInfo.click2Continue();
        break;
      }
      System.out.println("Invalid user information. Please try again.");
      DisplayInfo.click2Continue();
      break;
    }
    // scanner.close();
  }

  public static void startScreen() {
    boolean exitStartScreen = false;
    do {
      // this.clearScreen();
      Header.welcome();
      System.out.println("(1) Login");
      System.out.println("(2) Register");
      System.out.print("Please enter your choice: ");
      int input = Validator.validateInt(1, 2);
      switch (input) {
        case 1:
          UserSystemView.loginScreen();
          exitStartScreen = true;
          break;
        case 2:
          UserSystemView.registration();
          break;
        default:
          DisplayInfo.invalidInput();
          break;
      }
    } while (!exitStartScreen);
  }

  public static void registration() {
    String username, password, role;
    boolean isRegisterSuccessfully = false;
    Scanner scanner = new Scanner(System.in);
    while (!isRegisterSuccessfully) {
      // this.clearScreen();
      Header.signup();
      System.out.print("Please enter username: ");
      username = scanner.nextLine();
      System.out.print("Please enter your password: ");
      password = scanner.nextLine();

      System.out.println("Set your role in PTT system.");
      System.out.println("Input \"a\" as the role as Administrator");
      System.out.println("Input \"pd\" as the role as PTT Director");
      System.out.println("Input \"cd\" as the role as Course Director");
      System.out.println("Input \"t\" as the role as Teacher");
      System.out.print("Please enter your role: ");
      role = scanner.nextLine();

      if (username.equals("") || password.equals("") || role.equals("")) {
        isRegisterSuccessfully = false;
      } else {
        isRegisterSuccessfully = UserSystem.register(username, password, role);
      }
      if (!isRegisterSuccessfully) {
        System.out.println("Registration failed. Possible reasons are as follows:");
        System.out.println("(1) There is an empty input(s).");
        System.out.println("(2) That username is taken. Try another.");
        System.out.println("(3) The input role was invalid.");
        System.out.println("Please try again.");
        DisplayInfo.click2Continue();
      }
    }
    System.out.println("Register successfully.");
    DisplayInfo.click2Continue();
  }
}