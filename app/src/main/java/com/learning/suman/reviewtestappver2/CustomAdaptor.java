
package com.learning.suman.reviewtestappver2;


import android.widget.ArrayAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CustomAdaptor extends ArrayAdapter<String> {
    public CustomAdaptor(Context context, List<String> books) {
        super(context,R.layout.books_display_layout,books);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater booksInflater=LayoutInflater.from(getContext());
        View customwView=booksInflater.inflate(R.layout.books_display_layout,parent,false);

        String eachBook=getItem(position);
        TextView myBooksTextView=(TextView) customwView.findViewById(R.id.displayTextView);
        //TextView bookItemTextView=(TextView)customwView.findViewById(R.id.bookItemTextView);
        ImageView booksImageView=(ImageView)customwView.findViewById(R.id.displayImageView);

        myBooksTextView.setText(eachBook);
        booksImageView.setImageResource(R.mipmap.ic_launcher);

        return customwView;

    }
}
