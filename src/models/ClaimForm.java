package models;

import java.util.Date;

import models.users.Teacher;

public abstract class ClaimForm {
  private int id;
  private boolean hasPayment = true;
  private String type;
  private Teacher teacher;
  private Course course;
  private boolean approved;
  private Date dueDate;
  private boolean isSent;

  /**
   * @return the id
   */
  public int getId() {
    return id;
  }

  /**
   * @param id the id to set
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * @return the hasPayment
   */
  public boolean isHasPayment() {
    return hasPayment;
  }

  /**
   * @param hasPayment the hasPayment to set
   */
  public void setHasPayment(boolean hasPayment) {
    this.hasPayment = hasPayment;
  }

  /**
   * @return the type
   */
  public String getType() {
    return type;
  }

  /**
   * @param type the type to set
   */
  public void setType(String type) {
    this.type = type;
  }

  /**
   * @return the teacher
   */
  public Teacher getTeacher() {
    return teacher;
  }

  /**
   * @param teacher the teacher to set
   */
  public void setTeacher(Teacher teacher) {
    this.teacher = teacher;
  }

  /**
   * @return the course
   */
  public Course getCourse() {
    return course;
  }

  /**
   * @param course the course to set
   */
  public void setCourse(Course course) {
    this.course = course;
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
   * @return the dueDate
   */
  public Date getDueDate() {
    return dueDate;
  }

  /**
   * @param dueDate the dueDate to set
   */
  public void setDueDate(Date dueDate) {
    this.dueDate = dueDate;
  }

  /**
   * @return the isSent
   */
  public boolean isSent() {
    return isSent;
  }

  /**
   * @param isSent the isSent to set
   */
  public void setSent(boolean isSent) {
    this.isSent = isSent;
  }
}