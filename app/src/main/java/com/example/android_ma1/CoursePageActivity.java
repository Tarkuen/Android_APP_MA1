package com.example.android_ma1;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.support.design.widget.Snackbar;

import com.example.android_ma1.datamodels.Course;
import com.example.android_ma1.datamodels.Rating;

public class CoursePageActivity extends AppCompatActivity {

    Course course;
    Rating r;

    String course_name;
    String teacher_name;

    String teacher_id;
    String student_ID;

    TextView c_name;
    TextView t_name;

    Button rate;
    Button already_rated;
    Button mail;

    RatingBar ratingBar1;
    RatingBar ratingBar2;
    RatingBar ratingBar3;
    RatingBar ratingBar4;
    RatingBar ratingBar5;
    RatingBar ratingBar6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DatabaseHandler db = new DatabaseHandler(getApplicationContext());
        SQLiteDatabase database = db.getWritableDatabase();

        setContentView(R.layout.activity_course_page);



        if(getIntent().getExtras()!= null){

            try{
                course = getIntent().getExtras().getParcelable(getString(R.string.course_parcel));
                course_name = course.getName();
                teacher_name = course.getTeacher().getName();
            }
            catch (AssertionError ae){
                System.out.println(ae.getMessage());
                course_name = " ";
                teacher_name = " ";
            }

            student_ID = getString(R.string.student_id);
            teacher_id = String.valueOf(course.getTeacher_id());
            String target = DatabaseContract.rating_where_clause();

            String[] values = {student_ID, teacher_id};

            r = db.fetch_one_rating(database, target, values);

        }
        else{
            course_name = " ";
            teacher_name = " ";
        }

        init();

    }

    public void init(){

        final DatabaseHandler db = new DatabaseHandler(getApplicationContext());
        final SQLiteDatabase database = db.getWritableDatabase();
        View parentLayout = findViewById(android.R.id.content);

        final Intent intent = new Intent(CoursePageActivity.this, MainActivity.class);

        c_name = (TextView) findViewById(R.id.course_name);
        c_name.setText(course_name);

        t_name = (TextView) findViewById(R.id.course_teacher);
        t_name.setText(teacher_name);

        already_rated = (Button) findViewById(R.id.delete_rating);
        rate = (Button) findViewById(R.id.course_rate);
        mail = (Button) findViewById(R.id.course_mail);

        ratingBar1 = (RatingBar) findViewById(R.id.ratingBar1);
        ratingBar2 = (RatingBar) findViewById(R.id.ratingBar2);
        ratingBar3 = (RatingBar) findViewById(R.id.ratingBar3);
        ratingBar4 = (RatingBar) findViewById(R.id.ratingBar4);
        ratingBar5 = (RatingBar) findViewById(R.id.ratingBar5);
        ratingBar6 = (RatingBar) findViewById(R.id.ratingBar6);

        final Snackbar snackbar = Snackbar.make(parentLayout, getString(R.string.rating_warning), Snackbar.LENGTH_LONG);
        final Snackbar post_snackbar = Snackbar.make(parentLayout, getString(R.string.rating_deleted), Snackbar.LENGTH_LONG);

        post_snackbar.setAction(getString(R.string.OK), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });

        if(r.getId()!=-1){
            already_rated.setVisibility(View.VISIBLE);
            rate.setVisibility(View.INVISIBLE);

            String[] values = new String[] {teacher_id};
            int[] rating = db.fetch_teacher_ratings(database, values);

            ratingBar1.setRating(rating[0]);
            ratingBar2.setRating(rating[1]);
            ratingBar3.setRating(rating[2]);
            ratingBar4.setRating(rating[3]);
            ratingBar5.setRating(rating[4]);
            ratingBar6.setRating(rating[5]);

        }

        rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                assert !student_ID.equalsIgnoreCase(" ");
                assert !teacher_id.equalsIgnoreCase(" ");

                String results = String.valueOf(ratingBar1.getRating());
                String results1 = String.valueOf(ratingBar2.getRating());
                String results2 = String.valueOf(ratingBar3.getRating());
                String results3 = String.valueOf(ratingBar4.getRating());
                String results4 = String.valueOf(ratingBar5.getRating());
                String results5 = String.valueOf(ratingBar6.getRating());

                String[] ratings = new String[]{results, results1, results2, results3,results4,results5,student_ID,teacher_id};

                db.insert_one_rating(database, ratings);

                Intent mail_message = new Intent(Intent.ACTION_SEND);

