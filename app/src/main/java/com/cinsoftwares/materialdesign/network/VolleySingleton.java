package com.cinsoftwares.materialdesign.network;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.cinsoftwares.materialdesign.application.MyApplication;

/**
 * Created by rahul on 5/31/15.
 */
public class VolleySingleton {

    private static VolleySingleton volleySingleton = null;


    private static RequestQueue requestQueue = null;
    private static ImageLoader imageLoader = null;


    private VolleySingleton() {

        requestQueue = Volley.newRequestQueue(MyApplication.getAppContext());
        imageLoader = new ImageLoader(requestQueue, new ImageLoader.ImageCache() {

            LruCache<String, Bitmap> lruCache  = new LruCache<>( (int) (Runtime.getRuntime().maxMemory() / 1024) / 8);

            @Override
            public Bitmap getBitmap(String url) {
                return lruCache.get(url);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {

                lruCache.put(url, bitmap);

            }
        });

    }
    public static VolleySingleton getInstance() {

            if(volleySingleton == null) volleySingleton = new VolleySingleton();
            return volleySingleton;
    }

    public static RequestQueue getRequestQueue() { return requestQueue;  }
}

