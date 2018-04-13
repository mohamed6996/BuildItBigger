package com.udacity.gradle.builditbigger;

/**
 * Created by lenovo on 4/12/2018.
 */

public interface JokeFetchListener {
    void onFetchComplete(String joke);

    void onFetchingJoke();
}
