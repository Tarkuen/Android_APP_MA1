package com.example.android_ma1;

import android.app.Application;
import android.content.res.Resources;

public class DatabaseContract extends Application {

    private static DatabaseContract db = DatabaseContract.getInstance();
//    Overall document
    private static final String DB_NAME = "Courses.db";

//    Courses Table
    private static final String COURSES = "courses_table";
    private static final String course_ID = "ID";
    private static final String course_NAME = "name";
    private static final String course_TEACHER = "teacher_FK";
    private static int course_row_id = 1;

//    Students Table
    private static final String STUDENTS =  "students_table";
    private static final String students_ID = "ID";
    private static final String students_NAME= "name";
    private static int student_row_id = 1;

//    Teacher Table
    private static final String TEACHERS = Resources.getSystem().getString(R.string.TEACHERS);
    private static final String teachers_ID = Resources.getSystem().getString(R.string.ID);
    private static final String teachers_NAME= Resources.getSystem().getString(R.string.name);
    private static final String teachers_MAIL= Resources.getSystem().getString(R.string.teachers_mail);

    private static final String teachers_rat1= Resources.getSystem().getString(R.string.trat1);
    private static final String teachers_rat2= Resources.getSystem().getString(R.string.trat2);
    private static final String teachers_rat3= Resources.getSystem().getString(R.string.trat3);
    private static final String teachers_rat4= Resources.getSystem().getString(R.string.trat4);
    private static final String teachers_rat5= Resources.getSystem().getString(R.string.trat5);
    private static final String teachers_rat6= Resources.getSystem().getString(R.string.trat6);
    private static int teacher_row_id = 1;

//    Rating Table
    private static final String RATINGS = Resources.getSystem().getString(R.string.RATINGS);
    private static final String ratings_ID = Resources.getSystem().getString(R.string.ID);
    private static final String ratings_VALUE1 = Resources.getSystem().getString(R.string.trat1);
    private static final String ratings_VALUE2 = Resources.getSystem().getString(R.string.trat2);
    private static final String ratings_VALUE3 = Resources.getSystem().getString(R.string.trat3);
    private static final String ratings_VALUE4 = Resources.getSystem().getString(R.string.trat4);
    private static final String ratings_VALUE5 = Resources.getSystem().getString(R.string.trat5);
    private static final String ratings_VALUE6 = Resources.getSystem().getString(R.string.trat6);

    private static final String ratings_FROM= Resources.getSystem().getString(R.string.ratings_from);
    private static final String ratings_TO= Resources.getSystem().getString(R.string.ratings_to);
    private static int rating_row_id = 1;

    public static DatabaseContract getInstance(){
        if(db == null){
            db = new DatabaseContract();
            return db;
        }
        else{
            return db;
        }
    }

    private DatabaseContract() {

    }

    public static String create_table_students(){
        return Resources.getSystem().getString(R.string.create_table)+STUDENTS
                +Resources.getSystem().getString(R.string.open_table)+
                students_ID + Resources.getSystem().getString(R.string.int_pk)+", "+
                students_NAME +Resources.getSystem().getString(R.string.text)
                +Resources.getSystem().getString(R.string.close_table);
    }

    public static String create_table_teachers(){
       return Resources.getSystem().getString(R.string.create_table)+ TEACHERS
               + Resources.getSystem().getString(R.string.open_table)+
                teachers_ID + Resources.getSystem().getString(R.string.int_pk)+", "+
                teachers_NAME +Resources.getSystem().getString(R.string.text)+", "+
                teachers_MAIL +Resources.getSystem().getString(R.string.text)+", "+

                teachers_rat1 + Resources.getSystem().getString(R.string.integer)+", "+
                teachers_rat2+ Resources.getSystem().getString(R.string.integer)+", "+
                teachers_rat3+ Resources.getSystem().getString(R.string.integer)+", "+
                teachers_rat4+ Resources.getSystem().getString(R.string.integer)+", "+
                teachers_rat5+ Resources.getSystem().getString(R.string.integer)+", "+
                teachers_rat6+ Resources.getSystem().getString(R.string.integer)+" "+

               Resources.getSystem().getString(R.string.close_table);
    }

    public static String create_table_courses(){
        return Resources.getSystem().getString(R.string.create_table)+ COURSES
                +Resources.getSystem().getString(R.string.open_table)+
                course_ID +Resources.getSystem().getString(R.string.int_pk)+", "+
                course_NAME +Resources.getSystem().getString(R.string.text)+", "+
                course_TEACHER + Resources.getSystem().getString(R.string.integer)+
                Resources.getSystem().getString(R.string.close_table);
    }

