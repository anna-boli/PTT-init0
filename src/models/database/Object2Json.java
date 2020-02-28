package models.database;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.json.JSONObject;

import models.Course;
import models.users.User;

public class Object2Json {
  ObjectMapper objectMapper = new ObjectMapper();

  public static ObjectMapper user2Json(User user) {
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      String jsonStr = objectMapper.writeValueAsString(user);
      System.out.println(jsonStr);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return objectMapper;
  }

  public static String users2Json(ArrayList<User> users) {
    String jsonStr = "";
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      jsonStr = objectMapper.writeValueAsString(users.toArray());
      System.out.println(jsonStr);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return jsonStr;
  }

  public static String course2Json(Course course) {
    ObjectMapper objectMapper = new ObjectMapper();
    String jsonStr = "";
    try {
      jsonStr = objectMapper.writeValueAsString(course);
      System.out.println(jsonStr);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return jsonStr;
  }

  public static void main(String[] args) {
    User user1 = new User("user1", "user1", "user1");
    User user2 = new User("user2", "user2", "user2");
    ArrayList<User> users = new ArrayList<User>();
    users.add(user1);
    users.add(user2);
    Object2Json.user2Json(user1);
    Object2Json.user2Json(user2);
    ObjectMapper objectMapper = new ObjectMapper();
    String jsonStr = Object2Json.users2Json(users);
    try {
      objectMapper.writeValue(new File("testJson.json"), jsonStr);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    File file = new File("testJson.json");
    String s = "";
    System.out.println(users.toString());

    users.clear();
    System.out.println(users.toString());
    try {
      // objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
      objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
      s = new ObjectMapper().readValue(file, String.class);
      // System.out.println(s);
    } catch (IOException e) {
      e.printStackTrace();
    }
    try {
      Collections.addAll(users, objectMapper.readValue(s, User[].class));
    } catch (JsonProcessingException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    System.out.println(users.toString());

    // https://stackoverflow.com/questions/17862418/how-to-get-a-value-from-a-json-string-using-jackson-library
    ObjectNode objectNode1 = objectMapper.createObjectNode();
    // objectNode1.get(fieldName).asText();
    JSONObject jsonObject = new JSONObject(); 
  }
}