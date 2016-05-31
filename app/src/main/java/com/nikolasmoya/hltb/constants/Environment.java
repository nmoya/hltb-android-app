package com.nikolasmoya.hltb.constants;

public class Environment
{
    public static final String IGDB_BASE_URL = "https://www.igdb.com/api/v1/";
    public static final String IGDB_APP_KEY = "COi3U73j9ZTnz-ZtND0LCvf67Ki2_gqw0QcIPL500ZU";
    public static final String IGDB_COVER_BASE = "https://res.cloudinary.com/igdb/image/upload/t_cover_big_2x/";
    public static final String IGDB_LANDING_SUFFIX = "games/search?limit=50&filters[platforms.id_eq]=48&filters[release_date_gt]=2015-12-31&filters[release_date_lt]=2016-05-15&token=" + IGDB_APP_KEY;

    public static String append(String base, String... suffixes)
    {
        String out = base;
        for (String suffix : suffixes)
        {
            out = out + suffix;
        }
        return out;
    }

}
