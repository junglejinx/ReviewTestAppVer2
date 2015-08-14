package com.learning.suman.reviewtestappver2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class ReadBooks extends ActionBarActivity {

    ReadBooksDBHandler displayDataHandler;
    Spinner readBooksSortSpinner;
    int flag=0;
    final String activityClass="ReadBooks";
    String dbString="";
    List<String> books=new ArrayList<String>();
    List<String> review=new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.read_books);


        displayDataHandler = new ReadBooksDBHandler(this, null, null, 1);

        readBooksSortSpinner = (Spinner) findViewById(R.id.readBooksSortSpinner);
        ArrayAdapter<CharSequence> readBooksSortAdapter = ArrayAdapter.createFromResource(this, R.array.SortReadBooks, android.R.layout.simple_spinner_item);
        readBooksSortAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        readBooksSortSpinner.setAdapter(readBooksSortAdapter);


        readBooksSortSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //String sortBy=String.valueOf(parent.getItemAtPosition(position));
                String sortBy = readBooksSortSpinner.getSelectedItem().toString();
                //Toast.makeText(getApplicationContext(), "option selected: " + sortBy, Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(), "position selected: " + position, Toast.LENGTH_LONG).show();
                /*if (sortOptionPosition!=0){
                    sortSpinner.setSelection(sortOptionPosition);
                }*/

                dbString = "";
                if(position==0){
                    dbString = displayDataHandler.readBooksDatabaseToStringLatest();
                    position=3;
                }
                else if (position == 1) {
                    dbString = displayDataHandler.readBooksDatabaseToStringBookName();
                } else if (position == 2) {
                    dbString = displayDataHandler.readBooksDatabaseToStringAuthorName();
                } else if (position == 3) {
                    dbString = displayDataHandler.readBooksDatabaseToStringLatest();
                } else if (position == 4) {
                    dbString = displayDataHandler.readBooksDatabaseToStringGenre();//error
                } else if (position == 5) {
                    dbString = displayDataHandler.readBooksDatabaseToStringLanguage();//no diff betn cap/small letters
                } else if (position == 6) {
                    dbString = displayDataHandler.readBooksDatabaseToStringRating();
                }
                //Toast.makeText(getApplicationContext(),"dbextracted: "+dbString,Toast.LENGTH_LONG).show();
                dbselected(dbString);
                readBooksListDisplay(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                dbString = displayDataHandler.readBooksDatabaseToStringLatest();
                dbselected(dbString);
                readBooksListDisplay(3);
            }

        });
    }


       /* public void readBooksListDisplay(final int sortOptionPosition){
            //Toast.makeText(getApplicationContext(), "after extracting: "+books.toString(), Toast.LENGTH_LONG).show();
            ListView booksListView = (ListView) findViewById(R.id.readBooksDisplayListView);
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

        }*/

    public void readBooksListDisplay(final int sortOptionPosition) {
        final ListView readBooksListView = (ListView) findViewById(R.id.readBooksDisplayListView);
        ListAdapter readBooksListAdapter = new CustomAdaptor(this, books);
        //ListAdapter booksListAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,books);
        readBooksListView.setAdapter(readBooksListAdapter);


        readBooksListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String bookSelected = String.valueOf(parent.getItemAtPosition(position));

                        view.setBackgroundColor(006600);
                        Intent i = new Intent(getApplicationContext(), EditDeleteBackListDisplay.class);
                        i.putExtra("bookName", bookSelected);
                        i.putExtra("activityClass", activityClass);
                        i.putExtra("reviewData",review.get(position).toString());
                        startActivity(i);
                        /*int bookPosition=books.indexOf(bookSelected);
                        String reviewData=review.*/


                    }
                }
        );
    }

     public  void dbselected( String dbString){

        int i=0;
        Character word;

        //final List<String> review= new ArrayList<String>();
        //Toast.makeText(this,"dbselected: "+dbString,Toast.LENGTH_LONG).show();

       // String dbString=displayDataHandler.databaseToString();

        String record="";
        String reviewData="";
        int flag=0;
        String extraString="";
        final String reviewSelected="";
        books.clear();

        if (dbString.equals("")) {
            Toast.makeText(getApplicationContext(), "no books to display", Toast.LENGTH_LONG).show();
            return;
        } else {


            //Book|Author|Lang|Genre*Review data*Rating|Date/n
            while (i < dbString.length()) {

                if (dbString.charAt(i) == '*') {
                    while (dbString.charAt(i + 1) != '*') {
                        reviewData = reviewData + dbString.charAt(i + 1);
                        i++;
                    }



                    review.add(reviewData);
                    reviewData = "";
                    extraString = " rated ";
                    record = record + extraString;
                    flag++;
                    i = i + 2;

                    //reviewData = record + dbString.charAt(i);

                }
                if (dbString.charAt(i) == '|') {

                    if (flag == 0) {
                        extraString = " by ";

                    } else if (flag == 1) {
                        extraString = " of type ";

                    } else if (flag == 2) {
                        extraString = " in ";

                    } else if (flag == 4) {
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

        //Toast.makeText(this,review.toString(),Toast.LENGTH_LONG).show();




        /*String[] editDeleteBackList={"Edit","Delete","Back"};

        final ListView editDeleteReadBooksListView=(ListView)findViewById(R.id.editDeleteBackListView);
        final ArrayAdapter editDeleteReadBooksListAdapter =new ArrayAdapter<String>
                (this,R.layout.activity_edit_delete_back_list_display,editDeleteBackList);



        readBooksListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        //String bookSelected=String.valueOf(parent.getItemAtPosition(position));
                        //displayItemTextView.setText(bookSelected);
                        //add 2 buttons delete and edit when an item(Book) is selected


                        //view.setBackgroundColor(Color.parseColor("#ffffff"));


                        String bookSelected = String.valueOf(parent.getItemAtPosition(position));

                        view.setBackgroundColor(006600);
                        Intent i = new Intent(getApplicationContext(), EditDeleteBackListDisplay.class);
                        i.putExtra("bookName", bookSelected);
                        startActivity(i);

                        //add 2 buttons delete and edit when an item(Book) is selected

                    }
                                }
                );


                        //String[] editDeleteBackList={"Edit","Delete","Back"};

                        //final ListView editDeleteReadBooksListView=(ListView)findViewById(R.id.editDeleteReadBooksListView);
                        //final ArrayAdapter editDeleteReadBooksListAdapter =new ArrayAdapter<String>(this,R.layout.edit_delete_read_books_layout,editDeleteBackList);
                        //ListAdapter booksListAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,books);
                        //editDeleteReadBooksListView.setAdapter(editDeleteReadBooksListAdapter);
                        //displayList();






        );*/
        

    }







    /*public  void displayList(){

        String[] editDeleteBackList={"Edit","Delete","Back"};
        final ListView editDeleteReadBooksListView=(ListView)findViewById(R.id.editDeleteBackListView);
        final ArrayAdapter editDeleteReadBooksListAdapter =new ArrayAdapter<String>(this,R.layout.activity_edit_delete_back_list_display,editDeleteBackList);
        editDeleteReadBooksListView.setAdapter(editDeleteReadBooksListAdapter);
        Intent i=new Intent(this,EditDeleteBackListDisplay.class);
        startActivity(i);
    }*/

    public void homeScreenClick(View view){
        Intent i=new Intent(this,MainActivity.class);
        startActivity(i);
    }

    /*public void toReadClick(View view){
        Intent i=new Intent(this,ToRead.class);
        startActivity(i);
    }*/

    public  void wishListClick(View view){
        Intent i=new Intent(this,WishList.class);
        startActivity(i);
    }
}
