package com.example.firebaseapp;

import com.google.firebase.firestore.Exclude;

import java.io.Serializable;

public class Courses implements Serializable {


    //we have to implement our modal class with serializable so that we can pass our object class to our new activity on item click of recyclerview

    //getter method for our id
    public String getId(){
        return id;
    }

    //setter method for our id
    public void setId(String id){
        this.id = id;
    }

    //we are using exclude because we are not saving our id
    @Exclude
    private String id;

    //variable for storing our data
    private String courseName, courseDescription, courseDuration;

    public Courses(){
        //empty constructor required for Firebase
    }

    //constructor for all variables
    public Courses(String courseName, String courseDescription, String courseDuration){
        this.courseName = courseName;
        this.courseDescription = courseDescription;
        this.courseDuration = courseDuration;
    }

    //getter and setter method for all variables
    public String getCourseName(){
        return courseName;
    }

    public void setCourseName(String courseName){
        this.courseName = courseName;
    }

    public String getCourseDescription(){
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription){
        this.courseDescription = courseDescription;
    }

    public String getCourseDuration(){
        return courseDuration;
    }

    public void setCourseDuration(String courseDuration){
        this.courseDuration = courseDuration;
    }

}

