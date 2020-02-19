package models;

import models.users.Teacher;

public abstract class Course {
  private int id;
  private String name;
  private Teacher teacher;
  private Teacher tempTeacher;

  /**
   * @return the id
   */
  public int getId() {
    return id;
  }

  /**
   * @param id the id to set
   */
  public void setId(int id) {
    this.id = id;
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

  /**
   * @return the tempTeacher
   */
  public Teacher getTempTeacher() {
    return tempTeacher;
  }

  /**
   * @param tempTeacher the tempTeacher to set
   */
  public void setTempTeacher(Teacher tempTeacher) {
    this.tempTeacher = tempTeacher;
  }
}