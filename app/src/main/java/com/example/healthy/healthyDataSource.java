package com.example.healthy;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
public class healthyDataSource {

    // Database fields
    private SQLiteDatabase database;
    private SQLiteHealthyHelper dbHelper;

    private String[] allData = { SQLiteHealthyHelper.CALORIES_ID,
            SQLiteHealthyHelper.FOOD, SQLiteHealthyHelper.QUANTITY , SQLiteHealthyHelper.CALORIES_COUNT};

    public healthyDataSource

            (Context context) {
        dbHelper = new SQLiteHealthyHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public List<String[]> getAllFood() {
        List<String[]> myFood = new ArrayList<String[]>();

        Cursor cursor = database.query(SQLiteHealthyHelper.CALORIES_TABLE,
                allData, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String[] food = cursorToFood(cursor);
            myFood.add(food);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return myFood;
    }

    public String[] createNewFood(String food, int qua, int count) {

        ContentValues values = new ContentValues();

        values.put(SQLiteHealthyHelper.FOOD, food);
        values.put(SQLiteHealthyHelper.QUANTITY, qua);
        values.put(SQLiteHealthyHelper.QUANTITY, count);

        // for the id
        long insertId = database.insert(SQLiteHealthyHelper.CALORIES_TABLE, null,
                values);
        Cursor cursor = database.query(SQLiteHealthyHelper.CALORIES_TABLE,
                allData, SQLiteHealthyHelper.CALORIES_ID + " = " + insertId,
                null, null, null, null);

        cursor.moveToFirst();
        String[] newFood = cursorToFood(cursor);
        cursor.close();
        return newFood;
    }

    public void deleteItem(int id) {
        database.delete(SQLiteHealthyHelper.CALORIES_TABLE,
                SQLiteHealthyHelper.CALORIES_ID + " = " + id, null);
    }

    private String[] cursorToFood(Cursor cursor) {
        String[] food = new String[3];

        food[0] = cursor.getString(1);
        food[1] = cursor.getString(2);
        food[2] = cursor.getString(3);
        return food;
    }
}




