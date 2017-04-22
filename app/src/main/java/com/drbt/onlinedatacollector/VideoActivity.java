package com.drbt.onlinedatacollector;

import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class VideoActivity extends AppCompatActivity {

    VideoView videoView;
    MediaController mediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_layout);

        videoView =(VideoView)findViewById(R.id.videoView);

        //Creating MediaController
        mediaController= new MediaController(this);
        mediaController.setAnchorView(videoView);

        //specify the location of media file
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/"
                + R.raw.video); // initialize Uri here

        //Setting MediaController and URI, then starting the videoView
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(uri);
        videoView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        videoView.requestFocus();
        videoView.start();
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();

        if(mediaController.isShowing()) {
            Toast.makeText(this,"Press again to go back",Toast.LENGTH_SHORT).show();
        }
        else {

            videoView.stopPlayback();
            finish();

        }

    }
}
