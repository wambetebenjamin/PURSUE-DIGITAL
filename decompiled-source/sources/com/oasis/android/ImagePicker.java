package com.oasis.android;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Build;
import android.os.StrictMode;
import android.provider.MediaStore;
import com.facebook.internal.ServerProtocol;
import com.oasis.Logger.Logger;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
/* loaded from: classes9.dex */
public final class ImagePicker {

    /* renamed from: a  reason: collision with root package name */
    private static ImagePickListener f17371a = null;

    /* renamed from: b  reason: collision with root package name */
    private static int f17372b = 0;

    /* renamed from: c  reason: collision with root package name */
    private static int f17373c = 0;

    /* renamed from: d  reason: collision with root package name */
    private static Uri f17374d = null;

    /* renamed from: e  reason: collision with root package name */
    private static boolean f17375e = false;

    /* renamed from: f  reason: collision with root package name */
    private static String f17376f = null;

    /* renamed from: g  reason: collision with root package name */
    static final /* synthetic */ boolean f17377g = true;

    /* loaded from: classes9.dex */
    class a extends com.oasis.android.a {
        a() {
        }

        @Override // com.oasis.android.a, com.oasis.android.ActivityListener
        public void onActivityResult(int i2, int i3, Intent intent) {
            ImagePicker.a(i2, i3, intent);
        }
    }

