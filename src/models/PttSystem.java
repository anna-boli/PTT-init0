package models;

import java.util.ArrayList;
import java.util.Date;

import models.users.Teacher;
import models.users.User;

public abstract class PttSystem {
  private ArrayList<RequirementList> historyData;
  private ArrayList<Teacher> teachers;
  private Date DUE_TIME;
  private ArrayList<User> users;

  /**
   * @return the historyData
   */
  public ArrayList<RequirementList> getHistoryData() {
    return historyData;
  }

  /**
   * @param historyData the historyData to set
   */
  public void setHistoryData(ArrayList<RequirementList> historyData) {
    this.historyData = historyData;
  }

  /**
   * @return the teachers
   */
  public ArrayList<Teacher> getTeachers() {
    return teachers;
  }

  /**
   * @param teachers the teachers to set
   */
  public void setTeachers(ArrayList<Teacher> teachers) {
    this.teachers = teachers;
  }

  /**
   * @return the dUE_TIME
   */
  public Date getDUE_TIME() {
    return DUE_TIME;
  }

  /**
   * @param dUE_TIME the dUE_TIME to set
   */
  public void setDUE_TIME(Date dUE_TIME) {
    DUE_TIME = dUE_TIME;
  }

  /**
   * @return the users
   */
  public ArrayList<User> getUsers() {
    return users;
  }

  /**
   * @param users the users to set
   */
  public void setUsers(ArrayList<User> users) {
    this.users = users;
  }

}