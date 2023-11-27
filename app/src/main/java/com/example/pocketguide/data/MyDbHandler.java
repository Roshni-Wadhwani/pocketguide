package com.example.pocketguide.data;

import android.content.*;
import android.database.sqlite.*;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.pocketguide.params.Params;

public class MyDbHandler extends SQLiteOpenHelper{
    public MyDbHandler(Context context){
        super(context, Params.DB_NAME,null,Params.DB_VERSION);
        System.out.println("inside Db");
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println("Inside onCreate function...");
        String create_user_table = "CREATE TABLE "+ Params.USER_TABLE_NAME +
                "("+ Params.KEY_USER_ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                Params.KEY_EMAIL +" TEXT,"+
                Params.KEY_PASSWORD +" TEXT)";
        db.execSQL(create_user_table);
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
