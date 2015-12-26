package org.zegerhoogeboom;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.GridView;

public class Qlock extends Activity {

    BroadcastReceiver broadcastReceiver;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //remove title bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); //remove notification bar
        setContentView(R.layout.main);

        updateGrid();
    }

    private void updateGrid() {
        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new TextAdapter(this, new EnglishRenderer()));
    }

    public void onStart() {
        super.onStart();
        updateGrid();
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context ctx, Intent intent) {
                if (intent.getAction().compareTo(Intent.ACTION_TIME_TICK) == 0) updateGrid();
            }
        };

        registerReceiver(broadcastReceiver, new IntentFilter(Intent.ACTION_TIME_TICK));
    }

    @Override
    public void onStop() {
        super.onStop();
        if (broadcastReceiver != null) unregisterReceiver(broadcastReceiver);
    }
}
