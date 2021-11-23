package com.example.firebaseapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class CourseDetails extends AppCompatActivity {

    //creating variables for our recycler view, array list, adapter, Firebase Firestore and our progress bar.
    private RecyclerView courseRV;
    private ArrayList<Courses>coursesArrayList;
    private CourseRVAdapter courseRVAdapter;
    private FirebaseFirestore db;
    ProgressBar loadingPB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_details);

        //initializing our variables.
        courseRV = findViewById(R.id.idRVCourses);
        loadingPB = findViewById(R.id.idProgressBar);

        //initializing our variable for firebase firestore and getting its instance
        db = FirebaseFirestore.getInstance();

        //creating our new array list
        coursesArrayList = new ArrayList<>();
        courseRV.setHasFixedSize(true);
        courseRV.setLayoutManager(new LinearLayoutManager(this));

        //adding our array list to our recycler view adapter class
        courseRVAdapter = new CourseRVAdapter(coursesArrayList, this);

        //setting adapter to our recycler view
        courseRV.setAdapter(courseRVAdapter);

        //below line is used to get the data form FirebaseFirestore
        db.collection("Courses").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        //after getting the data we are calling the success method and inside this method we are checking if the received snapshot is empty or not.
                        if (!queryDocumentSnapshots.isEmpty()){
                            loadingPB.setVisibility(View.GONE);
                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();

                            for (DocumentSnapshot d:list){
                                //after getting this we are passing tis to our object class

                                Courses c = d.toObject(Courses.class);
                                //we will pass this object class inside our arraylist which we created for the recycler view.
                                coursesArrayList.add(c);


                            }

                            courseRVAdapter.notifyDataSetChanged();
                        } else {
                            //if the snapshot is empty we are displaying a toast message
                            Toast.makeText(CourseDetails.this, "No data found is Database", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                //if we don't get any data and any error, we are displaying a toast message that we do not get any data
                Toast.makeText(CourseDetails.this, "Failed to get the data", Toast.LENGTH_SHORT).show();
            }
        });


    }
}