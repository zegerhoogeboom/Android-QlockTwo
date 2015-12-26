package org.zegerhoogeboom.settings;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import org.zegerhoogeboom.R;

/**
 * @author Zeger Hoogeboom
 */
public class SettingsFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}
