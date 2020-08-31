package com.jeffrey.plashdom

import android.app.WallpaperManager
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import com.google.android.material.snackbar.Snackbar
import com.jeffrey.plashdom.util.Constants
import com.jeffrey.plashdom.util.Utils
import com.jeffrey.plashdom.databinding.ActivityMainBinding
import com.jeffrey.plashdom.service.InternetConnectionService
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view: View = binding!!.root
        setContentView(view)
        setUpToolbar()
        loadPicture()
        checkConnection()

        binding!!.NoInternetButton.setOnClickListener {
            checkConnection()
            loadPicture()
        }
        binding!!.setWallpaper.setOnClickListener { setPictureAsWallpaper() }
    }

    private fun loadPicture() {
        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        val width = prefs.getString("width", "1080")
        val height = prefs.getString("height", "2000")
        val finalSize = width + "x" + height
        val finalUrl = Constants.mainApi + finalSize
        Picasso.get().load(finalUrl).networkPolicy(NetworkPolicy.NO_CACHE).memoryPolicy(MemoryPolicy.NO_CACHE).into(binding!!.randomPicture)
    }

    private fun setPictureAsWallpaper() {
        binding!!.randomPicture.buildDrawingCache()
        val m = WallpaperManager.getInstance(this)
        try {
            binding!!.randomPicture.isDrawingCacheEnabled = true
            val bitmap = (binding!!.randomPicture.drawable as BitmapDrawable).bitmap
            m.setBitmap(bitmap)
            Snackbar.make(binding!!.setWallpaper, R.string.applied, Snackbar.LENGTH_SHORT)
                    .show()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.reload_image) {
            loadPicture()
            checkConnection()
            return true
        }
        if (item.itemId == R.id.about_app) {
            Utils.startActivity(this, AboutActivity::class.java)
            return true
        }
        if (item.itemId == R.id.settings) {
            Utils.startActivity(this, PreferencesActivity::class.java)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setUpToolbar() {
        setSupportActionBar(binding!!.toolbar)
    }

    private fun checkConnection() {
        if (InternetConnectionService.checkConnection(this)) {
            binding!!.setWallpaper.visibility = View.VISIBLE
            binding!!.noConnectionOnboarding.visibility = View.GONE
        } else {
            binding!!.setWallpaper.visibility = View.GONE
            binding!!.noConnectionOnboarding.visibility = View.VISIBLE
        }
    }
}