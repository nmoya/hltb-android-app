package com.nikolasmoya.hltb.models;

public class IGDBLandingItem
{
    private int id;
    private String name;
    private String releaseDate;
    private String cover;
    private String coverId;

    public IGDBLandingItem(int id, String name, String releaseDate, String cover, String coverId)
    {
        this.id = id;
        this.name = name;
        this.releaseDate = releaseDate;
        this.cover = cover;
        this.coverId = coverId;
    }

    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public String getReleaseDate()
    {
        return releaseDate;
    }

    public String getCover()
    {
        return cover;
    }

    public String getCoverId()
    {
        return coverId;
    }
}
