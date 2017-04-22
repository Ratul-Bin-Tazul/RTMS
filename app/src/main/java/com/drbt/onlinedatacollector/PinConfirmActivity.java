package com.drbt.onlinedatacollector;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PinConfirmActivity extends AppCompatActivity {

    TextView pin;
    Button submitBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin_confirm);

        pin = (TextView)findViewById(R.id.confirmPin);
        submitBtn = (Button)findViewById(R.id.confirmSubmitBtn);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pinNumber = pin.getText().toString();
                SharedPreferences getPin = getSharedPreferences(LoginActivity.USER_PREF,MODE_PRIVATE);
                String actualPin = getPin.getString("pin","");
                if(pinNumber.equals(actualPin)) {
                    Intent i = new Intent(PinConfirmActivity.this,InputFieldActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    setResult(Activity.RESULT_OK,i);
                    finish();

                }else {

                    Intent i = new Intent(PinConfirmActivity.this,InputFieldActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    setResult(Activity.RESULT_CANCELED,i);
                    finish();
                }
            }
        });
    }
}
