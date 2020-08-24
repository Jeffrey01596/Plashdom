package com.jeffrey.plashdom;

import android.os.Bundle;
import android.text.Html;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.jeffrey.plashdom.Util.Constants;
import com.jeffrey.plashdom.Util.Utils;
import com.jeffrey.plashdom.databinding.ActivityAboutBinding;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class AboutActivity extends AppCompatActivity {

    private ActivityAboutBinding binding;

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        finish();
        return super.onSupportNavigateUp();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAboutBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        setUpToolbar();
        setUpStrings();
        setUpDevelopersAvatars();

        binding.developerWebsite.setOnClickListener(v -> Utils.openCustomTab(AboutActivity.this, Constants.mainDevWebsite, R.color.colorMainDevAccent));

        binding.githubString.setOnClickListener(v -> Utils.openCustomTab(AboutActivity.this, Constants.githubString, R.color.colorGithubBackground));

        binding.libraries.setOnClickListener(v -> new MaterialAlertDialogBuilder(AboutActivity.this)
                .setTitle(R.string.libraries_title)
                .setBackground(ContextCompat.getDrawable(this, R.drawable.dialog_background))
                .setMessage(Html.fromHtml("<h5>&bull; Material Components</h5>\n" +
                        "<a href=\"URL\">https://github.com/material-components/material-components-android</a>\n" +
                        "<p>Copyright (C) 2020 Google Licensed under the Apache License, Version 2.0 (the \"License\"); you may not use this file except in compliance with the License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an \"AS IS\" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.</p>\n" +
                        "<h5>&bull; AndroidX</h5>\n" +
                        "<a href=\"URL\">https://android.googlesource.com/platform/frameworks/support/+/androidx-master-dev</a>\n" +
                        "<p>Copyright (C) 2020 Google Licensed under the Apache License, Version 2.0 (the \"License\"); you may not use this file except in compliance with the License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an \"AS IS\" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.</p>\n" +
                        "<h5>&bull; AndroidX Browser</h5>\n" +
                        "<a href=\"URL\">https://developer.android.com/jetpack/androidx/releases/browser</a>\n" +
                        "<p>Copyright (C) 2015 Google Licensed under the Apache License, Version 2.0 (the \"License\"); you may not use this file except in compliance with the License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an \"AS IS\" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.</p>\n" +
                        "<h5>&bull; Picasso</h5>\n" +
                        "<a href=\"URL\">https://github.com/square/picasso</a>\n" +
                        "<p>Copyright (C) 2015 Google Licensed under the Apache License, Version 2.0 (the \"License\"); you may not use this file except in compliance with the License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an \"AS IS\" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.</p>\n"))
                .setPositiveButton(android.R.string.ok, null)
                .setCancelable(true)
                .show());
    }

    public void setUpToolbar() {
        setSupportActionBar(binding.toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(true);
    }

    public void setUpStrings() {
        String versionString = BuildConfig.VERSION_NAME;
        String githubString = Constants.githubString;
        binding.versionString.setText(versionString);
        binding.githubString.setText(githubString);
    }

    public void setUpDevelopersAvatars() {
        Picasso.get().load(Constants.mainDevAvatar).into(binding.mainDeveloperAvatar);
    }
}