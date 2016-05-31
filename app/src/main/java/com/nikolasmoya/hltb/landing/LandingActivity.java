package com.nikolasmoya.hltb.landing;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.nikolasmoya.hltb.MyActivity;
import com.nikolasmoya.hltb.R;
import com.nikolasmoya.hltb.application.MyVolley;
import com.nikolasmoya.hltb.application.VolleyCallback;
import com.nikolasmoya.hltb.constants.Environment;
import com.nikolasmoya.hltb.models.IGDBLandingItem;
import com.nikolasmoya.hltb.parsers.IGDBLandingListParser;

public class LandingActivity extends MyActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_landing);
//        ListView _listContainer = (ListView) findViewById(R.id.landing_list_container);
        final LinearLayout _listContainer = (LinearLayout) findViewById(R.id.landing_list_container);
        final LayoutInflater inflater = getLayoutInflater();
        final MyVolley volley = MyVolley.getInstance(getApplicationContext());

        IGDBLandingListParser parser = new IGDBLandingListParser();
        volley.get(
                Environment.append(Environment.IGDB_BASE_URL, Environment.IGDB_LANDING_SUFFIX), parser, new VolleyCallback<IGDBLandingItem[]>()
                {
                    @Override
                    public void onDone(IGDBLandingItem[] result)
                    {
                        for (IGDBLandingItem item : result)
                        {
                            View v = inflater.inflate(R.layout.inflate_landing_entry, null, false);
                            volley.getImage(
                                    (NetworkImageView) v.findViewById(R.id.img_gameCover),
                                    item.getCover());
                            ((TextView) v.findViewById(R.id.txt_title)).setText(item.getName());
                            _listContainer.addView(v);
                        }
                    }

                });
    }
}
