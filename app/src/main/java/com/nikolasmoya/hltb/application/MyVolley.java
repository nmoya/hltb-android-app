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
import com.nikolasmoya.hltb.parsers.BaseParser;

import org.json.JSONObject;

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

    public void get(String url, final BaseParser parser, final VolleyCallback callback)
    {
        doRequest(Request.Method.GET, url, parser, callback);
    }

    public void post(String url, final BaseParser parser, final VolleyCallback callback)
    {
        doRequest(Request.Method.POST, url, parser, callback);
    }

    public void doRequest(int requestType, String url, final BaseParser parser, final VolleyCallback callback)
    {
        JsonObjectRequest req = new JsonObjectRequest(requestType, url, null, new Response.Listener<JSONObject>()
        {
            @Override
            public void onResponse(JSONObject response)
            {
                callback.onDone(parser.parse(response));
            }
        }, null);
        _requestQueue.add(req);
    }
}