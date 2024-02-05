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

    private ArrayList<Book> bookDataList = new ArrayList<Book>();
    private ListView bookListView;
    private BookArrayAdapter bookArrayAdapter;
    private Book selectedBook;

    private TextView bookCount;
    private TextView readCount;

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
                int bookCountNumber = Integer.parseInt(bookCount.getText().toString());
                bookCount.setText(bookCountNumber+1);
                bookArrayAdapter.notifyDataSetChanged();
            }
        }
        for (Book bookInDataList : bookDataList ){
            if (bookInDataList.getRead()) {
                readCount.setText(Integer.parseInt(readCount.getText().toString()));
            }
        }
    }

    @Override
    public void deleteBook(Book book) {
        bookDataList.remove(book);
        int bookCountNumber = Integer.parseInt(bookCount.getText().toString());
        bookCount.setText(bookCountNumber-1);
        for (Book bookInDataList : bookDataList ){
            if (bookInDataList.getRead()) {
                readCount.setText(Integer.parseInt(readCount.getText().toString()));
            }
        }
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
        Book newBook = new Book("Artemis", "Andy Weir", "science fiction", 2017, true);
        Book newBook2 = new Book("Artemis", "Andy Weir", "science fiction", 2017, true);
        bookDataList.add(newBook);
        bookDataList.add(newBook2);

        bookListView =findViewById(R.id.book_list);

        bookArrayAdapter = new BookArrayAdapter(this, bookDataList);

        bookListView.setAdapter(bookArrayAdapter);
        bookListView.setOnItemClickListener(bookListSelector);
        FloatingActionButton fab = findViewById(R.id.button_add_book);
        fab.setOnClickListener(addBookButtonListener);

        bookCount = findViewById(R.id.book_count_number);
        readCount = findViewById(R.id.read_count_number);

    }

}