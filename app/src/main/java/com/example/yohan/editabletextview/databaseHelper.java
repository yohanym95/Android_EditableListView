package com.example.yohan.editabletextview;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class databaseHelper {

    public static final String KEY_ID = "ID";
    public static final String KEY_NAME = "NAME";
    public static final String KEY_AGE = "AGE";
    public static final String KEY_TELNO = "TEL_NO";
    public static final String KEY_CITY = "CITY";

    private final String DATABASE_NAME = "MYDETAILS";
    private final String DATABASE_TABLE = "MYDETAILS_LIST";
    private final int DATABASE_VERSION = 1;

    private DBHelper ourHelper;
    private final Context context;
    private SQLiteDatabase ourDatabase;

    public databaseHelper(Context context){
        this.context = (Context) context;


    }

    public class DBHelper extends SQLiteOpenHelper{

        public DBHelper(Context context){
            super(context,DATABASE_NAME,null,DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
             String sqlCode = "CREATE TABLE "+DATABASE_TABLE+" ("+KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+KEY_NAME+" TEXT NOT NULL, "+KEY_AGE+" TEXT NOT NULL, "+KEY_TELNO+" TEXT NOT NULL, "+KEY_CITY+" TEXT NOT NULL);";

             db.execSQL(sqlCode);


        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            db.execSQL("DROP TABLE IF EXISTS "+DATABASE_TABLE);
            onCreate(db);

        }
    }

    public databaseHelper open()throws SQLException {

        ourHelper = new DBHelper(context);
        ourDatabase = ourHelper.getWritableDatabase();

        return this;

    }

    public void close(){
        ourHelper.close();
    }

    public long addData(String name,String age,String telNo, String city){

        ContentValues cv = new ContentValues();
        cv.put(KEY_NAME,name);
        cv.put(KEY_AGE,age);
        cv.put(KEY_TELNO,telNo);
        cv.put(KEY_CITY,city);

        return ourDatabase.insert(DATABASE_TABLE,null,cv);

    }

    public Cursor getName(){

        Cursor cursor = ourDatabase.rawQuery("SELECT "+KEY_NAME+" FROM "+DATABASE_TABLE,null);

        return cursor;

    }

    public String getName1(int posiotion){

        Cursor cursor = null;

        String name = "";
        String no = posiotion+"";

        try{
            cursor = ourDatabase.rawQuery("SELECT "+KEY_NAME+" FROM "+DATABASE_TABLE+" WHERE "+KEY_ID+"=?",new String[]{no});

            if(cursor.getCount()>0){
                cursor.moveToFirst();
                name = cursor.getString(cursor.getColumnIndex(KEY_NAME));
            }

            return name;
        }finally {
            cursor.close();
        }


    }

    public String getAge(int posiotion){

        Cursor cursor = null;

        String age = "";
        String no = posiotion+"";

        try{
            cursor = ourDatabase.rawQuery("SELECT "+KEY_AGE+" FROM "+DATABASE_TABLE+" WHERE "+KEY_ID+"=?",new String[]{no});

            if(cursor.getCount()>0){
                cursor.moveToFirst();
                age = cursor.getString(cursor.getColumnIndex(KEY_AGE));
            }

            return age;
        }finally {
            cursor.close();
        }


    }

    public String getTelNo(int posiotion){

        Cursor cursor = null;

        String telNo = "";
        String no = posiotion+"";

        try{
            cursor = ourDatabase.rawQuery("SELECT "+KEY_TELNO+" FROM "+DATABASE_TABLE+" WHERE "+KEY_ID+"=?",new String[]{no});

            if(cursor.getCount()>0){
                cursor.moveToFirst();
                telNo = cursor.getString(cursor.getColumnIndex(KEY_TELNO));
            }

            return telNo;
        }finally {
            cursor.close();
        }


    }


    public String getCity(int posiotion){

        Cursor cursor = null;

        String city = "";
        String no = posiotion+"";

        try{
            cursor = ourDatabase.rawQuery("SELECT "+KEY_CITY+" FROM "+DATABASE_TABLE+" WHERE "+KEY_ID+"=?",new String[]{no});

            if(cursor.getCount()>0){
                cursor.moveToFirst();
                city = cursor.getString(cursor.getColumnIndex(KEY_CITY));
            }

            return city;
        }finally {
            cursor.close();
        }


    }

}
