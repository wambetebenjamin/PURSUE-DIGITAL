package d;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.webkit.MimeTypeMap;
import com.bytedance.librarian.c;
import com.oasis.Logger.Logger;
import com.oasis.android.DiskTool;
import com.oasis.photoalbum.SaveListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Locale;
/* loaded from: classes10.dex */
public class a {
    public static void a(Context context, String str, String str2, SaveListener saveListener) {
        Uri uri;
        File file;
        File file2 = new File(str);
        if (!a(file2)) {
            saveListener.onSaveResult(false);
            return;
        }
        int lastIndexOf = str.lastIndexOf(47);
        int lastIndexOf2 = str.lastIndexOf(46);
        String substring = lastIndexOf >= 0 ? str.substring(lastIndexOf + 1) : str;
        String substring2 = lastIndexOf2 >= 0 ? str.substring(lastIndexOf2 + 1) : "";
        String mimeTypeFromExtension = substring2.length() > 0 ? MimeTypeMap.getSingleton().getMimeTypeFromExtension(substring2.toLowerCase(Locale.ENGLISH)) : null;
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", substring);
        contentValues.put("_display_name", substring);
        contentValues.put("date_added", Long.valueOf(System.currentTimeMillis() / 1000));
        if (mimeTypeFromExtension != null && mimeTypeFromExtension.length() > 0) {
            contentValues.put("mime_type", mimeTypeFromExtension);
        }
        if (mimeTypeFromExtension.toLowerCase().contains("image")) {
            uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        } else if (mimeTypeFromExtension.toLowerCase().contains("video")) {
            uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        } else if (!mimeTypeFromExtension.toLowerCase().contains("audio")) {
            Logger.e("SaveMediaFile", "wrong mime type");
            saveListener.onSaveResult(false);
            return;
        } else {
            uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        }
        if (Build.VERSION.SDK_INT >= 29) {
            contentValues.put("relative_path", "DCIM/" + str2);
            contentValues.put("datetaken", Long.valueOf(System.currentTimeMillis()));
            contentValues.put("is_pending", Boolean.TRUE);
            Uri insert = context.getContentResolver().insert(uri, contentValues);
            if (insert != null) {
                try {
                    if (a(file2, context.getContentResolver().openOutputStream(insert))) {
                        contentValues.put("is_pending", Boolean.FALSE);
                        context.getContentResolver().update(insert, contentValues, null, null);
                        saveListener.onSaveResult(true);
                    } else {
                        saveListener.onSaveResult(false);
                    }
                    return;
                } catch (Exception e2) {
                    Logger.e("Unity", "Exception:", e2);
                    saveListener.onSaveResult(false);
                    context.getContentResolver().delete(insert, null, null);
                    return;
                }
            }
            return;
        }
        File file3 = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), str2);
        file3.mkdirs();
        String substring3 = substring2.length() > 0 ? substring.substring(0, (substring.length() - substring2.length()) - 1) : substring;
        int i2 = 1;
        while (true) {
            file = new File(file3, substring);
            int i3 = i2 + 1;
            substring = substring3 + i2;
            if (substring2.length() > 0) {
                substring = substring + c.a.DOT + substring2;
            }
            if (!file.exists()) {
                try {
                    break;
                } catch (Exception e3) {
                    saveListener.onSaveResult(false);
                    Logger.e("Unity", "Exception:", e3);
                    return;
                }
            }
            i2 = i3;
        }
        if (a(file2, new FileOutputStream(file))) {
            contentValues.put("_data", file.getAbsolutePath());
            context.getContentResolver().insert(uri, contentValues);
            Logger.d("Unity", "Saved media to: " + file.getPath());
            Intent intent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
            intent.setData(Uri.fromFile(file));
            context.sendBroadcast(intent);
            saveListener.onSaveResult(true);
        } else {
            saveListener.onSaveResult(false);
        }
    }

    public static boolean a(File file) {
        if (!file.exists() || !file.isFile()) {
            Logger.e("SaveMeiaFile", "file doesn't exist or is not a file");
            return false;
        }
        long freeSpace = DiskTool.getFreeSpace();
        long length = file.length();
        Logger.i("SaveMediaFile", "disk available: " + freeSpace + " file size: " + length);
        return freeSpace > length + 5242880;
    }

    public static boolean a(File file, OutputStream outputStream) {
        try {
            if (a(file)) {
                try {
                    FileInputStream fileInputStream = new FileInputStream(file);
                    try {
                        byte[] bArr = new byte[1024];
                        while (true) {
                            int read = fileInputStream.read(bArr);
                            if (read > 0) {
                                outputStream.write(bArr, 0, read);
                            } else {
                                try {
                                    break;
                                } catch (Exception e2) {
                                }
                            }
                        }
                        try {
                            outputStream.close();
                            return true;
                        } catch (Exception e3) {
                            Logger.e("Unity", "Exception:", e3);
                            return true;
                        }
                    } finally {
                        try {
                            fileInputStream.close();
                        } catch (Exception e22) {
                            Logger.e("Unity", "Exception:", e22);
                        }
                    }
                } catch (Exception e4) {
                    Logger.e("Unity", "Exception:", e4);
                    try {
                        outputStream.close();
                    } catch (Exception e5) {
                        Logger.e("Unity", "Exception:", e5);
                    }
                    return false;
                }
            }
            return false;
        } catch (Throwable th) {
            try {
                outputStream.close();
            } catch (Exception e6) {
                Logger.e("Unity", "Exception:", e6);
            }
            throw th;
        }
    }
}
