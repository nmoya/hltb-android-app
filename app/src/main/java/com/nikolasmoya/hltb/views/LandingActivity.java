package com.nikolasmoya.hltb.views;

import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;

import com.android.volley.Response;
import com.nikolasmoya.hltb.MyActivity;
import com.nikolasmoya.hltb.R;
import com.nikolasmoya.hltb.application.MyVolley;
import com.nikolasmoya.hltb.constants.Environment;
import com.nikolasmoya.hltb.models.HltbItem;
import com.nikolasmoya.hltb.models.IGDBLandingAdapter;
import com.nikolasmoya.hltb.models.IGDBLandingItem;
import com.nikolasmoya.hltb.parsers.HltbParser;
import com.nikolasmoya.hltb.parsers.IGDBLandingListParser;

public class LandingActivity extends MyActivity
{
    private Context _ctx;
    MyVolley _volley;
    IGDBLandingItem[] _landingData;
    ListView _container;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_landing);
        _container = (ListView) findViewById(R.id.landingItemsContainer);
        _ctx = getApplicationContext();
        _volley = MyVolley.getInstance(_ctx);
        IGDBLandingListParser parser = new IGDBLandingListParser();

        _volley.get(Environment.IGDB_LANDING, parser, new Response.Listener<IGDBLandingItem[]>()
        {
            @Override
            public void onResponse(IGDBLandingItem[] response)
            {
                _landingData = response;
                if (_landingData != null)
                {
                    populate();
                }
            }
        });
    }

    private void populate()
    {
        _container.setAdapter(new IGDBLandingAdapter(_ctx, R.layout.inflate_landing_item, _landingData));
        for (IGDBLandingItem game : _landingData)
        {
            _volley.doHLTBRequest(Environment.HLTB_SEARCH_URL, game.getName(), new HltbParser(), new Response.Listener<HltbItem>()
            {
                @Override
                public void onResponse(HltbItem response)
                {
                    response.print();
                }
            });
            break;
        }
    }
}
