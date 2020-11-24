package com.example.envsaqapp.JavaClasses;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.envsaqapp.Activities.MainActivity;
import com.example.envsaqapp.R;

import java.util.ArrayList;
import java.util.Locale;

import Models.Data;

public class ListViewAdapter extends BaseAdapter {

    Context mContext;
    LayoutInflater inflater;
    private ArrayList<Data> arraylist;

    public ListViewAdapter(Context context ) {
        mContext = context;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<Data>();
        this.arraylist.addAll(MainActivity.addressArrayList);
    }

    public class ViewHolder {
        TextView name;
    }

    @Override
    public int getCount() {
        return MainActivity.addressArrayList.size();
    }

    @Override
    public Data getItem(int position) {
        return MainActivity.addressArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.list_view_items, null);
            // Locate the TextViews in listview_item.xml
            holder.name = (TextView) view.findViewById(R.id.name);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        holder.name.setText(MainActivity.addressArrayList.get(position).getAddress() + ", " + MainActivity.addressArrayList.get(position).getKommune());
        return view;
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        MainActivity.addressArrayList.clear();
        if (charText.length() == 0) {
            MainActivity.addressArrayList.addAll(arraylist);
        } else {
            for (Data wp : arraylist) {
                if (wp.getAddress().toLowerCase(Locale.getDefault()).contains(charText)) {
                    MainActivity.addressArrayList.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }


}