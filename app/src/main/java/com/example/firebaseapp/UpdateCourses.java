package com.example.firebaseapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

public class UpdateCourses extends AppCompatActivity {

    //creating variable for our edit text
    private EditText courseNameEdt, courseDurationEdt, courseDescriptionEdt;

    //creating strings for storing our values from Edittext fields
    private String courseName, courseDuration, courseDescription;

    //creating a variable for firebaseFirestore
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_courses);
        Courses courses = (Courses) getIntent().getSerializableExtra("courses");

        //getting our instance from Firebase Firestore.
        db = FirebaseFirestore.getInstance();

        //initializing our edittext and buttons
        courseNameEdt = findViewById(R.id.idEdtCourseName);
        courseDurationEdt = findViewById(R.id.idEdtCourseDuration);
        courseDescriptionEdt = findViewById(R.id.idEdtCourseDescription);

        //creating a variable for the button
        Button updateCourseBtn = findViewById(R.id.idBtnUpdateCourse);

        courseNameEdt.setText(courses.getCourseName());
        courseDescriptionEdt.setText(courses.getCourseDescription());
        courseDurationEdt.setText(courses.getCourseDescription());

        updateCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                courseName = courseNameEdt.getText().toString();
                courseDescription = courseDescriptionEdt.getText().toString();
                courseDuration = courseDurationEdt.getText().toString();

                //validating if the text fields are empty or not
                if (TextUtils.isEmpty(courseName)){
                    courseNameEdt.setError("Please enter Course name");
                } else if (TextUtils.isEmpty(courseDescription)){
                    courseDescriptionEdt.setError("Please enter course Description");
                } else if (TextUtils.isEmpty(courseDuration)){
                    courseDurationEdt.setError("Please enter Course Duration");
                } else {
                    //calling a method to update our courses
                    updateCourses(courses, courseName, courseDescription, courseDuration);
                }

            }
        });

    }

    private void updateCourses(Courses courses, String courseName, String courseDescription, String courseDuration){

        //inside this method we are passing the updated values inside our object class and later onwe will pass the whole object to FirebaseFirestore
        Courses updatedCourse = new Courses(courseName, courseDescription, courseDescription);

        //after passing the data to object class we are sending it to Firebase with specific document id
        db.collection("Courses").document(courses.getId()).set(updatedCourse).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                //on successful completion, we are displaying a toast message
                Toast.makeText(UpdateCourses.this, "Course has been updated", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(UpdateCourses.this, "Failed to update courses", Toast.LENGTH_SHORT).show();
            }
        });
    }
}