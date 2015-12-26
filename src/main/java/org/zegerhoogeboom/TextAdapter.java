package org.zegerhoogeboom;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * @author Zeger Hoogeboom
 */
public class TextAdapter extends BaseAdapter {
    private Context context;
    private Qlock.EnglishRenderer renderer;

    public TextAdapter(Context c, Qlock.EnglishRenderer renderer) {
        context = c;
        this.renderer = renderer;
    }

    public int getCount() {
        return renderer.getLetters().size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView != null) return convertView;
        TextView tv = new TextView(context);
        tv.setText(renderer.getLetter(position));
        tv.setTextSize(20);
        int color = Color.DKGRAY;
        if (renderer.shouldShow(position)) color = Color.WHITE;
        tv.setTextColor(color);
        return tv;
    }


}