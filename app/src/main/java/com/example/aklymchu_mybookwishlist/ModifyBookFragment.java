package com.example.aklymchu_mybookwishlist;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import java.time.Year;

public class ModifyBookFragment extends DialogFragment {
    interface ModifyBookDialogListener {
        void modifyBook(Book book, String title, String author, String genre, Integer year, Boolean isRead);
        void deleteBook(Book book);
    }
    private ModifyBookDialogListener listener;
    private Book currentBook;

    public ModifyBookFragment() {
        this.currentBook = new Book(null, null, null,null , false);


    }
    public ModifyBookFragment(Book bookToEdit) {
        this.currentBook = bookToEdit;

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof ModifyBookDialogListener) {
            listener = (ModifyBookDialogListener) context;
        } else {
            throw new RuntimeException(context + " must implement AddCityDialogListener");
        }

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        System.out.println("Captured View!!!!!!!!");
        View view = LayoutInflater.from(getContext()).inflate(R.layout.add_or_modify_book_fragment, null);

        EditText editBookTitle = view.findViewById(R.id.edit_book_Title);
        editBookTitle.setText(currentBook.getTitle());

        EditText editBookAuthor = view.findViewById(R.id.edit_book_Author);
        editBookAuthor.setText(currentBook.getAuthor());

        EditText editBookGenre = view.findViewById(R.id.edit_book_Genre);
        editBookGenre.setText(currentBook.getGenre());

        EditText editBookDate = view.findViewById(R.id.edit_book_Date);
        if (currentBook.getYear() == null) {
            editBookDate.setText(null);
        } else {
            editBookDate.setText(String.valueOf(currentBook.getYear()));
        }
        CheckBox editReadCheckbox = view.findViewById((R.id.read_checkbox));
        editReadCheckbox.setChecked(currentBook.getRead());


        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        return builder
                .setView(view)
                .setTitle("Add/Modify a book")
                .setNegativeButton("Cancel/Delete Book", (dialog, which) -> {
                    listener.deleteBook(currentBook);
                })
                .setPositiveButton("Confirm", (dialog, which) -> {
                    String bookTitle = editBookTitle.getText().toString();
                    String bookAuthor = editBookAuthor.getText().toString();
                    String bookGenre = editBookGenre.getText().toString();
                    Integer bookDate = null;
                    String strOfDate = editBookDate.getText().toString();
                     if (!strOfDate.equals("")) {
                         bookDate = Integer.valueOf(strOfDate);
                    }
                    Boolean isRead = editReadCheckbox.isChecked();
                    listener.modifyBook(currentBook, bookTitle, bookAuthor, bookGenre, bookDate, isRead);
                })
                .create();
    }

}

