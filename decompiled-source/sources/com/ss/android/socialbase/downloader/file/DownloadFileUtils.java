package com.ss.android.socialbase.downloader.file;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Pair;
import android.util.SparseArray;
import com.bytedance.librarian.c;
import com.ss.android.socialbase.downloader.downloader.DownloadComponentManager;
import com.ss.android.socialbase.downloader.utils.DownloadUtils;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
/* loaded from: classes9.dex */
public class DownloadFileUtils {
    private static SparseArray<MediaItem> MEDIA_STORE_DATA = null;
    public static final String MODE_READ = "r";
    public static final String MODE_WRITE = "w";

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes9.dex */
    public static class MediaItem {
        public String columnId;
        public Uri contentUri;
        public String contentUriString;

        MediaItem() {
        }

        static MediaItem build(Uri uri, String str) {
            MediaItem mediaItem = new MediaItem();
            mediaItem.contentUri = uri;
            mediaItem.contentUriString = uri.toString();
            mediaItem.columnId = str;
            return mediaItem;
        }
    }

    static {
        try {
            MEDIA_STORE_DATA = new SparseArray<>();
            if (isAtLeastAndroidQ()) {
                MEDIA_STORE_DATA.put(2, MediaItem.build(MediaStore.Images.Media.getContentUri("external_primary"), "_id"));
                MEDIA_STORE_DATA.put(3, MediaItem.build(MediaStore.Video.Media.getContentUri("external_primary"), "_id"));
                MEDIA_STORE_DATA.put(4, MediaItem.build(MediaStore.Audio.Media.getContentUri("external_primary"), "_id"));
                MEDIA_STORE_DATA.put(5, MediaItem.build(MediaStore.Downloads.EXTERNAL_CONTENT_URI, "_id"));
            } else {
                MEDIA_STORE_DATA.put(2, MediaItem.build(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "_id"));
                MEDIA_STORE_DATA.put(3, MediaItem.build(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, "_id"));
                MEDIA_STORE_DATA.put(4, MediaItem.build(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, "_id"));
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static boolean isMediaUri(String str) {
        return getTypeByUri(str) > 1;
    }

    public static int getTypeByUri(String str) {
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        if (str.startsWith("content")) {
            for (int i2 = 0; i2 < MEDIA_STORE_DATA.size(); i2++) {
                int keyAt = MEDIA_STORE_DATA.keyAt(i2);
                if (str.startsWith(MEDIA_STORE_DATA.get(keyAt).contentUriString)) {
                    return keyAt;
                }
            }
            return -1;
        }
        return 1;
    }

    public static void checkUri(Uri uri, int i2) {
        MediaItem mediaItem = MEDIA_STORE_DATA.get(i2);
        if (mediaItem == null) {
            throw new Throwable("Fail to check Uri, type error :" + i2);
        }
        String str = mediaItem.columnId + " = ?";
        String[] strArr = {uri.getLastPathSegment()};
        Cursor cursor = null;
        try {
            cursor = DownloadComponentManager.getAppContext().getContentResolver().query(mediaItem.contentUri, null, str, strArr, null);
            if (cursor == null || !cursor.moveToFirst()) {
                throw new Throwable("Fail to check Uri with cursor.");
            }
            DownloadUtils.safeClose(cursor);
        } catch (Throwable th) {
            DownloadUtils.safeClose(cursor);
            throw th;
        }
    }

    public static void deleteUri(Uri uri) {
        DownloadComponentManager.getAppContext().getContentResolver().delete(uri, null, null);
    }

    public static ParcelFileDescriptor getParcelFileDescriptor(Uri uri, String str) {
        if (uri == null) {
            throw new IOException("Fail to get ParcelFileDescriptor, uri is null");
        }
        try {
            ParcelFileDescriptor openFileDescriptor = DownloadComponentManager.getAppContext().getContentResolver().openFileDescriptor(uri, str);
            if (openFileDescriptor != null) {
                return openFileDescriptor;
            }
            throw new IOException("Fail to get ParcelFileDescriptor, fileDescriptor is null");
        } catch (Exception e2) {
            e2.printStackTrace();
            throw new IOException("Fail to get ParcelFileDescriptor", e2);
        }
    }

    public static FileDescriptor getFileDescriptor(Uri uri, String str) {
        ParcelFileDescriptor parcelFileDescriptor = getParcelFileDescriptor(uri, str);
        if (parcelFileDescriptor != null) {
            return parcelFileDescriptor.getFileDescriptor();
        }
        throw new IOException("Fail to get FileDescriptor, ParcelFileDescriptor is null");
    }

    public static long getLength(Uri uri) {
        ParcelFileDescriptor parcelFileDescriptor = null;
        try {
            parcelFileDescriptor = getParcelFileDescriptor(uri, MODE_READ);
            long statSize = parcelFileDescriptor.getStatSize();
            DownloadUtils.safeClose(parcelFileDescriptor);
            return statSize;
        } catch (Throwable unused) {
            DownloadUtils.safeClose(parcelFileDescriptor);
            return -1L;
        }
    }

    public static boolean exists(Uri uri) {
        return getLength(uri) > 0;
    }

    public static boolean emptyFile(FileOutputStream fileOutputStream) {
        try {
            fileOutputStream.write(new byte[0]);
            fileOutputStream.close();
            return true;
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    public static boolean existsAndNotEmpty(DownloadFile downloadFile) {
        return downloadFile.exists() && downloadFile.length() > 0;
    }

    public static boolean isAtLeastAndroidQ() {
        return Build.VERSION.SDK_INT >= 29;
    }

    public static Uri getUriAtLeastQ(Uri uri, String str, String str2, String str3) {
        Uri queryUriAtLeastQ = queryUriAtLeastQ(uri, str, str2, str3);
        return queryUriAtLeastQ == null ? createUriAtLeastQ(uri, str, str2, str3) : queryUriAtLeastQ;
    }

    public static Uri queryUriAtLeastQ(Uri uri, String str, String str2, String str3) {
        Cursor cursor = null;
        if (uri == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return null;
        }
        if (str.startsWith(c.a.SEPARATOR)) {
            str = str.substring(1);
        }
        if (!str.endsWith(c.a.SEPARATOR)) {
            str = str + c.a.SEPARATOR;
        }
        Context appContext = DownloadComponentManager.getAppContext();
        try {
            String str4 = "relative_path = ? AND _display_name = ?" + (TextUtils.isEmpty(str3) ? "" : " AND mime_type = ?");
            String[] strArr = TextUtils.isEmpty(str3) ? new String[]{str, str2} : new String[]{str, str2, str3};
            Bundle bundle = new Bundle();
            bundle.putString("android:query-arg-sql-selection", str4);
            bundle.putStringArray("android:query-arg-sql-selection-args", strArr);
            bundle.putString("android:query-arg-sql-sort-order", "_id DESC");
            Cursor query = appContext.getContentResolver().query(MediaStore.setIncludePending(uri), new String[]{"_id"}, bundle, null);
            if (query != null) {
                try {
                    if (query.moveToFirst()) {
                        Uri withAppendedId = ContentUris.withAppendedId(uri, query.getLong(query.getColumnIndexOrThrow("_id")));
                        DownloadUtils.safeClose(query);
                        return withAppendedId;
                    }
                } catch (Throwable th) {
                    th = th;
                    cursor = query;
                    DownloadUtils.safeClose(cursor);
                    throw th;
                }
            }
            DownloadUtils.safeClose(query);
            return null;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static Uri createUriAtLeastQ(Uri uri, String str, String str2, String str3) {
        return DownloadComponentManager.getAppContext().getContentResolver().insert(uri, createContentValues(str, str2, str3));
    }

    public static ContentValues createContentValues(String str, String str2, String str3) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("relative_path", str);
        contentValues.put("_display_name", str2);
        contentValues.put("datetaken", Long.valueOf(System.currentTimeMillis()));
        if (!TextUtils.isEmpty(str3)) {
            contentValues.put("mime_type", str3);
        }
        return contentValues;
    }

    public static String getFilePathFromUri(Uri uri) {
        String[] strArr = {"_data"};
        Cursor query = DownloadComponentManager.getAppContext().getContentResolver().query(uri, strArr, null, null, null);
        if (query != null) {
            query.moveToFirst();
            String string = query.getString(query.getColumnIndex(strArr[0]));
            DownloadUtils.safeClose(query);
            return string;
        }
        return null;
    }

    public static Pair<String, String> getParentAndNameFromUri(Uri uri) {
        String filePathFromUri = getFilePathFromUri(uri);
        if (TextUtils.isEmpty(filePathFromUri)) {
            return null;
        }
        File file = new File(filePathFromUri);
        return new Pair<>(file.getParent(), file.getName());
    }

    public static boolean isScopedStorage() {
        int i2 = DownloadComponentManager.getAppContext().getApplicationInfo().targetSdkVersion;
        if (Build.VERSION.SDK_INT < 29) {
            return false;
        }
        return (i2 == 29 && !Environment.isExternalStorageLegacy()) || i2 > 29;
    }
}
