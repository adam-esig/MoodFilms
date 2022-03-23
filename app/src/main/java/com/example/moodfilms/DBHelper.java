package com.example.moodfilms;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context){
        super(context, "Filmdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table Filmdetails(title TEXT primary key, realisateur textPersonName, release TEXT, soundtrack textPersonName, mood TEXT, trailer TEXT, synopsis TEXT, image TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop Table if exists Filmdetails");
    }

    public Boolean insertfilmdata(String title, String realisateur, String release, String soundtrack, String mood, String trailer, String synopsis, String image){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", title);
        contentValues.put("realisateur", realisateur);
        contentValues.put("release", release);
        contentValues.put("soundtrack", soundtrack);
        contentValues.put("mood", mood);
        contentValues.put("trailer", trailer);
        contentValues.put("synopsis", synopsis);
        contentValues.put("image", image);

        long result=db.insert("Filmdetails", null, contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }

    public Boolean updatefilmdata(String title, String realisateur, String release, String soundtrack, String mood, String trailer, String synopsis, String image){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("realisateur", realisateur);
        contentValues.put("release", release);
        contentValues.put("soundtrack", soundtrack);
        contentValues.put("mood", mood);
        contentValues.put("trailer", trailer);
        contentValues.put("synopsis", synopsis);
        contentValues.put("image", image);
        Cursor cursor = db.rawQuery("select * from Filmdetails where title = ?", new String[]{title});
        if(cursor.getCount()>0) {
        long result = db.update("Filmdetails", contentValues, "name=?", new String[]{title});
        if (result == -1) {
            return false;
        } else {
            return true;
        }
        }else
        {
            return false;
        }
    }

    public Boolean deletefilmdata(String title){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from Filmdetails where title = ?", new String[]{title});
        if(cursor.getCount()>0) {
            long result = db.delete("Filmdetails","name=?", new String[]{title});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public Cursor getdata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from Filmdetails", null);
        return cursor;
    }
}