package org.zegerhoogeboom.settings;

import android.content.Context;
import android.graphics.Color;
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
}
