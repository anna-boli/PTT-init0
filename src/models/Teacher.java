package models;

import java.util.ArrayList;

public class Teacher {
  private int id;
  private String name;
  private boolean hasClass;
  private boolean trained;
  private ArrayList<String> skillset;
  private ArrayList<String> traning;
  private ArrayList<Class> classes;
  private ArrayList<Class> historyClasses;
  private AttendanceList attendanceList;
}