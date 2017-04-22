package com.drbt.onlinedatacollector;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewDataActivity extends AppCompatActivity {

    public static RecyclerView databaseRecycleView;
    public static RecyclerView.Adapter adapter;
    public static RecyclerView.LayoutManager layoutManager;



    public static ArrayList<DataProvider> arrayList = new ArrayList<>();

    public static final String PREFS_NAME = "viewDataCount";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_view_data);


            //arrayList = internalDatabaseBackgroundWorker.mArrayList;

            //adapter.notifyDataSetChanged();

            databaseRecycleView = (RecyclerView) findViewById(R.id.databaseRecycleView);
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            databaseRecycleView.setHasFixedSize(true);


            // use a linear layout manager
            layoutManager = new LinearLayoutManager(this);
            databaseRecycleView.setLayoutManager(layoutManager);

            // specify an adapter (see also next example)
            adapter = new MyAdapter(arrayList);
            databaseRecycleView.setAdapter(adapter);


            adapter.notifyDataSetChanged();
            //adapter.notifyItemInserted(adapter.getItemCount()-1);
            readFromInternalDatabase(this);


            //BOILER PLATE CODE FOR KEEPING CREATING ACIVITY 2 TIMES AND AVOIDING DELAY SCREEN UPDATE
            // Restore preferences
            SharedPreferences activityCount = getSharedPreferences(PREFS_NAME, 0);
            int count = activityCount.getInt("activityCount", 1);
            if (count % 2 != 0) {
                activityCount = getSharedPreferences(PREFS_NAME, 0);
                SharedPreferences.Editor editor = activityCount.edit();
                editor.putInt("activityCount", ++count);
                // Commit the edits!
                editor.apply();

                startActivity(new Intent(this, ViewDataActivity.class));
                finish();
            } else {
                activityCount = getSharedPreferences(PREFS_NAME, 0);
                SharedPreferences.Editor editor = activityCount.edit();
                editor.putInt("activityCount", ++count);
                // Commit the edits!
                editor.apply();
            }

        }catch(Exception e) {
            Toast.makeText(ViewDataActivity.this,e.toString(),Toast.LENGTH_SHORT).show();
        }

    }


    public static void readFromInternalDatabase(Context ctx) {
        internalDatabaseBackgroundWorker minternalDatabaseBackgroundWorker = new internalDatabaseBackgroundWorker(ctx.getApplicationContext());
        minternalDatabaseBackgroundWorker.execute("get_info");

    }
}
