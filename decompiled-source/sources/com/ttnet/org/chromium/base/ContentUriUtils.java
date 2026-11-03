package com.ttnet.org.chromium.base;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.provider.DocumentsContract;
import android.text.TextUtils;
import android.webkit.MimeTypeMap;
import com.bytedance.librarian.c;
import com.ss.android.socialbase.downloader.file.DownloadFileUtils;
import java.io.File;
import java.io.IOException;
/* loaded from: classes9.dex */
public abstract class ContentUriUtils {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String TAG = "ContentUriUtils";
    private static FileProviderUtil sFileProviderUtil;
    private static final Object sLock = new Object();

    /* loaded from: classes9.dex */
    public interface FileProviderUtil {
        Uri getContentUriFromFile(File file);
    }

    private ContentUriUtils() {
    }

    public static void setFileProviderUtil(FileProviderUtil fileProviderUtil) {
        synchronized (sLock) {
            sFileProviderUtil = fileProviderUtil;
        }
    }

    public static Uri getContentUriFromFile(File file) {
        synchronized (sLock) {
            FileProviderUtil fileProviderUtil = sFileProviderUtil;
            if (fileProviderUtil != null) {
                return fileProviderUtil.getContentUriFromFile(file);
            }
            return null;
        }
    }

    public static int openContentUriForRead(String str) {
        AssetFileDescriptor assetFileDescriptor = getAssetFileDescriptor(str);
        if (assetFileDescriptor != null) {
            return assetFileDescriptor.getParcelFileDescriptor().detachFd();
        }
        return -1;
    }

    public static boolean contentUriExists(String str) {
        AssetFileDescriptor assetFileDescriptor = getAssetFileDescriptor(str);
        boolean z = assetFileDescriptor != null;
        if (assetFileDescriptor != null) {
            try {
                assetFileDescriptor.close();
            } catch (IOException unused) {
            }
        }
        return z;
    }

    public static String getMimeType(String str) {
        ContentResolver contentResolver = ContextUtils.getApplicationContext().getContentResolver();
        Uri parse = Uri.parse(str);
        if (isVirtualDocument(parse)) {
            String[] streamTypes = contentResolver.getStreamTypes(parse, "*/*");
            if (streamTypes == null || streamTypes.length <= 0) {
                return null;
            }
            return streamTypes[0];
        }
        return contentResolver.getType(parse);
    }

    private static AssetFileDescriptor getAssetFileDescriptor(String str) {
        ContentResolver contentResolver = ContextUtils.getApplicationContext().getContentResolver();
        Uri parse = Uri.parse(str);
        try {
            if (isVirtualDocument(parse)) {
                String[] streamTypes = contentResolver.getStreamTypes(parse, "*/*");
                if (streamTypes != null && streamTypes.length > 0) {
                    AssetFileDescriptor openTypedAssetFileDescriptor = contentResolver.openTypedAssetFileDescriptor(parse, streamTypes[0], null);
                    if (openTypedAssetFileDescriptor == null || openTypedAssetFileDescriptor.getStartOffset() == 0) {
                        return openTypedAssetFileDescriptor;
                    }
                    try {
                        openTypedAssetFileDescriptor.close();
                    } catch (IOException unused) {
                    }
                    throw new SecurityException("Cannot open files with non-zero offset type.");
                }
            } else {
                ParcelFileDescriptor openFileDescriptor = contentResolver.openFileDescriptor(parse, DownloadFileUtils.MODE_READ);
                if (openFileDescriptor != null) {
                    return new AssetFileDescriptor(openFileDescriptor, 0L, -1L);
                }
            }
        } catch (Exception e2) {
            Log.w(TAG, "Cannot open content uri: %s", str, e2);
        }
        return null;
    }

    public static String getDisplayName(Uri uri, Context context, String str) {
        Cursor query;
        String[] streamTypes;
        String extensionFromMimeType;
        if (uri == null) {
            return "";
        }
        ContentResolver contentResolver = context.getContentResolver();
        try {
            query = contentResolver.query(uri, null, null, null, null);
        } catch (NullPointerException unused) {
        }
        if (query == null || query.getCount() < 1) {
            if (query != null) {
                query.close();
            }
            return "";
        }
        query.moveToFirst();
        int columnIndex = query.getColumnIndex(str);
        if (columnIndex == -1) {
            if (query != null) {
                query.close();
            }
            return "";
        }
        String string = query.getString(columnIndex);
        if (hasVirtualFlag(query) && (streamTypes = contentResolver.getStreamTypes(uri, "*/*")) != null && streamTypes.length > 0 && (extensionFromMimeType = MimeTypeMap.getSingleton().getExtensionFromMimeType(streamTypes[0])) != null) {
            string = string + c.a.DOT + extensionFromMimeType;
        }
        if (query != null) {
            query.close();
        }
        return string;
    }

    public static String maybeGetDisplayName(String str) {
        try {
            String displayName = getDisplayName(Uri.parse(str), ContextUtils.getApplicationContext(), "_display_name");
            if (TextUtils.isEmpty(displayName)) {
                return null;
            }
            return displayName;
        } catch (Exception e2) {
            Log.w(TAG, "Cannot open content uri: %s", str, e2);
            return null;
        }
    }

    private static boolean isVirtualDocument(Uri uri) {
        Cursor query;
        if (Build.VERSION.SDK_INT >= 19 && uri != null && DocumentsContract.isDocumentUri(ContextUtils.getApplicationContext(), uri)) {
            try {
                query = ContextUtils.getApplicationContext().getContentResolver().query(uri, null, null, null, null);
            } catch (NullPointerException unused) {
            }
            if (query == null || query.getCount() < 1) {
                if (query != null) {
                    query.close();
                }
                return false;
            }
            query.moveToFirst();
            boolean hasVirtualFlag = hasVirtualFlag(query);
            if (query != null) {
                query.close();
            }
            return hasVirtualFlag;
        }
        return false;
    }

    private static boolean hasVirtualFlag(Cursor cursor) {
        int columnIndex;
        return Build.VERSION.SDK_INT >= 24 && (columnIndex = cursor.getColumnIndex("flags")) > -1 && (cursor.getLong(columnIndex) & 512) != 0;
    }

    public static boolean isContentUri(String str) {
        Uri parse;
        return (str == null || (parse = Uri.parse(str)) == null || !"content".equals(parse.getScheme())) ? false : true;
    }

    public static boolean delete(String str) {
        return ContextUtils.getApplicationContext().getContentResolver().delete(Uri.parse(str), null, null) > 0;
    }

    public static String getContentUriFromFilePath(String str) {
        try {
            Uri contentUriFromFile = getContentUriFromFile(new File(str));
            if (contentUriFromFile != null) {
                return contentUriFromFile.toString();
            }
            return null;
        } catch (IllegalArgumentException e2) {
            Log.e(TAG, "Cannot retrieve content uri from file: %s", str, e2);
            return null;
        }
    }
}
