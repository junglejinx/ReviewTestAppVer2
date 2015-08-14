package com.learning.suman.reviewtestappver2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Suman on 07-Jun-15.
 */
public class ToDo {


    /* helpful links:
        To add ext library/jar files as in this case adding ACHartEngine 1.1.0 ---> https://www.youtube.com/watch?v=tfPgx6c5wgA
        other queries---> www.stackoverflow.com
     */

    //work with " genre/language/rating" spinners/stars in "mainactivity/toread" activity
    //fix the ReadBooks activity--->urgent dbSelected(dbstring)
    //fix the bug when the app runs for the first time without any datain db-->imp
    //replace all ==null to .isempty() in the if statements
    //work on the image of display list items
    //camera option to take d pic of d book
    //validation of all input fields
    //glorify appearance,color,button shape etc
    //get an app icon
    //store in app store
    //user-id/pwd creation...database name changes with each user???
    //graphic representation of yr vs genre/author vs lang etc--almost
    //add another button where you store the books you own details??


    //--------------scratch book--------------------

    /*import android.content.Intent;
    import android.support.v7.app.ActionBarActivity;
    import android.os.Bundle;
    import android.view.Menu;
    import android.view.MenuItem;
    import android.view.View;
    import android.widget.AdapterView;
    import android.widget.ArrayAdapter;
    import android.widget.EditText;
    import android.widget.ListAdapter;
    import android.widget.ListView;
    import android.widget.Spinner;
    import android.widget.TextView;
    import android.widget.Toast;

    import java.util.ArrayList;
    import java.util.List;


    public class WishList extends ActionBarActivity {

        ToReadDBHandler displayDataHandler;
        Spinner sortSpinner;
        final String activityClass="WishList";
        String dbString="";
        int i=0;
        List<String> books=new ArrayList<String>();
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_wish_list);




            sortSpinner=(Spinner)findViewById(R.id.sortSpinner);
            ArrayAdapter<CharSequence> sortAdapter=ArrayAdapter.createFromResource(this,R.array.Sort,android.R.layout.simple_spinner_item);
            sortAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sortSpinner.setAdapter(sortAdapter);

            //final int i=0;
            Character word;
            //final List<String> books=new ArrayList<String>();
            displayDataHandler=new ToReadDBHandler(this,null,null,1);
            //String dbString="";
            //final String dbString=displayDataHandler.databaseToString();


            sortSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    //String sortBy=String.valueOf(parent.getItemAtPosition(position));
                    String sortBy = sortSpinner.getSelectedItem().toString();
                    //Toast.makeText(getApplicationContext(), "option selected: " + sortBy, Toast.LENGTH_LONG).show();
                    Toast.makeText(getApplicationContext(), "option selected position: " + position, Toast.LENGTH_LONG).show();
                for(int k=0;k<books.size();k++) {
                    books.remove(k);
                }
                    if (books.size()!=0) {
                        books.remove(0);
                        books.remove(1);
                        books.remove(2);
                    }
                    dbString="";
                    Toast.makeText(getApplicationContext(), "book list before: " + books, Toast.LENGTH_LONG).show();
                    //if(sortBy=="Book Name"){
                    if (position== 1) {


                        dbString = displayDataHandler.databaseToStringAscOrderByBookname();
                        //Toast.makeText(getApplicationContext(),"db extracted: "+dbString,Toast.LENGTH_LONG).show();
                        //dbselected(dbString);
                  /*  Toast.makeText(getApplicationContext(), books.toString(), Toast.LENGTH_LONG).show();
                    ListView booksListView = (ListView) findViewById(R.id.booksListView);
                    ListAdapter booksListAdapter = new CustomAdaptor(getApplicationContext(), books);
                    //ListAdapter booksListAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,books);

                    booksListView.setAdapter(booksListAdapter);

                    booksListView.setOnItemClickListener(
                            new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    String bookSelected = String.valueOf(parent.getItemAtPosition(position));

                                    view.setBackgroundColor(006600);
                                    Intent i = new Intent(getApplicationContext(), EditDeleteBackListDisplay.class);
                                    i.putExtra("bookName", bookSelected);
                                    i.putExtra("activityClass", activityClass);
                                    startActivity(i);


                                }
                            }
                    );
                    }
                    else if(position== 2){
                        dbString=displayDataHandler.databaseToStringAscOrderByAuthorname();
                    }
                    else if(position== 3){
                        dbString=displayDataHandler.databaseToStringAscOrderByDate();
                    }
                    else if(position== 4){
                        dbString=displayDataHandler.databaseToStringAscOrderByGenre();//error
                    }
                    else if(position== 5){
                        dbString=displayDataHandler.databaseToStringAscOrderByLanguage();//no diff betn cap/small letters
                    }
                    dbselected(dbString);
                    //Toast.makeText(getApplicationContext(), "book list b4 list: " + books, Toast.LENGTH_LONG).show();
                    listDisplay();

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    dbString=displayDataHandler.databaseToStringAscOrderByDate();
                    dbselected(dbString);
                    listDisplay();
                }

            });


                    /*String record = "";
                    int flag = 0;
                    String extraString = "";
                    //Toast.makeText(getApplicationContext(), "db extracted: " + dbString, Toast.LENGTH_LONG).show();
                    if (dbString == null) {
                        Toast.makeText(getApplicationContext(), "no books to display", Toast.LENGTH_LONG).show();
                        return;
                    } else {
                        //book|author|lang|genre|date\n
                        while (i < dbString.length()) {

                            if (dbString.charAt(i) == '|') {

                                if (flag == 0) {
                                    extraString = " by ";

                                } else if (flag == 1) {
                                    extraString = " in ";

                                } else if (flag == 2) {
                                    extraString = " of type ";

                                } else if (flag == 3) {
                                    extraString = " on ";

                                }
                                record = record + extraString;
                                flag++;
                                i++;
                            }
                            if (dbString.charAt(i) != '\n') {

                                record = record + dbString.charAt(i);


                            } else if (dbString.charAt(i) == '\n') {
                                books.add(record);
                                record = "";
                                flag = 0;
                            }
                            i++;
                        }
                    }*/
            //Toast.makeText(getApplicationContext(), books.toString(), Toast.LENGTH_LONG).show();


