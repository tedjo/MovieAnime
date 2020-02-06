package com.studio.joe.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.studio.joe.Model.Pos;
import com.studio.joe.R;

import java.util.ArrayList;

public class PosAdapter extends BaseAdapter {

    private ArrayList<Pos> posts;
    private Context context;

    public PosAdapter(ArrayList<Pos> posts, Context context) {
        this.posts = posts;
        this.context = context;
    }

    @Override
    public int getCount() {
        return posts.size();
    }

    @Override
    public Object getItem(int position) {
        return posts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_movie,parent,false);
            holder = new ViewHolder();
            holder.image = (ImageView) convertView.findViewById(R.id.imageItem);
            holder.title = (TextView) convertView.findViewById(R.id.titleItem);
            holder.episode = (TextView) convertView.findViewById(R.id.episodeItem);
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.title
                .setText(posts.get(position).getTitle());
        holder.episode
                .setText(posts.get(position).getEpisode());
        Picasso.with(context)
                .load(posts.get(position).getImage())
                .into(holder.image);
        return convertView;
    }

    private class ViewHolder {
        ImageView image;
        TextView title;
        TextView episode;
    }
}
