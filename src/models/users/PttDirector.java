package models.users;

import models.RequirementList;

public abstract class PttDirector extends User {
  private int budget;
  private RequirementList requirementList;

  /**
   * @return the budget
   */
  public int getBudget() {
    return budget;
  }

  /**
   * @param budget the budget to set
   */
  public void setBudget(int budget) {
    this.budget = budget;
  }

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