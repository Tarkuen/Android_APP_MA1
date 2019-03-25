package com.example.android_ma1;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.android_ma1.datamodels.Course;
import com.example.android_ma1.datamodels.Teacher;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    boolean start;
    Button b_courses;
    Button b_teachers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final DatabaseHandler db = new DatabaseHandler(getApplicationContext());

        final SQLiteDatabase database = db.getWritableDatabase();

        init(db, database);

    }

    public void drop_all_tables(DatabaseHandler db, SQLiteDatabase database){

        db.onUpgrade(database, DatabaseContract.getTEACHERS());
        DatabaseContract.setTeacher_row_id(1);
        db.onUpgrade(database, DatabaseContract.getSTUDENTS());
        DatabaseContract.setStudent_row_id(1);
        db.onUpgrade(database, DatabaseContract.getCOURSES());
        DatabaseContract.setCourse_row_id(1);
        db.onUpgrade(database, DatabaseContract.getRATINGS());
        DatabaseContract.setRating_row_id(1);
    }

    public void init(final DatabaseHandler db, final SQLiteDatabase database){

        start = db.check_table(database);
        System.out.println(start);

        if(start){

        db.onCreate(database);

        String[] teacher_v ={"John Nielsen","JN@kea.dk", "0"};
        db.insert_one_teacher(database, teacher_v);
        teacher_v =new String[]{"Ygrid Daniella","YD@kea.dk", "0"};
        db.insert_one_teacher(database, teacher_v);
        teacher_v =new String[]{"Theis Karlsen","TK@kea.dk", "0"};
        db.insert_one_teacher(database, teacher_v);
        teacher_v =new String[]{"Helena Nordberg","HN@kea.dk", "0"};
        db.insert_one_teacher(database, teacher_v);

        String[] values = {"SWD", "4"};
        db.insert_one_course(database, values);
        values = new String[]{"SWC", "3"};
        db.insert_one_course(database, values);
        values = new String[]{"TECH","2"};
        db.insert_one_course(database, values);
        values = new String[]{"AP", "1"};
        db.insert_one_course(database, values);

        start = false;




        }
        b_courses = (Button) findViewById(R.id.button_courses);
        b_teachers = (Button) findViewById(R.id.button_teachers);


        b_courses.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                List<Course> temp = db.fetch_all_courses(database);

                ArrayList<Course> results = new ArrayList<>(temp);

                Intent intent = new Intent(MainActivity.this, CoursesActivity.class);

                intent.putExtra(getString(R.string.course_list_parcel), results);

                startActivity(intent);
            }
        });

        b_teachers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Teacher> result_list = db.fetch_all_teachers(database);

                Intent teacher_top = new Intent(MainActivity.this, TopTeacherActivity.class);
                Collections.sort(result_list);

                teacher_top.putExtra(getString(R.string.teachers_list_parcel), result_list);
                startActivity(teacher_top);

            }
        });


    }





}
