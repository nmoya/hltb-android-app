package com.nikolasmoya.hltb.parsers;

import com.nikolasmoya.hltb.constants.Environment;
import com.nikolasmoya.hltb.models.IGDBLandingItem;

import org.json.JSONException;
import org.json.JSONObject;

public class IGDBLandingItemParser extends BaseParser<IGDBLandingItem>
{
    public IGDBLandingItemParser()
    {
    }

    @Override
    public IGDBLandingItem parse(JSONObject json)
    {
        int id = 0;
        String name = "";
        String releaseDate = "";
        String coverId = "";
        String cover = "";
        try
        {
            id = json.getInt("id");
            name = json.getString("name");
            releaseDate = json.getString("release_date");
            coverId = json.getString("cover_id");
            cover = Environment.append(Environment.IGDB_COVER_BASE, coverId, ".jpg");
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        return new IGDBLandingItem(id, name, releaseDate, cover, coverId);
    }


}
