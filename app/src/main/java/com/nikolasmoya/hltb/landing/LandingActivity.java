package com.nikolasmoya.hltb.landing;

import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;

import com.nikolasmoya.hltb.MyActivity;
import com.nikolasmoya.hltb.R;
import com.nikolasmoya.hltb.application.MyVolley;
import com.nikolasmoya.hltb.application.VolleyCallback;
import com.nikolasmoya.hltb.constants.Environment;
import com.nikolasmoya.hltb.models.IGDBLandingAdapter;
import com.nikolasmoya.hltb.models.IGDBLandingItem;
import com.nikolasmoya.hltb.parsers.IGDBLandingListParser;

public class LandingActivity extends MyActivity
{
    private Context _ctx;
    MyVolley _volley;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_landing);
        final ListView _listContainer = (ListView) findViewById(R.id.landingItemsContainer);
        _ctx = getApplicationContext();
        _volley = MyVolley.getInstance(_ctx);
        IGDBLandingListParser parser = new IGDBLandingListParser();

        _volley.get(
                Environment.append(Environment.IGDB_BASE_URL, Environment.IGDB_LANDING_SUFFIX), parser, new VolleyCallback<IGDBLandingItem[]>()
                {
                    @Override
                    public void onDone(IGDBLandingItem[] result)
                    {
                        _listContainer.setAdapter(new IGDBLandingAdapter(_ctx, R.layout.inflate_landing_item, result));
                    }
                });

    }
}
