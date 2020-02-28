package models;

import java.util.ArrayList;

import models.users.Teacher;
import models.users.User;

/**
 * As singleton pattern
 */
public class UserSystem {
  private static ArrayList<User> users = new ArrayList<User>();
  private static User currentUser;

  public static void importUsers(User user) {
    users.add(user);
  }

  public static boolean login(User unknownUser) {
    for (User user : users) {
      if (user.getUserName().equals(unknownUser.getUserName())
          && user.getPassword().equals(unknownUser.getPassword())) {
        UserSystem.currentUser = user;
        return true;
      }
    }
    return false;
  }

  public static boolean logout(User user) {
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

  public static boolean register(String username, String password, String role) {
    User newUser = new User(username, password);
    if (!(role.equals("a") || !role.equals("pd") || !role.equals("cd") || !role.equals("t"))) {
      return false;
    }
    if (UserSystem.login(newUser)) {
      return false;
    }

    if (!role.equals("t")) {
      UserSystem.users.add(new User(username, password, role));
    } else {
      UserSystem.users.add((User) new Teacher(username, username, password));
    }

    return true;
  }

  /**
   * @return the currentUser
   */
  public static User getCurrentUser() {
    return currentUser;
  }

  /**
   * @param currentUser the currentUser to set
   */
  public static void setCurrentUser(User currentUser) {
    UserSystem.currentUser = currentUser;
  }

}