package com.example.firebaseapp;

public class Courses {

    //variable for storing data
    private String courseName, courseDescription, courseDuration;

    public Courses(){
        //empty constructor required for firebase.

    }

    //constructor for all variables
    public Courses(String courseName, String courseDuration, String courseDescription){
        this.courseName = courseName;
        this.courseDuration = courseDuration;
        this.courseDescription = courseDescription;
    }

    //getter method for all our variables
    public String getCourseName(){
        return courseName;
    }

    public void setCourseName(String courseName){
        this.courseName = courseName;
    }

    public String getCourseDuration() {
        return courseDuration;
    }

    public void setCourseDuration(String courseDuration) {
        this.courseDuration = courseDuration;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }
}

