package com.iuiu_library.huzykamz.iuiulibrary;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

import iuiu.login.app.LoginActivity;
import student.login.app.StudentLogin;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public int currentimageindex=0;
    //    Timer timer;
//    TimerTask task;
    ImageView slidingimage;

    private int[] IMAGE_IDS = {
            R.drawable.garden1, R.drawable.garden2
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);









        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        //Animation starts...
        final Handler mHandler = new Handler();

        // Create runnable for posting
        final Runnable mUpdateResults = new Runnable() {
            public void run() {

               AnimateandSlideShow();

            }
        };

        int delay = 1000; // delay for 1 sec.

        int period = 15000; // repeat every 4 sec.

        Timer timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {

            public void run() {

                mHandler.post(mUpdateResults);

            }

        }, delay, period);

    }
    private void AnimateandSlideShow() {

        try {
            slidingimage = (ImageView) findViewById(R.id.mainactivity_image_view);
            slidingimage.setImageResource(IMAGE_IDS[currentimageindex % IMAGE_IDS.length]);

            currentimageindex++;

            Animation rotateimage = AnimationUtils.loadAnimation(MainActivity.this, R.anim.fade_in);


            slidingimage.startAnimation(rotateimage);
        }
        catch (NullPointerException e){
            e.printStackTrace();


        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
   Intent i;
        if (id == R.id.nav_camera) {
            // Handle the camera action
  i = new Intent(MainActivity.this,StudentLogin.class);
            startActivity(i);

        } else if (id == R.id.nav_gallery) {


i = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_slideshow) {

            /*https://196.43.182.3:92/?AspxAutoDetectCookieSupport=1*/

            String url2 = "https://196.43.182.3:92/?AspxAutoDetectCookieSupport=1";
            Intent q = new Intent(Intent.ACTION_VIEW);
            q.setData(Uri.parse(url2));
            startActivity(q);

        } else if (id == R.id.nav_manage) {
                    i = new Intent(MainActivity.this, About.class);
            startActivity(i);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
