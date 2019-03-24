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
    private static final String TEACHERS = "teachers_table";
    private static final String teachers_ID = "ID";
    private static final String teachers_NAME= "name";
    private static final String teachers_MAIL= "mail";

    private static final String teachers_rat1= "rat1";
    private static final String teachers_rat2= "rat2";
    private static final String teachers_rat3= "rat3";
    private static final String teachers_rat4= "rat4";
    private static final String teachers_rat5= "rat5";
    private static final String teachers_rat6= "rat6";
    private static int teacher_row_id = 1;

//    Rating Table
    private static final String RATINGS = "ratings_table";
    private static final String ratings_ID = "ID";
    private static final String ratings_VALUE1 = "rat1";
    private static final String ratings_VALUE2 = "rat2";
    private static final String ratings_VALUE3 = "rat3";
    private static final String ratings_VALUE4 = "rat4";
    private static final String ratings_VALUE5 = "rat5";
    private static final String ratings_VALUE6 = "rat6";

    private static final String ratings_FROM= "from_student_FK";
    private static final String ratings_TO= "to_teacher_FK";
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
        return "CREATE TABLE "+STUDENTS
                +" ( "+
                students_ID + " INTEGER PRIMARY KEY, "+
                students_NAME +" TEXT "
                +" ); ";
    }

    public static String create_table_teachers(){
       return "CREATE TABLE "+ TEACHERS
               + " ( "+
                teachers_ID +" INTEGER PRIMARY KEY, "+
                teachers_NAME +" TEXT, "+
                teachers_MAIL +" TEXT, "+

                teachers_rat1 +" INTEGER, "+
                teachers_rat2+ " INTEGER, "+
                teachers_rat3+ " INTEGER, "+
                teachers_rat4+ " INTEGER, "+
                teachers_rat5+ " INTEGER, "+
                teachers_rat6+ " INTEGER "+

               " ); ";
    }

    public static String create_table_courses(){
        return "CREATE TABLE "+ COURSES
                +" ( "+
                course_ID +" INTEGER PRIMARY KEY, "+
                course_NAME +" TEXT, "+
                course_TEACHER + " INTEGER "+
                " ); ";
    }

    public static String create_table_ratings(){
        return "CREATE TABLE "+ RATINGS +
                " ( "+
                ratings_ID + " INTEGER PRIMARY KEY, "+
                ratings_VALUE1 + " INTEGER, "+
                ratings_VALUE2 + " INTEGER, "+
                ratings_VALUE3 + " INTEGER, "+
                ratings_VALUE4 + " INTEGER, "+
                ratings_VALUE5 + " INTEGER, "+
                ratings_VALUE6 + " INTEGER, "+
                ratings_FROM + " INTEGER, "+
                ratings_TO + " INTEGER "+
//                "FOREIGN KEY ( "+ ratings_FROM +") REFERENCES "+STUDENTS+
//                " ("+students_ID+"), "+
//
//                "FOREIGN KEY ( "+ratings_TO +") REFERENCES "+TEACHERS+
//                "("+teachers_ID+")"+
                "); "
                ;
    }

    public static String fetch_all(String name){
        return "SELECT * FROM "+ name;
    }

    public static String rating_where_clause(){
        return DatabaseContract.getRatings_FROM() +" = ? AND "+
                DatabaseContract.getRatings_TO()+" = ?";
    }

    public static String rating_teacher_clause(){
        return DatabaseContract.getRatings_TO() + " = ?";
    }

    public static String delete_all_tables(){
        return "DROP TABLE IF EXISTS ? ;";
    }

    public static String delete_table(String DB_NAME){
        return "DROP TABLE IF EXISTS "+
                DB_NAME+
                "; ";
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
