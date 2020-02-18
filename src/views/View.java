package views;

import java.io.IOException;

import controllers.Controller;
import models.Model;

public class View {
  Model model;
  Controller controller;
  String os = System.getProperty("os.name");

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

}