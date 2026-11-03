package com.facebook.imageutils;

import android.media.ExifInterface;
import android.os.Build;
import com.facebook.common.logging.FLog;
import java.io.IOException;
import java.io.InputStream;
/* loaded from: classes8.dex */
public class DngExifUtil {
    public static final String TAG = "DngExifUtil";

    public static int getOrientation(InputStream inputStream) {
        if (Build.VERSION.SDK_INT >= 24) {
            return DngExifUtilAndroidN.getOrientation(inputStream);
        }
        FLog.d(TAG, "Trying to read Dng Exif information before Android N -> ignoring");
        return 0;
    }

    /* loaded from: classes8.dex */
    private static class DngExifUtilAndroidN {
        private DngExifUtilAndroidN() {
        }

        static int getOrientation(InputStream inputStream) {
            try {
                return new ExifInterface(inputStream).getAttributeInt(androidx.exifinterface.media.ExifInterface.TAG_ORIENTATION, 1);
            } catch (IOException e2) {
                FLog.d(DngExifUtil.TAG, "Failed reading Dng Exif orientation -> ignoring", (Throwable) e2);
                return 0;
            }
        }
    }
}
