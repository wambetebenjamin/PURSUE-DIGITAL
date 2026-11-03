package com.bytedance.react.framework.utils;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import androidx.core.content.FileProvider;
import androidx.core.os.EnvironmentCompat;
import com.bytedance.react.framework.RNUtil;
import com.bytedance.react.framework.core.BRNManager;
import com.facebook.react.bridge.Callback;
import com.ss.android.socialbase.downloader.utils.DownloadExpSwitchCode;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
/* loaded from: classes2.dex */
public class MultiMediaModule {
    public static final int CAMERA_REQUEST_CODE = 10091;
    public static final int OPEN_GALLERY_PIC_CODE = 10092;
    private Callback mCallback;
    private File photoFile = null;
    private Uri photoUri = null;

    public MultiMediaModule(Callback callback) {
        this.mCallback = callback;
    }

    public void onActivityResult(Activity activity, int i2, int i3, Intent intent) {
        Callback callback;
        Callback callback2;
        if (i3 != 0) {
            if (this.mCallback != null) {
                if (i2 == 10091) {
                    File file = this.photoFile;
                    HashMap formatMap = formatMap(activity, this.photoUri, file != null ? file.getAbsolutePath() : null);
                    if (formatMap == null || (callback2 = this.mCallback) == null) {
                        return;
                    }
                    callback2.invoke(RNUtil.getSuccessRsp(formatMap));
                    this.mCallback = null;
                    return;
                } else if (i2 == 10092) {
                    Uri data = intent.getData();
                    if (data != null) {
                        HashMap formatMap2 = formatMap(activity, data, null);
                        if (formatMap2 == null || (callback = this.mCallback) == null) {
                            return;
                        }
                        callback.invoke(RNUtil.getSuccessRsp(formatMap2));
                        this.mCallback = null;
                        return;
                    }
                    this.mCallback.invoke(RNUtil.getErrorRsp());
                    this.mCallback = null;
                    return;
                } else {
                    return;
                }
            }
            return;
        }
        Callback callback3 = this.mCallback;
        if (callback3 != null) {
            callback3.invoke(RNUtil.getErrorRsp());
            this.mCallback = null;
        }
    }

    private HashMap formatMap(Activity activity, Uri uri, String str) {
        HashMap hashMap = new HashMap();
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            options.inDither = true;
            if (!TextUtils.isEmpty(str)) {
                if (uri != null) {
                    BitmapFactory.decodeFile(str, options);
                }
                return hashMap;
            }
            InputStream openInputStream = activity.getContentResolver().openInputStream(uri);
            BitmapFactory.decodeStream(openInputStream, null, options);
            openInputStream.close();
            int i2 = options.outHeight;
            int i3 = options.outWidth;
            String str2 = options.outMimeType;
            if (TextUtils.isEmpty(str)) {
                hashMap.put("path", uri.toString());
            } else if (uri != null) {
                hashMap.put("path", str);
            }
            hashMap.put("height", Integer.valueOf(i2));
            hashMap.put("width", Integer.valueOf(i3));
            hashMap.put("mimetype", str2);
            return hashMap;
        } catch (FileNotFoundException unused) {
            this.mCallback.invoke(RNUtil.getErrorRsp());
            this.mCallback = null;
            return null;
        } catch (IOException unused2) {
            this.mCallback.invoke(RNUtil.getErrorRsp());
            this.mCallback = null;
            return null;
        }
    }

    public boolean openCamera(Activity activity, int i2, boolean z) {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        if (intent.resolveActivity(activity.getPackageManager()) != null) {
            if (Build.VERSION.SDK_INT >= 29) {
                this.photoUri = createImageUri(activity);
            } else {
                File createFile = createFile(activity, !z);
                this.photoFile = createFile;
                if (createFile != null) {
                    if (Build.VERSION.SDK_INT >= 24) {
                        String appID = BRNManager.newInstance().getAppID();
                        String definedPackageName = BRNManager.newInstance().getDefinedPackageName();
                        if (TextUtils.isEmpty(definedPackageName)) {
                            this.photoUri = FileProvider.getUriForFile(activity, "com.bytedance.rn.brnprovider_" + appID, this.photoFile);
                        } else {
                            this.photoUri = FileProvider.getUriForFile(activity, definedPackageName + ".rn.brnprovider", this.photoFile);
                        }
                    } else {
                        this.photoUri = Uri.fromFile(this.photoFile);
                    }
                }
            }
            System.out.println("photoUri = " + this.photoUri);
            if (this.photoUri != null) {
                if (Build.VERSION.SDK_INT < 21) {
                    for (ResolveInfo resolveInfo : activity.getPackageManager().queryIntentActivities(intent, 65536)) {
                        activity.grantUriPermission(resolveInfo.activityInfo.packageName, this.photoUri, 3);
                    }
                }
                intent.putExtra("output", this.photoUri);
                intent.addFlags(2);
                activity.startActivityForResult(intent, i2);
            }
            return true;
        }
        return false;
    }

    private Uri createImageUri(Activity activity) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("_display_name", System.currentTimeMillis() + "");
        if (Build.VERSION.SDK_INT >= 29) {
            contentValues.put("relative_path", "Pictures/preventpro");
        }
        contentValues.put("mime_type", "image/JPEG");
        return activity.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
    }

    private File createImageFile(Activity activity) {
        String str = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date()) + ".jpg";
        File file = new File(activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES).getAbsolutePath() + File.separator + "Pictures" + File.separator + "temp");
        if (!file.exists()) {
            file.mkdirs();
        }
        File file2 = new File(file, str);
        if ("mounted".equals(EnvironmentCompat.getStorageState(file2))) {
            return file2;
        }
        return null;
    }

    private File createFile(Activity activity, boolean z) {
        File file;
        File externalFilesDir = activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        if (!externalFilesDir.exists()) {
            externalFilesDir.mkdir();
        }
        File file2 = new File(externalFilesDir, "temp");
        if (!file2.exists()) {
            file2.mkdir();
        }
        if (!z) {
            file = new File(file2, "TEMP_Picture.jpg");
        } else {
            file = new File(file2, new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date()) + ".jpg");
        }
        if (file.exists()) {
            file.delete();
        }
        return file;
    }

    public void openGallery(Activity activity, int i2) {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setFlags(DownloadExpSwitchCode.BACK_BUGFIX_SIGBUS);
        intent.putExtra("android.intent.extra.ALLOW_MULTIPLE", false);
        intent.addCategory("android.intent.category.OPENABLE");
        intent.setType("image/*");
        activity.startActivityForResult(Intent.createChooser(intent, "Pick an image"), i2);
    }
}
