package com.example.android_ma1;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.android_ma1.datamodels.Course;
import com.example.android_ma1.datamodels.Teacher;

import java.util.ArrayList;

public class TopTeacherActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_teacher);

        if(getIntent().getExtras()!= null){
            ArrayList<Teacher> teacher_toplist = getIntent().getExtras().getParcelableArrayList(getString(R.string.teachers_list_parcel));
            init(teacher_toplist);
        }



    }


    public void init(final ArrayList<Teacher> result_list){

        final DatabaseHandler db = new DatabaseHandler(getApplicationContext());
        final SQLiteDatabase database = db.getWritableDatabase();

        listView = (ListView) findViewById(R.id.listView);
        ArrayAdapter<Teacher> adapter = new ArrayAdapter<>(this, android.R.layout.select_dialog_item, result_list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(TopTeacherActivity.this, CoursePageActivity.class);

                Teacher t = (Teacher) parent.getItemAtPosition(position);

                Course c = db.fetch_one_course(database, new String[]{String.valueOf(t.getId())});

                if(c.getID()!=-1){
                    intent.putExtra(getString(R.string.course_parcel ),c);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }
}
