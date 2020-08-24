package com.jeffrey.plashdom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.app.WallpaperManager;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;
import com.jeffrey.plashdom.Util.Constants;
import com.jeffrey.plashdom.Util.Utils;
import com.jeffrey.plashdom.databinding.ActivityMainBinding;
import com.jeffrey.plashdom.service.InternetConnectionService;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        setUpToolbar();
        loadPicture();
        checkConnection();

        binding.NoInternetButton.setOnClickListener(v -> {
            checkConnection();
            loadPicture();
        });

        binding.setWallpaper.setOnClickListener(v -> setPictureAsWallpaper());
    }

    public void loadPicture() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String width = prefs.getString("width", "1080");
        String height = prefs.getString("height", "2000");
        String final_size = width + "x" + height;
        String final_url = Constants.mainApi + final_size;
        Picasso.get().load(final_url).networkPolicy(NetworkPolicy.NO_CACHE).memoryPolicy(MemoryPolicy.NO_CACHE).into(binding.randomPicture);
    }

    public void setPictureAsWallpaper() {
        binding.randomPicture.buildDrawingCache();
        WallpaperManager m = WallpaperManager.getInstance(this);

        try {
            binding.randomPicture.setDrawingCacheEnabled(true);
            Bitmap bitmap = ((BitmapDrawable)binding.randomPicture.getDrawable()).getBitmap();
            m.setBitmap(bitmap);
            Snackbar.make(binding.setWallpaper, R.string.applied, Snackbar.LENGTH_SHORT)
                    .show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.reload_image) {
            loadPicture();
            checkConnection();
            return true;
        } if (item.getItemId() == R.id.about_app) {
            Utils.startActivity(this, AboutActivity.class);
            return true;
        } if (item.getItemId() == R.id.settings) {
            Utils.startActivity(this, PreferencesActivity.class);
        }
        return super.onOptionsItemSelected(item);
    }

    public void setUpToolbar() {
        setSupportActionBar(binding.toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(true);
    }

    public void checkConnection() {
        if (InternetConnectionService.checkConnection(this)) {
            binding.setWallpaper.setVisibility(View.VISIBLE);
            binding.noConnectionOnboarding.setVisibility(View.GONE);
        } else {
            binding.setWallpaper.setVisibility(View.GONE);
            binding.noConnectionOnboarding.setVisibility(View.VISIBLE);
        }
    }
}