    public static String create_table_ratings(){
        return Resources.getSystem().getString(R.string.create_table)+ RATINGS +
                Resources.getSystem().getString(R.string.open_table)+
                ratings_ID + Resources.getSystem().getString(R.string.int_pk)+", "+
                ratings_VALUE1 + Resources.getSystem().getString(R.string.integer)+", "+
                ratings_VALUE2 + Resources.getSystem().getString(R.string.integer)+", "+
                ratings_VALUE3 + Resources.getSystem().getString(R.string.integer)+", "+
                ratings_VALUE4 + Resources.getSystem().getString(R.string.integer)+", "+
                ratings_VALUE5 + Resources.getSystem().getString(R.string.integer)+", "+
                ratings_VALUE6 + Resources.getSystem().getString(R.string.integer)+", "+
                ratings_FROM + Resources.getSystem().getString(R.string.integer)+", "+
                ratings_TO + Resources.getSystem().getString(R.string.integer)+", "+
                Resources.getSystem().getString(R.string.fk_o)+ ratings_FROM + Resources.getSystem().getString(R.string.fk_c)+
                STUDENTS+Resources.getSystem().getString(R.string.ref_o)+students_ID+Resources.getSystem().getString(R.string.ref_c)+", "+

                Resources.getSystem().getString(R.string.fk_o)+ratings_TO +Resources.getSystem().getString(R.string.fk_c)+TEACHERS+
                Resources.getSystem().getString(R.string.ref_o)+teachers_ID+Resources.getSystem().getString(R.string.ref_c)
                +Resources.getSystem().getString(R.string.close_table)
                ;
    }

    public static String fetch_all(String name){
        return Resources.getSystem().getString(R.string.select_all)+ name;
    }

    public static String rating_where_clause(){
        return DatabaseContract.getRatings_FROM() +
                Resources.getSystem().getString(R.string.where)+
                Resources.getSystem().getString(R.string.and)+
                DatabaseContract.getRatings_TO()+
                Resources.getSystem().getString(R.string.where);
    }

    public static String rating_teacher_clause(){
        return DatabaseContract.getRatings_TO() + Resources.getSystem().getString(R.string.where);
    }

    public static String delete_table(String DB_NAME){
        return Resources.getSystem().getString(R.string.drop)+
                DB_NAME+
                Resources.getSystem().getString(R.string.statement_end);
    }




//    GETTERS & SETTERS

    public static DatabaseContract getDb() {
        return db;
    }

    public static String getDbName() {
        return DB_NAME;
    }

    public static String getCourses() {
        return COURSES;
    }

    public static String getCourse_ID() {
        return course_ID;
    }

    public static String getCourse_NAME() {
        return course_NAME;
    }

    public static String getCOURSES() {
        return COURSES;
    }

    public static String getCourse_TEACHER() {
        return course_TEACHER;
    }

    public static String getSTUDENTS() {
        return STUDENTS;
    }

    public static String getStudents_ID() {
        return students_ID;
    }

    public static String getStudents_NAME() {
        return students_NAME;
    }

    public static String getTEACHERS() {
        return TEACHERS;
    }

    public static String getTeachers_ID() {
        return teachers_ID;
    }

    public static String getTeachers_NAME() {
        return teachers_NAME;
    }

    public static String getTeachers_MAIL() {
        return teachers_MAIL;
    }

    public static String getTeachers_rat1() {
        return teachers_rat1;
    }

    public static String getTeachers_rat2() {
        return teachers_rat2;
    }

    public static String getTeachers_rat3() {
        return teachers_rat3;
    }

    public static String getTeachers_rat4() {
        return teachers_rat4;
    }

    public static String getTeachers_rat5() {
        return teachers_rat5;
    }

    public static String getTeachers_rat6() {
        return teachers_rat6;
    }

    public static String getRATINGS() {
        return RATINGS;
    }

    public static String getRatings_ID() {
        return ratings_ID;
    }

    public static String getRatings_VALUE1() {
        return ratings_VALUE1;
    }

    public static String getRatings_VALUE2() {
        return ratings_VALUE2;
    }

    public static String getRatings_VALUE3() {
        return ratings_VALUE3;
    }

    public static String getRatings_VALUE4() {
        return ratings_VALUE4;
    }

    public static String getRatings_VALUE5() {
        return ratings_VALUE5;
    }

    public static String getRatings_VALUE6() {
        return ratings_VALUE6;
    }

    public static String getRatings_FROM() {
        return ratings_FROM;
    }

    public static String getRatings_TO() {
        return ratings_TO;
    }

    public static int getCourse_row_id() {
        return course_row_id;
    }

    public static void setCourse_row_id(int course_row_id) {
        DatabaseContract.course_row_id = course_row_id;
    }

    public static int getStudent_row_id() {
        return student_row_id;
    }

    public static void setStudent_row_id(int student_row_id) {
        DatabaseContract.student_row_id = student_row_id;
    }

    public static int getTeacher_row_id() {
        return teacher_row_id;
    }

    public static void setTeacher_row_id(int teacher_row_id) {
        DatabaseContract.teacher_row_id = teacher_row_id;
    }

    public static int getRating_row_id() {
        return rating_row_id;
    }

    public static void setRating_row_id(int rating_row_id) {
        DatabaseContract.rating_row_id = rating_row_id;
    }
}
