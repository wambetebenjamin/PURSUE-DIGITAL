package com.bytedance.applog.compress;

import android.content.Context;
import com.bytedance.applog.log.h;
import com.bytedance.applog.log.o;
import com.bytedance.compression.zstd.ZstdCompress;
import com.bytedance.compression.zstd.ZstdDictCompress;
import java.util.Arrays;
/* loaded from: classes.dex */
public class CompressManager {
    public static final int TYPE_EMPTY = -1;
    public static final int TYPE_GZIP = 0;
    public static final int TYPE_ZSTD = 1;
    public static final int TYPE_ZSTD_DICT = 2;
    private static byte[] mDictionary;
    private final Context mContext;
    private final h mLogger;

    @Deprecated
    public static void setReportStatsEnabled(boolean z) {
    }

    public static void setZstdDict(byte[] bArr) {
        if (bArr == null || bArr.length <= 0) {
            return;
        }
        mDictionary = Arrays.copyOf(bArr, bArr.length);
    }

    @Deprecated
    public CompressManager(Context context, String str, d dVar, h hVar) {
        this(context, hVar);
    }

    public CompressManager(Context context, h hVar) {
        this.mContext = context;
        this.mLogger = hVar == null ? o.a() : hVar;
    }

    private int getEncodeType(int i2) {
        if (i2 == 2 && mDictionary == null) {
            return 1;
        }
        return i2;
    }

    public byte[] compress(byte[] bArr, int i2, b bVar) {
        int encodeType = getEncodeType(i2);
        if (encodeType == 0) {
            bArr = compressUsingGzip(bArr, bVar);
        } else if (encodeType == 1 || encodeType == 2) {
            bArr = compressUsingZstd(bArr, bVar, i2);
        }
        return (bArr == null || bArr.length == 0) ? compressUsingGzip(bArr, bVar) : bArr;
    }

    public byte[] compressUsingGzip(byte[] bArr, b bVar) {
        bVar.f2557a = bArr == null ? 0 : bArr.length;
        bVar.f2559c = 0;
        long currentTimeMillis = System.currentTimeMillis();
        byte[] a2 = c.a(bArr);
        long currentTimeMillis2 = System.currentTimeMillis();
        bVar.f2561e = 0;
        bVar.f2560d = currentTimeMillis2 - currentTimeMillis;
        bVar.f2558b = a2.length;
        return a2;
    }

    public byte[] compressUsingZstd(byte[] bArr, b bVar, int i2) {
        byte[] compress;
        bVar.f2557a = bArr.length;
        bVar.f2559c = i2;
        bVar.f2562f = 0;
        byte[] bArr2 = null;
        try {
            long currentTimeMillis = System.currentTimeMillis();
            if (i2 == 2 && mDictionary != null) {
                compress = ZstdCompress.compress(bArr, new ZstdDictCompress(mDictionary, 15));
            } else {
                compress = ZstdCompress.compress(bArr, 15);
            }
            bArr2 = compress;
            bVar.f2560d = System.currentTimeMillis() - currentTimeMillis;
            bVar.f2558b = bArr2.length;
            bVar.f2561e = 0;
            return bArr2;
        } catch (Throwable th) {
            this.mLogger.a(14, "compress with zstd failed", th, new Object[0]);
            bVar.f2561e = 1;
            return bArr2;
        }
    }
}
