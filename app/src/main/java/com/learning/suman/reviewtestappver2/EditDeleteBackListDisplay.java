package com.learning.suman.reviewtestappver2;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


public class EditDeleteBackListDisplay extends ActionBarActivity /*implements AdapterView.OnItemClickListener*/ {


    ReadBooksDBHandler readBooksDataHandler;
    ToReadDBHandler displayDataHandler;
    String bookName="";
    String deleteBook="";
    String activityClass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_delete_back_list_display);


        Intent i=getIntent();
        final String bookSelected=i.getStringExtra("bookName");
        activityClass=i.getStringExtra("activityClass");
        final String reviewData=i.getStringExtra("reviewData");
        final int pos=i.getIntExtra("sortOptionPosition",3);
        //Toast.makeText(this,bookSelected,Toast.LENGTH_LONG).show();

        int j=0;
        /*while(bookSelected.charAt(j) != ' ') {

                bookName = bookName + bookSelected.charAt(j);
                j++;
            }*/

        int index=bookSelected.indexOf(" by ");
        while(j<index) {

            deleteBook = deleteBook + bookSelected.charAt(j);
            j++;
        }


        final String[] options={bookSelected,"Edit","Delete","Back"};



        final RelativeLayout editDeleteBackRelativeLayout=(RelativeLayout)findViewById(R.id.editDeleteBackRelativeLayout);
        final TextView confirmDeleteTextView=(TextView) findViewById(R.id.confirmDeleteTextView);
        final Button confirmDeleteButton=(Button)findViewById(R.id.confirmDeleteButton);
        final Button confirmCancelButton=(Button)findViewById(R.id.confirmCancelButton);

        confirmCancelButton.setVisibility(View.INVISIBLE);
        confirmDeleteButton.setVisibility(View.INVISIBLE);
        confirmDeleteTextView.setVisibility(View.INVISIBLE);

        final ListView editDeleteBackListView=(ListView)findViewById(R.id.editDeleteBackListView);
        ListAdapter editDeleteBackListAdapter=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_activated_1,options);
        editDeleteBackListView.setAdapter(editDeleteBackListAdapter);



        editDeleteBackListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String actionSelected=String.valueOf(parent.getItemAtPosition(position));
                //Toast.makeText(getApplicationContext(),actionSelected,Toast.LENGTH_LONG).show();
                //Toast.makeText(getApplication(),activityClass,Toast.LENGTH_LONG).show();
                if (actionSelected=="Back"){

                    if (activityClass.equals("WishList")){

                        //Toast.makeText(getApplication(),"wishlist-----"+activityClass,Toast.LENGTH_LONG).show();
                        Intent wishListIntent=new Intent(getApplicationContext(),WishList.class);
                        wishListIntent.putExtra("sortOptionPosition",pos);
                        startActivity(wishListIntent);
                    }
                    else if(activityClass.equals("ReadBooks")) {

                        //Toast.makeText(getApplication(),"ReadBooks---"+activityClass,Toast.LENGTH_LONG).show();
                        Intent readBooksIntent = new Intent(getApplicationContext(), ReadBooks.class);
                        startActivity(readBooksIntent);


                    }
                }
                else if (actionSelected=="Delete"){
                    //appearance of text view and 2 buttons asking for deletion confirmation
                    Toast.makeText(getApplicationContext(),"Are you sure you want to delete?",Toast.LENGTH_LONG).show();
                    editDeleteBackListView.setVisibility(View.INVISIBLE);
                    confirmCancelButton.setVisibility(View.VISIBLE);
                    confirmDeleteButton.setVisibility(View.VISIBLE);
                    confirmDeleteTextView.setVisibility(View.VISIBLE);
                    editDeleteBackRelativeLayout.setBackgroundColor(Color.parseColor("#484848"));

                }

                else if (actionSelected=="Edit"){
                    //back to toreadscreen with thebookname etc in the textfields
                    //update the record

                    if(activityClass.equals("WishList")) {
                        deleteWishListBook(deleteBook);
                        Intent toReadIntent = new Intent(getApplicationContext(), ToRead.class);
                        toReadIntent.putExtra("editBookInformation", bookSelected);
                        toReadIntent.putExtra("activityClass", activityClass);


                        startActivity(toReadIntent);
                    }
                    else if(activityClass.equals("ReadBooks")){
                        deleteReadBook(deleteBook);
                        Intent homeIntent = new Intent(getApplicationContext(), MainActivity.class);
                        homeIntent.putExtra("editBookInformation", bookSelected);
                        homeIntent.putExtra("activityClass", activityClass);
                        homeIntent.putExtra("reviewData",reviewData);
                        startActivity(homeIntent);
                    }

                }

                confirmDeleteButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (activityClass.equals("WishList")) {
                            deleteWishListBook(deleteBook);
                            Intent wishListIntent = new Intent(getApplicationContext(), WishList.class);
                            startActivity(wishListIntent);
                        } else if (activityClass.equals("ReadBooks")) {
                            deleteReadBook(deleteBook);
                            Intent homeIntent = new Intent(getApplicationContext(), ReadBooks.class);
                            startActivity(homeIntent);
                        }

                    }
                });
            };
        });


   }




    public void deleteWishListBook(String bookName){
        displayDataHandler=new ToReadDBHandler(this,null,null,1);
        displayDataHandler.deleteBook(bookName);
        Toast.makeText(this,"deleted"+bookName,Toast.LENGTH_LONG).show();


    }

    public void deleteReadBook(String bookName){
        readBooksDataHandler=new ReadBooksDBHandler(this,null,null,1);
        readBooksDataHandler.deleteBook(bookName);
        Toast.makeText(this,"deleted"+bookName,Toast.LENGTH_LONG).show();


    }


    public void cancelDeleteWishListBook(View view){

        if (activityClass.equals("WishList")) {

            Intent wishListIntent = new Intent(getApplicationContext(), WishList.class);
            startActivity(wishListIntent);
        }
        else if (activityClass.equals("ReadBooks")) {
            Intent readBooksIntent = new Intent(getApplicationContext(), ReadBooks.class);
            startActivity(readBooksIntent);

        }

    }


}
