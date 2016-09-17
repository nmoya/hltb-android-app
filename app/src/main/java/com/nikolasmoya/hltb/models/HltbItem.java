package com.nikolasmoya.hltb.models;

import java.io.Serializable;

public class HltbItem implements Serializable
{
    private int _id;
    private String _imgSrc;
    private String _title;
    private String _mainStory;
    private String _mainExtra;
    private String _completionist;
    private String _combined;
    private int _confidence;

    public HltbItem(int id, String imgSrc, String title, String mainStory)
    {
        _id = id;
        _imgSrc = imgSrc;
        _title = title;
        _mainStory = mainStory;
    }

    public int getId()
    {
        return _id;
    }

    public String getImgSrc()
    {
        return _imgSrc;
    }

    public String getTitle()
    {
        return _title;
    }

    public String getMainStory()
    {
        return _mainStory;
    }

    public String getMainExtra()
    {
        return _mainExtra;
    }

    public String getCompletionist()
    {
        return _completionist;
    }

    public String getCombined()
    {
        return _combined;
    }

    public int getConfidence()
    {
        return _confidence;
    }

    public void print()
    {
        System.out.println(_imgSrc);
        System.out.println(_title);
        System.out.println(_mainStory);
        System.out.println(_confidence);
    }
}
