package models;

import java.util.ArrayList;

public class RequirementList {
  private int year;
  private int semester;
  private boolean approval = false;
  private ArrayList<Course> courses = new ArrayList<Course>();
  private String readList;

  public RequirementList(int year, int semester) {
    // System.out.println("list is created.");
    this.year = year;
    this.semester = semester;
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
    readList = "\n<< " + year + " semester " + semester + " Requirement List >>\n";
    for (int i = 0; i < courses.size(); i++) {
      readList += " " + courses.get(i).getName();
      if (courses.get(i).getTeacher() == null) {
        readList += ",\tTeacher: " + courses.get(i).getTeacher() + "\n";
      } else {
        readList += ",\tTeacher: " + courses.get(i).getTeacher().getName() + "\n";
      }
    }
    readList += "--------------\nApproval status: " + approval;
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

}