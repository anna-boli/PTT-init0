package models;

import java.util.ArrayList;

import models.users.User;

public abstract class UserSystem {
  private ArrayList<User> users;

  public void importUsers() {
  }

  public abstract User checkUser(User unknownUser);

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