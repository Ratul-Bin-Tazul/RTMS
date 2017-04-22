package com.drbt.onlinedatacollector;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by SAMSUNG on 3/24/2017.
 */

public class DraftAdapter extends RecyclerView.Adapter<DraftAdapter.DraftHolder> {

    private ArrayList<DraftDataProvider> arrayList;
    private Context context;

    public DraftAdapter(ArrayList<DraftDataProvider> arrayList, Context ctx) {
        this.arrayList = arrayList;
        this.context = ctx;
    }

    @Override
    public DraftAdapter.DraftHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.draft_item_layout,parent,false);

        DraftAdapter.DraftHolder draftHolder = new DraftAdapter.DraftHolder(view);
        return draftHolder;
    }

    @Override
    public void onBindViewHolder(DraftAdapter.DraftHolder holder, int position) {

        DraftDataProvider draftDataProvider = arrayList.get(position);

        holder.draftName.setText(draftDataProvider.getDraftName());
        holder.draftTime.setText("Saved on: "+draftDataProvider.getSaveTime());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class DraftHolder extends RecyclerView.ViewHolder {
        TextView draftName,draftTime;
        ImageButton editDraft,dltDraft;
        public DraftHolder(final View itemView) {
            super(itemView);
            draftName = (TextView)itemView.findViewById(R.id.draftNameTitle);
            draftTime = (TextView)itemView.findViewById(R.id.saveTime);
            editDraft = (ImageButton)itemView.findViewById(R.id.editDraftBtn);
            dltDraft = (ImageButton) itemView.findViewById(R.id.dltDraftBtn);

            editDraft.setOnClickListener(new View.OnClickListener() { //editing a record
                @Override
                public void onClick(View v) {

                    draftDbOperation draftDbOperation = new draftDbOperation(context);
                    SQLiteDatabase database = draftDbOperation.getWritableDatabase();

                    String DraftName = draftName.getText().toString();
                    Cursor cursor = draftDbOperation.getRow(database,DraftName);
                    String dnsoName="", district="", year="", month="", firstLine="", frontLine="", male="", female="", anthropocentric="", synced="";




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
                        //publishProgress(dataProvider);

                    }

                    //ViewDataActivity.adapter.notifyDataSetChanged();


                    Intent i = new Intent(context,InputFieldActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    i.putExtra("cameFrom","DraftAdapter");
                    i.putExtra("dnsoName",dnsoName);
                    i.putExtra("district",district);
                    i.putExtra("year",year);
                    i.putExtra("month",month);
                    i.putExtra("firstLine",firstLine);
                    i.putExtra("frontLine",frontLine);
                    i.putExtra("male",male);
                    i.putExtra("female",female);
                    i.putExtra("anthropocentric",anthropocentric);
                    i.putExtra("synced",synced);
                    context.startActivity(i);
                    ((Activity)context).finish();


                    cursor.close();
                }
            });

            dltDraft.setOnClickListener(new View.OnClickListener() { //deleting a record
                @Override
                public void onClick(View v) {
                    draftDbOperation draftDbOperation = new draftDbOperation(context);
                    SQLiteDatabase database = draftDbOperation.getWritableDatabase();
                    String DraftName = draftName.getText().toString();
                    String DraftTime = draftTime.getText().toString();


                    draftDbOperation.deleteDraftRecord(database,DraftName,DraftTime);
                    Toast.makeText(context,"Deleted draft "+DraftName,Toast.LENGTH_SHORT).show();

                    //notifyItemRemoved(getPosition());

                    String draftNameText,draftTimeText;


                    Cursor cursor = draftDbOperation.getDraftNameTime(database);
                    ViewDraftDataActivity.draftArrayList.clear(); //TODO

                    while (cursor.moveToNext()) {

                        draftNameText = cursor.getString(cursor.getColumnIndex(internalDatabase.draftDatabaseEntry.DRAFT_NAME));
                        draftTimeText = cursor.getString(cursor.getColumnIndex(internalDatabase.draftDatabaseEntry.DRAFT_TIME));

                        DraftDataProvider draftDataProvider = new DraftDataProvider(draftNameText,draftTimeText);
                        //publishProgress(draftDataProvider);
                        //arrayList.add(draftDataProvider);

                        ViewDraftDataActivity.draftArrayList.add(draftDataProvider); //TODO

                    }

                    ViewDraftDataActivity.draftAdapter.notifyDataSetChanged(); //TODO
                    cursor.close();
                    draftDbOperation.close();
                    notifyDataSetChanged();

                }
            });
        }

    }
}
