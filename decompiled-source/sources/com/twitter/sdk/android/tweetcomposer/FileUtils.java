package com.twitter.sdk.android.tweetcomposer;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.webkit.MimeTypeMap;
import com.bytedance.librarian.c;
import com.facebook.internal.security.CertificateUtil;
import java.io.File;
/* loaded from: classes10.dex */
class FileUtils {
    private static final String MEDIA_SCHEME = "com.android.providers.media.documents";

    FileUtils() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getPath(Context context, Uri uri) {
        if ((Build.VERSION.SDK_INT >= 19) && isMediaDocumentAuthority(uri)) {
            String[] split = DocumentsContract.getDocumentId(uri).split(CertificateUtil.DELIMITER);
            if ("image".equals(split[0])) {
                return resolveFilePath(context, MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "_id=?", new String[]{split[1]});
            }
            return null;
        } else if (isContentScheme(uri)) {
            return resolveFilePath(context, uri, null, null);
        } else {
            if (isFileScheme(uri)) {
                return uri.getPath();
            }
            return null;
        }
    }

    static boolean isMediaDocumentAuthority(Uri uri) {
        return MEDIA_SCHEME.equalsIgnoreCase(uri.getAuthority());
    }

    static boolean isContentScheme(Uri uri) {
        return "content".equalsIgnoreCase(uri.getScheme());
    }

    static boolean isFileScheme(Uri uri) {
        return "file".equalsIgnoreCase(uri.getScheme());
    }

    static String resolveFilePath(Context context, Uri uri, String str, String[] strArr) {
        Cursor cursor = null;
        try {
            Cursor query = context.getContentResolver().query(uri, new String[]{"_data"}, str, strArr, null);
            if (query != null) {
                try {
                    if (query.moveToFirst()) {
                        String string = query.getString(query.getColumnIndexOrThrow("_data"));
                        if (query != null) {
                            query.close();
                        }
                        return string;
                    }
                } catch (Throwable th) {
                    th = th;
                    cursor = query;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            }
            if (query != null) {
                query.close();
            }
            return null;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getMimeType(File file) {
        String extension = getExtension(file.getName());
        return !TextUtils.isEmpty(extension) ? MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension) : "application/octet-stream";
    }

    static String getExtension(String str) {
        if (str == null) {
            return null;
        }
        int lastIndexOf = str.lastIndexOf(c.a.DOT);
        return lastIndexOf < 0 ? "" : str.substring(lastIndexOf + 1);
    }
}
