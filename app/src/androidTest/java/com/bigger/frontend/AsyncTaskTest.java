package com.bigger.frontend;

import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.IdlingResource;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by lenovo on 4/12/2018.
 */
@RunWith(AndroidJUnit4.class)
public class AsyncTaskTest {
IdlingResource idlingResource;
    @Test
    public void nun_empty_string(){
        EndpointsAsyncTask asyncTask = new EndpointsAsyncTask(new JokeFetchListener() {
            @Override
            public void onFetchComplete(String joke) {
                Assert.assertTrue(joke.length() >0);
            }

            @Override
            public void onFetchingJoke() {
            // do nothing
            }
        });

        idlingResource = asyncTask.getIdlingResource();
        IdlingRegistry.getInstance().register(idlingResource);
        asyncTask.execute();

    }

    @After
    public void unRegister(){
        if (idlingResource != null)
            IdlingRegistry.getInstance().unregister(idlingResource);

    }
}

