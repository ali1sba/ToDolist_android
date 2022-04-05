package com.example.android.tpnot1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

public class TweetAdapter extends ArrayAdapter<Tweet> {

    public List<Tweet> data;

    public TweetAdapter( Context context, List<Tweet> tweets) {
        super(context, 0, tweets);
        data = tweets;
    }

    @Override
    public View getView (int position, View convertView, ViewGroup parent){

        convertView = LayoutInflater.from(getContext()).inflate(R.layout.item,parent, false);
        TweetViewHolder viewHolder = (TweetViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new TweetViewHolder();
            viewHolder.name = (TextView) convertView.findViewById(R.id.title);
            viewHolder.text = (TextView) convertView.findViewById(R.id.description);
            viewHolder.img = (ImageView) convertView.findViewById(R.id.image);
            convertView.setTag(viewHolder);
        }

        viewHolder.name.setText(data.get(position).getName());
        viewHolder.text.setText(data.get(position).getText());
        viewHolder.img.setImageResource(data.get(position).getImgURL());

        return convertView;
    }
}
