package com.jeffrey.plashdom

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class PreferencesActivity : AppCompatActivity() {

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        finish()
        return super.onSupportNavigateUp()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)
        setUpToolbar()
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.settings, SettingsFragment())
                .commit()
    }

    class SettingsFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey)
            val sizePreference = findPreference<Preference>("size")!!
            sizePreference.onPreferenceClickListener = Preference.OnPreferenceClickListener {
                MaterialAlertDialogBuilder(requireContext())
                        .setTitle(R.string.size_title_dialog)
                        .setView(R.layout.size_layout)
                        .setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.dialog_background))
                        .setNegativeButton(android.R.string.cancel, null)
                        .setPositiveButton(android.R.string.ok) { dialogInterface: DialogInterface, _: Int ->
                            val width = (dialogInterface as AlertDialog).findViewById<EditText>(R.id.width)
                            val height = dialogInterface.findViewById<EditText>(R.id.height)
                            val getWidth = width!!.text.toString()
                            val getHeight = height!!.text.toString()
                            putStrPref("width", getWidth, requireContext())
                            putStrPref("height", getHeight, requireContext())
                        }
                        .setCancelable(true)
                        .show()
                true
            }
        }
    }

    private fun setUpToolbar() {
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    companion object {
        fun putStrPref(key: String?, value: String?, context: Context?) {
            val prefs = PreferenceManager.getDefaultSharedPreferences(context)
            val editor = prefs.edit()
            editor.putString(key, value)
            editor.apply()
        }
    }
}