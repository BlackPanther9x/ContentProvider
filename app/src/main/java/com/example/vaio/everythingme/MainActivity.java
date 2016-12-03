package com.example.vaio.everythingme;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import me.everything.providers.android.media.Audio;
import me.everything.providers.android.media.MediaProvider;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private MediaProvider mediaProvider;
    private List<Audio> listData;
    private ArrayList<Song> listSong;
    private SongAdapter mAdapter;


    private void initUI(){
        listView=(ListView)findViewById(R.id.list_view);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
        mediaProvider= new MediaProvider(this);
        listData= mediaProvider.getAudios(MediaProvider.Storage.EXTERNAL).getList();
        listSong= new ArrayList<>();
        for(int i=0; i<listData.size(); i++){
            listSong.add(new Song(listData.get(i).title,listData.get(i).artist));
        }
        mAdapter= new SongAdapter(this,listSong);
        mAdapter.notifyDataSetChanged();
        listView.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_settings:
                Toast.makeText(this, R.string.settings, Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
