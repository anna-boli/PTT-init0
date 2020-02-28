package models;

import java.util.ArrayList;

import models.users.Teacher;

public class PTT_System {
    private ArrayList<RequirementList> data = new ArrayList<RequirementList>();
    private ArrayList<Teacher> teachers = new ArrayList<Teacher>();

    public void addToData(RequirementList newList) {
        data.add(newList);
    }

    public void addTeacherToData(Teacher newTeacher) {
        teachers.add(newTeacher);
    }

    // getters and setters
    public ArrayList<RequirementList> getData() {
        return data;
    }

    public ArrayList<Teacher> getTeachers() {
        return teachers;
    }
}