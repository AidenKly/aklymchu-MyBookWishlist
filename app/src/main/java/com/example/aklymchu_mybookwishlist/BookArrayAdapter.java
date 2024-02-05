package com.example.aklymchu_mybookwishlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class BookArrayAdapter extends ArrayAdapter {
    public BookArrayAdapter(Context context, ArrayList<Book> BookList) {
        super(context,0, BookList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.list_item_content, parent, false);
        } else {
            view = convertView;
        }

        Book book = (Book) getItem(position);
        TextView bookTitle = view.findViewById(R.id.book_title);
        TextView bookAuthor = view.findViewById(R.id.book_author);
        TextView bookGenre = view.findViewById(R.id.book_genre);
        TextView bookDate = view.findViewById(R.id.book_date);
        CheckBox readCheckbox =view.findViewById(R.id.read_checkbox);



        bookTitle.setText(book.getTitle());
        bookAuthor.setText(book.getAuthor());
        bookGenre.setText(book.getGenre());
        bookDate.setText(book.getYear().toString());
        readCheckbox.setChecked(book.getRead());
        return view;
    }


}
