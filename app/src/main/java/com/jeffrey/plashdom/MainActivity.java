package com.jeffrey.plashdom;

import androidx.appcompat.app.AppCompatActivity;

import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;
import com.jeffrey.plashdom.Util.Constants;
import com.jeffrey.plashdom.databinding.ActivityMainBinding;
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

        binding.setWallpaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setPictureAsWallpaper();
            }
        });
    }

    public void loadPicture() {
        Picasso.get().load(Constants.mainApi).networkPolicy(NetworkPolicy.NO_CACHE).memoryPolicy(MemoryPolicy.NO_CACHE).into(binding.randomPicture);
    }

    public void setPictureAsWallpaper() {
        binding.randomPicture.buildDrawingCache();
        WallpaperManager m = WallpaperManager.getInstance(this);

        try {
            binding.randomPicture.setDrawingCacheEnabled(true);
            binding.randomPicture.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
            binding.randomPicture.layout(0, 0,
                    binding.randomPicture.getMeasuredWidth(), binding.randomPicture.getMeasuredHeight());
            binding.randomPicture.buildDrawingCache(true);
            Bitmap bitmap = Bitmap.createBitmap(binding.randomPicture.getDrawingCache());
            binding.randomPicture.setDrawingCacheEnabled(false);
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
            return true;
        } if (item.getItemId() == R.id.about_app) {
            startAboutActivity();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setUpToolbar() {
        setSupportActionBar(binding.toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(true);
    }

    public void startAboutActivity() {
        Intent i = new Intent(this, AboutActivity.class);
        this.startActivity(i);
    }
}