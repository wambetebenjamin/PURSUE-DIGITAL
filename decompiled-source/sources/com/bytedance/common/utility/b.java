package com.bytedance.common.utility;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import com.bytedance.librarian.c;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
/* compiled from: BitmapUtils.java */
/* loaded from: classes2.dex */
public final class b {

    /* renamed from: a  reason: collision with root package name */
    private static final String f5200a = "BitmapUtils";

    /* renamed from: b  reason: collision with root package name */
    private static final int f5201b = 4000;

    /* renamed from: c  reason: collision with root package name */
    private static final int f5202c = 150;

    /* renamed from: d  reason: collision with root package name */
    private static final int f5203d = 8192;

    /* renamed from: e  reason: collision with root package name */
    private static final int f5204e = 4;

    /* renamed from: f  reason: collision with root package name */
    private static final int f5205f = 100;

    /* renamed from: g  reason: collision with root package name */
    private static final int f5206g = 270;

    /* renamed from: h  reason: collision with root package name */
    private static final int f5207h = 180;

    /* renamed from: i  reason: collision with root package name */
    private static final int f5208i = 90;

    /* renamed from: j  reason: collision with root package name */
    private static final int f5209j = 50;
    private static final int k = 80;
    private static final int l = 100;

    private b() {
    }

    public static Bitmap decodeBitmap(byte[] bArr, int i2, int i3) {
        if (bArr != null && i2 >= 1 && i3 >= 1) {
            try {
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
                int i4 = options.outWidth;
                int i5 = options.outHeight;
                int i6 = 1;
                while (true) {
                    if (i4 / 2 < i2 && i5 / 2 < i3) {
                        BitmapFactory.Options options2 = new BitmapFactory.Options();
                        options2.inSampleSize = i6;
                        options2.inPurgeable = true;
                        options2.inPreferredConfig = Bitmap.Config.RGB_565;
                        return BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options2);
                    }
                    i4 /= 2;
                    i5 /= 2;
                    i6 *= 2;
                }
            } catch (Exception e2) {
                Logger.d(f5200a, "decode image error: " + e2);
            }
        }
        return null;
    }

    public static Bitmap decodeBitmap(File file, int i2, int i3, Bitmap.Config config) {
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2 = null;
        if (file != null && i2 > 0 && i3 > 0) {
            try {
                if (!file.isFile()) {
                    return null;
                }
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                fileInputStream = new FileInputStream(file);
                try {
                    BitmapFactory.decodeStream(fileInputStream, null, options);
                    fileInputStream.close();
                    int i4 = options.outWidth;
                    int i5 = options.outHeight;
                    int i6 = 1;
                    while (true) {
                        if (i4 / 2 < i2 && i5 / 2 < i3) {
                            break;
                        }
                        i4 /= 2;
                        i5 /= 2;
                        i6 *= 2;
                    }
                    BitmapFactory.Options options2 = new BitmapFactory.Options();
                    options2.inSampleSize = i6;
                    options2.inPurgeable = true;
                    if (config != null) {
                        options2.inPreferredConfig = config;
                    } else {
                        options2.inPreferredConfig = Bitmap.Config.RGB_565;
                    }
                    FileInputStream fileInputStream3 = new FileInputStream(file);
                    try {
                        Bitmap decodeStream = BitmapFactory.decodeStream(fileInputStream3, null, options2);
                        fileInputStream3.close();
                        return decodeStream;
                    } catch (Exception unused) {
                        fileInputStream = fileInputStream3;
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (Exception unused2) {
                            }
                        }
                        return null;
                    } catch (Throwable th) {
                        th = th;
                        fileInputStream2 = fileInputStream3;
                        if (fileInputStream2 != null) {
                            try {
                                fileInputStream2.close();
                            } catch (Exception unused3) {
                            }
                        }
                        throw th;
                    }
                } catch (Exception unused4) {
                } catch (Throwable th2) {
                    th = th2;
                    fileInputStream2 = fileInputStream;
                }
            } catch (Exception unused5) {
                fileInputStream = null;
            } catch (Throwable th3) {
                th = th3;
            }
        }
        return null;
    }

    public static Bitmap decodeBitmap(File file, int i2) {
        return decodeBitmap(file, i2, false);
    }

    public static Bitmap decodeBitmap(File file, int i2, boolean z) {
        BitmapFactory.Options options;
        Bitmap bitmap = null;
        try {
            options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            FileInputStream fileInputStream = new FileInputStream(file);
            BitmapFactory.decodeStream(fileInputStream, null, options);
            fileInputStream.close();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (options.outWidth > 0 && options.outHeight > 0) {
            int i3 = 1;
            while (options.outWidth >= i2 * 2 && options.outHeight >= 4000) {
                options.outWidth /= 2;
                options.outHeight /= 2;
                i3 *= 2;
            }
            BitmapFactory.Options options2 = new BitmapFactory.Options();
            options2.inSampleSize = i3;
            options2.inPurgeable = true;
            options2.inPreferredConfig = z ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565;
            FileInputStream fileInputStream2 = new FileInputStream(file);
            bitmap = BitmapFactory.decodeStream(fileInputStream2, null, options2);
            fileInputStream2.close();
            if (bitmap == null) {
                file.delete();
            }
            return bitmap;
        }
        file.delete();
        return null;
    }

    public static Bitmap decodeBitmap(File file) {
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2 = null;
        if (!file.exists()) {
            return null;
        }
        try {
            fileInputStream = new FileInputStream(file);
            try {
                try {
                    Bitmap decodeStream = BitmapFactory.decodeStream(fileInputStream);
                    try {
                        fileInputStream.close();
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                    return decodeStream;
                } catch (Exception e3) {
                    e = e3;
                    e.printStackTrace();
                    try {
                        fileInputStream.close();
                    } catch (Exception e4) {
                        e4.printStackTrace();
                    }
                    return null;
                }
            } catch (Throwable th) {
                th = th;
                fileInputStream2 = fileInputStream;
                try {
                    fileInputStream2.close();
                } catch (Exception e5) {
                    e5.printStackTrace();
                }
                throw th;
            }
        } catch (Exception e6) {
            e = e6;
            fileInputStream = null;
        } catch (Throwable th2) {
            th = th2;
            fileInputStream2.close();
            throw th;
        }
    }

    public static boolean saveBitmapToSD(Bitmap bitmap, String str, String str2) {
        FileOutputStream fileOutputStream;
        if ("mounted".equals(Environment.getExternalStorageState())) {
            File file = new File(str);
            if (!file.exists()) {
                file.mkdirs();
            }
            File file2 = new File(file + c.a.SEPARATOR + str2);
            if (!file2.exists()) {
                try {
                    file2.createNewFile();
                } catch (Exception e2) {
                    e2.printStackTrace();
                    return false;
                }
            }
            FileOutputStream fileOutputStream2 = null;
            try {
                try {
                    fileOutputStream = new FileOutputStream(file2);
                } catch (Throwable th) {
                    th = th;
                }
            } catch (Exception e3) {
                e = e3;
            }
            try {
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
                fileOutputStream.flush();
                try {
                    fileOutputStream.close();
                    return true;
                } catch (Exception e4) {
                    e4.printStackTrace();
                    return true;
                }
            } catch (Exception e5) {
                e = e5;
                fileOutputStream2 = fileOutputStream;
                e.printStackTrace();
                try {
                    fileOutputStream2.close();
                } catch (Exception e6) {
                    e6.printStackTrace();
                }
                return false;
            } catch (Throwable th2) {
                th = th2;
                fileOutputStream2 = fileOutputStream;
                try {
                    fileOutputStream2.close();
                } catch (Exception e7) {
                    e7.printStackTrace();
                }
                throw th;
            }
        }
        return false;
    }

    public static boolean saveImageData(byte[] bArr, String str, String str2) {
        FileOutputStream fileOutputStream = null;
        try {
            try {
                File file = new File(str);
                if (file.exists() || file.mkdirs() || file.exists()) {
                    FileOutputStream fileOutputStream2 = new FileOutputStream(new File(file, str2));
                    try {
                        fileOutputStream2.write(bArr);
                        fileOutputStream2.flush();
                        fileOutputStream2.close();
                        return true;
                    } catch (Exception e2) {
                        e = e2;
                        fileOutputStream = fileOutputStream2;
                        Logger.d(f5200a, "save image exception " + e);
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (Exception unused) {
                            }
                        }
                        return false;
                    } catch (Throwable th) {
                        th = th;
                        fileOutputStream = fileOutputStream2;
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (Exception unused2) {
                            }
                        }
                        throw th;
                    }
                }
                return false;
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception e3) {
            e = e3;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v12 */
    /* JADX WARN: Type inference failed for: r1v15 */
    /* JADX WARN: Type inference failed for: r1v16 */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r1v18 */
    /* JADX WARN: Type inference failed for: r1v8 */
    @Deprecated
    public static void rotateImage(String str) {
        FileOutputStream fileOutputStream;
        int i2 = -1;
        try {
            int attributeInt = new ExifInterface(str).getAttributeInt(androidx.exifinterface.media.ExifInterface.TAG_ORIENTATION, 1);
            if (attributeInt == 3) {
                i2 = 180;
            } else if (attributeInt == 6) {
                i2 = 90;
            } else if (attributeInt == 8) {
                i2 = 270;
            }
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        if (i2 > 0) {
            Bitmap decodeFile = BitmapFactory.decodeFile(str);
            Matrix matrix = new Matrix();
            matrix.postRotate(i2);
            Bitmap createBitmap = Bitmap.createBitmap(decodeFile, 0, 0, decodeFile.getWidth(), decodeFile.getHeight(), matrix, true);
            decodeFile.recycle();
            FileOutputStream fileOutputStream2 = null;
            try {
                try {
                    fileOutputStream = new FileOutputStream(str);
                } catch (Throwable th) {
                    th = th;
                }
            } catch (FileNotFoundException e3) {
                e = e3;
            } catch (IOException e4) {
                e = e4;
            }
            try {
                fileOutputStream2 = 50;
                createBitmap.compress(Bitmap.CompressFormat.JPEG, 50, fileOutputStream);
                fileOutputStream.flush();
                try {
                    fileOutputStream.close();
                } catch (IOException e5) {
                    e = e5;
                    e.printStackTrace();
                    createBitmap.recycle();
                }
            } catch (FileNotFoundException e6) {
                e = e6;
                fileOutputStream2 = fileOutputStream;
                e.printStackTrace();
                fileOutputStream2 = fileOutputStream2;
                if (fileOutputStream2 != null) {
                    try {
                        fileOutputStream2.close();
                        fileOutputStream2 = fileOutputStream2;
                    } catch (IOException e7) {
                        e = e7;
                        e.printStackTrace();
                        createBitmap.recycle();
                    }
                }
                createBitmap.recycle();
            } catch (IOException e8) {
                e = e8;
                fileOutputStream2 = fileOutputStream;
                e.printStackTrace();
                fileOutputStream2 = fileOutputStream2;
                if (fileOutputStream2 != null) {
                    try {
                        fileOutputStream2.close();
                        fileOutputStream2 = fileOutputStream2;
                    } catch (IOException e9) {
                        e = e9;
                        e.printStackTrace();
                        createBitmap.recycle();
                    }
                }
                createBitmap.recycle();
            } catch (Throwable th2) {
                th = th2;
                fileOutputStream2 = fileOutputStream;
                if (fileOutputStream2 != null) {
                    try {
                        fileOutputStream2.close();
                    } catch (IOException e10) {
                        e10.printStackTrace();
                    }
                }
                createBitmap.recycle();
                throw th;
            }
            createBitmap.recycle();
        }
    }

    public static int readPictureDegree(String str) {
        int i2;
        try {
            int attributeInt = new ExifInterface(str).getAttributeInt(androidx.exifinterface.media.ExifInterface.TAG_ORIENTATION, 1);
            if (attributeInt == 3) {
                i2 = 180;
            } else if (attributeInt == 6) {
                i2 = 90;
            } else if (attributeInt != 8) {
                return 0;
            } else {
                i2 = 270;
            }
            return i2;
        } catch (IOException e2) {
            e2.printStackTrace();
            return 0;
        }
    }

    public static Bitmap rotateBitmap(Bitmap bitmap, int i2) {
        if (bitmap != null) {
            Matrix matrix = new Matrix();
            matrix.postRotate(i2);
            return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        }
        return bitmap;
    }

    public static Bitmap getBitmapFromSD(String str, int i2, int i3, int i4) {
        return getBitmapFromSD(str, i2, i3, i4, null);
    }

    public static Bitmap getBitmapFromSD(String str, int i2, int i3, int i4, Bitmap.Config config) {
        try {
            File file = new File(str);
            if (file.isFile()) {
                if (i4 > 0 && file.length() > i4) {
                    file.delete();
                    return null;
                }
                try {
                    file.setLastModified(System.currentTimeMillis());
                } catch (Exception unused) {
                }
                return decodeBitmap(file, i2, i3, config);
            }
            return null;
        } catch (Exception unused2) {
            return null;
        }
    }

    public static Bitmap getBitmapFromSD(String str, int i2, int i3) {
        return getBitmapFromSD(str, i2, i3, -1, null);
    }

    public static Bitmap getBitmapFromSD(String str, int i2, int i3, Bitmap.Config config) {
        return getBitmapFromSD(str, i2, i3, -1, config);
    }

    public static Bitmap getBitmapFromSD(String str, int i2) {
        return getBitmapFromSD(str, i2, false);
    }

    public static Bitmap getBitmapFromSD(String str, int i2, boolean z) {
        File file = new File(str);
        if (file.exists()) {
            try {
                file.setLastModified(System.currentTimeMillis());
            } catch (Exception unused) {
            }
            try {
                return decodeBitmap(file, i2, z);
            } catch (Exception e2) {
                e2.printStackTrace();
                return null;
            }
        }
        return null;
    }

    public static Bitmap getBitmapFromSD(String str) {
        File file = new File(str);
        if (file.exists()) {
            try {
                return decodeBitmap(file);
            } catch (Exception e2) {
                e2.printStackTrace();
                return null;
            }
        }
        return null;
    }

    public static Bitmap resizeBitmap(Bitmap bitmap, int i2, int i3) {
        boolean z;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (width > i2 || height > i3) {
            if (width <= height || width <= i2) {
                i2 = (int) (width * (i3 / height));
            } else {
                i3 = (int) (height * (i2 / width));
            }
            width = i2;
            z = true;
        } else {
            z = false;
            i3 = height;
        }
        return z ? Bitmap.createScaledBitmap(bitmap, width, i3, true) : bitmap;
    }

    public static Bitmap loadBitmap(int i2, String str) {
        return loadBitmap(i2, str, false);
    }

    public static Bitmap loadBitmap(int i2, String str, boolean z) {
        Bitmap bitmapFromSD = getBitmapFromSD(str, i2, z);
        if (bitmapFromSD == null) {
            return null;
        }
        int width = bitmapFromSD.getWidth();
        int height = bitmapFromSD.getHeight();
        if (width > i2 + 150 || (width > i2 && height > 4000)) {
            Bitmap createScaledBitmap = Bitmap.createScaledBitmap(bitmapFromSD, i2, (int) (((i2 * 1.0f) * height) / width), true);
            bitmapFromSD.recycle();
            if (createScaledBitmap == null) {
                return null;
            }
            bitmapFromSD = createScaledBitmap;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmapFromSD.compress(z ? Bitmap.CompressFormat.PNG : Bitmap.CompressFormat.JPEG, 80, byteArrayOutputStream);
        bitmapFromSD.recycle();
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        byteArrayOutputStream.close();
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPurgeable = true;
        options.inPreferredConfig = z ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565;
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length, options);
    }

    public static Bitmap decodeBitmap(ContentResolver contentResolver, Uri uri, int i2, int i3) {
        InputStream openInputStream;
        byte[] bArr = null;
        if (contentResolver == null || uri == null || i2 < 1 || i3 < 1) {
            return null;
        }
        try {
            openInputStream = contentResolver.openInputStream(uri);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (openInputStream == null) {
            return null;
        }
        byte[] bArr2 = new byte[8192];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while (true) {
            int read = openInputStream.read(bArr2);
            if (read == -1) {
                break;
            }
            byteArrayOutputStream.write(bArr2, 0, read);
        }
        bArr = byteArrayOutputStream.toByteArray();
        return decodeBitmap(bArr, i2, i3);
    }

    public static Bitmap decodeBitmap(Context context, int i2, int i3, int i4) {
        if (context != null && i2 != 0 && i3 > 0 && i4 > 0) {
            Resources resources = context.getResources();
            try {
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeResource(resources, i2, options);
                int i5 = options.outWidth;
                int i6 = options.outHeight;
                int i7 = 1;
                while (true) {
                    if (i5 / 2 < i3 && i6 / 2 < i4) {
                        BitmapFactory.Options options2 = new BitmapFactory.Options();
                        options2.inSampleSize = i7;
                        options2.inPurgeable = true;
                        options2.inPreferredConfig = Bitmap.Config.RGB_565;
                        return BitmapFactory.decodeResource(resources, i2, options2);
                    }
                    i5 /= 2;
                    i6 /= 2;
                    i7 *= 2;
                }
            } catch (Exception unused) {
            }
        }
        return null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x004c, code lost:
        if (r7 == 0) goto L12;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static android.graphics.Bitmap compressBitmap(android.graphics.Bitmap r6, long r7) {
        /*
            if (r6 != 0) goto L4
            r6 = 0
            return r6
        L4:
            r0 = 0
            int r0 = (r7 > r0 ? 1 : (r7 == r0 ? 0 : -1))
            if (r0 > 0) goto Lb
            return r6
        Lb:
            android.graphics.Bitmap$Config r0 = android.graphics.Bitmap.Config.ARGB_8888
            int r1 = r6.getRowBytes()
            int r2 = r6.getHeight()
            int r1 = r1 * r2
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "bitmap size is "
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.StringBuilder r2 = r2.append(r1)
            java.lang.String r2 = r2.toString()
            java.lang.String r3 = "BitmapUtils"
            com.bytedance.common.utility.Logger.d(r3, r2)
            long r1 = (long) r1
            long r1 = r1 / r7
            double r7 = (double) r1
            long r7 = java.lang.Math.round(r7)
            double r7 = (double) r7
            r1 = 4611686018427387904(0x4000000000000000, double:2.0)
            int r1 = (r7 > r1 ? 1 : (r7 == r1 ? 0 : -1))
            r2 = 1
            if (r1 < 0) goto L47
            r4 = 4616189618054758400(0x4010000000000000, double:4.0)
            int r1 = (r7 > r4 ? 1 : (r7 == r4 ? 0 : -1))
            if (r1 >= 0) goto L47
            android.graphics.Bitmap$Config r0 = android.graphics.Bitmap.Config.RGB_565
        L45:
            r7 = r2
            goto L4f
        L47:
            double r7 = java.lang.Math.sqrt(r7)
            int r7 = (int) r7
            if (r7 != 0) goto L4f
            goto L45
        L4f:
            if (r7 != r2) goto L56
            android.graphics.Bitmap$Config r8 = android.graphics.Bitmap.Config.ARGB_8888
            if (r0 != r8) goto L56
            return r6
        L56:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r1 = "compress sample size is "
            java.lang.StringBuilder r8 = r8.append(r1)
            java.lang.StringBuilder r8 = r8.append(r7)
            java.lang.String r8 = r8.toString()
            com.bytedance.common.utility.Logger.d(r3, r8)
            java.io.ByteArrayOutputStream r8 = new java.io.ByteArrayOutputStream
            r8.<init>()
            android.graphics.Bitmap$CompressFormat r1 = android.graphics.Bitmap.CompressFormat.JPEG
            r3 = 100
            r6.compress(r1, r3, r8)
            byte[] r6 = r8.toByteArray()
            android.graphics.BitmapFactory$Options r8 = new android.graphics.BitmapFactory$Options
            r8.<init>()
            r8.inSampleSize = r7
            r8.inPurgeable = r2
            r8.inPreferredConfig = r0
            r7 = 0
            int r0 = r6.length
            android.graphics.Bitmap r6 = android.graphics.BitmapFactory.decodeByteArray(r6, r7, r0, r8)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.common.utility.b.compressBitmap(android.graphics.Bitmap, long):android.graphics.Bitmap");
    }
}
