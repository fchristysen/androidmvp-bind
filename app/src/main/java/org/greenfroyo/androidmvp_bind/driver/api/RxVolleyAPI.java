package org.greenfroyo.androidmvp_bind.driver.api;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import rx.Observable;

/**
 * Created by fchristysen on 7/27/16.
 */

public class RxVolleyAPI implements ApiDriver {
    private final Context mContext;

    private final RequestQueue mRequestQueue;

    public RxVolleyAPI(Context context) {
        this.mContext = context;
        RequestManager.init(context);
        mRequestQueue = RequestManager.getRequestQueue();
    }

    @Override
    public <R> Observable<R> get(final String url, final Class<R> responseClass) {
        return Observable.create(subscriber -> {
            GetRequest request = new GetRequest(url
                , response -> {
                    Gson gson = new Gson();
                    try{
                        R responseObject = gson.fromJson(response, responseClass);
                        subscriber.onNext(responseObject);
                    }catch (Exception e) {
                        subscriber.onError(e);
                    }
                    subscriber.onCompleted();
                }, error -> {
                    subscriber.onError(error);
            });

            addTravelokaHeaders(request);
            request.setTag(responseClass);
            mRequestQueue.add(request);
        });
    }

    @Override
    public <P, R> Observable<R> post(final String url, final P body, final Class<P> requestClass, final Class<R> responseClass) {
        return Observable.create(subscriber -> {
            Gson gson = new Gson();

            PostRequest request = new PostRequest(url, gson.toJson(body)
                    , response -> {
                try{
                    R responseObject = gson.fromJson(response.toString(), responseClass);
                    subscriber.onNext(responseObject);
                }catch (Exception e) {
                    subscriber.onError(e);
                }
                subscriber.onCompleted();
            }, error -> {
                subscriber.onError(error);
            });

            addTravelokaHeaders(request);
            request.setTag(responseClass);
            mRequestQueue.add(request);
        });
    }

    private void addTravelokaHeaders(Request request) {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("Origin", "m.traveloka.com"); //NON-NLS

        if(request.getMethod() == Request.Method.GET){
//            params.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8"); //NON-NLS
            params.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        }else{
            params.put("Accept", "text/json"); //NON-NLS
        }
        try {
            request.getHeaders().putAll(params);
        } catch (AuthFailureError authFailureError) {
            authFailureError.printStackTrace();
        }
    }

    public void cancelRequest() {
        mRequestQueue.cancelAll(getClass().getSimpleName());
    }

    public static class GetRequest extends StringRequest{
        Map<String, String> mHeader;

        public GetRequest(String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
            super(Request.Method.GET, url, listener, errorListener);
            this.mHeader = new HashMap<>();
        }

        @Override
        public Map<String, String> getHeaders() throws AuthFailureError {
            return mHeader;
        }
    }

    public static class PostRequest extends StringRequest{
        /** Charset for request. */
        private static final String PROTOCOL_CHARSET = "utf-8";

        /** Content type for request. */
        private static final String PROTOCOL_CONTENT_TYPE =
                String.format("application/json; charset=%s", PROTOCOL_CHARSET); //NON-NLS

        String mRequestBody;
        Map<String, String> mHeader;

        public PostRequest(String url, String body, Response.Listener<String> listener, Response.ErrorListener errorListener) {
            super(Method.POST, url, listener, errorListener);
            this.mRequestBody = body;
            this.mHeader = new HashMap<>();
        }

        @Override
        public Map<String, String> getHeaders() throws AuthFailureError {
            return mHeader;
        }

        @Override
        public String getBodyContentType() {
            return PROTOCOL_CONTENT_TYPE;
        }

        @Override
        public byte[] getBody() {
            try {
                return mRequestBody == null ? null : mRequestBody.getBytes(PROTOCOL_CHARSET);
            } catch (UnsupportedEncodingException uee) {
                VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", mRequestBody, PROTOCOL_CHARSET); //NON-NLS
                return null;
            }
        }
    }

}
