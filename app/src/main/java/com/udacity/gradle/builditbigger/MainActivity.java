package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.bigger.frontend.JokeActivity;



public class MainActivity extends AppCompatActivity implements JokeFetchListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      //  getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, new AdFragment()).commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view) {
        new EndpointsAsyncTask(this).execute();
        // new Pair<Context, String>(this, "Manfred")
    }


    @Override
    public void onFetchComplete(String joke) {
        Log.i("joke", joke + " from main activity");
        Intent intent = new Intent(this, JokeActivity.class);
        intent.putExtra("joke", joke);
        startActivity(intent);
    }

    @Override
    public void onFetchingJoke() {

    }
}
