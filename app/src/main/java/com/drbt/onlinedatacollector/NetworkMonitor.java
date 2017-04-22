package com.drbt.onlinedatacollector;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;


public class NetworkMonitor extends BroadcastReceiver {

    public static int syncedData = 0;
    @Override
    public void onReceive(final Context context, Intent intent) {

        //Toast.makeText(context.getApplicationContext(),"entered receive",Toast.LENGTH_LONG).show();
        try {
            boolean internet = checkInternet(context);

            //Toast.makeText(context.getApplicationContext(),"internet "+internet,Toast.LENGTH_LONG).show();

            if (internet) {


                //Toast.makeText(context.getApplicationContext(),"entered if ",Toast.LENGTH_LONG).show();
                final dbOperation dbOperation = new dbOperation(context);
                final SQLiteDatabase database = dbOperation.getWritableDatabase();

                Cursor cursor = dbOperation.getFullInfo(database);

                while (cursor.moveToNext()) {
                    //Toast.makeText(context.getApplicationContext(),"entered while ",Toast.LENGTH_LONG).show();
                    //boolean SYNCED = Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(internalDatabase.internalDatabaseEntry.COLUMN_NAME_SYNC_STATE)));

                    int i= Integer.parseInt(cursor.getString(cursor.getColumnIndex(internalDatabase.internalDatabaseEntry.COLUMN_NAME_SYNC_STATE)));
                    //Toast.makeText(context.getApplicationContext(),"synced "+SYNCED,Toast.LENGTH_LONG).show();
                    if (i!=1) {

                        //Toast.makeText(context.getApplicationContext(),"entered if synced ",Toast.LENGTH_LONG).show();
                        //Toast.makeText(context.getApplicationContext(),"column "+cursor.getColumnIndex(internalDatabase.internalDatabaseEntry.COLUMN_NAME_ID),Toast.LENGTH_LONG).show();
                        final String id = cursor.getString(cursor.getColumnIndex(internalDatabase.internalDatabaseEntry.COLUMN_NAME_ID));

                        final String DNSOName, District, Year, Month, FirstLine, FrontLine, Female, Male, Anthropocentric;
                        DNSOName = cursor.getString(cursor.getColumnIndex(internalDatabase.internalDatabaseEntry.COLUMN_NAME_DNSO));
                        District = cursor.getString(cursor.getColumnIndex(internalDatabase.internalDatabaseEntry.COLUMN_NAME_DISTRICT));
                        Year = cursor.getString(cursor.getColumnIndex(internalDatabase.internalDatabaseEntry.COLUMN_NAME_YEAR));
                        Month = cursor.getString(cursor.getColumnIndex(internalDatabase.internalDatabaseEntry.COLUMN_NAME_MONTH));
                        FirstLine = cursor.getString(cursor.getColumnIndex(internalDatabase.internalDatabaseEntry.COLUMN_NAME_FIRST_LINE));
                        FrontLine = cursor.getString(cursor.getColumnIndex(internalDatabase.internalDatabaseEntry.COLUMN_NAME_FRONT_LINE));
                        Male = cursor.getString(cursor.getColumnIndex(internalDatabase.internalDatabaseEntry.COLUMN_NAME_MALE));
                        Female = cursor.getString(cursor.getColumnIndex(internalDatabase.internalDatabaseEntry.COLUMN_NAME_FEMALE));
                        Anthropocentric = cursor.getString(cursor.getColumnIndex(internalDatabase.internalDatabaseEntry.COLUMN_NAME_ANTHROPOCENTRI));

                        //Toast.makeText(context.getApplicationContext(),"id "+id,Toast.LENGTH_LONG).show();
                        StringRequest stringRequest = new StringRequest(Request.Method.POST, internalDatabase.internalDatabaseEntry.SERVER_NAME,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {

                                        //Toast.makeText(context,"entered response",Toast.LENGTH_LONG).show();
                                        if (response.contains("successfully")) {

                                            syncedData++;
                                            Toast.makeText(context.getApplicationContext(),"Synced "+syncedData+" record automatically",Toast.LENGTH_LONG).show();
                                            dbOperation.updateLocalDatabase(database, id, true);

                                            //Toast.makeText(InputFieldActivity.this,"JSON TRUE",Toast.LENGTH_SHORT).show();
                                            context.sendBroadcast(new Intent(internalDatabase.internalDatabaseEntry.UI_UPDATE_BROADCAST));
                                        }
                                    }
                                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        }) {

                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                HashMap<String, String> params = new HashMap<>();
                                params.put("name_of_DNSO", DNSOName);
                                params.put("district", District);
                                params.put("year", Year);
                                params.put("month", Month);
                                params.put("first_line", FirstLine);
                                params.put("front_line", FrontLine);
                                params.put("male", Male);
                                params.put("female", Female);
                                params.put("anthropocentric_measurement", Anthropocentric);
                                return params;
                            }
                        };
                        MySingelton.getInstance(context).addToRequestQueue(stringRequest);
                    }
                }
                //database.close();
            }

        }catch (Exception e) {
            Toast.makeText(context.getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
        }

    }

    public Boolean checkInternet(Context ctx) {
        ConnectivityManager cm = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        //Toast.makeText(ctx.getApplicationContext(),"Network state changed",Toast.LENGTH_LONG).show();

        return (cm.getActiveNetworkInfo() != null && networkInfo.isConnected());

        //return (cm.getActiveNetworkInfo() != null && networkInfo.isConnectedOrConnecting());
    }

}
