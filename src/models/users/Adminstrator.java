package models.users;

import models.RequirementList;

public abstract class Adminstrator  extends User{
  private RequirementList requirementList;

  /**
   * @return the requirementList
   */
  public RequirementList getRequirementList() {
    return requirementList;
  }

  /**
   * @param requirementList the requirementList to set
   */
  public void setRequirementList(RequirementList requirementList) {
    this.requirementList = requirementList;
  }
}