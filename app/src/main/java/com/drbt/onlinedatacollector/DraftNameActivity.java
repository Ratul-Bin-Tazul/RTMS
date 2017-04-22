package com.drbt.onlinedatacollector;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DraftNameActivity extends AppCompatActivity {

    TextView draftNameText;
    Button saveDraftBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draft_name);

        draftNameText = (TextView)findViewById(R.id.draftName);
        saveDraftBtn = (Button)findViewById(R.id.saveDraftBtn);

        saveDraftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (draftNameText.getText().toString().equals("")){

                    Toast.makeText(DraftNameActivity.this,"Please Specify a unique Draft Name",Toast.LENGTH_SHORT).show();
            }else {
                    Intent i = new Intent(DraftNameActivity.this, InputFieldActivity.class);
                    //i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    InputFieldActivity.DraftName = draftNameText.getText().toString();
                    i.putExtra("DraftName", draftNameText.getText().toString());
                    setResult(Activity.RESULT_OK, i);
                    finish();

                }
            }
        });
    }
}
