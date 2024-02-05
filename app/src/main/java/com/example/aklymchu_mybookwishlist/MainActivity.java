package com.example.aklymchu_mybookwishlist;

import static android.service.autofill.Validators.or;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.aklymchu_mybookwishlist.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements ModifyBookFragment.ModifyBookDialogListener {
    //Purpose: Host the logic for the main android activity
    //Design rationale: This Class is used to contain the logic for the main android app activity
    //is is responsible for the implementaions of modifyBook(), deleteBook() as well
    // as the on click listeners for the floating action button and the book list.
    private ArrayList<Book> bookDataList = new ArrayList<Book>();
    private ListView bookListView;
    private BookArrayAdapter bookArrayAdapter;
    private Book selectedBook;

    @Override
    public void modifyBook(Book book, String title, String author, String genre, Integer year, Boolean isRead) {
        System.out.println("made it to Modify book");
        if (book == null || Objects.equals(title, "") || Objects.equals(author, "") || Objects.equals(genre, "") || year == null) {
            Toast createBookFailed = new Toast(getApplicationContext()) ;
            createBookFailed.setText("Please fill in all fields");
            createBookFailed.show();
        } else {
            System.out.println(book);
            book.setTitle(title);
            book.setAuthor(author);
            book.setGenre(genre);
            book.setYear(year);
            book.setRead(isRead);
            if (!bookDataList.contains(book)) {
                bookDataList.add(book);
                bookArrayAdapter.notifyDataSetChanged();
            } else {
                bookArrayAdapter.notifyDataSetChanged();
            }
        }

    }

    @Override
    public void deleteBook(Book book) {
        bookDataList.remove(book);
        bookArrayAdapter.notifyDataSetChanged();
    }

    AdapterView.OnItemClickListener bookListSelector  = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectedBook = (Book) bookListView.getItemAtPosition(position);
            new ModifyBookFragment(selectedBook).show(getSupportFragmentManager(), "Edit book");
        }
    };

    View.OnClickListener addBookButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            new ModifyBookFragment().show(getSupportFragmentManager(), "Add a book");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bookListView =findViewById(R.id.book_list);

        bookArrayAdapter = new BookArrayAdapter(this, bookDataList);

        bookListView.setAdapter(bookArrayAdapter);
        bookListView.setOnItemClickListener(bookListSelector);
        FloatingActionButton fab = findViewById(R.id.button_add_book);
        fab.setOnClickListener(addBookButtonListener);

    }

}