    static {
        ActivityManager.registerListener(new a());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void a(int i2, int i3, Intent intent) {
        try {
            switch (i2) {
                case 181:
                    c(i3, intent);
                    break;
                case 182:
                    b(i3, intent);
                    break;
                case 183:
                    a(i3, intent);
                    break;
                default:
                    return;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private static void a(int i2, Intent intent) {
        if (-1 != i2) {
            Logger.e("ImagePicker", "ACTIVITY_REQ_CROP_PHOTO failed with code: " + i2);
            a((String) null, (String) null);
            return;
        }
        Bitmap decodeFile = BitmapFactory.decodeFile(f17376f);
        String a2 = FileUtil.a();
        String str = a2 + "avatar_big.jpg";
        String str2 = a2 + "avatar_small.jpg";
        a(decodeFile, f17372b, str);
        a(decodeFile, f17373c, str2);
        FileUtil.a(a2 + "camera/");
        a(str, str2);
    }

    private static void a(Bitmap bitmap, int i2, String str) {
        float width = i2 / bitmap.getWidth();
        Matrix matrix = new Matrix();
        matrix.postScale(width, width);
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        FileOutputStream fileOutputStream = new FileOutputStream(str);
        createBitmap.compress(Bitmap.CompressFormat.JPEG, 80, fileOutputStream);
        fileOutputStream.flush();
        fileOutputStream.close();
    }

    private static void a(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setType("image/*");
        Activity activity = ActivityManager.getActivity();
        List<ResolveInfo> queryIntentActivities = activity.getPackageManager().queryIntentActivities(intent, 0);
        Logger.i("ImagePicker", "cropList.size()" + queryIntentActivities.size());
        if (queryIntentActivities.size() <= 0) {
            Logger.e("ImagePicker", "can't found crop activity");
            a((String) null, (String) null);
            return;
        }
        String str = FileUtil.a() + "camera/";
        File file = new File(str);
        if (!file.exists() && !file.mkdirs()) {
            a((String) null, (String) null);
            return;
        }
        f17376f = str + "imagepicker.jpg";
        Uri fromFile = Uri.fromFile(new File(f17376f));
        Intent intent2 = new Intent();
        ActivityInfo activityInfo = queryIntentActivities.get(0).activityInfo;
        intent2.setClassName(activityInfo.packageName, activityInfo.name);
        intent2.setData(uri);
        intent2.putExtra("crop", ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
        if (Build.MANUFACTURER.equals("HUAWEI")) {
            intent2.putExtra("aspectX", 9998);
            intent2.putExtra("aspectY", 9999);
        } else {
            intent2.putExtra("aspectX", 1);
            intent2.putExtra("aspectY", 1);
        }
        intent2.putExtra("outputX", f17372b);
        intent2.putExtra("outputY", f17372b);
        intent2.putExtra("scale", true);
        intent2.putExtra("circleCrop", false);
        intent2.putExtra("return-data", false);
        intent2.putExtra("output", fromFile);
        intent2.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent2.putExtra("noFaceDetection", true);
        activity.startActivityForResult(intent2, 183);
    }

    private static void a(String str, Integer num, Integer num2) {
        if (!f17377g && f17371a == null) {
            throw new AssertionError();
        }
        ImagePickListener imagePickListener = f17371a;
        f17371a = null;
        imagePickListener.onPickOrigin(str, num.intValue(), num2.intValue());
    }

    private static void a(String str, String str2) {
        if (!f17377g && f17371a == null) {
            throw new AssertionError();
        }
        ImagePickListener imagePickListener = f17371a;
        f17371a = null;
        imagePickListener.onPick(str, str2);
    }

    private static void b(int i2, Intent intent) {
        if (-1 != i2) {
            Logger.e("ImagePicker", "ACTIVITY_REQ_CAMERA not completion: " + i2);
            a((String) null, (String) null);
        } else if (f17375e) {
            b(f17374d);
        } else {
            a(f17374d);
        }
    }

    private static void b(Uri uri) {
        try {
            Bitmap decodeStream = BitmapFactory.decodeStream(ActivityManager.getActivity().getContentResolver().openInputStream(uri));
            int width = decodeStream.getWidth();
            int height = decodeStream.getHeight();
            String a2 = FileUtil.a();
            File file = new File(a2);
            if (!file.exists() && !file.mkdirs()) {
                a((String) null, (Integer) 0, (Integer) 0);
                return;
            }
            String str = a2 + "avatar_origin.jpg";
            FileOutputStream fileOutputStream = new FileOutputStream(str);
            decodeStream.compress(Bitmap.CompressFormat.JPEG, 80, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            a(str, Integer.valueOf(width), Integer.valueOf(height));
        } catch (IOException e2) {
            e2.printStackTrace();
            a((String) null, (Integer) 0, (Integer) 0);
        }
    }

    private static void c(int i2, Intent intent) {
        if (-1 != i2) {
            Logger.e("ImagePicker", "ACTIVITY_REQ_PHOTO not completion: " + i2);
            a((String) null, (String) null);
            return;
        }
        Uri data = intent.getData();
        if (f17375e) {
            b(data);
        } else {
            a(data);
        }
    }

    public static void pickFromCamera(int i2, int i3, ImagePickListener imagePickListener) {
        if (!f17377g && imagePickListener == null) {
            throw new AssertionError();
        }
        if (f17371a != null) {
            imagePickListener.onPick(null, null);
            return;
        }
        try {
            Activity activity = ActivityManager.getActivity();
            if (Build.VERSION.SDK_INT >= 24) {
                StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
                StrictMode.setVmPolicy(builder.build());
                builder.detectFileUriExposure();
            }
            String str = FileUtil.a() + "camera/";
            File file = new File(str);
            if (!file.exists() && !file.mkdir()) {
                imagePickListener.onPick(null, null);
                return;
            }
            f17374d = Uri.fromFile(new File(str + System.currentTimeMillis() + ".jpg"));
            f17371a = imagePickListener;
            f17372b = i2;
            f17373c = i3;
            f17375e = false;
            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
            intent.putExtra("output", f17374d);
            activity.startActivityForResult(intent, 182);
        } catch (Exception e2) {
            Logger.e("ImagePicker", e2.getMessage());
            a((String) null, (String) null);
        }
    }

    public static void pickFromCameraOrigin(ImagePickListener imagePickListener) {
        if (!f17377g && imagePickListener == null) {
            throw new AssertionError();
        }
        if (f17371a != null) {
            imagePickListener.onPickOrigin(null, 0, 0);
            return;
        }
        try {
            Activity activity = ActivityManager.getActivity();
            if (Build.VERSION.SDK_INT >= 24) {
                StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
                StrictMode.setVmPolicy(builder.build());
                builder.detectFileUriExposure();
            }
            String a2 = FileUtil.a();
            File file = new File(a2);
            if (!file.exists() && !file.mkdirs()) {
                a((String) null, (Integer) 0, (Integer) 0);
                return;
            }
            f17374d = Uri.fromFile(new File(a2 + "avatar_origin.jpg"));
            f17371a = imagePickListener;
            f17375e = true;
            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
            intent.putExtra("output", f17374d);
            activity.startActivityForResult(intent, 182);
        } catch (Exception e2) {
            Logger.e("ImagePicker", e2.getMessage());
            a((String) null, (Integer) 0, (Integer) 0);
        }
    }

    public static void pickFromPhoto(int i2, int i3, ImagePickListener imagePickListener) {
        if (!f17377g && imagePickListener == null) {
            throw new AssertionError();
        }
        if (f17371a != null) {
            imagePickListener.onPick(null, null);
            return;
        }
        f17371a = imagePickListener;
        f17372b = i2;
        f17373c = i3;
        f17375e = false;
        try {
            Activity activity = ActivityManager.getActivity();
            Intent intent = new Intent("android.intent.action.PICK", (Uri) null);
            intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
            activity.startActivityForResult(intent, 181);
        } catch (Exception e2) {
            Logger.e("ImagePicker", e2.getMessage());
            a((String) null, (String) null);
        }
    }

    public static void pickFromPhotoOrigin(ImagePickListener imagePickListener) {
        if (!f17377g && imagePickListener == null) {
            throw new AssertionError();
        }
        if (f17371a != null) {
            imagePickListener.onPickOrigin(null, 0, 0);
            return;
        }
        f17371a = imagePickListener;
        f17375e = true;
        try {
            Activity activity = ActivityManager.getActivity();
            Intent intent = new Intent("android.intent.action.PICK", (Uri) null);
            intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
            activity.startActivityForResult(intent, 181);
        } catch (Exception e2) {
            Logger.e("ImagePicker", e2.getMessage());
            a((String) null, (Integer) 0, (Integer) 0);
        }
    }
}
