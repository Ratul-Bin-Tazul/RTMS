package com.drbt.onlinedatacollector;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Button inputBtn,viewBtn,exportBtn;
    TextView drawerDnsoName,welcomeUsername;
    ImageButton videoImageButton;


    BroadcastReceiver broadcastReceiver;

    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle toggle;
    public Toolbar toolbar;
    VideoView videoView;

    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        try{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar =(Toolbar)findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
        drawerLayout = (DrawerLayout)findViewById(R.id.activity_main);
        inputBtn = (Button) findViewById(R.id.addrecordBtn);
        viewBtn = (Button) findViewById(R.id.viewrecordBtn);
        exportBtn = (Button) findViewById(R.id.exportBtn);
            welcomeUsername = (TextView)findViewById(R.id.wellcomeUsername);
            videoImageButton = (ImageButton)findViewById(R.id.videoImage);

            videoView =(VideoView)findViewById(R.id.videoView);
        //drawerDnsoName = (TextView)findViewById(R.id.dnsoName);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);



            toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
            drawerLayout.addDrawerListener(toggle);
            toggle.syncState();
            //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);
            drawerLayout.setDrawerListener(toggle);

            //setting the DNSO name in the drawer
            drawerDnsoName = (TextView)navigationView.inflateHeaderView(R.layout.nav_header_test).findViewById(R.id.dnsoName);
            sharedPreferences = getSharedPreferences(LoginActivity.USER_PREF,0);
            String user = sharedPreferences.getString("username","no user");
            drawerDnsoName.setText(user);



            //setting up the welcome Username
            welcomeUsername.setText(user);


            inputBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, InputFieldActivity.class);
                startActivityForResult(i,1);
            }
        });

        viewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ViewDataActivity.class);
                startActivity(i);
            }
        });

        exportBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "This feature will be available in the released version", Toast.LENGTH_SHORT).show();
                //startActivity(new Intent(MainActivity.this,HomeActivity.class));
            }
        });

            videoImageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Intent intent = new Intent(Intent.ACTION_VIEW);
//                    intent.setDataAndType(Uri.parse("android.resource://" + getPackageName() + "/"
//                            + R.raw.video), "video/*");
//                    startActivity(intent);

//                    Uri myUri = Uri.parse("android.resource://" + getPackageName() + "/"
//                            + R.raw.video); // initialize Uri here
//                    MediaPlayer mediaPlayer = new MediaPlayer();
//                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//                    try {
//                        mediaPlayer.setDataSource(getApplicationContext(), myUri);
//
//                    mediaPlayer.prepare();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    mediaPlayer.start();



                    //Creating MediaController
//                    MediaController mediaController= new MediaController(getApplicationContext());
//                    mediaController.setAnchorView(videoView);
//
//                    //specify the location of media file
//                    Uri uri = Uri.parse("android.resource://" + getPackageName() + "/"
//                            + R.raw.video); // initialize Uri here
//
//                    //Setting MediaController and URI, then starting the videoView
//                    videoView.setMediaController(mediaController);
//                    videoView.setVideoURI(uri);
//                    videoView.requestFocus();
//                    videoView.start();
                    startActivity(new Intent(MainActivity.this,VideoActivity.class));
                }
            });

            broadcastReceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {


                }
            };

    }catch(Exception e) {

            Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        try {
            registerReceiver(broadcastReceiver,new IntentFilter(internalDatabase.internalDatabaseEntry.UI_UPDATE_BROADCAST));
        }catch(Exception e) {

            Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    protected void onPause() {
        super.onPause();

        try{
            unregisterReceiver(broadcastReceiver);

        }catch(Exception e) {

            Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.right_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.logout:
                SharedPreferences userLoggedIn = getSharedPreferences(LoginActivity.USER_PREF, 0);
                SharedPreferences.Editor editor = userLoggedIn.edit();
                editor.putString("username","no user");
                editor.apply();

                Intent i = new Intent(this,LoginActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                finish();
                return true;
            case R.id.exit:
                finish();
                System.exit(0);
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if(id==R.id.addRecord) {
            Intent i = new Intent(MainActivity.this, InputFieldActivity.class);
            i.putExtra("cameFrom","MainActivity");
            startActivityForResult(i,1);
        }
        else if(id==R.id.viewAllRecord) {
            Intent i = new Intent(MainActivity.this, ViewDataActivity.class);
            startActivity(i);
        }
        else if(id==R.id.draftData) {
            Intent i = new Intent(MainActivity.this, ViewDraftDataActivity.class);
            startActivity(i);
        }
        else if(id==R.id.logout) {
            SharedPreferences userLoggedIn = getSharedPreferences(LoginActivity.USER_PREF, 0);
            SharedPreferences.Editor editor = userLoggedIn.edit();
            editor.putString("username","no user");
            editor.apply();

            Intent i = new Intent(this,LoginActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
            finish();
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
