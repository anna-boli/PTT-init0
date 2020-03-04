package models;

import java.util.ArrayList;

import models.users.Teacher;
import models.users.User;

public class Model {
  // attributes
  private PttSystem data = new PttSystem();
  private UserSystem userSystem = new UserSystem(this);

  // Course Director create requirement list
  public RequirementList createRequirementList(int year, int semester) {
    RequirementList list = new RequirementList(year, semester); // create new course to list
    data.addToData(list); // add to system
    return list;
  }

  public void addCourseToList(RequirementList list, Course course) {
    list.updateCourse(course);
  }

  public void addTeacherToList(String newTeacher) {
    Teacher teacher = new Teacher(newTeacher, newTeacher, newTeacher);
    data.addTeacherToData(teacher); // add to system
    UserSystem.addUser((User) teacher);
  }

  // get specific list by input the year and the semester
  public RequirementList validateSpecificList(int year, int semester) {
    ArrayList<RequirementList> lists = this.data.getData();
    for (int i = 0; i < lists.size(); i++) {
      if (lists.get(i).getYear() == year && lists.get(i).getSemester() == semester) {
        return lists.get(i);
      }
    }
    return null;
  }

  // return list in the same year
  public ArrayList<RequirementList> getSameYearLists(int year) {
    ArrayList<RequirementList> lists = this.data.getData();
    for (int i = 0; i < lists.size(); i++) {
      if (lists.get(i).getYear() == year) {
        lists.add(lists.get(i));
      }
    }
    return lists;
  }

  // return list in the same year
  public ArrayList<RequirementList> getSameSemesterLists(int semester) {
    ArrayList<RequirementList> lists = this.data.getData();
    for (int i = 0; i < lists.size(); i++) {
      if (lists.get(i).getSemester() == semester) {
        lists.add(lists.get(i));
      }
    }
    return lists;
  }

  // get all lists
  public ArrayList<RequirementList> getAllLists() {
    ArrayList<RequirementList> lists = this.data.getData();
    for (int i = 0; i < lists.size(); i++) {
      lists.add(lists.get(i));
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
    ArrayList<RequirementList> lists = this.data.getData();
    ArrayList<RequirementList> newList = new ArrayList<RequirementList>();
    newList.clear();
    for (int i = 0; i < lists.size(); i++) {
      if (!lists.get(i).getApproval()) {
        newList.add(lists.get(i));
      }
    }
    return newList;
  }

  // set teacher to course // not complete...
  public void setTeacherToCourse(RequirementList list, String courseName, String teacherName) {
    Teacher teacher = null;
    Course course = null;
    ArrayList<Teacher> teachers = data.getTeachers();
    for (int i = 0; i < teachers.size(); i++) {
      if (teachers.get(i).getName().equals(teacherName)) {
        teacher = teachers.get(i);
        System.out.println(teacher.getName());
      }
    }
    ArrayList<Course> courses = list.getCourses();
    for (int i = 0; i < courses.size(); i++) {
      if (courses.get(i).getName().equals(courseName)) {
        System.out.println(teachers);
        course = list.getCourses().get(i);
        course.setTeacher(teacher);
        teacher.addCourse(list.getYear(), list.getSemester(), course);
        UserSystem.updateTeacher(teacher);
        break;
      }
    }
  }

  public void trainTeacher(Teacher teacher) {
    teacher.setTrained(true);
  }

  // get isTrained from teacher list
  public Boolean teacherTrained(String teacherGuid) {
    Boolean isTrained = false;
    return isTrained;
  }

  public ArrayList<String> getTeacherCourse(String teacherName) {
    for (Teacher teacher : this.data.getTeachers()) {
      if (teacher.getName().equals(teacherName)) {
        return teacher.getCourses();
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

  // get teacher list
  public ArrayList<Teacher> getTeacherList() {
    return data.getTeachers();
  }

  /**
   * @return the data
   */
  public PttSystem getData() {
    return data;
  }

  /**
   * @param data the data to set
   */
  public void setData(PttSystem data) {
    this.data = data;
  }

}