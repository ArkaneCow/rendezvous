package edu.gatech.rendezvous.network;

import android.os.AsyncTask;
import android.util.Log;
import com.android.volley.toolbox.RequestFuture;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by jwpilly on 9/24/16.
 */

enum ApiResult {
    SUCCESS,
    FAIL,
    NOT_DONE;

    @Override
    public String toString() {
        switch (this) {
            case SUCCESS:
                return "Success";
            case FAIL:
                return "Failed";
            case NOT_DONE:
                return "Not Done";
            default:
                throw new IllegalArgumentException();
        }
    }
}

public abstract class ApiReceiver<T, V> {
    private static final int API_MAX_WAIT = 5;
    private RequestFuture responseFuture;
    private T responseData;
    private ApiCallback responseCallback = null;
    private ApiResult responseStatus = ApiResult.NOT_DONE;

    public static final String API_RECEIVER_ERROR = "API Receiver Error";

    protected ApiReceiver(RequestFuture requestFuture,
                          ApiCallback responseCallback) {
        this.responseFuture = requestFuture;
        this.responseCallback = responseCallback;
        startRetrieve();
    }

    public void setResponseCallback(ApiCallback callback) {
        responseCallback = callback;
    }

    public ApiResult getResponseStatus() {
        return responseStatus;
    }

    private void startRetrieve() {
        final Thread retrieveThread = new Thread(new FutureThread());
        retrieveThread.start();
    }

    protected T getResponse() {
        return responseData;
    }

    public abstract V getEntity();

    private class AsyncFutureTask extends AsyncTask<RequestFuture, Integer,
                Object> {
        @Override
        protected void onPreExecute() {
            responseStatus = ApiResult.NOT_DONE;
        }

        @Override
        protected Object doInBackground(RequestFuture... params) {
            final RequestFuture future = params[0];
            try {
                return future.get(API_MAX_WAIT, TimeUnit.SECONDS);
            } catch (InterruptedException | ExecutionException |
                    TimeoutException e) {
                responseStatus = ApiResult.FAIL;
                Log.e(API_RECEIVER_ERROR, "Can't perform the next request", e);
            }
            return null;
        }
    }

    private class FutureThread implements Runnable {
        @Override
        public void run() {
            final AsyncFutureTask futureTask = new AsyncFutureTask();
            try {
                //noinspection unchecked
                responseData = (T) futureTask.execute(responseFuture).get();
                if (responseCallback != null) {
                    //noinspection unchecked
                    responseCallback.onReceive(ApiReceiver.this);
                }
                responseStatus = ApiResult.SUCCESS;
            } catch (InterruptedException | ExecutionException e) {
                Log.e(API_RECEIVER_ERROR, "Can't receive data", e);
            }
        }
    }
}
