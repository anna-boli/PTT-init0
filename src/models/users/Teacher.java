package models.users;

import java.util.ArrayList;

import models.Course;

public class Teacher extends User {
  private int guid;
  private String name;
  private boolean hasClass;
  private boolean trained;
  private ArrayList<String> skillset;
  private ArrayList<String> traning;
  private ArrayList<Course> courses;

  /**
   * @return the id
   */
  public int getGuid() {
    return guid;
  }

  /**
   * @param id the id to set
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
   * @return the hasClass
   */
  public boolean isHasClass() {
    return hasClass;
  }

  /**
   * @param hasClass the hasClass to set
   */
  public void setHasClass(boolean hasClass) {
    this.hasClass = hasClass;
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

  /**
   * @return the skillset
   */
  public ArrayList<String> getSkillset() {
    return skillset;
  }

  /**
   * @param skillset the skillset to set
   */
  public void setSkillset(ArrayList<String> skillset) {
    this.skillset = skillset;
  }

  /**
   * @return the traning
   */
  public ArrayList<String> getTraning() {
    return traning;
  }

  /**
   * @param traning the traning to set
   */
  public void setTraning(ArrayList<String> traning) {
    this.traning = traning;
  }

  /**
   * @return the classes
   */
  public ArrayList<Course> getClasses() {
    return courses;
  }

  /**
   * @param classes the classes to set
   */
  public void setClasses(ArrayList<Course> courses) {
    this.courses = courses;
  }



}