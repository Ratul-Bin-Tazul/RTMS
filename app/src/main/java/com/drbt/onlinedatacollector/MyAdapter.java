package com.drbt.onlinedatacollector;


import android.graphics.drawable.InsetDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.RecyclerHolder>{

    private ArrayList<DataProvider> arrayList;

    public MyAdapter(ArrayList<DataProvider> arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }



    @Override
    public MyAdapter.RecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent,false);

        RecyclerHolder recyclerHolder = new RecyclerHolder(view);
        return recyclerHolder;
    }

    @Override
    public void onBindViewHolder(MyAdapter.RecyclerHolder holder, int position) {

        DataProvider dataProvider = arrayList.get(position);

        holder.dnsoName.setText(dataProvider.getDnsoName());
        holder.district.setText(dataProvider.getDistrict());
        holder.year.setText(dataProvider.getYear());
        holder.month.setText(dataProvider.getMonth());
        holder.firstLine.setText(dataProvider.getFirstLine());
        //holder.firstLine.setText(dataProvider.getDnsoName());

        holder.frontLine.setText(dataProvider.getFrontLine());
        holder.male.setText(dataProvider.getMale());
        holder.female.setText(dataProvider.getFemale());
        holder.anthropocentric.setText(dataProvider.getAnthropocentric());
        //holder.synced.setText(dataProvider.getSynced());
        if(Integer.parseInt(dataProvider.getSynced())==1)
            holder.synced.setCompoundDrawablesWithIntrinsicBounds(0,0,R.mipmap.ic_done_black_24dp,0);
        else
            holder.synced.setCompoundDrawablesWithIntrinsicBounds(0,0,R.mipmap.ic_clear_black_24dp,0);


    }

    public class RecyclerHolder extends RecyclerView.ViewHolder{
        TextView dnsoName,district,year,month,firstLine,frontLine,male,female,anthropocentric,synced;
        public RecyclerHolder(View itemView) {
            super(itemView);
            dnsoName = (TextView)itemView.findViewById(R.id.dnsoNameData);
            district = (TextView)itemView.findViewById(R.id.districtData);
            year = (TextView)itemView.findViewById(R.id.yearData);
            month = (TextView)itemView.findViewById(R.id.monthData);
            firstLine = (TextView)itemView.findViewById(R.id.firstLineData);
            frontLine = (TextView)itemView.findViewById(R.id.frontLineData);
            male = (TextView)itemView.findViewById(R.id.maleData);
            female = (TextView)itemView.findViewById(R.id.femaleData);
            anthropocentric = (TextView)itemView.findViewById(R.id.anthropocentricData);
            synced = (TextView)itemView.findViewById(R.id.syncedData);
        }
    }
}
