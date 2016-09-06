package com.iuiu_library.huzykamz.iuiulibrary;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by HUZY_KAMZ on 5/25/2016.
 */
public class Select_Faculty extends AppCompatActivity {

    Spinner select;
    Button search;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_faculty);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Choose Your Faculty ");


        select =(Spinner) findViewById(R.id.spinner_select_faculty);
        search =(Button) findViewById(R.id.search_faculty);

        String Faculties [] ={"Faculty Of Medicine","Faculty Of Science","Faculty Of Law","Faculty of Education"
        ,"Faculty Of Social Arts"};

        ArrayAdapter<String> faculty_adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,Faculties);
        select.setAdapter(faculty_adapter);

        select.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position){


                    case 0:

                        search.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(getApplicationContext(),"Loading Faculty Of Medicine Books",Toast.LENGTH_LONG).show();
                                Intent i = new Intent(Select_Faculty.this,Medicine.class);
                                startActivity(i);

                            }
                        });
                        break;

                    case 1:

                          search.setOnClickListener(new View.OnClickListener() {
                              @Override
                              public void onClick(View v) {
                                  Toast.makeText(getApplicationContext(),"Loading Faculty Of Science Books",Toast.LENGTH_LONG).show();
                                  Intent i = new Intent(Select_Faculty.this,Science.class);
                                  startActivity(i);

                              }
                          });

                        break;

                    case 2:

                        search.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(getApplicationContext(),"Loading Faculty Of Law Books",Toast.LENGTH_LONG).show();
                                Intent i = new Intent(Select_Faculty.this,Law.class);
                                startActivity(i);

                            }
                        });
                        break;

                    case 3:

                        search.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(getApplicationContext(),"Loading Faculty Of Education Books",Toast.LENGTH_LONG).show();
                                Intent i = new Intent(Select_Faculty.this,Education.class);
                                startActivity(i);

                            }
                        });


                        break;
                    case 4:


                        search.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(getApplicationContext(),"Loading Faculty Of Social Arts Books",Toast.LENGTH_LONG).show();
                                Intent i = new Intent(Select_Faculty.this,SocialArts.class);
                                startActivity(i);

                            }
                        });
                        
                        break;







                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("Are sure you want to logout...");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user pressed "yes", then he is allowed to exit from application
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user select "No", just cancel this dialog and continue with app
                dialog.cancel();
            }
        });
        AlertDialog alert=builder.create();
        alert.show();
    }
}
