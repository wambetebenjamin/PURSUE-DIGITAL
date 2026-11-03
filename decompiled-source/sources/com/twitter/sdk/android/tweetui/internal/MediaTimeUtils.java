package com.twitter.sdk.android.tweetui.internal;

import java.util.Locale;
/* loaded from: classes10.dex */
final class MediaTimeUtils {
    private static final String TIME_FORMAT_LONG = "%1$d:%2$02d:%3$02d";
    private static final String TIME_FORMAT_SHORT = "%1$d:%2$02d";

    private MediaTimeUtils() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getPlaybackTime(long j2) {
        int i2 = (int) (j2 / 1000);
        int i3 = i2 % 60;
        int i4 = (i2 / 60) % 60;
        int i5 = i2 / 3600;
        return i5 > 0 ? String.format(Locale.getDefault(), TIME_FORMAT_LONG, Integer.valueOf(i5), Integer.valueOf(i4), Integer.valueOf(i3)) : String.format(Locale.getDefault(), TIME_FORMAT_SHORT, Integer.valueOf(i4), Integer.valueOf(i3));
    }
}
