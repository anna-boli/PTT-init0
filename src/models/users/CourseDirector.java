package models.users;

import models.AttendanceList;
import models.RequirementList;

public abstract class CourseDirector extends User {
  private RequirementList requirementList;
  private AttendanceList attendanceList;

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

  /**
   * @return the attendanceList
   */
  public AttendanceList getAttendanceList() {
    return attendanceList;
  }

  /**
   * @param attendanceList the attendanceList to set
   */
  public void setAttendanceList(AttendanceList attendanceList) {
    this.attendanceList = attendanceList;
  }
}