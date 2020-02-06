package com.studio.joe;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class ViewMovie extends AppCompatActivity {

    private WebView webView;
    private TextView title;
    private TextView episode;

    ProgressBar progressBar;
    public static String URL = new String();
    public static String URLVIDEO = new String();
    private static String SELECTCONTEN = new String();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_movie);

        webView = findViewById(R.id.viewWeb);
        title = findViewById(R.id.titleDes);
        episode = findViewById(R.id.episodeDes);

        if(getIntent().getExtras()!=null){
            Bundle data = getIntent().getExtras();
            URL = data.getString("LinkPageDes");
            title.setText(data.getString("TitleDes"));
            episode.setText(data.getString("EpisodeDes"));
        }

        progressBar.setVisibility(View.VISIBLE);
        webView.setWebViewClient(new Browser_home());
        webView.setWebChromeClient(new MyChrome());

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setAppCacheEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setLoadsImagesAutomatically(true);

        new DownloadData().execute();
    }

    private class DownloadData extends AsyncTask<Void, Void, Void>
    {
        ProgressDialog progressDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(ViewMovie.this);
            progressDialog.setMessage("Mendownload data...");
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            webView.loadUrl(URLVIDEO);

            progressDialog.dismiss();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Document doc = null;
            ArrayList<String> saveData = new ArrayList<>();
            try {
                doc = Jsoup.connect(URL).get();
                Elements elements = doc.select("div.container");
                for(int i=0; i< 1; i++){
                    String video = elements.select("div.player-ratio")
                            .select("iframe")
                            .eq(i)
                            .attr("data-src");
                    URLVIDEO = "https:" + video;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    private class Browser_home extends WebViewClient {

        Browser_home() {
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, "Anime", favicon);

        }

        @Override
        public void onPageFinished(WebView view, String url) {
            progressBar.setVisibility(View.GONE);
            super.onPageFinished(view, "Adnim");

        }
    }

    private class MyChrome extends WebChromeClient {
        private View mCustomView;
        private WebChromeClient.CustomViewCallback mCustomViewCallback;
        protected FrameLayout mFullscreenContainer;
        private int mOriginalOrientation;
        private int mOriginalSystemUiVisibility;

        MyChrome() {}

        @Override
        public void onHideCustomView() {
            ((FrameLayout)getWindow().getDecorView()).removeView(this.mCustomView);
            this.mCustomView = null;
            getWindow().getDecorView().setSystemUiVisibility(this.mOriginalSystemUiVisibility);
            setRequestedOrientation(this.mOriginalOrientation);
            this.mCustomViewCallback.onCustomViewHidden();
            this.mCustomViewCallback = null;
        }


        @Override
        public void onShowCustomView(View view, WebChromeClient.CustomViewCallback callback) {
            if(this.mCustomView !=null){
                onHideCustomView();
                return;
            }
            this.mCustomView = view;
            this.mOriginalSystemUiVisibility = getWindow().getDecorView().getSystemUiVisibility();
            this.mOriginalOrientation = getRequestedOrientation();
            this.mCustomViewCallback = callback;
            ((FrameLayout)getWindow().getDecorView()).addView(this.mCustomView );
            getWindow().getDecorView().setSystemUiVisibility(3846);

        }
    }

}
