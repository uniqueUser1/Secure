package com.example.secure;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Sahil on 29-03-2017.
 */
public class DbHandler extends SQLiteOpenHelper {
    public DbHandler(Context context) {
        super(context, "security", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE register(id TEXT,name TEXT,mobile TEXT,email TEXT,emerg1 TEXT,emerg2 TEXT,emerg3 TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS login");
    }

    long put(String id,String name,String mobile,String email,String emerg1,String emerg2,String emerg3){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("id",id);
        cv.put("name",name);
        cv.put("mobile",mobile);
        cv.put("email",email);
        cv.put("emerg1",emerg1);
        cv.put("emerg2",emerg2);
        cv.put("emerg3",emerg3);
        long n = db.insert("register",null,cv);
        return n;
    }
}
