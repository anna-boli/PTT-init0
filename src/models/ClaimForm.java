package models;

import java.util.Date;

public class ClaimForm {
  private int id;
  private boolean hasPayment = true;
  private String type;
  private Teacher teacher;
  private Course course;
  private boolean approved;
  private Date dueDate;
  private boolean isSent;
}