package models;

import java.util.HashMap;

/**
 * Singleton class
 */
public abstract class AttendanceList {
  private static HashMap<Course, Boolean> attendance = new HashMap<Course, Boolean>();

  /**
   * @return the attendance
   */
  public static HashMap<Course, Boolean> getAttendance() {
    return attendance;
  }

  /**
   * @param attendance the attendance to set
   */
  public static void setAttendance(HashMap<Course, Boolean> attendance) {
    AttendanceList.attendance = attendance;
  }


}