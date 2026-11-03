package com.bytedance.push.img;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
/* compiled from: UrlConnectionDownloader.java */
/* loaded from: classes2.dex */
public class d implements a {

    /* renamed from: a  reason: collision with root package name */
    private static final int f11383a = 65536;

    /* renamed from: b  reason: collision with root package name */
    private static final int f11384b = 20000;

    /* renamed from: c  reason: collision with root package name */
    private static final int f11385c = 15000;

    @Override // com.bytedance.push.img.a
    public Bitmap downloadImage(c cVar) {
        try {
            return a(cVar);
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private Bitmap a(c cVar) {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(cVar.uri.toString()).openConnection();
        httpURLConnection.setConnectTimeout(15000);
        httpURLConnection.setReadTimeout(20000);
        httpURLConnection.setUseCaches(true);
        int responseCode = httpURLConnection.getResponseCode();
        if (responseCode >= 300) {
            httpURLConnection.disconnect();
            throw new IOException(responseCode + " " + httpURLConnection.getResponseMessage());
        }
        InputStream inputStream = httpURLConnection.getInputStream();
        if (inputStream == null) {
            return null;
        }
        try {
            if (httpURLConnection.getHeaderFieldInt("Content-Length", -1) == 0) {
                com.bytedance.common.utility.io.b.close(inputStream);
                throw new IOException("Received response with 0 content-length header.");
            }
            return a(inputStream, cVar);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        } finally {
            com.bytedance.common.utility.io.b.close(inputStream);
        }
    }

    private Bitmap a(InputStream inputStream, c cVar) {
        b bVar = new b(inputStream);
        long savePosition = bVar.savePosition(65536);
        BitmapFactory.Options b2 = b(cVar);
        boolean a2 = a(b2);
        boolean a3 = e.a(bVar);
        bVar.reset(savePosition);
        if (a3) {
            byte[] b3 = e.b(bVar);
            if (a2) {
                BitmapFactory.decodeByteArray(b3, 0, b3.length, b2);
                a(cVar.targetWidth, cVar.targetHeight, b2);
            }
            return BitmapFactory.decodeByteArray(b3, 0, b3.length, b2);
        }
        if (a2) {
            BitmapFactory.decodeStream(bVar, null, b2);
            a(cVar.targetWidth, cVar.targetHeight, b2);
            bVar.reset(savePosition);
        }
        Bitmap decodeStream = BitmapFactory.decodeStream(bVar, null, b2);
        if (decodeStream != null) {
            return decodeStream;
        }
        throw new IOException("Failed to decode stream.");
    }

    private static void a(int i2, int i3, BitmapFactory.Options options) {
        a(i2, i3, options.outWidth, options.outHeight, options);
    }

    private static void a(int i2, int i3, int i4, int i5, BitmapFactory.Options options) {
        int floor;
        if (i5 > i3 || i4 > i2) {
            int floor2 = (int) Math.floor(i5 / i3);
            floor = (int) Math.floor(i4 / i2);
            if (floor2 < floor) {
                floor = floor2;
            }
        } else {
            floor = 1;
        }
        options.inSampleSize = floor;
        options.inJustDecodeBounds = false;
    }

    private static BitmapFactory.Options b(c cVar) {
        boolean hasSize = cVar.hasSize();
        boolean z = cVar.config != null;
        BitmapFactory.Options options = null;
        if (hasSize || z) {
            options = new BitmapFactory.Options();
            options.inJustDecodeBounds = hasSize;
            if (z) {
                options.inPreferredConfig = cVar.config;
            }
        }
        return options;
    }

    private static boolean a(BitmapFactory.Options options) {
        return options != null && options.inJustDecodeBounds;
    }
}
