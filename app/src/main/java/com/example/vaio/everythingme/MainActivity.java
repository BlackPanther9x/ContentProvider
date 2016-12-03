package com.example.vaio.everythingme;

import android.content.BroadcastReceiver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import me.everything.providers.android.media.Audio;
import me.everything.providers.android.media.MediaProvider;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView listView;
    private MediaProvider mediaProvider;
    private List<Audio> listData;
    private ArrayList<Song> listSong;
    private SongAdapter mAdapter;
    public static String EXTRA_SEND_SONG= "song_selection";
    private BroadcastReceiver mReceiver;
    private IntentFilter mFilter;


    private void initUI(){
        listView=(ListView)findViewById(R.id.list_view);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFilter= new IntentFilter("test");
        mReceiver= new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String value= intent.getExtras().getString(MyReceiver.EXTRA_VALUE);
                if(value!=null) HandlerDelay(value,3000);
            }
        };
        registerReceiver(mReceiver,mFilter);

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
        listView.setOnItemClickListener(this);


    }

    private void HandlerDelay(final String in, long time){
        Handler mHandler= new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, in , Toast.LENGTH_SHORT).show();
            }
        },time);
    }


    private Uri getURI(long id){
        return ContentUris.withAppendedId(Audio.uriExternal,id);
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent i= new Intent("my.receiver");
        i.putExtra(EXTRA_SEND_SONG ,listSong.get(position).getTitle());
        i.setPackage(getPackageName());
        sendBroadcast(i);
    }
}
