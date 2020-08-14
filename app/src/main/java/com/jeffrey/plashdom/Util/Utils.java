package com.jeffrey.plashdom.Util;

import android.content.Context;
import android.net.Uri;

import androidx.annotation.ColorRes;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.core.content.ContextCompat;

public class Utils {

    public static void openCustomTab(Context context, String url, @ColorRes int colorResId) {
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        builder.setToolbarColor(ContextCompat.getColor(context, colorResId));
        builder.setShowTitle(true);
        builder.addDefaultShareMenuItem();
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(context, Uri.parse(url));
    }
}
