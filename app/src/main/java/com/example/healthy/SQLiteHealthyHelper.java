package com.example.healthy;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

public class SQLiteHealthyHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "healthy_food.db";
    private static final int DB_VERSION = 1;

    public static final String CALORIES_TABLE = "calories";
    public static final String CALORIES_ID = BaseColumns._ID;
    public static final String FOOD = "food";
    public static final String QUANTITY = "quantity";
    public static final String CALORIES_COUNT = "calories_count";


    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table " + CALORIES_TABLE
            + " (" + CALORIES_ID + " integer primary key autoincrement, "
            + FOOD + " text, " + QUANTITY + " int, " + CALORIES_COUNT + " int);";

    public SQLiteHealthyHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL("DROP TABLE IF EXISTS " + CALORIES_TABLE);
        database.execSQL(DATABASE_CREATE);

        database.execSQL("insert into "
                + CALORIES_TABLE
                + " ('"+FOOD+"', '"+QUANTITY+"', '"+CALORIES_COUNT+"') values('Apple',100,52)");

        database.execSQL("insert into "
                + CALORIES_TABLE
                + " ('"+FOOD+"', '"+QUANTITY+"','"+CALORIES_COUNT+"') values('Beans',20,108)");

        database.execSQL("insert into "
                + CALORIES_TABLE
                + " ('"+FOOD+"', '"+QUANTITY+"','"+CALORIES_COUNT+"') values('Peas',12,60)");

        database.execSQL("insert into "
                + CALORIES_TABLE
                + " ('"+FOOD+"', '"+QUANTITY+"','"+CALORIES_COUNT+"') values('Peas',12,60)");

        database.execSQL("insert into "
                + CALORIES_TABLE
                + " ('"+FOOD+"', '"+QUANTITY+"','"+CALORIES_COUNT+"') values('Potato',50,200)");
        database.execSQL("insert into "
                + CALORIES_TABLE
                + " ('"+FOOD+"', '"+QUANTITY+"','"+CALORIES_COUNT+"') values('Sweet Potato',20,100)");

        //Insert initial data here
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(SQLiteHealthyHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        // db.execSQL("DROP DATABASE IF EXISTS " + DB_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + CALORIES_TABLE);
        onCreate(db);
    }

}
