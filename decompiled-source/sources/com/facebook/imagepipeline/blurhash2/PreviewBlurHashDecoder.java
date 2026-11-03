package com.facebook.imagepipeline.blurhash2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Base64;
import com.facebook.common.logging.FLog;
import java.io.UnsupportedEncodingException;
/* loaded from: classes8.dex */
public final class PreviewBlurHashDecoder {
    private static final String TAG = "PreviewBlurHashDecoder";

    public static Bitmap getBlurhashBitmap(String str, String str2, int i2, int i3) {
        String recoverMetaData = recoverMetaData(str, str2);
        if (TextUtils.isEmpty(recoverMetaData)) {
            FLog.d(TAG, "getBlurhashBitmap: totalImgStr is null!");
            return null;
        }
        try {
            byte[] bytes = recoverMetaData.getBytes("ISO-8859-1");
            Bitmap decodeByteArray = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            try {
                Class<?> cls = Class.forName("com.facebook.imagepipeline.nativecode.NativeBlurFilter");
                cls.getDeclaredMethod("iterativeBoxBlur", Bitmap.class, Integer.TYPE, Integer.TYPE).invoke(cls, decodeByteArray, Integer.valueOf(i2), Integer.valueOf(i3));
                return decodeByteArray;
            } catch (Exception e2) {
                e2.printStackTrace();
                return decodeByteArray;
            }
        } catch (UnsupportedEncodingException e3) {
            e3.printStackTrace();
            return null;
        }
    }

    public static String recoverMetaData(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            FLog.d(TAG, "recoverMetaData: base64data or metaData is null!");
            return null;
        }
        try {
            String base64Decode = base64Decode(str, "ISO-8859-1");
            String base64Decode2 = base64Decode(str2, "ISO-8859-1");
            char charAt = base64Decode.charAt(0);
            char charAt2 = base64Decode.charAt(1);
            StringBuilder sb = new StringBuilder(base64Decode2);
            sb.setCharAt(166, charAt);
            sb.setCharAt(164, charAt2);
            sb.append(base64Decode.substring(2));
            return sb.toString();
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private static String base64Decode(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            str2 = "UTF-8";
        }
        try {
            return new String(Base64.decode(str, 0), str2);
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            return "";
        }
    }
}
