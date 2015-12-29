package org.zegerhoogeboom;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.GridView;
import org.zegerhoogeboom.settings.SettingsActivity;
import org.zegerhoogeboom.settings.SettingsFactory;

public class Qlock extends Activity {

    BroadcastReceiver broadcastReceiver;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //remove title bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); //remove notification bar
        setContentView(R.layout.main);

        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
        updateGrid();
    }

    private void updateGrid() {
        SettingsFactory settingsFactory = new SettingsFactory(this);

        findViewById(R.id.outer).setBackgroundColor(settingsFactory.backgroundColor());
        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new TextAdapter(this, settingsFactory));
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

    public void gotoSettings(View view) {
        Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
        startActivity(intent);
    }
}
