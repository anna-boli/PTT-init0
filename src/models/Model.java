package models;

import java.util.ArrayList;

import models.users.Teacher;
import models.users.User;

public class Model {
  // attributes
  private PttSystem data = new PttSystem();
  private UserSystem userSystem = new UserSystem(this);
  private RequirementList list;
  private ArrayList<Course> courses;
  // TODO not to call getter in class arrtributes
  private ArrayList<RequirementList> listData = data.getData();
  private ArrayList<RequirementList> lists = new ArrayList<RequirementList>();
  private ArrayList<Teacher> teacherData = data.getTeachers();
  private Teacher teacher;


  // Course Director create requirement list
  public void createRequirementList(int year, int semester) {
    list = new RequirementList(year, semester); // create new course to list
    data.addToData(list); // add to system
  }

  public void addCourseToList(String newCourse) {
    list.updateCourse(new Course(newCourse));
  }

  public void addTeacherToList(String newTeacher) {
    teacher = new Teacher(newTeacher, newTeacher, newTeacher);
    data.addTeacherToData(teacher); // add to system
    UserSystem.addUser((User) teacher);
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
  public void setTeacherToCourse(RequirementList list, String courseName, String teacherName) {
    Teacher teacher = null;
    Course course = null;
    for (int i = 0; i < teacherData.size(); i++) {
      if (teacherData.get(i).getName().equals(teacherName)) {
        teacher = teacherData.get(i);
        System.out.println(teacher.getName());
      }
    }
    ArrayList<Course> courses = list.getCourses();
    for (int i = 0; i < courses.size(); i++) {
      if (courses.get(i).getName().equals(courseName)) {
        System.out.println(this.teacherData);
        course = list.getCourses().get(i);
        course.setTeacher(teacher); // can't input correctly
        teacher.addCourse(list.getYear(), list.getSemester(), course);
        // System.out.println(list.getCourses().get(i).getName());
        // System.out.println(list.getCourses().get(i).getTeacher().getName());
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
    for (User user : UserSystem.getUsers()) {
      if (user.getUserName().equals(teacherName) && user.getRole().equals("t")) {
        return ((Teacher) user).getCourses();
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