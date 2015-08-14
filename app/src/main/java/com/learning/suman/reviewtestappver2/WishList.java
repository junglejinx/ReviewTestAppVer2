package com.learning.suman.reviewtestappver2;

import android.content.Intent;
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
    Intent backIntent=getIntent();
    int sortOptionPosition=0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wish_list);



        //sortOptionPosition=backIntent.getIntExtra("sortOptionPosition",0);
        sortSpinner=(Spinner)findViewById(R.id.sortSpinner);
        final ArrayAdapter<CharSequence> sortAdapter=ArrayAdapter.createFromResource(this,R.array.Sort,android.R.layout.simple_spinner_item);
        sortAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sortSpinner.setAdapter(sortAdapter);


        Character word;
        displayDataHandler=new ToReadDBHandler(this,null,null,1);
        //String dbString="";
        //final String dbString=displayDataHandler.databaseToString();


        sortSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //String sortBy=String.valueOf(parent.getItemAtPosition(position));
                String sortBy = sortSpinner.getSelectedItem().toString();
                //Toast.makeText(getApplicationContext(), "option selected: " + sortBy, Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(), "position by intent: " + sortOptionPosition, Toast.LENGTH_LONG).show();

                if (sortOptionPosition!=0){
                    sortSpinner.setSelection(sortOptionPosition);
                }

                dbString="";
                if(position==0){
                    dbString=displayDataHandler.databaseToStringAscOrderByDate();
                    position=3;
                }

                else if (position== 1) {
                    dbString = displayDataHandler.databaseToStringAscOrderByBookname();
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
                listDisplay(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                return;
            }
        });
    }


    public void listDisplay(final int sortOptionPosition){
        //Toast.makeText(getApplicationContext(), "after extracting: "+books.toString(), Toast.LENGTH_LONG).show();
        ListView booksListView = (ListView) findViewById(R.id.booksListView);
        ListAdapter booksListAdapter = new CustomAdaptor(getApplicationContext(), books);
        //ListAdapter booksListAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,books);

        booksListView.setAdapter(booksListAdapter);

        booksListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String bookSelected = String.valueOf(parent.getItemAtPosition(position));
                        Toast.makeText(getApplicationContext(), "position selected: "+sortOptionPosition, Toast.LENGTH_LONG).show();
                        view.setBackgroundColor(006600);
                        Intent i = new Intent(getApplicationContext(), EditDeleteBackListDisplay.class);
                        i.putExtra("bookName", bookSelected);
                        i.putExtra("activityClass", activityClass);
                        i.putExtra("sortOptionPosition",sortOptionPosition);
                        startActivity(i);


                    }
                }
        );

    }

    public  void dbselected( String dbString){

        int i=0;
        String record = "";
        int flag = 0;
        String extraString = "";
        books.clear();


        //Toast.makeText(getApplicationContext(), "db extracted: " + dbString, Toast.LENGTH_LONG).show();
        if (dbString.equals("")) {
            Toast.makeText(getApplicationContext(), "no books to display", Toast.LENGTH_LONG).show();
            return;
        } else {
            //book|author|lang|genre|date\n
            while (i < dbString.length()) {

                if (dbString.charAt(i) == '|') {

                    if (flag == 0) {
                        extraString = " by ";

                    } else if (flag == 1) {
                        extraString = " of type ";

                    } else if (flag == 2) {
                        extraString = " in ";

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

        //Toast.makeText(getApplicationContext(), "book list in dbextract: " + books.toString(), Toast.LENGTH_LONG).show();
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


}
