package com.iuiu_library.huzykamz.iuiulibrary;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.iuiu_library.huzykamz.iuiulibrary.adapters.AdministratorAdapter;
import com.iuiu_library.huzykamz.iuiulibrary.adapters.ScienceAdapter;
import com.iuiu_library.huzykamz.iuiulibrary.json.AdministratorJson;
import com.iuiu_library.huzykamz.iuiulibrary.model.AdministratorModel;
import com.iuiu_library.huzykamz.iuiulibrary.model.ScienceModel;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import iuiu.login.app.LoginActivity;
import iuiu.login.app.Register;
import iuiu.login.app.SQLiteHandler;
import iuiu.login.app.SessionManager;

/**
 * Created by HUZY_KAMZ on 5/25/2016.
 */
public class Administrator extends AppCompatActivity {
    private TextView txtName;
    private TextView txtEmail;
    private Button btnLogout;

    private SQLiteHandler db;
    private SessionManager session;
    private Spinner spinner1, spinner2;
    private Button btnSubmit;
    private static final String PHOTOS_BASE_URL =
            "http://192.168.43.104/Library/MedicinePics/";

    public static String KEY_USERNAME = "username";
    public static String KEY_BOOKNAME = "bookname";
    public static String KEY_RENUMBER = "reg_number";
    public static String KEY_BORROWDATE = "borrow_date";
    public static String KEY_BORROWDAYS = "borrow_days";
    public static String KEY_INITAL = "initial";
    public static String KEY_FINAL = "final";

    ListView listView_admin;
    ProgressBar pb;
    List<MyTask> tasks;

    List<AdministratorModel> modelClassList;
    EditText Sch;
    AdministratorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.administrator);
        Infor();
        listView_admin = (ListView) findViewById(R.id.listView_admin);


        getSupportActionBar().setDisplayShowHomeEnabled(true);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Administrator Panel ");
        pb = (ProgressBar) findViewById(R.id.progressBar_admin);
        pb.setVisibility(View.INVISIBLE);
        tasks = new ArrayList<>();
        Fetch();


        txtName = (TextView) findViewById(R.id.name);
        txtEmail = (TextView) findViewById(R.id.email);


        // SqLite database handler
        db = new SQLiteHandler(getApplicationContext());

        // session manager
        session = new SessionManager(getApplicationContext());

        if (!session.isLoggedIn()) {
            logoutUser();
        }








    }


    public void Infor() {

        listView_admin = (ListView) findViewById(R.id.listView_admin);

        listView_admin.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AdministratorModel jhild = (AdministratorModel) adapter.getItem(position);


                Intent in = new Intent(getApplicationContext(), AdministratorInfo.class);
                in.putExtra(KEY_USERNAME, jhild.getName());
                in.putExtra(KEY_RENUMBER, jhild.getRegno());
                in.putExtra(KEY_BORROWDATE, jhild.getBorrow_date());
                in.putExtra(KEY_BOOKNAME, jhild.getBookname());
                in.putExtra(KEY_BORROWDAYS, jhild.getBorrowing_days());
                in.putExtra(KEY_INITAL, jhild.getInitialdate());
                in.putExtra(KEY_FINAL, jhild.getFinaldate());


                startActivity(in);
            }
        });


    }

    private void logoutUser() {
        session.setLogin(false);

        db.deleteUsers();

        // Launching the login activity
        Intent intent = new Intent(Administrator.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.logout_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (item.getItemId()) {

            case R.id.action_logout:

                logoutUser();

            case R.id.action_register:
Intent intent = new Intent(Administrator.this,Register.class);
                startActivity(intent);

            case android.R.id.home:
                onBackPressed();
                break;
        }


        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void Fetch(){

        Thread timerThread = new Thread(){
            public void run(){
                try{
                    if (isOnline()) {
                        requestData("http://192.168.43.104/Library/Administrator/fecth.php");
                    } else {

                    }

                }catch(NullPointerException e){
                    e.printStackTrace();
                    Toast.makeText(Administrator.this, "Check your Connectivity....", Toast.LENGTH_LONG).show();
                }
            }
        };
        timerThread.start();
    }





    private void requestData(String uri) {
        MyTask task = new MyTask();
        task.execute(uri);
    }

    protected void updateDisplay() {
        //Use LandAdapter to display data
        adapter = new AdministratorAdapter(this, R.layout.item_administrator, modelClassList);
        listView_admin =(ListView) findViewById(R.id.listView_admin);
        listView_admin.setAdapter(adapter);
    }

    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }

    private class MyTask extends AsyncTask<String, String, List<AdministratorModel>> {

        @Override
        protected void onPreExecute() {
            if (tasks.size() == 0) {
                pb.setVisibility(View.VISIBLE);
            }
            tasks.add(this);
        }

        @Override
        protected List<AdministratorModel> doInBackground(String... params) {
            try {

                String content = HttpManager.getData(params[0], "feeduser", "feedpassword");
                modelClassList = AdministratorJson.parseFeed(content);
//match pics
                for (AdministratorModel modelClass : modelClassList) {
                    try {
                        String imageUrl = PHOTOS_BASE_URL + modelClass.getPhoto();
                        InputStream in = (InputStream) new URL(imageUrl).getContent();
                        Bitmap bitmap = BitmapFactory.decodeStream(in);
                        modelClass.setBitmap(bitmap);
                        in.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }


            }
            catch (NullPointerException e)

            {
                Toast.makeText(Administrator.this, "Check your Connection...", Toast.LENGTH_LONG).show();
            }
            return modelClassList;
        }

        @Override
        protected void onPostExecute(List<AdministratorModel> result) {

            tasks.remove(this);
            if (tasks.size() == 0) {
                pb.setVisibility(View.INVISIBLE);
            }

            if (result == null) {
                Toast.makeText(Administrator.this, "Data is  not available", Toast.LENGTH_LONG).show();
                return;
            }

            modelClassList = result;
            updateDisplay();

        }

    }
}
