package com.example.android_ma1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.android_ma1.datamodels.Course;

import java.util.ArrayList;

public class CoursesActivity extends AppCompatActivity {

    Spinner course_spinner;
    ArrayList<Course> result_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);


        if(getIntent().getExtras()!=null){
            result_list = (ArrayList<Course>) getIntent().getSerializableExtra(getString(R.string.course_list_parcel));}

        init();



    }

    public void init(){

        course_spinner = (Spinner) findViewById(R.id.course_drop_down);
        ArrayAdapter<Course> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, result_list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        course_spinner.setAdapter(adapter);

        course_spinner.setSelection(0, false);
        course_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Course c = result_list.get(position);

                Intent intent = new Intent(CoursesActivity.this, CoursePageActivity.class);
                intent.putExtra(getString(R.string.course_parcel), c);
                startActivity(intent);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.i(getString(R.string.empty_select),getString(R.string.empty_select));
            }
        });
    }

}
