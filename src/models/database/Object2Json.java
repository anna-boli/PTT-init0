package models.database;

import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONObject;

import models.Course;
import models.Model;
import models.PttSystem;
import models.RequirementList;
import models.UserSystem;
import models.users.Teacher;
import models.users.User;

public class Object2Json {
  ObjectMapper objectMapper = new ObjectMapper();

  public static JSONObject teacher2Json(Teacher teacher) {
    JSONObject jsonTeacher = new JSONObject();
    jsonTeacher.put("userName", teacher.getUserName());
    jsonTeacher.put("password", teacher.getPassword());
    jsonTeacher.put("role", teacher.getRole());
    jsonTeacher.put("isTrained", teacher.isTrained());
    JSONArray jsonCourses = new JSONArray();
    ArrayList<String> courses = teacher.getCourses();
    for (String courseName : courses) {
      jsonCourses.put(courseName);
    }
    jsonTeacher.put("courses", jsonCourses);
    return jsonTeacher;
  }

  public static JSONObject user2Json(User user) {
    JSONObject jsonUser = new JSONObject();
    jsonUser.put("userName", user.getUserName());
    jsonUser.put("password", user.getPassword());
    jsonUser.put("role", user.getRole());
    return jsonUser;
  }

  public static JSONObject course2Json(Course course) {
    JSONObject jsonCourse = new JSONObject();
    jsonCourse.put("name", course.getName());
    Teacher teacher = course.getTeacher();
    if (teacher != null) {
      jsonCourse.put("teacher", Object2Json.teacher2Json(teacher));
    } else {
      jsonCourse.put("teacher", JSONObject.NULL);
    }
    return jsonCourse;
  }

  public static JSONObject userSystem2JsonObject(UserSystem userSystem) {
    JSONObject jsonUserSystem = new JSONObject();
    JSONArray jsonUsers = new JSONArray();
    ArrayList<User> users = UserSystem.getUsers();
    for (User user : users) {
      jsonUsers.put(Object2Json.user2Json(user));
    }
    jsonUserSystem.put("users", users);
    return jsonUserSystem;
  }

  public static JSONObject pttSystem2Json(PttSystem pttSystem) {
    JSONObject jsonPttSystem = new JSONObject();
    ArrayList<RequirementList> data = pttSystem.getData();
    JSONArray jsonData = new JSONArray();
    for (RequirementList requirementList : data) {
      jsonData.put(Object2Json.requirementList2Json(requirementList));
    }
    ArrayList<Teacher> teachers = pttSystem.getTeachers();
    JSONArray jsonTeachers = new JSONArray();
    for (Teacher teacher : teachers) {
      jsonTeachers.put(Object2Json.teacher2Json(teacher));
    }
    jsonPttSystem.put("data", jsonData);
    jsonPttSystem.put("teachers", jsonTeachers);
    return jsonPttSystem;
  }

  public static JSONObject model2Json(Model model) {
    JSONObject jsonModel = new JSONObject();
    jsonModel.put("userSystem", Object2Json.userSystem2JsonObject(model.getUserSystem()));
    jsonModel.put("pttSystem", Object2Json.pttSystem2Json(model.getData()));
    return jsonModel;
  }

  public static JSONObject requirementList2Json(RequirementList requirementList) {
    JSONObject jsonRequirementList = new JSONObject();
    jsonRequirementList.put("year", requirementList.getSemester());
    jsonRequirementList.put("semester", requirementList.getSemester());
    jsonRequirementList.put("approval", requirementList.getApproval());
    ArrayList<Course> courses = requirementList.getCourses();
    JSONArray jsonCourses = new JSONArray();
    for (Course course : courses) {
      jsonCourses.put(Object2Json.course2Json(course));
    }
    jsonRequirementList.put("courses", jsonCourses);
    return jsonRequirementList;
  }

  public static void main(String[] args) {

    Model model2 = new Model();
    System.out.println(Object2Json.model2Json(model2).toString());
    Database.save(model2);
    model2 = Database.load();
    System.out.println(Object2Json.model2Json(model2).toString());

    User user1 = new User("user1", "user1", "user1");
    User user2 = new User("user2", "user2", "user2");
    ArrayList<User> users = new ArrayList<User>();
    users.add(user1);
    users.add(user2);
    Model model = new Model();
    RequirementList requirementList = new RequirementList(2020, 1);
    model.getData().addToData(requirementList);
    model.getUserSystem().addUser(user1);
    model.getUserSystem().addUser(user2);
    System.out.println(Object2Json.model2Json(model).toString());
    Database.save(model);
    model = new Model();
    System.out.println(Object2Json.model2Json(model).toString());

  }
}