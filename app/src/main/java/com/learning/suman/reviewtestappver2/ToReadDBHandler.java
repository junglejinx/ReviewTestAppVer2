package com.learning.suman.reviewtestappver2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_LONG;

public class ToReadDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="testbooks.db";
    private static final String TABLE_NAME="books";
    public static final String COLUMN_ID="_id";
    public static final String COLUMN_BOOKNAME="bookname";
    public static final String COLUMN_AUTHORNAME="authorname";
    public static final String COLUMN_GENRE="genre";
    public static final String COLUMN_LANGUAGE="language";
    public static final String COLUMN_DATE="date";

    public ToReadDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try {
            String query = "CREATE TABLE " + TABLE_NAME + "(" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_BOOKNAME + " TEXT, " +
                    COLUMN_AUTHORNAME + " TEXT, " +
                    COLUMN_GENRE + " TEXT, " +
                    COLUMN_LANGUAGE + " TEXT, " +
                    COLUMN_DATE + " TEXT " +
                    ");";
            db.execSQL(query);

        }

        catch (SQLiteException e){
            e.printStackTrace();
            throw e;
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    //Add a new row to the database
    public void addBook(Books book){
        ContentValues values = new ContentValues();

        values.put(COLUMN_BOOKNAME, book.get_bookname());
        values.put(COLUMN_AUTHORNAME,book.get_authorname());
        values.put(COLUMN_GENRE, book.get_genre());
        values.put(COLUMN_LANGUAGE, book.get_language());
        values.put(COLUMN_DATE, book.get_date());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_NAME, null, values);

        db.close();
    }

    //Delete a product from the database
    public void deleteBook(String bookName){
        SQLiteDatabase db = getWritableDatabase();
        //System.out.println(bookName);
        Log.d("database",bookName);
        db.execSQL("DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_BOOKNAME + "=\"" + bookName + "\";");
    }

    public String databaseToString(){
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE 1";

        //Cursor points to a location in your results
        Cursor c = db.rawQuery(query, null);
        //Move to the first row in your results
        c.moveToFirst();

        //Position after the last row means the end of the results
        while (!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex("bookname")) != null) {
                dbString += c.getString(c.getColumnIndex("bookname"));
                dbString+='|';
                dbString += c.getString(c.getColumnIndex("authorname"));
                dbString+='|';
                dbString += c.getString(c.getColumnIndex("genre"));
                dbString+='|';
                dbString += c.getString(c.getColumnIndex("language"));
                dbString+='|';
                dbString += c.getString(c.getColumnIndex("date"));
                dbString += "\n";

            }
            c.moveToNext();
        }
        db.close();

        return dbString;
    }

    public String databaseToStringAscOrderByBookname(){
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " ORDER BY "+COLUMN_BOOKNAME+" ASC ";
        //String query = "SELECT * FROM " + TABLE_NAME + " ORDER BY "+bookname;

        //Cursor points to a location in your results
        Cursor c = db.rawQuery(query, null);
        //Move to the first row in your results
        c.moveToFirst();

        //Position after the last row means the end of the results
        while (!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex("bookname")) != null) {
                dbString += c.getString(c.getColumnIndex("bookname"));
                dbString+='|';
                dbString += c.getString(c.getColumnIndex("authorname"));
                dbString+='|';
                dbString += c.getString(c.getColumnIndex("genre"));
                dbString+='|';
                dbString += c.getString(c.getColumnIndex("language"));
                dbString+='|';
                dbString += c.getString(c.getColumnIndex("date"));
                dbString += "\n";

            }
            c.moveToNext();
        }
        db.close();

        return dbString;
    }

    public String databaseToStringAscOrderByAuthorname(){
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " ORDER BY "+COLUMN_AUTHORNAME+" ASC ";
        //String query = "SELECT * FROM " + TABLE_NAME + " ORDER BY "+bookname;

        //Cursor points to a location in your results
        Cursor c = db.rawQuery(query, null);
        //Move to the first row in your results
        c.moveToFirst();

        //Position after the last row means the end of the results
        while (!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex("bookname")) != null) {
                dbString += c.getString(c.getColumnIndex("bookname"));
                dbString+='|';
                dbString += c.getString(c.getColumnIndex("authorname"));
                dbString+='|';
                dbString += c.getString(c.getColumnIndex("genre"));
                dbString+='|';
                dbString += c.getString(c.getColumnIndex("language"));
                dbString+='|';
                dbString += c.getString(c.getColumnIndex("date"));
                dbString += "\n";

            }
            c.moveToNext();
        }
        db.close();

        return dbString;
    }

    public String databaseToStringAscOrderByGenre(){
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " ORDER BY "+COLUMN_GENRE+" ASC ";
        //String query = "SELECT * FROM " + TABLE_NAME + " ORDER BY "+bookname;

        //Cursor points to a location in your results
        Cursor c = db.rawQuery(query, null);
        //Move to the first row in your results
        c.moveToFirst();

        //Position after the last row means the end of the results
        while (!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex("bookname")) != null) {
                dbString += c.getString(c.getColumnIndex("bookname"));
                dbString+='|';
                dbString += c.getString(c.getColumnIndex("authorname"));
                dbString+='|';
                dbString += c.getString(c.getColumnIndex("genre"));
                dbString+='|';
                dbString += c.getString(c.getColumnIndex("language"));
                dbString+='|';
                dbString += c.getString(c.getColumnIndex("date"));
                dbString += "\n";

            }
            c.moveToNext();
        }
        db.close();

        return dbString;
    }

    public String databaseToStringAscOrderByLanguage(){
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " ORDER BY "+COLUMN_LANGUAGE+" ASC ";
        //String query = "SELECT * FROM " + TABLE_NAME + " ORDER BY "+bookname;

        //Cursor points to a location in your results
        Cursor c = db.rawQuery(query, null);
        //Move to the first row in your results
        c.moveToFirst();

        //Position after the last row means the end of the results
        while (!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex("bookname")) != null) {
                dbString += c.getString(c.getColumnIndex("bookname"));
                dbString+='|';
                dbString += c.getString(c.getColumnIndex("authorname"));
                dbString+='|';
                dbString += c.getString(c.getColumnIndex("genre"));
                dbString+='|';
                dbString += c.getString(c.getColumnIndex("language"));
                dbString+='|';
                dbString += c.getString(c.getColumnIndex("date"));
                dbString += "\n";

            }
            c.moveToNext();
        }
        db.close();

        return dbString;
    }
    public String databaseToStringAscOrderByDate(){
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " ORDER BY "+COLUMN_DATE+" DESC ";
        //String query = "SELECT * FROM " + TABLE_NAME + " ORDER BY "+bookname;

        //Cursor points to a location in your results
        Cursor c = db.rawQuery(query, null);
        //Move to the first row in your results
        c.moveToFirst();

        //Position after the last row means the end of the results
        while (!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex("bookname")) != null) {
                dbString += c.getString(c.getColumnIndex("bookname"));
                dbString+='|';
                dbString += c.getString(c.getColumnIndex("authorname"));
                dbString+='|';
                dbString += c.getString(c.getColumnIndex("genre"));
                dbString+='|';
                dbString += c.getString(c.getColumnIndex("language"));
                dbString+='|';
                dbString += c.getString(c.getColumnIndex("date"));
                dbString += "\n";

            }
            c.moveToNext();
        }
        db.close();

        return dbString;
    }

}
