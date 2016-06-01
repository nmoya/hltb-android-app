package com.nikolasmoya.hltb.models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.nikolasmoya.hltb.R;
import com.nikolasmoya.hltb.application.MyVolley;

public class IGDBLandingAdapter extends ArrayAdapter<IGDBLandingItem>
{
    public IGDBLandingAdapter(Context context, int resource, IGDBLandingItem[] items)
    {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View v = convertView;

        if (v == null)
        {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.inflate_landing_item, null, false);
        }

        MyVolley volley = MyVolley.getInstance(getContext());
        IGDBLandingItem item = getItem(position);
        if (item != null)
        {
            ((TextView) v.findViewById(R.id.name)).setText(item.getName());
            ((TextView) v.findViewById(R.id.releaseDate)).setText(item.getReleaseDate());
            volley.getImage((NetworkImageView) v.findViewById(R.id.img_gameCover), item.getCover());
        }
        return v;
    }
}
