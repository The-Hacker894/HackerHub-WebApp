package com.hackerlabs.sky.hackerhub;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;import android.widget.ListView;
import android.net.Uri;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.view.View.OnLongClickListener;
import android.view.View.OnClickListener;
import android.net.ConnectivityManager;
import android.content.Context;
import android.net.NetworkInfo;
import android.widget.Toast;
import android.widget.Button;



public class HomeActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("https://discordapp.com/invite/ASEPsVy"));
                    startActivity(intent);

            }
        });
    }

  //  Button websiteButton = (Button) findViewById(R.id.Website_Button);


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
    public void gotoDiscord (View view) {
        goToUrl ( "https://discordapp.com/invite/ASEPsVy");
    }

   /* Button websiteButton = (Button) findViewById(R.id.Website_Button);

    websiteButton.setOnLongClickListener(new OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            // TODO Auto-generated method stub
            return true;
        }
    }); */

    public void goToWebsite (View view) {
        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            startActivity(new Intent(HomeActivity.this, ContentActivity.class));

            connected = true;
        }
        else

            Toast.makeText(this, "You need an Internet Connection for this!",
                    Toast.LENGTH_LONG).show();

        connected = false;


    }

    public void goToAboutMe (View view) {
        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            startActivity(new Intent(HomeActivity.this, AboutMe.class));

            connected = true;
        }
        else

            Toast.makeText(this, "You need an Internet Connection for this!",
                    Toast.LENGTH_LONG).show();

        connected = false;


    }
    public void goToProjects (View view) {
        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            startActivity(new Intent(HomeActivity.this, Projects.class));

            connected = true;
        }
        else

            Toast.makeText(this, "You need an Internet Connection for this!",
                    Toast.LENGTH_LONG).show();

        connected = false;


    }

    private void goToUrl (String url) {
        Uri uriUrl = Uri.parse(url);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }




}
