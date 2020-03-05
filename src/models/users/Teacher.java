package models.users;

import java.util.ArrayList;

import models.Course;

public class Teacher extends User {
  private String name;
  private boolean trained = false;
  private ArrayList<String> courses = new ArrayList<String>();

  public Teacher(String name) {
    this.name = name;
    courses.clear();
  }

  public Teacher(String name, String username, String password) {
    super(username, password, "t");
    this.name = username;
    courses.clear();
  }

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @return the trained
   */
  public boolean isTrained() {
    return trained;
  }

  /**
   * @param trained the trained to set
   */
  public void setTrained(boolean trained) {
    this.trained = trained;
  }

  public void addCourse(int year, int semester, Course course) {
    this.courses.add(String.format("%d/%d-%s", year, semester, course.getName()));
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (object == null) {
      return false;
    }
    if (object instanceof Teacher) {
      Teacher teacher = (Teacher) object;
      return this.name.equals(teacher.name);
    }
    return false;
  }

  /**
   * @return the courses
   */
  public ArrayList<String> getCourses() {
    return courses;
  }

  /**
   * @param courses the courses to set
   */
  public void setCourses(ArrayList<String> courses) {
    this.courses = courses;
  }

}