//                Enconding
                    mail_message.setType(getString(R.string.mail_type));

//                Adresse
                    String reciever = course.getTeacher().getMail();
                    mail_message.putExtra(Intent.EXTRA_EMAIL, new String[]{reciever});

//                Subject
                    String subject = getString(R.string.mail_greeting)+ student_ID;
                    mail_message.putExtra(Intent.EXTRA_SUBJECT, subject);

//                Body
                    String body = getString(R.string.mail_greeting)+
                           getResources().getString(R.string.rat1)+' '+ ratingBar1.getRating()+'\n'+
                            getResources().getString(R.string.rat2)+' '+ ratingBar2.getRating()+'\n'+
                            getResources().getString(R.string.rat3)+' '+ ratingBar3.getRating()+'\n'+
                            getResources().getString(R.string.rat4)+' '+ ratingBar4.getRating()+'\n'+
                            getResources().getString(R.string.rat5)+' '+ ratingBar5.getRating()+'\n'+
                            getResources().getString(R.string.rat6)+' '+ ratingBar6.getRating()+'\n'+
                            '\n'+getString(R.string.mail_course)+ course_name +
                            '\n'+ getString(R.string.mail_student)+student_ID+" ";

                    mail_message.putExtra(Intent.EXTRA_TEXT, body);

                    try{
                        rate.setVisibility(View.INVISIBLE);
                        already_rated.setVisibility(View.VISIBLE);
                        startActivity(mail_message);
                    }
                    catch (Exception e){
                        System.out.println(R.string.mail_exception);
                        System.out.println(e.getMessage());
                    }
                }
                catch (AssertionError ae){
                    System.out.println(ae.getMessage());
                }
//                startActivity(intent);
            }
        });

        mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mail_message = new Intent(Intent.ACTION_SEND);

//                Enconding
                mail_message.setType(getString(R.string.mail_type));

//                Adresse
                String reciever = course.getTeacher().getMail();
                mail_message.putExtra(Intent.EXTRA_EMAIL, new String[]{reciever});

//                Subject
                String subject = getString(R.string.mail_greeting)+ student_ID;
                mail_message.putExtra(Intent.EXTRA_SUBJECT, subject);

//                Body
                String body = getString(R.string.mail_greeting)
                        +teacher_name+R.string.mail_student+student_ID
//                        +"\n This body could be dynamic. It's sent from the Course rating app."
                    ;

                mail_message.putExtra(Intent.EXTRA_TEXT, body);
                try{
                    startActivity(mail_message);
                }
                catch (Exception e){
                    System.out.println(getString(R.string.mail_exception));
                    System.out.println(e.getMessage());
                }
            }
        });

        already_rated.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final String[] values = new String[]{student_ID, teacher_id};

                snackbar.setAction(getString(R.string.OK), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        db.delete_one_rating(database,values);
                        snackbar.dismiss();

                        post_snackbar.show();
                        snackbar.show();
                    }
                });

                snackbar.show();

            }
        });

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
//        TODO: Add state info from: All ratingbars.
        super.onSaveInstanceState(outState);

        outState.putFloat(getString(R.string.rat1), ratingBar1.getRating());
        outState.putFloat(getString(R.string.rat2), ratingBar2.getRating());
        outState.putFloat(getString(R.string.rat3), ratingBar3.getRating());
        outState.putFloat(getString(R.string.rat4), ratingBar4.getRating());
        outState.putFloat(getString(R.string.rat5), ratingBar5.getRating());
        outState.putFloat(getString(R.string.rat6), ratingBar6.getRating());

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if(savedInstanceState!=null){
            ratingBar1.setRating(savedInstanceState.getFloat(getString(R.string.rat1)));
            ratingBar2.setRating(savedInstanceState.getFloat(getString(R.string.rat2)));
            ratingBar3.setRating(savedInstanceState.getFloat(getString(R.string.rat3)));
            ratingBar4.setRating(savedInstanceState.getFloat(getString(R.string.rat4)));
            ratingBar5.setRating(savedInstanceState.getFloat(getString(R.string.rat5)));
            ratingBar6.setRating(savedInstanceState.getFloat(getString(R.string.rat6)));
        }
    }
}
