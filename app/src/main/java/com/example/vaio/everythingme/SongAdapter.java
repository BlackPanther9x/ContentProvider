package com.example.vaio.everythingme;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class SongAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private ArrayList<Song> listData;
    private TextView tvName, tvArtist;

    public SongAdapter(Context context, ArrayList<Song> listData){
        this.listData=listData;
        this.inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null) convertView=inflater.inflate(R.layout.item, parent, false);
        Song item= listData.get(position);
        tvName=(TextView) convertView.findViewById(R.id.tv_name);
        tvArtist=(TextView)convertView.findViewById(R.id.tv_artist);
        tvName.setText(item.getTitle());
        tvArtist.setText(item.getArtist());
        return convertView;
    }
}
