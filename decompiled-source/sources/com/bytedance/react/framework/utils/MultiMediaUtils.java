package com.bytedance.react.framework.utils;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import com.bytedance.librarian.c;
import com.facebook.internal.security.CertificateUtil;
/* loaded from: classes2.dex */
public class MultiMediaUtils {
    private static final String SCHEME_CONTENT = "content";
    private static final String SCHEME_FILE = "file";

    public static String getFilePathByUri(Context context, Uri uri) {
        if (uri == null) {
            return null;
        }
        if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        if ("content".equalsIgnoreCase(uri.getScheme())) {
            if (Build.VERSION.SDK_INT >= 19) {
                return getRealPathFromUriAboveApi19(context, uri);
            }
            return getRealPathFromUriBelowAPI19(context, uri);
        }
        return null;
    }

    private static String getRealPathFromUriAboveApi19(Context context, Uri uri) {
        Uri uri2 = null;
        if (uri == null) {
            return null;
        }
        if (DocumentsContract.isDocumentUri(context, uri)) {
            String documentId = DocumentsContract.getDocumentId(uri);
            if (TextUtils.isEmpty(documentId)) {
                return null;
            }
            String[] split = documentId.split(CertificateUtil.DELIMITER);
            if (split.length < 2) {
                return null;
            }
            String str = split[0];
            String str2 = split[1];
            if (isMediaDocument(uri)) {
                String[] strArr = {str2};
                if ("image".equals(str)) {
                    uri2 = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(str)) {
                    uri2 = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(str)) {
                    uri2 = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }
                return getDataColumn(context, uri2, "_id=?", strArr);
            } else if (isDownloadsDocument(uri)) {
                return getDataColumn(context, ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(documentId).longValue()), null, null);
            } else {
                if (isExternalStorageDocument(uri) && "primary".equalsIgnoreCase(str)) {
                    return Environment.getExternalStorageDirectory() + c.a.SEPARATOR + str2;
                }
                return null;
            }
        }
        return getDataColumn(context, uri, null, null);
    }

    private static String getRealPathFromUriBelowAPI19(Context context, Uri uri) {
        return getDataColumn(context, uri, null, null);
    }

    private static String getDataColumn(Context context, Uri uri, String str, String[] strArr) {
        String[] strArr2 = {"_data"};
        Cursor cursor = null;
        try {
            Cursor query = context.getContentResolver().query(uri, strArr2, str, strArr, null);
            if (query != null) {
                try {
                    if (query.moveToFirst()) {
                        return query.getString(query.getColumnIndexOrThrow(strArr2[0]));
                    }
                    return null;
                } catch (Exception unused) {
                    if (0 != 0) {
                        cursor.close();
                        return null;
                    }
                    return null;
                }
            }
            return null;
        } catch (Exception unused2) {
        }
    }

    private static boolean isMediaDocument(Uri uri) {
        return uri != null && "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    private static boolean isExternalStorageDocument(Uri uri) {
        return uri != null && "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    private static boolean isDownloadsDocument(Uri uri) {
        return uri != null && "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }
}
