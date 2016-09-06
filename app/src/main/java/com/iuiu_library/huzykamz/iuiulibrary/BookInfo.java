package com.iuiu_library.huzykamz.iuiulibrary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * Created by HUZY_KAMZ on 5/26/2016.
 */
public class BookInfo extends AppCompatActivity {
    String description="";
    String bookid ="";
    String author="";
    String bookname ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_info);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        Intent inn = getIntent();
        if (null != inn) {
            description = inn.getStringExtra(Science.KEY_DESCRIPTION);
            bookid = inn.getStringExtra(Science.KEY_BOOK_ID);
            author = inn.getStringExtra(Science.KEY_AUTHOR);
            bookname = inn.getStringExtra(Science.KEY_BOOKNAME);

            // Attach

            getSupportActionBar().setTitle(bookname);

            TextView author_info = (TextView) findViewById(R.id.author_info);
            author_info.setText("Author :"+author);

            TextView book_description = (TextView) findViewById(R.id.book_description);
            book_description.setText(description);


        }






    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.borrow_book:

                Intent i = new Intent(BookInfo.this, BorrowBook.class);
                i.putExtra(Science.KEY_BOOK_ID, bookid);
                startActivity(i);
                break;






        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.items_book, menu);
        return true;
    }
}
