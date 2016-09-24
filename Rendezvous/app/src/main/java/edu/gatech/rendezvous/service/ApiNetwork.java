package edu.gatech.rendezvous.service;

import android.content.Context;
import android.graphics.Bitmap;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.*;
import org.json.JSONObject;

/**
 * Created by jwpilly on 9/24/16.
 */
public class ApiNetwork {
    private static ApiNetwork ourInstance = null;
    private static Context apiContext;
    private RequestQueue apiRequestQueue;

    private ApiNetwork(Context context) {
        apiContext = context;
        apiRequestQueue = getApiRequestQueue();
    }

    public static ApiNetwork getInstance(Context context) {
        if (ourInstance == null) {
            ourInstance = new ApiNetwork(context);
        }
        return ourInstance;
    }

    public static ApiNetwork getInstance() {
        return ourInstance;
    }

    private RequestQueue getApiRequestQueue() {
        if (apiRequestQueue == null) {
            apiRequestQueue = Volley.newRequestQueue(apiContext.getApplicationContext());
        }
        return apiRequestQueue;
    }

    public RequestFuture<JSONObject> apiJSON(String url) {
        final RequestFuture<JSONObject> jsonFuture = RequestFuture.newFuture();
        final JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, url, jsonFuture, jsonFuture);
        apiRequestQueue.add(jsonRequest);
        return jsonFuture;
    }

    public RequestFuture<String> apiString(String url) {
        final RequestFuture<String> stringFuture = RequestFuture.newFuture();
        final StringRequest stringRequest = new StringRequest(Request.Method.GET, url, stringFuture, stringFuture);
        apiRequestQueue.add(stringRequest);
        return stringFuture;
    }

    public RequestFuture<Bitmap> apiImage(String url) {
        final RequestFuture<Bitmap> bmpFuture = RequestFuture.newFuture();
        final ImageRequest imageRequest = new ImageRequest(url, bmpFuture, 0, 0, null, bmpFuture);
        apiRequestQueue.add(imageRequest);
        return bmpFuture;
    }
}
