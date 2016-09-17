package com.nikolasmoya.hltb.application;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.nikolasmoya.hltb.parsers.BaseParser;

import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;

import java.io.IOException;

public class MyVolley
{
    private static MyVolley _instance;
    private RequestQueue _requestQueue;
    private ImageLoader _imageLoader;
    private static Context _ctx;

    private MyVolley(Context context)
    {
        _ctx = context;
        _requestQueue = getRequestQueue();

        _imageLoader = new ImageLoader(_requestQueue,
                new ImageLoader.ImageCache()
                {
                    private final LruCache<String, Bitmap>
                            cache = new LruCache<String, Bitmap>(20);

                    @Override
                    public Bitmap getBitmap(String url)
                    {
                        return cache.get(url);
                    }

                    @Override
                    public void putBitmap(String url, Bitmap bitmap)
                    {
                        cache.put(url, bitmap);
                    }
                });
    }

    public static synchronized MyVolley getInstance(Context context)
    {
        if (_instance == null)
        {
            _instance = new MyVolley(context);
        }
        return _instance;
    }

    public RequestQueue getRequestQueue()
    {
        if (_requestQueue == null)
        {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            _requestQueue = Volley.newRequestQueue(_ctx.getApplicationContext());
        }
        return _requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req)
    {
        getRequestQueue().add(req);
    }

    public ImageLoader getImageLoader()
    {
        return _imageLoader;
    }

    public void getImage(NetworkImageView view, String imgUrl)
    {
        view.setImageUrl(imgUrl, _imageLoader);
    }

    public void get(String url, final BaseParser parser, Response.Listener callback)
    {
        doJsonRequest(Request.Method.GET, url, parser, callback, null);
    }

    public void post(String url, final BaseParser parser, Response.Listener callback, Object requestBody)
    {
        doJsonRequest(Request.Method.POST, url, parser, callback, requestBody);
    }

//    public void postString(String url, final BaseParser parser, Response.Listener callback, Map<String, String> requestBody)
//    {
//        doStringRequest(Request.Method.POST, url, parser, callback, requestBody);
//    }

    private JSONObject convertObjetToJson(Object body)
    {
        JSONObject obj = null;
        try
        {
            if (body != null)
            {
                obj = new JSONObject(new Gson().toJson(body));
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        return obj;
    }
//
//    private void doStringRequest(int requestType, String url, final BaseParser parser, final Response.Listener callback, final Map<String, String> body)
//    {
//        StringRequest req = new StringRequest(requestType, url, new Response.Listener<String>()
//        {
//            @Override
//            public void onResponse(String response)
//            {
//                callback.onResponse(parser.parse(response));
//            }
//        }, null)
//        {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError
//            {
//                return body == null ? super.getParams() : body;
//            }
//        };
//        _requestQueue.add(req);
//    }

    public void doHLTBRequest(String url, String gameTitle, BaseParser parser, Response.Listener callback)
    {
        try
        {
            callback.onResponse(parser.parse(Jsoup.connect(url).data("queryString", gameTitle).post()));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    private void doJsonRequest(int requestMethod, String url, final BaseParser parser, final Response.Listener callback, Object requestBody)
    {

        JsonObjectRequest req = new JsonObjectRequest(requestMethod, url, convertObjetToJson(requestBody), new Response.Listener<JSONObject>()
        {
            @Override
            public void onResponse(JSONObject response)
            {
                callback.onResponse(parser.parse(response));
            }
        }, null);
        _requestQueue.add(req);
    }
}