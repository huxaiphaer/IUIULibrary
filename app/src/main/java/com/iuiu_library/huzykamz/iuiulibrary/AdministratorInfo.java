package com.iuiu_library.huzykamz.iuiulibrary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

/**
 * Created by HUZY_KAMZ on 5/26/2016.
 */
public class AdministratorInfo extends AppCompatActivity {
    String username="";
    String regno ="";
    String bookname="";
    String borrowdate ="";
    String borrowdays ="";
    String initial_ ="";
    String final_ = "";
    TextView info_borrowdays;
    TextView return_book;

    double days;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_info);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Students Information ");


        Intent inn = getIntent();
        if (null != inn) {
            username = inn.getStringExtra(Administrator.KEY_USERNAME);
            regno = inn.getStringExtra(Administrator.KEY_RENUMBER);
            borrowdate = inn.getStringExtra(Administrator.KEY_BORROWDATE);
            borrowdays = inn.getStringExtra(Administrator.KEY_BORROWDAYS);
            bookname = inn.getStringExtra(Administrator.KEY_BOOKNAME);
            initial_ = inn.getStringExtra(Administrator.KEY_INITAL);
            final_ = inn.getStringExtra(Administrator.KEY_FINAL);


            double INITIAL = Double.parseDouble(initial_);
            double FINAL = Double.parseDouble(final_);
            /*
            int INITIAL = Integer.parseInt(initial_);
            int FINAL = Integer.parseInt(final_);
*/


            days = (FINAL - INITIAL);



            if (days <=0.0){



                 return_book =(TextView) findViewById(R.id.return_book);
                return_book.setText(R.string.returnu);

                TextView info_name = (TextView) findViewById(R.id.info_name);
                info_name.setText("User Name :" + username);

                TextView info_reg = (TextView) findViewById(R.id.info_reg);
                info_reg.setText("Reg Number :" + regno);

                TextView info_bookname = (TextView) findViewById(R.id.info_bookname);
                info_bookname.setText("Book Name :" + bookname);

                info_borrowdays = (TextView) findViewById(R.id.info_borrowdays);
                info_borrowdays.setText("Days Left : " + days + " day(s)");


                TextView info_borrowdate = (TextView) findViewById(R.id.info_borrowdate);
                info_borrowdate.setText("Date Of Borrowing : " + borrowdate);


                info_borrowdays.setVisibility(View.INVISIBLE);
                return_book.setVisibility(View.VISIBLE);

            }


             else {


                // Attach



                return_book =(TextView) findViewById(R.id.return_book);
                return_book.setText("  with reg number   has to return back the book");

                TextView info_name = (TextView) findViewById(R.id.info_name);
                info_name.setText("User Name :" + username);

                TextView info_reg = (TextView) findViewById(R.id.info_reg);
                info_reg.setText("Reg Number :" + regno);

                TextView info_bookname = (TextView) findViewById(R.id.info_bookname);
                info_bookname.setText("Book Name :" + bookname);

                info_borrowdays = (TextView) findViewById(R.id.info_borrowdays);
                info_borrowdays.setText("Days Left : " + days + " day(s)");


                TextView info_borrowdate = (TextView) findViewById(R.id.info_borrowdate);
                info_borrowdate.setText("Date Of Borrowing : " + borrowdate);


                return_book.setVisibility(View.INVISIBLE);
                info_borrowdays.setVisibility(View.VISIBLE);

            }

        }






    }







    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                onBackPressed();
                break;


        }
        return super.onOptionsItemSelected(item);
    }
}
