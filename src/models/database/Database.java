package models.database;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONObject;

import models.Model;

public class Database {
  private static final String FILE_NAME = "model.json";

  public static void save(Model model) {
    JSONObject jsonModel = Object2Json.model2Json(model);
    try (FileWriter file = new FileWriter(FILE_NAME)) {
      file.write(jsonModel.toString());
      file.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static JSONObject file2Json() {
    String content = "";
    try {
      content = new String(Files.readAllBytes(Paths.get(FILE_NAME)));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return new JSONObject(content);
  }

  public static Model load() {
    return Json2Object.json2Model(Database.file2Json());
  }

  public static boolean isDataExist() {
    File file = new File(FILE_NAME);
    if (file.exists() && file.isFile()) {
      return true;
    }
    return false;
  }
}