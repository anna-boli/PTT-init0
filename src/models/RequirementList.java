package models;

import java.util.ArrayList;

public abstract class RequirementList {
  private int year;
  private int semester;
  private boolean approved;
  private ArrayList<Course> requirements;

  /**
   * @return the year
   */
  public int getYear() {
    return year;
  }

  /**
   * @param year the year to set
   */
  public void setYear(int year) {
    this.year = year;
  }

  /**
   * @return the semester
   */
  public int getSemester() {
    return semester;
  }

  /**
   * @param semester the semester to set
   */
  public void setSemester(int semester) {
    this.semester = semester;
  }

  /**
   * @return the approved
   */
  public boolean isApproved() {
    return approved;
  }

  /**
   * @param approved the approved to set
   */
  public void setApproved(boolean approved) {
    this.approved = approved;
  }

  /**
   * @return the requirements
   */
  public ArrayList<Course> getRequirements() {
    return requirements;
  }

  /**
   * @param requirements the requirements to set
   */
  public void setRequirements(ArrayList<Course> requirements) {
    this.requirements = requirements;
  }
}