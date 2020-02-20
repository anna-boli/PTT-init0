package models;

import models.users.Teacher;

public abstract class Course {
  private int guid;
  private String name;
  private Teacher teacher;

  public Course(String name, Teacher teacher) {
    this.name = name;
    this.teacher = teacher;
  }

  /**
   * @return the guid
   */
  public int getGuid() {
    return guid;
  }

  /**
   * @param guid the guid to set
   */
  public void setGuid(int guid) {
    this.guid = guid;
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
   * @return the teacher
   */
  public Teacher getTeacher() {
    return teacher;
  }

  /**
   * @param teacher the teacher to set
   */
  public void setTeacher(Teacher teacher) {
    this.teacher = teacher;
  }

}