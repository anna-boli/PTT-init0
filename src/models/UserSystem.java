package models;

import java.util.ArrayList;

import models.users.User;

/**
 * As singleton pattern
 */
public class UserSystem {
  private static ArrayList<User> users = new ArrayList<User>();

  public static void importUsers(User user) {
    users.add(user);
  }

  public static boolean login(User unknownUser) {
    for (User user : users) {
      if (user.getUserName().equals(unknownUser.getUserName())
          && user.getPassword().equals(unknownUser.getPassword())) {
        unknownUser.setLoggedIn(true);
        return true;
      }
    }
    return false;
  }

  public static boolean logout(User user) {
    user.setLoggedIn(false);
    return true;
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