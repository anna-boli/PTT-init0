package models.database;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import models.Course;
import models.Model;
import models.RequirementList;
import models.users.Teacher;
import models.users.User;

public class Json2Object {

  public static Model json2Model(JSONObject jsonModel) {
    Model model = new Model();
    JSONArray jsonTeachers = jsonModel.getJSONObject("pttSystem").getJSONArray("teachers");
    JSONArray jsonUsers = jsonModel.getJSONObject("userSystem").getJSONArray("users");
    JSONArray jsonRequirementLists = jsonModel.getJSONObject("pttSystem").getJSONArray("data");

    ArrayList<User> users = new ArrayList<User>();
    users.clear();
    if (jsonUsers != null && jsonUsers.length() != 0) {
      User user = new User();
      for (int i = 0; i < jsonUsers.length(); i++) {
        user = Json2Object.json2User(jsonUsers.getJSONObject(i));
        users.add(user);
      }
    }
    model.getUserSystem().setUsers(users);

    ArrayList<Teacher> teachers = new ArrayList<Teacher>();
    teachers.clear();
    Teacher teacher;
    if (jsonTeachers != null && jsonTeachers.length() != 0) {
      for (int i = 0; i < jsonTeachers.length(); i++) {
        teacher = Json2Object.json2Teacher(jsonTeachers.getJSONObject(i));
        teachers.add(teacher);
      }
      model.getData().setTeachers(teachers);
    }

    if (jsonRequirementLists != null && jsonRequirementLists.length() != 0) {
      JSONArray jsonCourses;
      RequirementList requirementList;
      int year, semester;
      boolean approved, isNew;
      Course course;
      for (int i = 0; i < jsonRequirementLists.length(); i++) {
        year = jsonRequirementLists.getJSONObject(i).getInt("year");
        semester = jsonRequirementLists.getJSONObject(i).getInt("semester");
        requirementList = new RequirementList(year, semester);
        approved = jsonRequirementLists.getJSONObject(i).getBoolean("approval");
        requirementList.setApproval(approved);
        isNew = jsonRequirementLists.getJSONObject(i).getBoolean("isNew");
        requirementList.setNew(isNew);
        jsonCourses = jsonRequirementLists.getJSONObject(i).getJSONArray("courses");
        if (jsonCourses != null && jsonCourses.length() != 0) {
          for (int j = 0; j < jsonCourses.length(); j++) {
            course = json2Course(jsonCourses.getJSONObject(j));
            requirementList.updateCourse(course);
          }
          model.getData().addToData(requirementList);
        }
      }

    }
    return model;
  }

  public static User json2User(JSONObject jsonUser) {
    User user = new User();
    user.setUserName(jsonUser.getString("userName"));
    user.setPassword(jsonUser.getString("password"));
    user.setRole(jsonUser.getString("role"));
    return user;
  }

  public static Teacher json2Teacher(JSONObject jsonTeacher) {
    String username = jsonTeacher.getString("userName");
    String password = jsonTeacher.getString("password");
    boolean isTrained = jsonTeacher.getBoolean("isTrained");
    JSONArray jsonCourses = jsonTeacher.getJSONArray("courses");
    ArrayList<String> courses = new ArrayList<String>();
    courses.clear();
    if (jsonCourses != null && jsonCourses.length() != 0) {
      for (int i = 0; i < jsonCourses.length(); i++) {
        courses.add(jsonCourses.getString(i));
      }
    }
    Teacher teacher = new Teacher(username, username, password);
    teacher.setCourses(courses);
    teacher.setTrained(isTrained);
    return teacher;
  }

  public static Course json2Course(JSONObject jsonCourse) {
    Course course = new Course(jsonCourse.getString("name"));
    JSONObject jsonTeacher;
    try {
      jsonTeacher = jsonCourse.getJSONObject("teacher");
    } catch (JSONException e) {
      jsonTeacher = null;
    }
    if (jsonTeacher != null) {
      course.setTeacher(Json2Object.json2Teacher(jsonTeacher));
    }
    return course;
  }
}