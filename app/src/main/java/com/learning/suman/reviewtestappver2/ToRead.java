package com.learning.suman.reviewtestappver2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ToRead extends ActionBarActivity {


    EditText bookNameEditText;
    EditText authorNameEditText;
    EditText languageEditText;
    EditText genreEditText;
    TextView databaseGenreTextView;

    //Intent editBookselected;

    ToReadDBHandler db_handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.to_read);

        String bookName="";
        String author="";
        String language="";
        String genre="";
        String data="";
        int i=0;
        String bookSelected="";
        String editBookSelectedInfo="";
        Intent editBookselected=getIntent();
        //Bundle
        bookSelected=editBookselected.getStringExtra("editBookInformation");

        List<String> record=new ArrayList<String>();
        /*genreSpinner=(Spinner) findViewById(R.id.genreSpinner);
        languageSpinner=(Spinner) findViewById(R.id.languageSpinner);*/
        bookNameEditText=(EditText)findViewById(R.id.homeScreenBookNameEditText);
        authorNameEditText=(EditText)findViewById(R.id.homeScreenAuthorEditText);
        languageEditText=(EditText)findViewById(R.id.homeScreenLanguageEditText);
        genreEditText=(EditText)findViewById(R.id.homeScreenGenreEditText);


        //databaseGenreTextView=(TextView)findViewById(R.id.databaseGenreTextView);

        db_handler=new ToReadDBHandler(this,null,null,1);

        if (bookSelected==null){
            return;
        }

        else{

            //book by author in lang of type genre on date
            editBookSelectedInfo = bookSelected.replace(" by ","|");
            editBookSelectedInfo=editBookSelectedInfo.replace(" in ","|");
            editBookSelectedInfo=editBookSelectedInfo.replace(" of type ","|");
            editBookSelectedInfo=editBookSelectedInfo.replace(" on ","|");
            editBookSelectedInfo=editBookSelectedInfo+"|";
            //book|author|lang|genre|date

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
            authorNameEditText.setText(record.get(1).toString());
            languageEditText.setText(record.get(2).toString());
            genreEditText.setText(record.get(3).toString());

        }



        //printDatabase();

       /* ArrayAdapter<CharSequence> genreAdapter= ArrayAdapter.createFromResource(this,R.array.genre,android.R.layout.simple_spinner_item);
        genreAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genreSpinner.setAdapter(genreAdapter);

        genreSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                //displayTextView.setText("selected!"+position);
                TextView genredisplayTextView=(TextView)genreSpinner.getSelectedView();
                genre_Item=genredisplayTextView.getText().toString();
                databaseGenreTextView.setText(genre_Item);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

        ArrayAdapter<CharSequence> languageAdapter= ArrayAdapter.createFromResource(this,R.array.language,android.R.layout.simple_spinner_item);
        languageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        languageSpinner.setAdapter(languageAdapter);

        languageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //displayTextView.setText("selected!"+position);
                TextView languagedisplayTextView=(TextView)languageSpinner.getSelectedView();
                language_Item=languagedisplayTextView.getText().toString();
                databaseLanguageTextView.setText(language_Item);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });*/
    }

    public void addToList(View view){
        //Toast.makeText(this, "before adding", Toast.LENGTH_LONG).show();
        /*Books book=new Books(bookNameEditText.getText().toString(),authorNameEditText.getText().toString(),
                databaseGenreTextView.getText().toString(),databaseLanguageTextView.getText().toString());*/

        Date currentDate=new Date();
        SimpleDateFormat currentDateFormat=new SimpleDateFormat("d-M-y H-mm");
        String date=currentDateFormat.format(currentDate);

        Books book=new Books(bookNameEditText.getText().toString(),authorNameEditText.getText().toString(),
                genreEditText.getText().toString(),languageEditText.getText().toString(),date);
        //Books book=new Books("book","author","genre1","lang1");
        db_handler.addBook(book);
        bookNameEditText.setText("");
        authorNameEditText.setText("");
        genreEditText.setText("");
        languageEditText.setText("");

        Toast.makeText(this, "added", Toast.LENGTH_LONG).show();
        //printDatabase();



    }

    public  void deleteBook(View view){
        String input=bookNameEditText.getText().toString();
        db_handler.deleteBook(input);
        printDatabase();

    }

    public void printDatabase(){
        String dbString=db_handler.databaseToString();
        databaseGenreTextView.setText(dbString);
        bookNameEditText.setText("");
        authorNameEditText.setText("");
        genreEditText.setText("");
        languageEditText.setText("");

    }


    public void homescreenButtonClick(View view){
        Intent i=new Intent(this,MainActivity.class);
        startActivity(i);
    }

    public void readBooks(View view){
        Intent i=new Intent(this,ReadBooks.class);
        startActivity(i);
    }

    public void showBooksList(View view){
        Intent i=new Intent(this,WishList.class);
        startActivity(i);
    }
}

