package org.zegerhoogeboom;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * @author Zeger Hoogeboom
 */
public class TextAdapter extends BaseAdapter {
    private Context context;

    public TextAdapter(Context c) {
        context = c;
    }

    public int getCount() {
        return letters.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }


    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView != null) return convertView;
        TextView tv = new TextView(context);
        tv.setText(letters[position]);
        tv.setTextSize(20);
        return tv;
    }

    private String[] letters = {
        "I","T","L","I","S","A","S","A","M","P","M",
        "A","C","Q","U","A","R","T","E","R","D","C",
        "T","W","E","N","T","Y","F","I","V","E","X",
        "H","A","L","F","B","T","E","N","F","T","O",
        "P","A","S","T","E","R","U","N","I","N","E",
        "O","N","E","S","I","X","T","H","R","E","E",
        "F","O","U","R","F","I","V","E","T","W","O",
        "E","I","G","H","T","E","L","E","V","E","N",
        "S","E","V","E","N","T","W","E","L","V","E",
        "T","E","N","S","E","O","C","L","O","C","K"
    };
}