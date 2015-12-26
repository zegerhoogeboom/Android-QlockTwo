package org.zegerhoogeboom;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

public class Qlock extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new TextAdapter(this, new EnglishRenderer()));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Toast.makeText(Qlock.this, "" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }


}
