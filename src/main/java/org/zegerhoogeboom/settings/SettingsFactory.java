package org.zegerhoogeboom.settings;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import org.zegerhoogeboom.EnglishRenderer;
import org.zegerhoogeboom.Renderer;

/**
 * @author Zeger Hoogeboom
 */
public class SettingsFactory {

    private Context context;

    public SettingsFactory(Context context) {
        this.context = context;
    }

    public Renderer renderer()
    {
        String language = PreferenceManager.getDefaultSharedPreferences(context).getString("language", "EN");
        switch (language) {
            case "EN": return new EnglishRenderer();
            case "NL": return new EnglishRenderer();
            default: return new EnglishRenderer();
        }
    }

    public int backgroundColor()
    {
        String color = PreferenceManager.getDefaultSharedPreferences(context).getString("color", "#000000");
        return Color.parseColor(color);
    }

    public Typeface font()
    {
        String font = PreferenceManager.getDefaultSharedPreferences(context).getString("font", "droid");
        switch (font) {
            case "mono": return Typeface.MONOSPACE;
            case "serif": return Typeface.SANS_SERIF;
            case "coneria": return Typeface.createFromAsset(context.getAssets(), "fonts/coneria.ttf");
            case "droid":
            default: return Typeface.DEFAULT;
        }
    }
}
