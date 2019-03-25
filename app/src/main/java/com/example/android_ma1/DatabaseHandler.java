package com.example.android_ma1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android_ma1.datamodels.Course;
import com.example.android_ma1.datamodels.Rating;
import com.example.android_ma1.datamodels.Student;
import com.example.android_ma1.datamodels.Teacher;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    public DatabaseHandler(Context context) {
        super(context,DatabaseContract.getDbName(), null, 1);
    }

    public boolean check_table(SQLiteDatabase db){
        try{
            String[] values = new String[]{"table", DatabaseContract.getSTUDENTS()};
            String[] columns = {"name"};
            String where_clause = "type = ? AND name = ?";
            Cursor result = db.query("sqlite_master",columns,where_clause, values,null,null,null);
            if(result.getCount()==1){
                result.close();
                return false;
            }
            else{
                result.close();
                return true;
            }

        }catch (RuntimeException e){
            System.out.println(e.getMessage());
            return true;
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            db.execSQL(DatabaseContract.create_table_students());
        }
        catch (RuntimeException e){
            System.out.println(e.getMessage());
//            Log.i(Resources.getSystem().getString(R.string.table_exception), Resources.getSystem().getString(R.string.STUDENTS));
        }
        try{
            db.execSQL(DatabaseContract.create_table_teachers());
        }
        catch (RuntimeException e){
            System.out.println(e.getMessage());
//            Log.i(Resources.getSystem().getString(R.string.table_exception), Resources.getSystem().getString(R.string.TEACHERS));
        }
        try{
            db.execSQL(DatabaseContract.create_table_courses());
        }
        catch (RuntimeException e){
            System.out.println(e.getMessage());
//            Log.i(Resources.getSystem().getString(R.string.table_exception), Resources.getSystem().getString(R.string.COURSES));
        }
        try{
            db.execSQL(DatabaseContract.create_table_ratings());
        }
        catch (RuntimeException e){
            System.out.println(e.getMessage());
  //          Log.i(Resources.getSystem().getString(R.string.table_exception), Resources.getSystem().getString(R.string.RATINGS));
        }



    }

    public ArrayList<Rating> fetch_all_ratings(SQLiteDatabase db){
        ArrayList<Rating> result_list = new ArrayList<>();

        Cursor result  = db.query(DatabaseContract.getRATINGS(), null,null,null,null,null,null);

        while(result.moveToNext()){
            int id = result.getInt(result.getColumnIndex(DatabaseContract.getRatings_ID()));
            int[] value = new int[6];
            value[0] = result.getInt(result.getColumnIndex(DatabaseContract.getRatings_VALUE1()));
            value[1] = result.getInt(result.getColumnIndex(DatabaseContract.getRatings_VALUE2()));
            value[2] = result.getInt(result.getColumnIndex(DatabaseContract.getRatings_VALUE3()));
            value[3] = result.getInt(result.getColumnIndex(DatabaseContract.getRatings_VALUE4()));
            value[4] = result.getInt(result.getColumnIndex(DatabaseContract.getRatings_VALUE5()));
            value[5] = result.getInt(result.getColumnIndex(DatabaseContract.getRatings_VALUE6()));

            int from = result.getInt(result.getColumnIndex(DatabaseContract.getRatings_FROM()));
            int to = result.getInt(result.getColumnIndex(DatabaseContract.getRatings_TO()));

            Rating r = new Rating(id, value, from, to);
            result_list.add(r);

        }
        result.close();
        return result_list;

    }

    public List<Course> fetch_all_courses(SQLiteDatabase db){

        List<Course> result_list = new ArrayList<>();

        Cursor result = db.query(DatabaseContract.getCOURSES(), null, null, null,null,null,null);

        while (result.moveToNext()){

            int id = result.getInt(result.getColumnIndex(DatabaseContract.getCourse_ID()));
            String name = result.getString(result.getColumnIndex(DatabaseContract.getCourse_NAME()));
            int t_id = result.getInt(result.getColumnIndex(DatabaseContract.getCourse_TEACHER()));

            String[] val = new String[]{String.valueOf(t_id)};

            Teacher t = fetch_one_teacher(db, val);

            Course c = new Course(id, name, t);

            result_list.add(c);
        }

            result.close();

        return result_list;
    }

    public ArrayList<Teacher> fetch_all_teachers(SQLiteDatabase db){

        ArrayList<Teacher> result_list = new ArrayList<>();

        Cursor result = db.query(DatabaseContract.getTEACHERS(), null, null,null,null,null,null);

        while(result.moveToNext()){

            int id = result.getInt(result.getColumnIndex(DatabaseContract.getTeachers_ID()));
            String name = result.getString(result.getColumnIndex(DatabaseContract.getTeachers_NAME()));
            String mail = result.getString(result.getColumnIndex(DatabaseContract.getTeachers_MAIL()));

            int[] rating = fetch_teacher_ratings(db, new String[]{String.valueOf(id)});
            Teacher t = new Teacher(id,name,mail, rating);
            result_list.add(t);

        }
        result.close();
        return result_list;
    }

    public void insert_one_student(SQLiteDatabase db, String[] values){
        ContentValues subject_values = new ContentValues();
        subject_values.put(DatabaseContract.getStudents_ID(), DatabaseContract.getStudent_row_id());
        subject_values.put(DatabaseContract.getStudents_NAME(), values[0]);
        db.insert(DatabaseContract.getSTUDENTS(), null, subject_values);
        DatabaseContract.setStudent_row_id(DatabaseContract.getStudent_row_id()+1);
    }

    public void insert_one_teacher(SQLiteDatabase db, String values[]){
        ContentValues subject_values = new ContentValues();

        subject_values.put(DatabaseContract.getTeachers_ID(), DatabaseContract.getTeacher_row_id());
        subject_values.put(DatabaseContract.getTeachers_NAME(), values[0]);
        subject_values.put(DatabaseContract.getTeachers_MAIL(), values[1]);

        db.insert(DatabaseContract.getTEACHERS(), null, subject_values);
        DatabaseContract.setTeacher_row_id(DatabaseContract.getTeacher_row_id()+1);
    }

    public void insert_one_course(SQLiteDatabase db, String[] values){
        ContentValues subject_values = new ContentValues();

        subject_values.put(DatabaseContract.getCourse_ID(), DatabaseContract.getCourse_row_id());
        subject_values.put(DatabaseContract.getCourse_NAME(), values[0]);
        subject_values.put(DatabaseContract.getCourse_TEACHER(), values[1]);

        db.insert(DatabaseContract.getCOURSES(), null, subject_values);
        DatabaseContract.setCourse_row_id(DatabaseContract.getCourse_row_id()+1);
    }

    public void insert_one_rating(SQLiteDatabase db, String[] values){
        ContentValues subject_values = new ContentValues();

        subject_values.put(DatabaseContract.getRatings_ID(), DatabaseContract.getRating_row_id());

        subject_values.put(DatabaseContract.getRatings_VALUE1(), values[0]);
        subject_values.put(DatabaseContract.getRatings_VALUE2(), values[1]);
        subject_values.put(DatabaseContract.getRatings_VALUE3(), values[2]);
        subject_values.put(DatabaseContract.getRatings_VALUE4(), values[3]);
        subject_values.put(DatabaseContract.getRatings_VALUE5(), values[4]);
        subject_values.put(DatabaseContract.getRatings_VALUE6(), values[5]);

        subject_values.put(DatabaseContract.getRatings_FROM(), values[6]);
        subject_values.put(DatabaseContract.getRatings_TO(), values[7]);

        db.insert(DatabaseContract.getRATINGS(), null, subject_values);
        DatabaseContract.setRating_row_id(DatabaseContract.getRating_row_id()+1);
    }

    public Course fetch_one_course(SQLiteDatabase db, String[] values){

        Cursor result = db.query(DatabaseContract.getCOURSES(), null, DatabaseContract.getCourse_TEACHER()+" = ?", values,null,null,null);

                int id = -1;
                String name = "";
                int t_id = -1;
                Teacher t = new Teacher();

                while(result.moveToNext()){
                    id = result.getInt(result.getColumnIndex(DatabaseContract.getCourse_ID()));
                    name = result.getString(result.getColumnIndex(DatabaseContract.getCourse_NAME()));
                    t_id = result.getInt(result.getColumnIndex(DatabaseContract.getCourse_TEACHER()));

                    t = fetch_one_teacher(db, new String[]{String.valueOf(t_id)});


                }
                result.close();
        return new Course(id,name,t);
    }

    public Student fetch_one_student(SQLiteDatabase db, String[] values){
        Cursor cursor = db.query(DatabaseContract.getSTUDENTS(), null, "ID = ?", values,null,null,null);
        int id = -1;
        String name = "";
        while(cursor.moveToNext()) {
            id = cursor.getInt(cursor.getColumnIndex(DatabaseContract.getStudents_ID()));
            name = cursor.getString(cursor.getColumnIndex(DatabaseContract.getStudents_NAME()));
        }
        cursor.close();
        return new Student(id,name);
    }

    public Rating fetch_one_rating(SQLiteDatabase db, String[] values){
        Cursor result = db.query(DatabaseContract.getRATINGS(), null, "ID = ?", values, null, null, null);
        int id = -1;
        int[] value = new int[6];
        int from = -1;
        int to = -1;
        //TODO: ADD THE NEXT 5 ROWS
        while(result.moveToNext()){
            id = result.getInt(result.getColumnIndex(DatabaseContract.getRatings_ID()));
            value[0] = result.getInt(result.getColumnIndex(DatabaseContract.getRatings_VALUE1()));
            value[1] = result.getInt(result.getColumnIndex(DatabaseContract.getRatings_VALUE2()));
            value[2] = result.getInt(result.getColumnIndex(DatabaseContract.getRatings_VALUE3()));
            value[3] = result.getInt(result.getColumnIndex(DatabaseContract.getRatings_VALUE4()));
            value[4] = result.getInt(result.getColumnIndex(DatabaseContract.getRatings_VALUE5()));
            value[5] = result.getInt(result.getColumnIndex(DatabaseContract.getRatings_VALUE6()));

            from = result.getInt(result.getColumnIndex(DatabaseContract.getRatings_FROM()));
            to = result.getInt(result.getColumnIndex(DatabaseContract.getRatings_TO()));
        }
        result.close();
        return new Rating(id, value,from, to);
    }

    public Rating fetch_one_rating(SQLiteDatabase db, String targetClause, String[] values){
        Cursor result = db.query(DatabaseContract.getRATINGS(), null, targetClause, values, null, null, null);

        int id = -1;
        int[] value = new int[6];
        int from = -1;
        int to = -1;

        while(result.moveToNext()){
            id = result.getInt(result.getColumnIndex(DatabaseContract.getRatings_ID()));
            value[0] = result.getInt(result.getColumnIndex(DatabaseContract.getRatings_VALUE1()));
            value[1] = result.getInt(result.getColumnIndex(DatabaseContract.getRatings_VALUE2()));
            value[2] = result.getInt(result.getColumnIndex(DatabaseContract.getRatings_VALUE3()));
            value[3] = result.getInt(result.getColumnIndex(DatabaseContract.getRatings_VALUE4()));
            value[4] = result.getInt(result.getColumnIndex(DatabaseContract.getRatings_VALUE5()));
            value[5] = result.getInt(result.getColumnIndex(DatabaseContract.getRatings_VALUE6()));

            from = result.getInt(result.getColumnIndex(DatabaseContract.getRatings_FROM()));
            to = result.getInt(result.getColumnIndex(DatabaseContract.getRatings_TO()));
        }
        result.close();
        return new Rating(id, value,from, to);
    }

    public void delete_one_rating(SQLiteDatabase db, String[] values){
        String where_clause = DatabaseContract.getRatings_FROM()+" = ? AND "+
                DatabaseContract.getRatings_TO()+" = ?";

        int del = db.delete(DatabaseContract.getRATINGS(), where_clause, values);

    }

    public int[] fetch_teacher_ratings(SQLiteDatabase db, String[] values){

        String[] columns = new String[]{DatabaseContract.getRatings_VALUE1(),
                DatabaseContract.getRatings_VALUE2(),
                DatabaseContract.getRatings_VALUE3(),
                DatabaseContract.getRatings_VALUE4(),
                DatabaseContract.getRatings_VALUE5(),
                DatabaseContract.getRatings_VALUE6()
        };
        Cursor cursor = db.query(DatabaseContract.getRATINGS(), columns, DatabaseContract.rating_teacher_clause(), values, null, null, null);

        int[] rating = new int[6];
        int counter = 1;

        while(cursor.moveToNext()){
                rating[0] = cursor.getInt(cursor.getColumnIndex(DatabaseContract.getRatings_VALUE1()))/counter;
                rating[1] = cursor.getInt(cursor.getColumnIndex(DatabaseContract.getRatings_VALUE2()))/counter;
                rating[2] = cursor.getInt(cursor.getColumnIndex(DatabaseContract.getRatings_VALUE3()))/counter;
                rating[3] = cursor.getInt(cursor.getColumnIndex(DatabaseContract.getRatings_VALUE4()))/counter;
                rating[4] = cursor.getInt(cursor.getColumnIndex(DatabaseContract.getRatings_VALUE5()))/counter;
                rating[5] = cursor.getInt(cursor.getColumnIndex(DatabaseContract.getRatings_VALUE6()))/counter;

//            else{
//                rating[0] += cursor.getInt(cursor.getColumnIndex(DatabaseContract.getRatings_VALUE1()))/counter;
//                rating[1] += cursor.getInt(cursor.getColumnIndex(DatabaseContract.getRatings_VALUE2()))/counter;
//                rating[2] += cursor.getInt(cursor.getColumnIndex(DatabaseContract.getRatings_VALUE3()))/counter;
//                rating[3] += cursor.getInt(cursor.getColumnIndex(DatabaseContract.getRatings_VALUE4()))/counter;
//                rating[4] += cursor.getInt(cursor.getColumnIndex(DatabaseContract.getRatings_VALUE5()))/counter;
//                rating[5] += cursor.getInt(cursor.getColumnIndex(DatabaseContract.getRatings_VALUE6()))/counter;
//            }



            counter++;
        }
        cursor.close();

        return rating;
    }

    public Teacher fetch_one_teacher(SQLiteDatabase db, String[] values){

        Cursor cursor = db.query(DatabaseContract.getTEACHERS(), null, DatabaseContract.getTeachers_ID()+" = ?", values,null,null,null);

        while(cursor.moveToNext()){

            int id = cursor.getInt(cursor.getColumnIndex(DatabaseContract.getTeachers_ID()));
            String name = cursor.getString(cursor.getColumnIndex(DatabaseContract.getTeachers_NAME()));
            String mail = cursor.getString(cursor.getColumnIndex(DatabaseContract.getTeachers_MAIL()));

            cursor.close();
            return new Teacher(id,name,mail);
        }
        cursor.close();
        return new Teacher();
    }

    public void delete_all_tables(SQLiteDatabase db){
        db.execSQL(DatabaseContract.delete_all_tables());
    }

    public void onUpgrade(SQLiteDatabase db, String name){
        db.execSQL(DatabaseContract.delete_table(name));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
