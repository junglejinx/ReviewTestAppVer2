package com.learning.suman.reviewtestappver2;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class MainActivity extends ActionBarActivity {
    //defining the variables
    EditText bookNameEditText;
    EditText authorEditText;
    /*Spinner genreSpinner;
    Spinner languageSpinner;*/
    EditText reviewEditText;
    EditText ratingEditText;
    EditText genreEditText;
    EditText languageEditText;
    ReadBooksDBHandler db_handler;
    TextView bookNumberTextView;

    String bookName="";
    String author="";
    String language="";
    String genre="";
    String data="";
    int i=0;
    String bookSelected="";
    String editBookSelectedInfo="";
    String totalBooksRead="";
    String ownBook="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //widgets initialization
        bookNameEditText = (EditText) findViewById(R.id.homeScreenBookNameEditText);
        authorEditText = (EditText) findViewById(R.id.homeScreenAuthorEditText);
        /*genreSpinner = (Spinner) findViewById(R.id.genreSpinner);
        languageSpinner = (Spinner) findViewById(R.id.languageSpinner);*/
        reviewEditText = (EditText) findViewById(R.id.homeScreenReviewEditText);
        genreEditText=(EditText)findViewById(R.id.homeScreenGenreEditText);
        languageEditText=(EditText)findViewById(R.id.homeScreenLanguageEditText);
        ratingEditText=(EditText)findViewById(R.id.homeScreenRatingEditText);
        bookNumberTextView=(TextView)findViewById(R.id.bookNumberTextView);




        //db initialization
        db_handler=new ReadBooksDBHandler(this,null,null,1);

        //display the total count of books reviewed
        totalBooksRead=booksReadSoFar();
        if(totalBooksRead.equals("")){
            totalBooksRead="0";
        }
        bookNumberTextView.setText( totalBooksRead+ " book(s) read so far!");

        Intent editBookselected=getIntent();
        //Bundle
        bookSelected=editBookselected.getStringExtra("editBookInformation");
        String reviewData=editBookselected.getStringExtra("reviewData");

        List<String> record=new ArrayList<String>();

        //Toast.makeText(this,bookSelected,Toast.LENGTH_LONG).show();
        if (bookSelected==null){
            return;
        }
        else {
                //cars by baby in eng of type stories rated 3 0n 26-5-2015 0-15
                editBookSelectedInfo = bookSelected.replace(" by ","|");
                editBookSelectedInfo=editBookSelectedInfo.replace(" in ","|");
                editBookSelectedInfo=editBookSelectedInfo.replace(" of type ","|");
                editBookSelectedInfo=editBookSelectedInfo.replace(" rated ","|");
                editBookSelectedInfo=editBookSelectedInfo.replace(" on ","|");
                editBookSelectedInfo=editBookSelectedInfo+"|";

                //Toast.makeText(this,editBookSelectedInfo,Toast.LENGTH_LONG).show();
                while(i<editBookSelectedInfo.length()) {
                    if(editBookSelectedInfo.charAt(i)!='|' ) {
                        data = data + editBookSelectedInfo.charAt(i);
                        i++;
                    }
                    else if(editBookSelectedInfo.charAt(i)=='|') {
                        record.add(data);
                        data="";
                        i++;
                    }

                }



                bookNameEditText.setText(record.get(0).toString());
                authorEditText.setText(record.get(1).toString());;
                languageEditText.setText(record.get(2).toString());
                genreEditText.setText(record.get(3).toString());
                ratingEditText.setText(record.get(4).toString());
                reviewEditText.setText(reviewData);






        }

        /* ArrayAdapter<CharSequence> genreAdapter = ArrayAdapter.createFromResource(this, R.array.genre, android.R.layout.simple_spinner_item);
        genreAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genreSpinner.setAdapter(genreAdapter);

        ArrayAdapter<CharSequence> languageAdapter = ArrayAdapter.createFromResource(this, R.array.language, android.R.layout.simple_spinner_item);
        languageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        languageSpinner.setAdapter(languageAdapter);*/
    }


    /*Method to count the number of books reviewed so far*/
    public String booksReadSoFar(){
        String bookCount="";
        return(bookCount=db_handler.totalBookCount());


    }

   /* public void ownBook(View view){
        boolean radioOptionSelected=((RadioButton) view).isChecked();
        switch(view.getId()){

            case R.id.YesRadioButton :
                if(radioOptionSelected) {
                    ownBook = "yes";
                    Toast.makeText(this,"own book",Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(this,"Please choose if it's your own book",Toast.LENGTH_LONG).show();
                }
                break;

            case R.id.NoRadioButton:
                if(radioOptionSelected) {
                    ownBook = "no";
                    Toast.makeText(this,"not my own book",Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(this,"Please choose if it's your own book",Toast.LENGTH_LONG).show();
                }
                break;

        }
    }*/


    /* Method to add reviewed books to the database */
    public void addToReviewedList(View view){

        //Add the date stamp
        Date currentDate=new Date();
        SimpleDateFormat currentDateFormat=new SimpleDateFormat("d-M-y H-mm");
        String date=currentDateFormat.format(currentDate);






        //Check for null values in the fields
        if(bookNameEditText.getText().toString().equals("") || authorEditText.getText().toString().equals("") || genreEditText.getText().toString().equals("")
           || languageEditText.getText().toString().equals("") ||  reviewEditText.getText().toString().equals("") || ratingEditText.getText().toString().equals("")) {

            Toast.makeText(this,"Please enter all the fields",Toast.LENGTH_LONG).show();
            return;


        }
        else{





            ReviewedBooks book=new ReviewedBooks(bookNameEditText.getText().toString(),
                    authorEditText.getText().toString(),genreEditText.getText().toString(),
                    languageEditText.getText().toString(),reviewEditText.getText().toString(),
                    ratingEditText.getText().toString(),date);

            //add the book to the ReadBook Database
            db_handler.addBook(book);

            //set all the text fields back to null;
            bookNameEditText.setText("");
            authorEditText.setText("");
            genreEditText.setText("");
            languageEditText.setText("");
            reviewEditText.setText("");
            ratingEditText.setText("");

            //notify the book being added
            Toast.makeText(this, "added", Toast.LENGTH_LONG).show();
        }


    }


    /*public void toRead(View view) {
        Intent i = new Intent(this, ToRead.class);
        startActivity(i);
    }*/

    public void readBooks(View view) {
        Intent i = new Intent(this, ReadBooks.class);
        startActivity(i);
    }

    public  void wishListActivity(View view) {
        Intent i = new Intent(this, WishList.class);
        startActivity(i);
    }

    public void showGraph(View view){
        Intent i = new Intent(this, ShowGraph.class);
        startActivity(i);
    }


    public void myCollection(View view){
        Intent i=new Intent(this,MyBooks.class);
        startActivity(i);
    }
}
