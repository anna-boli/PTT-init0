package controllers;

import java.util.Scanner;

import models.Model;
import models.UserSystem;
import models.users.User;
import views.View;

public class Controller {
  Model model;
  View view;

  public Controller(Model model) {
    this.model = model;
  }

  public void addView(View view) {
    this.view = view;
  }

  /**
   * Scan input and validate it according to # of choices. The choices is valid
   * when it's between 0(included) amd nChoices(excluded). Print "invalid input"
   * and ask user to input again when the input is not within the range.
   * 
   * @param nChoices an int, # of choices shown in view
   * @return an int, the valid input of user
   */
  public int validateInput(int nChoices) {
    // Use scanner for input
    Scanner scanner = new Scanner(System.in);
    // the input value as valid integer
    int intInput = -1;
    // infinite loop which break when the input is validated
    boolean isInvalidInput = true;
    while (isInvalidInput) {
      // use nextLine() rather than nextLine(nextInt) to exclude exception raised
      String stringInput = scanner.nextLine();
      // if the input cannot be converted to int, then it's invalid
      try {
        intInput = Integer.parseInt(stringInput);
      } catch (NumberFormatException e) {
      }
      // When input can be converted to integer & input is within acceptable range
      if (intInput > 0 && intInput <= nChoices) {
        System.out.println();
        scanner.close();
        return intInput;
      }
      System.out.println("Invalid input. Please enter a number between 1 to " + Integer.toString(nChoices) + ".");
      System.out.print("Please enter your choice again: ");
    }
    scanner.close();
    return -1;
  }

  public boolean validateLogin(String username, String password) {
    User user = new User(username, password);
    return UserSystem.login(user);
  }
}