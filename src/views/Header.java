package views;

import controllers.Controller;
import models.Model;

public class Header {
  Model model;
  Controller controller;

  public Header(Model model, Controller controller) {
    this.model = model;
    this.controller = controller;
  }

  public static void welcome() {
    Header.horizontalLine();
    System.out.println("Welcome to PTT system");
    Header.horizontalLine();
  }

  public static void signup() {
    Header.horizontalLine();
    System.out.println("Sign up for PTT");
    Header.horizontalLine();
  }

  public static void login() {
    Header.horizontalLine();
    System.out.println("Login to PTT system");
    Header.horizontalLine();
  }

  public static void administratorHeader() {
    Header.horizontalLine();
    System.out.println("Administrator menu");
    Header.horizontalLine();
  }

  public static void classDirectorHeader() {
    Header.horizontalLine();
    System.out.println("Class Director menu");
    Header.horizontalLine();
  }

  public static void pttDirectorHeader() {
    Header.horizontalLine();
    System.out.println("PTT Director menu");
    Header.horizontalLine();
  }

  public static void teacherHeader() {
    Header.horizontalLine();
    System.out.println("Teacher menu");
    Header.horizontalLine();
  }

  public static void horizontalLine() {
    System.out.println("----------------------------------------");
  }

}