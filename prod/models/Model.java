package models;

import java.util.ArrayList;
import java.util.Scanner;
import models.users.Teacher;
import models.users.User;

public class Model {
  // attributes
  private PTT_System data = new PTT_System();
  private UserSystem userSystem = new UserSystem();
  private RequirementList list;
  private String info = "";
  private ArrayList<Course> courses;
  private ArrayList<RequirementList> listData = data.getData();
  private ArrayList<RequirementList> lists = new ArrayList<RequirementList>();
  private ArrayList<Teacher> teacherData = data.getTeachers();
  private Teacher teacher;
  private Course teacherCourse;
  private String teacherCourses = "";
  private Scanner s = new Scanner(System.in);

  // Course Director create requirement list
  public void createRequirementList(int year, int semester) {
    list = new RequirementList(year, semester); // create new course to list
    data.addToData(list); // add to system
  }

  public void addCourseToList(String newCourse) {
    list.updateCourse(new Course(newCourse));
  }

  public void addTeacherToList(String newTeacher) {
    teacher = new Teacher(newTeacher);
    data.addTeacherToData(teacher); // add to system
  }

  // get specific list by input the year and the semester
  public RequirementList getSpecificList(int year, int semester) {
    for (int i = 0; i < listData.size(); i++) {
      if (listData.get(i).getYear() == year && listData.get(i).getSemester() == semester) {
        list = listData.get(i);
        return list;
      }
    }
    return null;
  }

  // return list in the same year
  public ArrayList<RequirementList> getSameYearLists(int year) {
    for (int i = 0; i < listData.size(); i++) {
      if (listData.get(i).getYear() == year) {
        lists.add(listData.get(i));
      }
    }
    return lists;
  }

  // return list in the same year
  public ArrayList<RequirementList> getSameSemesterLists(int semester) {
    for (int i = 0; i < listData.size(); i++) {
      if (listData.get(i).getSemester() == semester) {
        lists.add(listData.get(i));
      }
    }
    return lists;
  }

  // get all lists
  public ArrayList<RequirementList> getAllLists() {
    for (int i = 0; i < listData.size(); i++) {
      lists.add(listData.get(i));
    }
    return lists;
  }

  // set approval
  public void setApproval(RequirementList list) {
    if (!list.getApproval()) {
      list.setApproval(true);
    }
  }

  // get unapproval list approval
  public ArrayList<RequirementList> getUnapprovedLists() {
    for (int i = 0; i < listData.size(); i++) {
      if (!listData.get(i).getApproval()) {
        lists.add(listData.get(i));
      }
    }
    return lists;
  }

  // set teacher to course // not complete...
  public void setTeacherToCourse(RequirementList list, String guid, String name) {
    this.courses = list.getCourses();

    for (int i = 0; i < teacherData.size(); i++) {
      if (teacherData.get(i).getName().equals(name)) {
        teacher = teacherData.get(i);
        System.out.println(teacher.getName());
      }
    }

    for (int i = 0; i < courses.size(); i++) {
      if (courses.get(i).getGuid().equals(guid)) {
        list.getCourses().get(i).setTeacher(teacher);
        break;
      }
    }
  }

  // get isTrained from teacher list
  public Boolean teacherTrained(String teacherGuid) {
    Boolean isTrained = false;
    return isTrained;
  }

  public String getTeacherCourse(int year, int semester, String teacherName) {
    for (int i = 0; i < listData.size(); i++) {
      if (listData.get(i).getYear() == year && listData.get(i).getSemester() == semester
          && teacherCourse.getTeacher().equals(teacherName)) {
        return teacherCourses = "" + teacherCourse.getName();
      }
    }
    return null;
  }

  /**
   * @return the userSystem
   */
  public UserSystem getUserSystem() {
    return userSystem;
  }

  /**
   * @param userSystem the userSystem to set
   */
  public void setUserSystem(UserSystem userSystem) {
    this.userSystem = userSystem;
  }

  // // getters and setters
  public ArrayList<RequirementList> getListData() {
    return data.getData();
  }

  // get teacher list
  public ArrayList<Teacher> getTeacherList() {
    return data.getTeachers();
  }

}