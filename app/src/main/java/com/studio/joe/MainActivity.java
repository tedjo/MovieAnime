package com.studio.joe;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.studio.joe.Adapter.PosAdapter;
import com.studio.joe.Model.Pos;
import com.studio.joe.Util.Scrape;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ArrayList<Pos> posts;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.list);
        new DownloadData().execute();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Bundle data = new Bundle();
        data.putString("LinkPageDes", posts.get(position).getLinkPage());
        data.putString("TitleDes", posts.get(position).getTitle());
        data.putString("EpisodeDes", posts.get(position).getEpisode());

        Intent intent = new Intent(this,ViewMovie.class);
        intent.putExtras(data);
        startActivity(intent);
    }

    private class DownloadData extends AsyncTask<Void, Void, Void>
    {
        ProgressDialog progressDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("Pless wite download data...");
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            PosAdapter adapter = new PosAdapter(posts,MainActivity.this);
            listView.setAdapter(adapter);
            progressDialog.dismiss();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            posts = Scrape.loadDataPos(Pos.URL);
            return null;
        }
    }
}
