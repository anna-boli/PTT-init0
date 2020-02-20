package models.users;

public class User {
  private int guid;
  private String name;
  private String userName;
  private String password;
  private String role;
  private boolean isLoggedIn = false;

  public User() {

  }

  /**
   * @return the isLoggedIn
   */
  public boolean isLoggedIn() {
    return isLoggedIn;
  }

  /**
   * @param isLoggedIn the isLoggedIn to set
   */
  public void setLoggedIn(boolean isLoggedIn) {
    this.isLoggedIn = isLoggedIn;
  }

  /**
   * For login screen to check this username password pair
   * 
   * @param userName
   * @param password
   */
  public User(String userName, String password) {
    this.userName = userName;
    this.password = password;
  }

  /**
   * To create a new user inside user system
   * 
   * @param userName
   * @param password
   * @param role
   */
  public User(String userName, String password, String role) {
    this.userName = userName;
    this.password = password;
    this.role = role;
  }

  /**
   * @return the id
   */
  public int getGuid() {
    return guid;
  }

  /**
   * @param id the id to set
   */
  public void setId(int guid) {
    this.guid = guid;
  }

  /**
   * @return the userName
   */
  public String getUserName() {
    return userName;
  }

  /**
   * @param userName the userName to set
   */
  public void setUserName(String userName) {
    this.userName = userName;
  }

  /**
   * @return the password
   */
  public String getPassword() {
    return password;
  }

  /**
   * @param password the password to set
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * @return the role
   */
  public String getRole() {
    return role;
  }

  /**
   * @param role the role to set
   */
  public void setRole(String role) {
    this.role = role;
  }

}