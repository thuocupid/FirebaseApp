package com.example.firebaseapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CourseRVAdapter extends RecyclerView.Adapter<CourseRVAdapter.ViewHolder> {

    //creating variables for our ArrayList and context
    private ArrayList<Courses>coursesArrayList;
    private Context context;

    //creating constructor for our Adapter class
    public CourseRVAdapter(ArrayList<Courses>coursesArrayList, Context context){
        this.coursesArrayList = coursesArrayList;
        this.context = context;
    }

    public CourseRVAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        //passin gour layout file for displaying our card item
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.course_item, parent, false));
    }

    public void onBindViewHolder(CourseRVAdapter.ViewHolder holder, int position){
        //setting data to our text views from our modal class
        Courses courses = coursesArrayList.get(position);
        holder.courseNameTV.setText(courses.getCourseName());
        holder.courseDurationTV.setText(courses.getCourseDuration());
        holder.courseDescriptionTV.setText(courses.getCourseDescription());
    }

    public int getItemCount(){
        //returning the size of our array list
        return coursesArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        //creating variable for our text view
        private final TextView courseNameTV;
        private final TextView courseDurationTV;
        private final TextView courseDescriptionTV;

        public ViewHolder(View itemView){
            super(itemView);
            //initializing our text view
            courseNameTV = itemView.findViewById(R.id.idTVCourseName);
            courseDescriptionTV = itemView.findViewById(R.id.idTVcourseDescription);
            courseDurationTV = itemView.findViewById(R.id.idTVcourseDuration);
        }
    }

}
