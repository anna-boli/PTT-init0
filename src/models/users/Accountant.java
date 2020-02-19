package models.users;

import models.ClaimForm;

public abstract class Accountant extends User{
  private ClaimForm claimForm;

  /**
   * @return the claimForm
   */
  public ClaimForm getClaimForm() {
    return claimForm;
  }

  /**
   * @param claimForm the claimForm to set
   */
  public void setClaimForm(ClaimForm claimForm) {
    this.claimForm = claimForm;
  }
}