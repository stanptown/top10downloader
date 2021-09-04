package com.example.top10downloader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class FeedAdapter extends ArrayAdapter {
    private static final String TAG = "FeedAdapter";
    private final int layoutResource;
    private final LayoutInflater layoutInflater;
    private List<FeedEntry> applications;

    public FeedAdapter(@NonNull Context context, int resource, List<FeedEntry> applications) {
        super(context, resource);
        this.applications = applications;
        this.layoutResource=resource;
        this.layoutInflater= LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return applications.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if( convertView==null){
            convertView=layoutInflater.inflate(layoutResource,parent,false);
        }
//        View view=layoutInflater.inflate(layoutResource,parent,false);
//        TextView tvName=(TextView)view.findViewById(R.id.tvName);    used earlier but used more memory
//        TextView tvArtist=(TextView)view.findViewById(R.id.tvArtist);
//        TextView tvSummary=(TextView)view.findViewById(R.id.textView3);

        TextView tvName=(TextView)convertView.findViewById(R.id.tvName);
        TextView tvArtist=(TextView)convertView.findViewById(R.id.tvArtist);
        TextView tvSummary=(TextView)convertView.findViewById(R.id.textView3);

        FeedEntry currentApp= applications.get(position);

        tvName.setText(currentApp.getName());
        tvArtist.setText(currentApp.getArtist());
        tvSummary.setText(currentApp.getSummary());

        return convertView;
    }
}
