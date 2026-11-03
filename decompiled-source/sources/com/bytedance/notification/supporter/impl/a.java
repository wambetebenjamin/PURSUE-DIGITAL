package com.bytedance.notification.supporter.impl;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
/* compiled from: IconFileServiceImpl.java */
/* loaded from: classes2.dex */
public class a implements com.bytedance.notification.supporter.service.a {

    /* renamed from: a  reason: collision with root package name */
    private final String f9918a = "proxy_icon_files";

    /* renamed from: b  reason: collision with root package name */
    private final File f9919b;

    public a(Context context) {
        this.f9919b = new File(context.getFilesDir(), "proxy_icon_files");
    }

    @Override // com.bytedance.notification.supporter.service.a
    public Bitmap a(String str) {
        b(str);
        File file = new File(this.f9919b, b(str));
        if (file.exists()) {
            try {
                return BitmapFactory.decodeStream(new FileInputStream(file));
            } catch (FileNotFoundException e2) {
                e2.printStackTrace();
                return null;
            }
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.bytedance.notification.supporter.service.a
    public void a(String str, Bitmap bitmap) {
        FileOutputStream fileOutputStream;
        File file = this.f9919b;
        if (file == null || bitmap == null) {
            return;
        }
        if (file.exists() || this.f9919b.mkdir()) {
            FileOutputStream fileOutputStream2 = null;
            try {
                try {
                    try {
                        fileOutputStream = new FileOutputStream(new File(this.f9919b, b(str)));
                    } catch (Throwable th) {
                        th.printStackTrace();
                        return;
                    }
                } catch (IOException e2) {
                    e = e2;
                }
            } catch (Throwable th2) {
                th = th2;
            }
            try {
                Bitmap.CompressFormat compressFormat = Bitmap.CompressFormat.PNG;
                bitmap.compress(compressFormat, 100, fileOutputStream);
                fileOutputStream.flush();
                fileOutputStream.close();
                fileOutputStream2 = compressFormat;
            } catch (IOException e3) {
                e = e3;
                fileOutputStream2 = fileOutputStream;
                e.printStackTrace();
                if (fileOutputStream2 != null) {
                    fileOutputStream2.flush();
                    fileOutputStream2.close();
                    fileOutputStream2 = fileOutputStream2;
                }
            } catch (Throwable th3) {
                th = th3;
                fileOutputStream2 = fileOutputStream;
                if (fileOutputStream2 != null) {
                    try {
                        fileOutputStream2.flush();
                        fileOutputStream2.close();
                    } catch (Throwable th4) {
                        th4.printStackTrace();
                    }
                }
                throw th;
            }
        }
    }

    private String b(String str) {
        return str + ".jpg";
    }
}
