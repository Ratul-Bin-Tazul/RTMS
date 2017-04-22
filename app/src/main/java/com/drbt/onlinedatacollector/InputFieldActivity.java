package com.drbt.onlinedatacollector;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class InputFieldActivity extends AppCompatActivity {

    Spinner distChooser,yearChooser,monthChooser;

    Button submitBtn,saveBtn;

    RadioGroup dnsoRadioGroup;


    EditText firstLine,frontLine,male,female,anthropocentric;

    public String DNSOName="Ratul",District="",Month="",Id="",Year="",FirstLine="",FrontLine="",Male="",Female="",Anthropocentric="";

    public static String DraftName="";
    public int lastId = 1;
    public static final int RESULT_CODE_PIN = 1;
    public static final int RESULT_CODE_DRAFT = 2;


    public static final String PREFS_NAME = "ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_field);


            submitBtn = (Button) findViewById(R.id.submitBtn);
            saveBtn = (Button) findViewById(R.id.saveBtn);
            dnsoRadioGroup = (RadioGroup) findViewById(R.id.dnsoRadioGroup);

            firstLine = (EditText) findViewById(R.id.firestLine);
            frontLine = (EditText) findViewById(R.id.frontLine);
            male = (EditText) findViewById(R.id.male);
            female = (EditText) findViewById(R.id.female);
            anthropocentric = (EditText) findViewById(R.id.anthropocentric);

            //Toast.makeText(this,getIntent().getComponent().getShortClassName(),Toast.LENGTH_SHORT).show();
            //Toast.makeText(this, getCallingActivity().getShortClassName(), Toast.LENGTH_SHORT).show();

        //Toast.makeText(this, getIntent().getStringExtra("cameFrom"), Toast.LENGTH_SHORT).show();

        String callingActivity = getIntent().getStringExtra("cameFrom");
        if(!callingActivity.equals("MainActivity")) { //populate with drafted data
            DNSOName= getIntent().getStringExtra("dnsoName");
            District=getIntent().getStringExtra("district");
            Month= getIntent().getStringExtra("month");
            Year=getIntent().getStringExtra("year");
            FirstLine=getIntent().getStringExtra("firstLine");
            FrontLine=getIntent().getStringExtra("frontLine");
            Male=getIntent().getStringExtra("male");
            Female=getIntent().getStringExtra("female");
            Anthropocentric=getIntent().getStringExtra("anthropocentric");

            if(!DNSOName.equals("")) {
                if(DNSOName.equals("ratul"))
                    dnsoRadioGroup.check(R.id.dnsoRatul);
                else if(DNSOName.equals("sadik"))
                    dnsoRadioGroup.check(R.id.dnsoSadik);
                else if(DNSOName.equals("amin"))
                    dnsoRadioGroup.check(R.id.dnsoAmin);
            }

            if(!FirstLine.equals("")) {
                firstLine.setText(FirstLine);
            }
            if(!FrontLine.equals("")) {
                frontLine.setText(FrontLine);
            }
            if(!Male.equals("")) {
                male.setText(Male);
            }
            if(!Female.equals("")) {
                female.setText(Female);
            }

            if(!Anthropocentric.equals("")) {
                anthropocentric.setText(Anthropocentric);
            }

        }


            // Restore preferences
            SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
            lastId = settings.getInt("lastID", 1);
            Id = "" + lastId;


            distChooser = (Spinner) findViewById(R.id.districtChooserSpinner);
            // Create an ArrayAdapter using the string array and a default spinner layout
            ArrayAdapter<CharSequence> distAdapter = ArrayAdapter.createFromResource(this,
                    R.array.district_name_array, android.R.layout.simple_spinner_item);
            // Specify the layout to use when the list of choices appears
            distAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            // Apply the adapter to the spinner
            distChooser.setAdapter(distAdapter);
            distChooser.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    District = parent.getItemAtPosition(position).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                    District = parent.getItemAtPosition(0).toString();
                }
            });

            yearChooser = (Spinner) findViewById(R.id.yearChosserSpinner);
            // Create an ArrayAdapter using the string array and a default spinner layout
            ArrayAdapter<CharSequence> yearAdapter = ArrayAdapter.createFromResource(this,
                    R.array.year_array, android.R.layout.simple_spinner_item);
            // Specify the layout to use when the list of choices appears
            distAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            // Apply the adapter to the spinner
            yearChooser.setAdapter(yearAdapter);
            yearChooser.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    Year = parent.getItemAtPosition(position).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                    Year = parent.getItemAtPosition(0).toString();
                }
            });

            monthChooser = (Spinner) findViewById(R.id.monthChooserSpinner);
            // Create an ArrayAdapter using the string array and a default spinner layout
            ArrayAdapter<CharSequence> monthAdapter = ArrayAdapter.createFromResource(this,
                    R.array.month_array, android.R.layout.simple_spinner_item);
            // Specify the layout to use when the list of choices appears
            distAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            // Apply the adapter to the spinner
            monthChooser.setAdapter(monthAdapter);
            monthChooser.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    Month = parent.getItemAtPosition(position).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                    Month = parent.getItemAtPosition(0).toString();
                }
            });

            dnsoRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    if (checkedId == R.id.dnsoRatul)
                        DNSOName = "Ratul";
                    else if (checkedId == R.id.dnsoSadik)
                        DNSOName = "Sadik";
                    else
                        DNSOName = "Amin";

                }
            });

            submitBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    setToGlobal();

                    if(!DNSOName.equals("") && !District.equals("") && !Month.equals("") && !Id.equals("") && !Year.equals("") && !FirstLine.equals("") && !FrontLine.equals("") && !Male.equals("") && !Female.equals("") && !Anthropocentric.equals("")) {

                        Intent i = new Intent(InputFieldActivity.this, PinConfirmActivity.class);
                        startActivityForResult(i, RESULT_CODE_PIN);
                    }else{
                        Toast.makeText(InputFieldActivity.this,"No field should be empty",Toast.LENGTH_SHORT).show();
                    }



                }
            });

            saveBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(InputFieldActivity.this, DraftNameActivity.class);
                    startActivityForResult(i, RESULT_CODE_DRAFT);
                }
            });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) { //getting the result from confirm activity
        if(requestCode==RESULT_CODE_PIN) {
            if(resultCode==RESULT_OK) {

                setToGlobal();
                Boolean hasInternet = checkInternet();



                if(hasInternet) {


                    saveToServerDatabase();
                    //Toast.makeText(InputFieldActivity.this,"One row added...",Toast.LENGTH_SHORT).show();


                }else{
                    saveToInternalDatabase(false);
                    Toast.makeText(InputFieldActivity.this,"No Internet Connection.\nRecord saved offline",Toast.LENGTH_SHORT).show();


                }

            }else {
                Toast.makeText(InputFieldActivity.this,"Wrong Pin Entered. User not authorized to post data",Toast.LENGTH_SHORT).show();
            }
        }

        else if(requestCode==RESULT_CODE_DRAFT) {

            //DraftName = getIntent().getStringExtra("DraftName");
            if(!DraftName.equals("")) {
                //Toast.makeText(InputFieldActivity.this, DraftName, Toast.LENGTH_SHORT).show();

                saveToDraftDatabase();
            }



        }

    }

    public void setToGlobal() { //sets everything up globally

        FirstLine = firstLine.getText().toString();
        FrontLine = frontLine.getText().toString();
        Male = male.getText().toString();
        Female = female.getText().toString();
        Anthropocentric = anthropocentric.getText().toString();
    }

    public void saveToInternalDatabase(boolean synced) {
        // Restore preferences
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        lastId = settings.getInt("lastID", 1);
        Id = ""+lastId;


        setToGlobal(); //setting every value global

        if(!DNSOName.equals("") && !District.equals("") && !Month.equals("") && !Id.equals("") && !Year.equals("") && !FirstLine.equals("") && !FrontLine.equals("") && !Male.equals("") && !Female.equals("") && !Anthropocentric.equals("")) {
            internalDatabaseBackgroundWorker internalDatabaseBackgroundWorker = new internalDatabaseBackgroundWorker(InputFieldActivity.this);
            internalDatabaseBackgroundWorker.execute("add_info", Id, DNSOName, District, Year, Month, FirstLine, FrontLine, Male, Female, Anthropocentric, "" + synced);

            settings = getSharedPreferences(PREFS_NAME, 0);
            SharedPreferences.Editor editor = settings.edit();
            editor.putInt("lastID", ++lastId);
            // Commit the edits!
            editor.apply();
            finish();
        }else{
            Toast.makeText(InputFieldActivity.this,"No field should be empty",Toast.LENGTH_SHORT).show();
        }

    }

    public void saveToDraftDatabase() {
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        lastId = settings.getInt("draftID", 1);
        Id = ""+lastId;

        setToGlobal();

        String saveTime = DateFormat.getDateTimeInstance().format(new Date());

        internalDatabaseBackgroundWorker internalDatabaseBackgroundWorker = new internalDatabaseBackgroundWorker(InputFieldActivity.this);
        internalDatabaseBackgroundWorker.execute("draft_info",DraftName,saveTime, Id, DNSOName, District, Year, Month, FirstLine, FrontLine, Male, Female, Anthropocentric, "" + false);

        settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("draftID", ++lastId);
        // Commit the edits!
        editor.apply();
        finish();
    }

    public void saveToServerDatabase() {

        if(!DNSOName.equals("") && !District.equals("") && !Month.equals("") && !Id.equals("") && !Year.equals("") && !FirstLine.equals("") && !FrontLine.equals("") && !Male.equals("") && !Female.equals("") && !Anthropocentric.equals("")) {

            StringRequest stringRequest = new StringRequest(Request.Method.POST, internalDatabase.internalDatabaseEntry.SERVER_NAME,
                    new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    //Toast.makeText(InputFieldActivity.this,"entered",Toast.LENGTH_SHORT).show();
                    try {
                        //JSONObject jsonObject = new JSONObject(response);
                        //String Response = jsonObject.getString("response");
                        //Toast.makeText(InputFieldActivity.this,response,Toast.LENGTH_SHORT).show();

                        if(response.contains("successfully")) {
                            saveToInternalDatabase(true);
                            //Toast.makeText(InputFieldActivity.this,"JSON TRUE",Toast.LENGTH_SHORT).show();
                        }else {
                            saveToInternalDatabase(false);
                        }

                    } catch (Exception e) {
                        Toast.makeText(InputFieldActivity.this,e.toString(),Toast.LENGTH_SHORT).show();
                        saveToInternalDatabase(false);
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    saveToInternalDatabase(false);
                }
            })
            {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    HashMap<String,String> params = new HashMap<String, String>();
                    params.put("name_of_DNSO",DNSOName);
                    params.put("district",District);
                    params.put("year",Year);
                    params.put("month",Month);
                    params.put("first_line",FirstLine);
                    params.put("front_line",FrontLine);
                    params.put("male",Male);
                    params.put("female",Female);
                    params.put("anthropocentric_measurement",Anthropocentric);
                    return params;
                }
            };

            MySingelton.getInstance(InputFieldActivity.this).addToRequestQueue(stringRequest);
            finish();


        }else{

            Toast.makeText(InputFieldActivity.this,"No field should be empty",Toast.LENGTH_SHORT).show();

        }


        }


    public Boolean checkInternet() {
        ConnectivityManager cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();

        return (cm.getActiveNetworkInfo() != null && networkInfo.isConnected());
    }

}