            //displayItemTextView=(TextView)findViewById(R.id.displayItemTextView);
                   /* ListView booksListView = (ListView) findViewById(R.id.booksListView);
                    ListAdapter booksListAdapter = new CustomAdaptor(getApplicationContext(), books);
                    //ListAdapter booksListAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,books);
                    booksListView.setAdapter(booksListAdapter);

                    booksListView.setOnItemClickListener(
                            new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    String bookSelected = String.valueOf(parent.getItemAtPosition(position));

                                    view.setBackgroundColor(006600);
                                    Intent i = new Intent(getApplicationContext(), EditDeleteBackListDisplay.class);
                                    i.putExtra("bookName", bookSelected);
                                    i.putExtra("activityClass", activityClass);
                                    startActivity(i);


                                }
                            }
                    );



        }


        public void listDisplay(){
            Toast.makeText(getApplicationContext(), "after extracting: "+books.toString(), Toast.LENGTH_LONG).show();
            ListView booksListView = (ListView) findViewById(R.id.booksListView);
            ListAdapter booksListAdapter = new CustomAdaptor(getApplicationContext(), books);
            //ListAdapter booksListAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,books);

            booksListView.setAdapter(booksListAdapter);

            booksListView.setOnItemClickListener(
                    new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            String bookSelected = String.valueOf(parent.getItemAtPosition(position));

                            view.setBackgroundColor(006600);
                            Intent i = new Intent(getApplicationContext(), EditDeleteBackListDisplay.class);
                            i.putExtra("bookName", bookSelected);
                            i.putExtra("activityClass", activityClass);
                            startActivity(i);


                        }
                    }
            );

        }

        public  void dbselected( String dbString){
            Toast.makeText(this,"function selected: "+dbString,Toast.LENGTH_LONG).show();
            //books.clear();
            String record = "";
            int flag = 0;
            String extraString = "";
            //books.clear();
            //Toast.makeText(getApplicationContext(), "db extracted: " + dbString, Toast.LENGTH_LONG).show();
            if (dbString == null) {
                Toast.makeText(getApplicationContext(), "no books to display", Toast.LENGTH_LONG).show();
                return;
            } else {
                //book|author|lang|genre|date\n
                while (i < dbString.length()) {

                    if (dbString.charAt(i) == '|') {

                        if (flag == 0) {
                            extraString = " by ";

                        } else if (flag == 1) {
                            extraString = " in ";

                        } else if (flag == 2) {
                            extraString = " of type ";

                        } else if (flag == 3) {
                            extraString = " on ";

                        }
                        record = record + extraString;
                        flag++;
                        i++;
                    }
                    if (dbString.charAt(i) != '\n') {

                        record = record + dbString.charAt(i);


                    } else if (dbString.charAt(i) == '\n') {
                        books.add(record);
                        record = "";
                        flag = 0;
                    }
                    i++;
                }
            }

            Toast.makeText(getApplicationContext(), "book list in dbextract: " + books, Toast.LENGTH_LONG).show();
        }

        public  void homeScreen(View view){
            Intent i=new Intent(this,MainActivity.class);
            startActivity(i);

        }

        public void toReadScreen(View view){
            Intent i=new Intent(this,ToRead.class);
            startActivity(i);
        }

        public void printDatabase(){
            //TextView displayTextView =(TextView)findViewById(R.id.wishListDisplayTextView);
            String dbString=displayDataHandler.databaseToString();
            Toast.makeText(this,dbString,Toast.LENGTH_LONG).show();
            //displayTextView.setText(dbString);


        }


    }*/



}
