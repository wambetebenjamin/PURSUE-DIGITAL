package com.facebook.imageutils;

import android.media.ExifInterface;
import android.os.Build;
import com.facebook.common.logging.FLog;
import java.io.IOException;
import java.io.InputStream;
/* loaded from: classes8.dex */
public class HeifExifUtil {
    public static final String TAG = "HeifExifUtil";

    public static int getOrientation(InputStream inputStream) {
        if (Build.VERSION.SDK_INT >= 24) {
            return HeifExifUtilAndroidN.getOrientation(inputStream);
        }
        FLog.d(TAG, "Trying to read Heif Exif information before Android N -> ignoring");
        return 0;
    }

    /* loaded from: classes8.dex */
    private static class HeifExifUtilAndroidN {
        private HeifExifUtilAndroidN() {
        }

        static int getOrientation(InputStream inputStream) {
            try {
                return new ExifInterface(inputStream).getAttributeInt(androidx.exifinterface.media.ExifInterface.TAG_ORIENTATION, 1);
            } catch (IOException e2) {
                FLog.d(HeifExifUtil.TAG, "Failed reading Heif Exif orientation -> ignoring", (Throwable) e2);
                return 0;
            } catch (Throwable th) {
                FLog.d(HeifExifUtil.TAG, "System api err, Failed reading Heif Exif orientation -> ignoring", th);
                return 0;
            }
        }
    }
}
