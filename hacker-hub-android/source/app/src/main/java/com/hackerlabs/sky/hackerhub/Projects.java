package com.hackerlabs.sky.hackerhub;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.content.ClipboardManager;
import android.view.MenuInflater;


public class Projects extends AppCompatActivity {

    WebView webView;
    SwipeRefreshLayout swipe;
    String PageURL, PageTitle ;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);



        //    getActionBar().setIcon(R.mipmap.ic_exit_to_app_black_24dp);

        swipe = (SwipeRefreshLayout)findViewById(R.id.swipe);
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                WebAction();
            }
        });

        WebAction();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.webview_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_settings:
                // User chose the "Settings" item, show the app settings UI...
                return true;

            case R.id.action_open_in:
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(PageURL));
                startActivity(i);
                return true;
            case R.id.action_copy:
                android.content.ClipboardManager clipboard = (android.content.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                android.content.ClipData clip = android.content.ClipData.newPlainText("Message", PageURL);
                clipboard.setPrimaryClip(clip);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }

    }
    public void WebAction(){

        webView = (WebView) findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAppCacheEnabled(true);
        webView.loadUrl("https://skylarmccauley.hacker-hub.com/#projects");
        swipe.setRefreshing(true);

        webView.setWebViewClient(new WebViewClient(){

            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {

                webView.loadUrl("https://skylarmccauley.hacker-hub.com/404.html");

            }

            public void onPageFinished(WebView view, String url) {
                // do your stuff here
                swipe.setRefreshing(false);

                PageURL = view.getUrl();
                PageTitle = view.getTitle();


                getSupportActionBar().setTitle(Html.fromHtml("<small style='small{font-size: smaller;}'>" + PageTitle + "</small>"));
                //  getSupportActionBar().setSubtitle(PageTitle);


            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                if(url.contains("hacker-hub.com")) {
                    view.loadUrl(url);
                } else {
                    Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(i);
                }
                return true;
            }

        });

    }
    @Override
    public void onBackPressed(){

        if (webView.canGoBack()){
            webView.goBack();
        }else {
            finish();
        }
    }
    @Override
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK)
        {
            startActivity(new Intent(Projects.this, HomeActivity.class));

            return true;
        }
        return super.onKeyLongPress(keyCode, event);
    }
}
