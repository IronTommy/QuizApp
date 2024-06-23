package com.example.preparation;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

public class ThemeChangeActivity extends BaseActivity {

    public static final String PREFS_NAME = "app_settings";
    private static final String KEY_THEME = "app_theme";
    private static final String THEME_LIGHT = "light";
    private static final String THEME_DARK = "dark";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Установить тему перед вызовом super.onCreate
        setTheme(getSavedTheme(this));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme_change);

        findViewById(R.id.light_theme_button).setOnClickListener(v -> saveTheme(THEME_LIGHT));
        findViewById(R.id.dark_theme_button).setOnClickListener(v -> saveTheme(THEME_DARK));

        String currentTheme = getSavedThemeName();
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
        restartApp();
    }

    private void restartApp() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    private String getSavedThemeName() {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        return prefs.getString(KEY_THEME, THEME_LIGHT);
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
}
