package com.learning.suman.reviewtestappver2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


public class ReadBooksDBHandler extends SQLiteOpenHelper {

        private static final int DATABASE_VERSION=1;
        private static final String DATABASE_NAME="testreadbooks.db";
        private static final String TABLE_NAME="readbooks";
        public static final String COLUMN_ID="_id";
        public static final String COLUMN_BOOKNAME="bookname";
        public static final String COLUMN_AUTHORNAME="authorname";
        public static final String COLUMN_GENRE="genre";
        public static final String COLUMN_LANGUAGE="language";
        public static final String COLUMN_REVIEW="review";
        public static final String COLUMN_RATING="rating";
        public static final String COLUMN_DATE="date";
        //public static final String COLUMN_OWN_BOOK="ownbook";

        public ReadBooksDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
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
                        COLUMN_REVIEW + " TEXT, " +
                        COLUMN_RATING + " TEXT, " +
                        COLUMN_DATE + " TEXT " +
                        //COLUMN_OWN_BOOK + " TEXT, " +
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
        public void addBook(ReviewedBooks book){
            ContentValues values = new ContentValues();

            values.put(COLUMN_BOOKNAME, book.get_bookname());
            values.put(COLUMN_AUTHORNAME,book.get_authorname());
            values.put(COLUMN_GENRE, book.get_genre());
            values.put(COLUMN_LANGUAGE, book.get_language());
            values.put(COLUMN_REVIEW, book.get_review());
            values.put(COLUMN_RATING, book.get_rating());
            values.put(COLUMN_DATE, book.get_date());
            //values.put(COLUMN_OWN_BOOK, book.get_ownbook());
            SQLiteDatabase db = getWritableDatabase();
            db.insert(TABLE_NAME, null, values);

            db.close();
        }

