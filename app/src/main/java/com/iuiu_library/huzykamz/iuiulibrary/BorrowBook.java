package com.iuiu_library.huzykamz.iuiulibrary;

import android.app.VoiceInteractor;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by HUZY_KAMZ on 5/26/2016.
 */
public class BorrowBook extends AppCompatActivity {


    EditText BookId, Name, Reg, Days,borrow_date;
    Button request;
    private static final String REGISTER_URL ="http://192.168.43.104/Library/Borrow/volleyRegister.php";
    public static final String KEY_NAME= "Name";
    public static final String KEY_BOOK_ID= "BookId";
    public static final String KEY_REG= "email";
    public static final String KEY_DAYS= "NumberOfDays";
    public static final String KEY_BORROW_DATE ="BorrowDate";
  String bookid="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.borrow_book);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Borrow A book");

        BookId =(EditText) findViewById(R.id.et_book_id);
        Intent inn = getIntent();
        if (null != inn) {

            bookid = inn.getStringExtra(Science.KEY_BOOK_ID);

            BookId.setText(bookid);

        }
        Name =(EditText)findViewById(R.id.et_name);
        Reg =(EditText) findViewById(R.id.et_reg);
        Days = (EditText) findViewById(R.id.et_days);
        borrow_date = (EditText) findViewById(R.id.borrow_date);

        request =(Button) findViewById(R.id.request);


        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });


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




    public void registerUser(){
        final String name_ = Name.getText().toString().trim();
        final String bookid_ = BookId.getText().toString().trim();
        final String reg_ = Reg.getText().toString().trim();
        final String days_ = Days.getText().toString().trim();
        final String borrow_date_ = borrow_date.getText().toString().trim();


        StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTER_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Toast.makeText(BorrowBook.this, s, Toast.LENGTH_SHORT).show();
                Toast.makeText(BorrowBook.this,"",Toast.LENGTH_SHORT).show();


            }
        }, new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(BorrowBook.this, volleyError.toString(),Toast.LENGTH_LONG).show();

            }
        }){
            protected Map<String,String> getParams()
            {
                Map<String,String> params = new HashMap<String,String>();
                params.put(KEY_NAME,name_);
                params.put(KEY_BOOK_ID,bookid_);
                params.put(KEY_REG,reg_);
                params.put(KEY_DAYS,days_);
                params.put(KEY_BORROW_DATE,borrow_date_);

                return params;


            }
        };


        Volley.newRequestQueue(this).add(stringRequest);



    }
}
