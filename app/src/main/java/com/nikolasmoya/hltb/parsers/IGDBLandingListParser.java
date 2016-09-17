package com.nikolasmoya.hltb.parsers;

import com.nikolasmoya.hltb.models.IGDBLandingItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class IGDBLandingListParser extends BaseParser<IGDBLandingItem[]>
{
    @Override
    public IGDBLandingItem[] parse(JSONObject response)
    {
        if (response.length() > 0)
        {
            ArrayList<IGDBLandingItem> games = new ArrayList<>();
            try
            {
                JSONArray jsonList = response.getJSONArray("games");
                for (int i = 0; i < jsonList.length(); i++)
                {
                    JSONObject obj = jsonList.getJSONObject(i);
                    IGDBLandingItemParser itemParser = new IGDBLandingItemParser();
                    games.add(itemParser.parse(obj));
                }
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
            IGDBLandingItem[] list = new IGDBLandingItem[games.size()];
            list = games.toArray(list);
            return list;
        }
        return null;
    }
}
