package org.zegerhoogeboom;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import org.zegerhoogeboom.settings.SettingsFactory;

/**
 * @author Zeger Hoogeboom
 */
public class TextAdapter extends BaseAdapter {
    private SettingsFactory settingsFactory;
    private Renderer renderer;

    public TextAdapter(SettingsFactory settingsFactory) {
        this.settingsFactory = settingsFactory;
        this.renderer = settingsFactory.renderer();
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
        TextView tv = new TextView(settingsFactory.getContext());
        tv.setText(renderer.getLetter(position));
        tv.setTextSize(20);
        tv.setTypeface(settingsFactory.font());
        int color = Color.DKGRAY;
        if (renderer.shouldShow(position)) color = Color.WHITE;
        tv.setTextColor(color);
        return tv;
    }


}