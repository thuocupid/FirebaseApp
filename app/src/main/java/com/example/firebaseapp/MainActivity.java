package com.example.firebaseapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {

    //creating a variable for our edit text
    private EditText courseNameEdt, courseDurationEdt, courseDescriptionEdt;

    //creating a variable for the button
    private Button submitCourseBtn, viewCoursesBtn;

    //creating a string for storing our values from edittext fields
    private String courseName, courseDuration, courseDescription;

    //creating a variable for firebase firestore
    private FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //getting our instance from Firebase Firestore
        db = FirebaseFirestore.getInstance();

        //initializing our edittext and buttons
        courseNameEdt = findViewById(R.id.idEdtCourseName);
        courseDurationEdt = findViewById(R.id.idEdtCourseDuration);
        courseDescriptionEdt = findViewById(R.id.idEdtCourseDescription);
        submitCourseBtn = findViewById(R.id.idBtnSubmitCourse);
        viewCoursesBtn = findViewById(R.id.idBtnViewCourses);

        //adding onclick listener to view data in new activity
        viewCoursesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //opening new activity on button click
                Intent i = new Intent(MainActivity.this, CourseDetails.class);
                startActivity(i);
            }
        });

        //adding a click listener for our button
        submitCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getting data from edittext fields
                courseName = courseNameEdt.getText().toString();
                courseDescription = courseDescriptionEdt.getText().toString();
                courseDuration = courseDurationEdt.getText().toString();

                //validating if the field is empty or not
                if (TextUtils.isEmpty(courseName)){
                    courseNameEdt.setError("Please enter Course Name");
                } else if (TextUtils.isEmpty(courseDuration)){
                    courseDurationEdt.setError("Please Enter course duration");
                } else if (TextUtils.isEmpty(courseDescription)){
                    courseDescriptionEdt.setError("Please enter Course Description");
                } else {
                    //calling method to add data to our firebase firestore
                    addDataToFirestore(courseName, courseDescription,courseDuration);
                }

            }
        });

    }

    private void addDataToFirestore(String courseName, String courseDescription, String courseDuration){
        //creating a collection reference for our Firebase Firestore database.
        CollectionReference dbCourses = db.collection("Courses");

        //adding data to our Courses object class
        Courses courses = new Courses(courseName, courseDescription, courseDuration);

        //below method is used to add data to our Firebase Firestore.
        dbCourses.add(courses).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                //after addition of data is successful, we are displaying a toast message
                Toast.makeText(MainActivity.this, "Your course has been added to the database", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                //this method is called when the data addition is failed.
                //displaying a Toast message when the data addition is failed
                Toast.makeText(MainActivity.this, "Failed to add courses/n" + e, Toast.LENGTH_SHORT).show();
            }
        });
    }

}