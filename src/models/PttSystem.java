package models;

import java.util.ArrayList;

import models.users.Teacher;

public class PttSystem {
    private ArrayList<RequirementList> data = new ArrayList<RequirementList>();
    private ArrayList<Teacher> teachers = new ArrayList<Teacher>();

    public PttSystem() {
        data.clear();
        teachers.clear();
    }

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

    /**
     * @param teachers the teachers to set
     */
    public void setTeachers(ArrayList<Teacher> teachers) {
        this.teachers = teachers;
    }

    /**
     * Get a teacher according to input username inside the teacher ArrayList
     * 
     * @param name a String, the input username
     * @return a Teacher, the teacher with the input username
     */
    public Teacher getTeacher(String name) {
        for (Teacher teacher : teachers) {
            if (teacher.getName().equals(name)) {
                return teacher;
            }
        }
        return null;
    }
}