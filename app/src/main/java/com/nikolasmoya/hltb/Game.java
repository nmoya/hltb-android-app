package com.nikolasmoya.hltb;

import android.graphics.Bitmap;

import java.io.Serializable;

public class Game implements Serializable
{
    private int _id;
    private String _imgSrc;
    private Bitmap _imgBitmap;
    private String _title;
    private String _mainStory;
    private String _mainExtra;
    private String _completionist;
    private String _combined;
    private int _confidence;

    public Game(int id, String imgSrc, String title)
    {
        _id = id;
        _imgSrc = imgSrc;
        _title = title;
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

    public void setId(int id)
    {
        _id = id;
    }

    public void setImgSrc(String imgSrc)
    {
        _imgSrc = imgSrc;
    }

    public void setTitle(String title)
    {
        _title = title;
    }

    public void setMainStory(String mainStory)
    {
        _mainStory = mainStory;
    }

    public void setMainExtra(String mainExtra)
    {
        _mainExtra = mainExtra;
    }

    public void setCompletionist(String completionist)
    {
        _completionist = completionist;
    }

    public void setCombined(String combined)
    {
        _combined = combined;
    }

    public void setConfidence(int confidence)
    {
        _confidence = confidence;
    }

    public void print()
    {
        System.out.println(_imgSrc);
        System.out.println(_title);
        System.out.println(_mainStory);
        System.out.println(_confidence);
    }
}