        //Delete a product from the database
        public void deleteBook(String bookName){
            SQLiteDatabase db = getWritableDatabase();
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
                    dbString +="|";
                    dbString += c.getString(c.getColumnIndex("authorname"));
                    dbString +="|";
                    dbString += c.getString(c.getColumnIndex("genre"));
                    dbString +="|";
                    dbString += c.getString(c.getColumnIndex("language"));
                    dbString +="*";
                    dbString += c.getString(c.getColumnIndex("review"));
                    dbString +="*";
                    dbString += c.getString(c.getColumnIndex("rating"));
                    dbString +="|";
                    dbString += c.getString(c.getColumnIndex("date"));
                    dbString += "\n";

                }
                c.moveToNext();
            }
            db.close();

            return dbString;
        }


    public String ownBookDatabaseToString(){
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
                dbString +="|";
                dbString += c.getString(c.getColumnIndex("authorname"));
                dbString +="|";
                dbString += c.getString(c.getColumnIndex("genre"));
                dbString +="|";
                dbString += c.getString(c.getColumnIndex("language"));
                dbString +="*";
                dbString += c.getString(c.getColumnIndex("review"));
                dbString +="*";
                dbString += c.getString(c.getColumnIndex("rating"));
                dbString +="|";
                dbString += c.getString(c.getColumnIndex("date"));
                dbString += "\n";

            }
            c.moveToNext();
        }
        db.close();

        return dbString;
    }


    public String readBooksDatabaseToStringBookName(){
            String dbString = "";
            SQLiteDatabase db = getWritableDatabase();
            String query = "SELECT * FROM " + TABLE_NAME +  " ORDER BY "+ COLUMN_BOOKNAME +" ASC ";

            //Cursor points to a location in your results
            Cursor c = db.rawQuery(query, null);
            //Move to the first row in your results
            c.moveToFirst();

            //Position after the last row means the end of the results
            while (!c.isAfterLast()) {
                if (c.getString(c.getColumnIndex("bookname")) != null) {
                    dbString += c.getString(c.getColumnIndex("bookname"));
                    dbString +="|";
                    dbString += c.getString(c.getColumnIndex("authorname"));
                    dbString +="|";
                    dbString += c.getString(c.getColumnIndex("genre"));
                    dbString +="|";
                    dbString += c.getString(c.getColumnIndex("language"));
                    dbString +="*";
                    dbString += c.getString(c.getColumnIndex("review"));
                    dbString +="*";
                    dbString += c.getString(c.getColumnIndex("rating"));
                    dbString +="|";
                    dbString += c.getString(c.getColumnIndex("date"));
                    dbString += "\n";

                }
                c.moveToNext();
            }
            db.close();

            return dbString;
        }

        public String readBooksDatabaseToStringAuthorName(){
            String dbString = "";
            SQLiteDatabase db = getWritableDatabase();
            String query = "SELECT * FROM " + TABLE_NAME +  " ORDER BY "+ COLUMN_AUTHORNAME +" ASC ";

            //Cursor points to a location in your results
            Cursor c = db.rawQuery(query, null);
            //Move to the first row in your results
            c.moveToFirst();

            //Position after the last row means the end of the results
            while (!c.isAfterLast()) {
                if (c.getString(c.getColumnIndex("bookname")) != null) {
                    dbString += c.getString(c.getColumnIndex("bookname"));
                    dbString +="|";
                    dbString += c.getString(c.getColumnIndex("authorname"));
                    dbString +="|";
                    dbString += c.getString(c.getColumnIndex("genre"));
                    dbString +="|";
                    dbString += c.getString(c.getColumnIndex("language"));
                    dbString +="*";
                    dbString += c.getString(c.getColumnIndex("review"));
                    dbString +="*";
                    dbString += c.getString(c.getColumnIndex("rating"));
                    dbString +="|";
                    dbString += c.getString(c.getColumnIndex("date"));
                    dbString += "\n";

                }
                c.moveToNext();
            }
            db.close();

            return dbString;
        }


        public String readBooksDatabaseToStringLanguage(){
            String dbString = "";
            SQLiteDatabase db = getWritableDatabase();
            String query = "SELECT * FROM " + TABLE_NAME +  " ORDER BY "+ COLUMN_LANGUAGE +" ASC ";

            //Cursor points to a location in your results
            Cursor c = db.rawQuery(query, null);
            //Move to the first row in your results
            c.moveToFirst();

            //Position after the last row means the end of the results
            while (!c.isAfterLast()) {
                if (c.getString(c.getColumnIndex("bookname")) != null) {
                    dbString += c.getString(c.getColumnIndex("bookname"));
                    dbString +="|";
                    dbString += c.getString(c.getColumnIndex("authorname"));
                    dbString +="|";
                    dbString += c.getString(c.getColumnIndex("genre"));
                    dbString +="|";
                    dbString += c.getString(c.getColumnIndex("language"));
                    dbString +="*";
                    dbString += c.getString(c.getColumnIndex("review"));
                    dbString +="*";
                    dbString += c.getString(c.getColumnIndex("rating"));
                    dbString +="|";
                    dbString += c.getString(c.getColumnIndex("date"));
                    dbString += "\n";

                }
                c.moveToNext();
            }
            db.close();

            return dbString;
        }


        public String readBooksDatabaseToStringGenre(){
            String dbString = "";
            SQLiteDatabase db = getWritableDatabase();
            String query = "SELECT * FROM " + TABLE_NAME + " ORDER BY " + COLUMN_GENRE +" ASC ";

            //Cursor points to a location in your results
            Cursor c = db.rawQuery(query, null);
            //Move to the first row in your results
            c.moveToFirst();

            //Position after the last row means the end of the results
            while (!c.isAfterLast()) {
                if (c.getString(c.getColumnIndex("bookname")) != null) {
                    dbString += c.getString(c.getColumnIndex("bookname"));
                    dbString +="|";
                    dbString += c.getString(c.getColumnIndex("authorname"));
                    dbString +="|";
                    dbString += c.getString(c.getColumnIndex("genre"));
                    dbString +="|";
                    dbString += c.getString(c.getColumnIndex("language"));
                    dbString +="*";
                    dbString += c.getString(c.getColumnIndex("review"));
                    dbString +="*";
                    dbString += c.getString(c.getColumnIndex("rating"));
                    dbString +="|";
                    dbString += c.getString(c.getColumnIndex("date"));
                    dbString += "\n";

                }
                c.moveToNext();
            }
            db.close();

            return dbString;
        }


        public String readBooksDatabaseToStringRating(){
            String dbString = "";
            SQLiteDatabase db = getWritableDatabase();
            String query = "SELECT * FROM " + TABLE_NAME +  " ORDER BY "+ COLUMN_RATING +" DESC ";

            //Cursor points to a location in your results
            Cursor c = db.rawQuery(query, null);
            //Move to the first row in your results
            c.moveToFirst();

            //Position after the last row means the end of the results
            while (!c.isAfterLast()) {
                if (c.getString(c.getColumnIndex("bookname")) != null) {
                    dbString += c.getString(c.getColumnIndex("bookname"));
                    dbString +="|";
                    dbString += c.getString(c.getColumnIndex("authorname"));
                    dbString +="|";
                    dbString += c.getString(c.getColumnIndex("genre"));
                    dbString +="|";
                    dbString += c.getString(c.getColumnIndex("language"));
                    dbString +="*";
                    dbString += c.getString(c.getColumnIndex("review"));
                    dbString +="*";
                    dbString += c.getString(c.getColumnIndex("rating"));
                    dbString +="|";
                    dbString += c.getString(c.getColumnIndex("date"));
                    dbString += "\n";

                }
                c.moveToNext();
            }
            db.close();

            return dbString;
        }

        public String readBooksDatabaseToStringLatest(){
            String dbString = "";
            SQLiteDatabase db = getWritableDatabase();
            String query = "SELECT * FROM " + TABLE_NAME +  " ORDER BY "+ COLUMN_DATE +" DESC ";

            //Cursor points to a location in your results
            Cursor c = db.rawQuery(query, null);
            //Move to the first row in your results
            c.moveToFirst();

            //Position after the last row means the end of the results
            while (!c.isAfterLast()) {
                if (c.getString(c.getColumnIndex("bookname")) != null) {
                    dbString += c.getString(c.getColumnIndex("bookname"));
                    dbString +="|";
                    dbString += c.getString(c.getColumnIndex("authorname"));
                    dbString +="|";
                    dbString += c.getString(c.getColumnIndex("genre"));
                    dbString +="|";
                    dbString += c.getString(c.getColumnIndex("language"));
                    dbString +="*";
                    dbString += c.getString(c.getColumnIndex("review"));
                    dbString +="*";
                    dbString += c.getString(c.getColumnIndex("rating"));
                    dbString +="|";
                    dbString += c.getString(c.getColumnIndex("date"));
                    dbString += "\n";

                }
                c.moveToNext();
            }
            db.close();

            return dbString;
        }

        /* Function used to get the author count to plot the bar graph
         * Used by 'ShowGraph' activity
         */
        public String authorCountGraphDatabaseToString(){
            String dbString = "";
            int flag=0;
            List<String> authorNames=null;
            SQLiteDatabase db = getWritableDatabase();


            String query="SELECT authorname FROM " + TABLE_NAME + " WHERE " + COLUMN_AUTHORNAME + " IN  (     SELECT " + COLUMN_AUTHORNAME +
                             " FROM " + TABLE_NAME + " GROUP BY " + COLUMN_AUTHORNAME + " HAVING COUNT(*) > 0  ) ORDER BY "
            + COLUMN_AUTHORNAME;

            //Cursor points to a location in your results
            Cursor c = db.rawQuery(query, null);

            //Move to the first row in your results
            c.moveToFirst();

            //Position after the last row means the end of the results
            while (!c.isAfterLast()) {
                if (c.getString(c.getColumnIndex("authorname")) != null) {
                    //chi|chi|chi|yas|yas

                    dbString += c.getString(c.getColumnIndex("authorname"));
                    dbString +="|";
                }


                c.moveToNext();
            }

            db.close();
            return dbString;
        }

        /* Function used to get the author count to plot the line graph
         * Used by 'ShowGraph' activity
         */

        public String languageCountGraphDatabaseToString(){
            String dbString = "";
            int flag=0;
            List<String> languages=null;
            SQLiteDatabase db = getWritableDatabase();


            String query="SELECT language FROM " + TABLE_NAME + " WHERE " + COLUMN_LANGUAGE + " IN  (     SELECT " + COLUMN_LANGUAGE +
                    " FROM " + TABLE_NAME + " GROUP BY " + COLUMN_LANGUAGE + " HAVING COUNT(*) > 0  ) ORDER BY "
                    + COLUMN_LANGUAGE;

            //Cursor points to a location in your results
            Cursor c = db.rawQuery(query, null);

            //Move to the first row in your results
            c.moveToFirst();

            //Position after the last row means the end of the results
            while (!c.isAfterLast()) {
                if (c.getString(c.getColumnIndex("language")) != null) {
                    //chi|chi|chi|yas|yas

                    dbString += c.getString(c.getColumnIndex("language"));
                    dbString +="|";
                }


                c.moveToNext();
            }

            db.close();
            return dbString;
        }


        /* Returns total number of books read*/
        public String totalBookCount(){
            String totalCount="";
            Integer count=0;
            SQLiteDatabase db = getWritableDatabase();

            String query= " SELECT _id FROM " + TABLE_NAME + " ORDER BY " +  COLUMN_ID + " DESC  LIMIT 1 ";
            Cursor c = db.rawQuery(query, null);
            if(c.moveToFirst()){
                count = c.getInt(c.getColumnIndex("_id"));
                totalCount = count.toString();
                db.close();
                return totalCount;
            }
            else
                return "0";
        }

    }
