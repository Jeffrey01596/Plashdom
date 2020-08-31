package com.jeffrey.plashdom

import android.os.Bundle
import android.text.Html
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.jeffrey.plashdom.databinding.ActivityAboutBinding
import com.jeffrey.plashdom.util.Constants
import com.jeffrey.plashdom.util.Utils.openCustomTab
import com.squareup.picasso.Picasso

class AboutActivity : AppCompatActivity() {
    private var binding: ActivityAboutBinding? = null
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        finish()
        return super.onSupportNavigateUp()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutBinding.inflate(layoutInflater)
        val view: View = binding!!.root
        setContentView(view)
        setUpToolbar()
        setUpStrings()
        setUpDevelopersAvatars()
        binding!!.developerWebsite.setOnClickListener { openCustomTab(this@AboutActivity, Constants.mainDevWebsite, R.color.colorMainDevAccent) }
        binding!!.githubString.setOnClickListener { openCustomTab(this@AboutActivity, Constants.githubString, R.color.colorGithubBackground) }
        binding!!.libraries.setOnClickListener {
            MaterialAlertDialogBuilder(this@AboutActivity)
                    .setTitle(R.string.libraries_title)
                    .setBackground(ContextCompat.getDrawable(this, R.drawable.dialog_background))
                    .setMessage(Html.fromHtml("""<h5>&bull; Material Components</h5>
                        <a href="URL">https://github.com/material-components/material-components-android</a>
                        <p>Copyright (C) 2020 Google Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.</p>
                        <h5>&bull; AndroidX</h5>
                        <a href="URL">https://android.googlesource.com/platform/frameworks/support/+/androidx-master-dev</a>
                        <p>Copyright (C) 2020 Google Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.</p>
                        <h5>&bull; AndroidX Browser</h5>
                        <a href="URL">https://developer.android.com/jetpack/androidx/releases/browser</a>
                        <p>Copyright (C) 2015 Google Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.</p>
                        <h5>&bull; Picasso</h5>
                        <a href="URL">https://github.com/square/picasso</a>
                        <p>Copyright (C) 2015 Google Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.</p>
                        """))
                    .setPositiveButton(android.R.string.ok, null)
                    .setCancelable(true)
                    .show()
        }
    }

    private fun setUpToolbar() {
        setSupportActionBar(binding!!.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setUpStrings() {
        val versionString = BuildConfig.VERSION_NAME
        val githubString = Constants.githubString
        binding!!.versionString.text = versionString
        binding!!.githubString.text = githubString
    }

    private fun setUpDevelopersAvatars() {
        Picasso.get().load(Constants.mainDevAvatar).into(binding!!.mainDeveloperAvatar)
    }
}