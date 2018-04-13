package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.util.Pair;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

/**
 * Created by lenovo on 4/12/2018.
 */

public class EndpointsAsyncTask extends AsyncTask<Void, Void, String> implements ResourceIdler {
    private static MyApi myApiService = null;
    private Context context;
    JokeFetchListener listener;
    SimpleIdlingResource simpleIdlingResource;

    public EndpointsAsyncTask(JokeFetchListener listener) {
        this.listener = listener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        listener.onFetchingJoke();
        getIdlingResource().setIdleState(false);

    }

    @Override
    protected String doInBackground(Void... params) {
        if (myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            myApiService = builder.build();
        }

//        context = params[0].first;
//        String name = params[0].second;

        try {
            return myApiService.sayHi("mohamed").execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        listener.onFetchComplete(result);
        Log.i("joke",result+ " from async");

        getIdlingResource().setIdleState(true);
    }

    @Override
    public SimpleIdlingResource getIdlingResource() {
        if (simpleIdlingResource == null) {
            simpleIdlingResource = new SimpleIdlingResource();
        }
        return simpleIdlingResource;
    }
}