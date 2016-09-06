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
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.iuiu_library.huzykamz.iuiulibrary.adapters.ScienceAdapter;
import com.iuiu_library.huzykamz.iuiulibrary.json.AdministratorJson;
import com.iuiu_library.huzykamz.iuiulibrary.json.ScienceJson;
import com.iuiu_library.huzykamz.iuiulibrary.model.ScienceModel;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HUZY_KAMZ on 5/25/2016.
 */
public class Education extends AppCompatActivity {

    private static final String PHOTOS_BASE_URL =
            "http://192.168.43.104/Library/EducationPics/";

    public static String KEY_BOOK_ID="BookId";
    public static String KEY_BOOKNAME="District";
    public static String KEY_DESCRIPTION="Area";
    public static String KEY_AUTHOR="floors";

    GridView gv;
    ProgressBar pb;
    List<MyTask> tasks;

    List<ScienceModel> modelClassList;

    ScienceAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.science);
        Infor();
        gv =(GridView) findViewById(R.id.gridView);


        getSupportActionBar().setDisplayShowHomeEnabled(true);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Faculty Of Education Books ");
        pb = (ProgressBar) findViewById(R.id.progressBar);
        pb.setVisibility(View.INVISIBLE);
        tasks = new ArrayList<>();
        Fetch();
    }


    public void Infor() {

        gv =(GridView) findViewById(R.id.gridView);

        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ScienceModel jhild = (ScienceModel) adapter.getItem(position);


                Intent in = new Intent(getApplicationContext(), BookInfo.class);
                in.putExtra(KEY_DESCRIPTION, jhild.getDescription());
                in.putExtra(KEY_BOOK_ID, jhild.getBookId());
                in.putExtra(KEY_AUTHOR, jhild.getAuthor());
                in.putExtra(KEY_BOOKNAME, jhild.getBookname());


                startActivity(in);
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
    public void Fetch(){

        Thread timerThread = new Thread(){
            public void run(){
                try{
                    if (isOnline()) {
                        requestData("http://192.168.43.104/Library/Education/fecth.php");
                    } else {

                    }

                }catch(NullPointerException e){
                    e.printStackTrace();
                    Toast.makeText(Education.this, "Check your Connectivity....", Toast.LENGTH_LONG).show();
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
        adapter = new ScienceAdapter(this, R.layout.item_science, modelClassList);
        gv =(GridView) findViewById(R.id.gridView);
        gv.setAdapter(adapter);
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

    private class MyTask extends AsyncTask<String, String, List<ScienceModel>> {

        @Override
        protected void onPreExecute() {
            if (tasks.size() == 0) {
                pb.setVisibility(View.VISIBLE);
            }
            tasks.add(this);
        }

        @Override
        protected List<ScienceModel> doInBackground(String... params) {
            try {

                String content = HttpManager.getData(params[0], "feeduser", "feedpassword");
                modelClassList = ScienceJson.parseFeed(content);
//match pics
                for (ScienceModel modelClass : modelClassList) {
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
                Toast.makeText(Education.this, "Check your Connection...", Toast.LENGTH_LONG).show();
            }
            return modelClassList;
        }

        @Override
        protected void onPostExecute(List<ScienceModel> result) {

            tasks.remove(this);
            if (tasks.size() == 0) {
                pb.setVisibility(View.INVISIBLE);
            }

            if (result == null) {
                Toast.makeText(Education.this, "Data is  not available", Toast.LENGTH_LONG).show();
                return;
            }

            modelClassList = result;
            updateDisplay();

        }

    }
}
