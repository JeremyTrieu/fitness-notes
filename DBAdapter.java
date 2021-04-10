package com.example.fitnessquoctrieu;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;
import android.widget.Toast;

public class DBAdapter {
    private static final String databaseName = "foodItems";
    private static final int databaseVersion = 5;

    private Context context = null;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    public DBAdapter(Context ctx) {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);

    }

    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, databaseName, null, databaseVersion);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                //create tables

                db.execSQL("CREATE TABLE IF NOT EXISTS food (" +
                        " food_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        " food_name VARCHAR, " +
                        " food_manufactor_name VARCHAR, " +
                        " food_serving_size DOUBLE, " +
                        " food_energy DOUBLE, " +
                        " food_proteins DOUBLE, " +
                        " food_carbs DOUBLE, " +
                        " food_fat DOUBLE, " +
                        " food_energy DOUBLE, " +
                        " food_user_id INT, " +
                        " food_image VARCHAR, " +
                        " food_notes VARCHAR);"
                );
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            //All tables dropped will be listed here

            db.execSQL("DROP TABLE IF EXISTS food");

            onCreate(db);
            String TAG = "tag";
            Log.w(TAG, "Upgrading database from version: " + oldVersion + " to " + newVersion);



        }
    }

    public DBAdapter open() throws SQLException {
        db = DBHelper.getWritableDatabase();
        return this;
    }
    public void close(){
        DBHelper.close();
    }
    public void insert(String table, String fields, String values) {
        db.execSQL("INSERT INTO " + table + "(" + fields + ") VALUES (" + values +  ")");
    }
}
