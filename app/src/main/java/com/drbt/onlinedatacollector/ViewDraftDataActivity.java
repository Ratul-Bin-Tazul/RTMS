package com.drbt.onlinedatacollector;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class ViewDraftDataActivity extends AppCompatActivity {

    public static RecyclerView draftDatabaseRecycleView;
    public static RecyclerView.Adapter draftAdapter;
    public static RecyclerView.LayoutManager draftLayoutManager;

    public static final String PREFS_NAME = "viewDataCount";

    public static ArrayList<DraftDataProvider> draftArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_draft_data);

        draftDatabaseRecycleView = (RecyclerView) findViewById(R.id.draftDatabaseRecycleView);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        draftDatabaseRecycleView.setHasFixedSize(true);


        // use a linear layout manager
        draftLayoutManager = new LinearLayoutManager(this);
        draftDatabaseRecycleView.setLayoutManager(draftLayoutManager);

        // specify an adapter (see also next example)
        draftAdapter = new DraftAdapter(draftArrayList,this);
        draftDatabaseRecycleView.setAdapter(draftAdapter);


        draftAdapter.notifyDataSetChanged();
        //adapter.notifyItemInserted(adapter.getItemCount()-1);
        readFromDraftDatabase(this);

        SharedPreferences activityCount = getSharedPreferences(PREFS_NAME, 0);
        int count = activityCount.getInt("draftActivityCount", 1);
        if (count % 2 != 0) {
            activityCount = getSharedPreferences(PREFS_NAME, 0);
            SharedPreferences.Editor editor = activityCount.edit();
            editor.putInt("draftActivityCount", ++count);
            // Commit the edits!
            editor.apply();

            startActivity(new Intent(this, ViewDraftDataActivity.class));
            finish();
        } else {
            activityCount = getSharedPreferences(PREFS_NAME, 0);
            SharedPreferences.Editor editor = activityCount.edit();
            editor.putInt("draftActivityCount", ++count);
            // Commit the edits!
            editor.apply();
        }


    }

    public static void readFromDraftDatabase(Context ctx) {
        internalDatabaseBackgroundWorker minternalDatabaseBackgroundWorker = new internalDatabaseBackgroundWorker(ctx.getApplicationContext());
        minternalDatabaseBackgroundWorker.execute("get_draft_info");

    }
}
