package com.drbt.onlinedatacollector;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class internalDatabaseBackgroundWorker extends AsyncTask<String,DataProvider,String>{

    public Context context;
    MyAdapter myAdapter;
    //Activity activity;
    //RecyclerView mDatabaseRecycleView;

    //RecyclerView.LayoutManager mLayoutManager;
    //RecyclerView recyclerView;
    public static ArrayList<DataProvider> mArrayList;

    public internalDatabaseBackgroundWorker(Context ctx) {

        context = ctx;
        //activity = (Activity)ctx;

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {


        dbOperation dbOperation = new dbOperation(context);
        draftDbOperation draftDbOperation = new draftDbOperation(context);

        String method = params[0];
        if(method.equals("add_info")) {
            int id = Integer.parseInt(params[1]);
            String dnsoName = params[2];
            String distName = params[3];
            int year = Integer.parseInt(params[4]);
            String month = params[5];
            int first = Integer.parseInt(params[6]);
            int front = Integer.parseInt(params[7]);
            int male = Integer.parseInt(params[8]);
            int female = Integer.parseInt(params[9]);
            int anthropocentric = Integer.parseInt(params[10]);
            boolean synced = Boolean.parseBoolean(params[11]);

            SQLiteDatabase db = dbOperation.getWritableDatabase();
            dbOperation.addInformation(db,id,dnsoName,distName,year,month,first,front,male,female,anthropocentric,synced);
            //ViewDataActivity.adapter.notifyItemInserted(ViewDataActivity.adapter.getItemCount()-1);
            //ViewDataActivity.adapter.notifyDataSetChanged();
            return "One row added...";
            //+id+dnsoName+distName+year+month+first+front+male+" female "+female+" antho "+anthropocentric
        }
        else if(method.equals("get_info")) {

            //mDatabaseRecycleView = (RecyclerView) activity.findViewById(R.id.databaseRecycleView);
            //mDatabaseRecycleView.setHasFixedSize(true);


            // use a linear layout manager
            //mLayoutManager = new LinearLayoutManager(this);
            //mDatabaseRecycleView.setLayoutManager(mLayoutManager);

            //recyclerView = (RecyclerView)activity.findViewById(R.id.databaseRecycleView);

            ArrayList<DataProvider> arrayList = new ArrayList<>();

            SQLiteDatabase db = dbOperation.getReadableDatabase();
            Cursor cursor = dbOperation.getInfo(db);

            String dnsoName, district, year, month, firstLine, frontLine, male, female, anthropocentric, synced;


            ViewDataActivity.arrayList.clear();

            while (cursor.moveToNext()) {

                dnsoName = cursor.getString(cursor.getColumnIndex(internalDatabase.internalDatabaseEntry.COLUMN_NAME_DNSO));
                district = cursor.getString(cursor.getColumnIndex(internalDatabase.internalDatabaseEntry.COLUMN_NAME_DISTRICT));
                year = cursor.getString(cursor.getColumnIndex(internalDatabase.internalDatabaseEntry.COLUMN_NAME_YEAR));
                month = cursor.getString(cursor.getColumnIndex(internalDatabase.internalDatabaseEntry.COLUMN_NAME_MONTH));
                firstLine = cursor.getString(cursor.getColumnIndex(internalDatabase.internalDatabaseEntry.COLUMN_NAME_FIRST_LINE));
                frontLine = cursor.getString(cursor.getColumnIndex(internalDatabase.internalDatabaseEntry.COLUMN_NAME_FRONT_LINE));
                male = cursor.getString(cursor.getColumnIndex(internalDatabase.internalDatabaseEntry.COLUMN_NAME_MALE));
                female = cursor.getString(cursor.getColumnIndex(internalDatabase.internalDatabaseEntry.COLUMN_NAME_FEMALE));
                anthropocentric = cursor.getString(cursor.getColumnIndex(internalDatabase.internalDatabaseEntry.COLUMN_NAME_ANTHROPOCENTRI));
                synced = cursor.getString(cursor.getColumnIndex(internalDatabase.internalDatabaseEntry.COLUMN_NAME_SYNC_STATE));

                DataProvider dataProvider = new DataProvider(dnsoName, district, year
                        , month, firstLine, frontLine, male, female, anthropocentric, synced);
                //publishProgress(dataProvider);
                arrayList.add(dataProvider);

                ViewDataActivity.arrayList.add(dataProvider);

            }

            //ViewDataActivity.adapter.notifyDataSetChanged();
            cursor.close();
            dbOperation.close();
            //myAdapter = new MyAdapter(arrayList);

            //MainActivity.mainArrayList = mArrayList;
            //ViewDataActivity.adapter.notifyItemInserted(arrayList.size()-6);
            //ViewDataActivity.adapter.notifyDataSetChanged();

            //mDatabaseRecycleView = (RecyclerView)activity.findViewById(R.id.databaseRecycleView);


            mArrayList = arrayList;

            //mAdapter = new MyAdapter(mArrayList);
            //mDatabaseRecycleView.setAdapter(mAdapter);
            return "getting info... ";
        }
        else if(method.equals("draft_info")) {
            String draftName = params[1];
            String draftTime = params[2];
            String id = params[3];
            String dnsoName = params[4];
            String distName = params[5];
            String year = params[6];
            String month = params[7];
            String first = params[8];
            String front = params[9];
            String male = params[10];
            String female = params[11];
            String anthropocentric = params[12];
            String synced = params[13];

            SQLiteDatabase db = draftDbOperation.getWritableDatabase();
            draftDbOperation.addInformation(db,draftName,draftTime,id,dnsoName,distName,year,month,first,front,male,female,anthropocentric,synced);
            //ViewDataActivity.adapter.notifyItemInserted(ViewDataActivity.adapter.getItemCount()-1);
            //ViewDataActivity.adapter.notifyDataSetChanged();
            return "Row saved as Draft";
            //+id+dnsoName+distName+year+month+first+front+male+" female "+female+" antho "+anthropocentric
        }
        else if(method.equals("get_draft_info")) {
            ArrayList<DraftDataProvider> arrayList = new ArrayList<>();

            SQLiteDatabase db = draftDbOperation.getReadableDatabase();
            Cursor cursor = draftDbOperation.getDraftNameTime(db);

            String draftName,draftTime;


            ViewDraftDataActivity.draftArrayList.clear(); //TODO

            while (cursor.moveToNext()) {

                draftName = cursor.getString(cursor.getColumnIndex(internalDatabase.draftDatabaseEntry.DRAFT_NAME));
                draftTime = cursor.getString(cursor.getColumnIndex(internalDatabase.draftDatabaseEntry.DRAFT_TIME));

                DraftDataProvider draftDataProvider = new DraftDataProvider(draftName,draftTime);
                //publishProgress(draftDataProvider);
                arrayList.add(draftDataProvider);

                ViewDraftDataActivity.draftArrayList.add(draftDataProvider); //TODO

            }

            //ViewDraftDataActivity.draftAdapter.notifyDataSetChanged(); //TODO
            cursor.close();
            draftDbOperation.close();
            //myAdapter = new MyAdapter(arrayList);

            //MainActivity.mainArrayList = mArrayList;
            //ViewDataActivity.adapter.notifyItemInserted(arrayList.size()-6);
            //ViewDataActivity.adapter.notifyDataSetChanged();

            //mDatabaseRecycleView = (RecyclerView)activity.findViewById(R.id.databaseRecycleView);


            //mArrayList = arrayList;

            //mAdapter = new MyAdapter(mArrayList);
            //mDatabaseRecycleView.setAdapter(mAdapter);
            return "getting draft info... ";
        }

        return null;
    }


    @Override
    protected void onProgressUpdate(DataProvider... values) {

        super.onProgressUpdate(values);
        //mArrayList.add(values[0]);
        //ViewDataActivity.arrayList.add(values[0]);
    }

    @Override
    protected void onPostExecute(String s) {


        if(s.equals("getting info...")) {

            //MainActivity.mainArrayList = mArrayList;

            //ViewDataActivity.adapter.notifyDataSetChanged();
            //recyclerView.setAdapter(myAdapter);
            //recyclerView.hasFixedSize();
            //LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            //recyclerView.setLayoutManager(linearLayoutManager);
        }
        if(s.equals("One row added...")) {
            //MainActivity.mainArrayList = mArrayList;
            //myAdapter.notifyItemInserted(myAdapter.getItemCount());
            //ViewDataActivity.adapter.notifyDataSetChanged();
        }

        Toast.makeText(context,s,Toast.LENGTH_SHORT).show();
    }
}
