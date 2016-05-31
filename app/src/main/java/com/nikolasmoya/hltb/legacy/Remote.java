package com.nikolasmoya.hltb.legacy;

import android.os.AsyncTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class Remote extends AsyncTask<String, Void, Document>
{
    private Exception exception;
    private HttpRequestListener _listener;

    public Remote(HttpRequestListener listener)
    {
        _listener = listener;
    }

    protected Document doInBackground(String... urls)
    {
        Document doc = null;
        if (urls.length != 2)
        {
            return doc;
        }
        String url = urls[0];
        String queryString = urls[1];
        if (queryString.isEmpty() || url.isEmpty())
        {
            return doc;
        }
        try
        {
            doc = Jsoup.connect(url).data("queryString", queryString).post();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            return doc;
        }
    }

    @Override
    protected void onPostExecute(Document doc)
    {
        _listener.onHttpResponse(doc);
    }
}