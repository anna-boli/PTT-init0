package models;

import java.util.ArrayList;

public class RequirementList {
  private int year;
  private int semester;
  private boolean approval = false;
  private ArrayList<Course> courses = new ArrayList<Course>();
  private boolean isNew = true;

  public RequirementList(int year, int semester) {
    this.year = year;
    this.semester = semester;
    this.courses.clear();
  }

  public void updateCourse(Course inputCourse) {
    for (Course course : this.courses) {
      if (inputCourse.getName().equals(course.getName())) {
        course = inputCourse; // make sure course do not repeat
        return;
      }
    }
    // create course guid: a number and the first char of the course name
    this.courses.add(inputCourse);
  }

  public String readList() {
    String readList = "--------------\n";
    readList = "<< " + year + " semester " + semester + " Requirement List >>\n";
    for (int i = 0; i < this.courses.size(); i++) {
      readList += " " + this.courses.get(i).getName();
      if (courses.get(i).getTeacher() == null) {
        readList += ",\tTeacher: " + this.courses.get(i).getTeacher() + "\n";
      } else {
        readList += ",\tTeacher: " + this.courses.get(i).getTeacher().getName() + "\n";
      }
    }
    readList += "Approval status: " + approval;
    readList += "\n--------------\n";
    return readList;
  }

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
  public boolean getApproval() {
    return approval;
  }

  /**
   * @param approved the approved to set
   */
  public void setApproval(boolean approved) {
    this.approval = approved;
  }

  /**
   * @return the requirements
   */
  public ArrayList<Course> getCourses() {
    return courses;
  }

  /**
   * @param requirements the requirements to set
   */
  public void setCourses(ArrayList<Course> courses) {
    this.courses = courses;
  }

  /**
   * @return the isNew
   */
  public boolean isNew() {
    return isNew;
  }

  /**
   * @param isNew the isNew to set
   */
  public void setNew(boolean isNew) {
    this.isNew = isNew;
  }

}