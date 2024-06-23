package com.example.preparation;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.RadioButton;

public class SettingsActivity extends BaseActivity {

    public static final String PREFS_NAME = "app_settings";
    private static final String KEY_THEME = "app_theme";
    private static final String KEY_CARD_VIEW_MODE = "card_view_mode";
    public static final String VIEW_MODE_SCROLL = "scroll";
    public static final String VIEW_MODE_FLIP = "flip";
    private static final String THEME_LIGHT = "light";
    private static final String THEME_DARK = "dark";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        findViewById(R.id.light_theme_button).setOnClickListener(v -> saveTheme(THEME_LIGHT));
        findViewById(R.id.dark_theme_button).setOnClickListener(v -> saveTheme(THEME_DARK));

        findViewById(R.id.scroll_view_mode).setOnClickListener(v -> saveCardViewMode(VIEW_MODE_SCROLL));
        findViewById(R.id.flip_view_mode).setOnClickListener(v -> saveCardViewMode(VIEW_MODE_FLIP));

        String currentViewMode = getCardViewMode(this);
        if (VIEW_MODE_SCROLL.equals(currentViewMode)) {
            ((RadioButton) findViewById(R.id.scroll_view_mode)).setChecked(true);
        } else {
            ((RadioButton) findViewById(R.id.flip_view_mode)).setChecked(true);
        }

        String currentTheme = getSavedThemeName(this);
        if (THEME_LIGHT.equals(currentTheme)) {
            ((RadioButton) findViewById(R.id.light_theme_button)).setChecked(true);
        } else {
            ((RadioButton) findViewById(R.id.dark_theme_button)).setChecked(true);
        }
    }

    private void saveTheme(String themeName) {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(KEY_THEME, themeName);
        editor.apply();
        recreate();
    }

    private void saveCardViewMode(String viewMode) {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(KEY_CARD_VIEW_MODE, viewMode);
        editor.apply();
    }

    public static int getSavedTheme(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String themeName = prefs.getString(KEY_THEME, THEME_LIGHT);
        if (THEME_DARK.equals(themeName)) {
            return R.style.AppTheme_Dark;
        } else {
            return R.style.AppTheme_Light;
        }
    }

    public static String getCardViewMode(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        return prefs.getString(KEY_CARD_VIEW_MODE, VIEW_MODE_SCROLL);
    }

    public static String getSavedThemeName(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        return prefs.getString(KEY_THEME, THEME_LIGHT);
    }

}
