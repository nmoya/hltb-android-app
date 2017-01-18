package com.nikolasmoya.hltb;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

public class VolleySingleton
{
    private static VolleySingleton _instance;
    private RequestQueue _requestQueue;
    private ImageLoader _imageLoader;
    private static Context _ctx;

    private VolleySingleton(Context context)
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

    public static synchronized VolleySingleton getInstance(Context context)
    {
        if (_instance == null)
        {
            _instance = new VolleySingleton(context);
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
}

