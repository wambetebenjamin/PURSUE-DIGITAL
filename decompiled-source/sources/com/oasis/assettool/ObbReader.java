package com.oasis.assettool;

import android.util.Log;
import com.oasis.assettool.a;
import java.io.InputStream;
import java.util.Collection;
/* loaded from: classes9.dex */
public final class ObbReader {

    /* renamed from: a  reason: collision with root package name */
    private a f17476a;

    public ObbReader(String str) {
        try {
            this.f17476a = new a(str);
        } catch (Exception e2) {
            Log.e("unity", "Load obb failed:".concat(String.valueOf(str)), e2);
        }
    }

    public final String[] getAllFilePath() {
        try {
            Collection<a.C0316a> values = this.f17476a.f17477a.values();
            a.C0316a[] c0316aArr = (a.C0316a[]) values.toArray(new a.C0316a[values.size()]);
            String[] strArr = new String[c0316aArr.length];
            for (int i2 = 0; i2 < c0316aArr.length; i2++) {
                if (c0316aArr[i2].f17481b.startsWith("assets/")) {
                    strArr[i2] = c0316aArr[i2].f17481b.substring(7);
                } else {
                    strArr[i2] = c0316aArr[i2].f17481b;
                }
            }
            return strArr;
        } catch (Exception e2) {
            Log.e("unity", "list files from obb failed.", e2);
            return null;
        }
    }

    public final InputStream open(String str) {
        try {
            InputStream a2 = this.f17476a.a("assets/".concat(String.valueOf(str)));
            if (a2 == null) {
                Log.e("unity", "open file from obb failed: ".concat(String.valueOf(str)));
                return null;
            }
            return a2;
        } catch (Exception e2) {
            Log.e("unity", "open file from obb failed: ".concat(String.valueOf(str)), e2);
            return null;
        }
    }

    public final byte[] readAllBytes(String str) {
        try {
            InputStream a2 = this.f17476a.a("assets/".concat(String.valueOf(str)));
            if (a2 == null) {
                Log.e("unity", "read file from obb failed: ".concat(String.valueOf(str)));
                return null;
            }
            int available = a2.available();
            byte[] bArr = new byte[available];
            int i2 = available;
            while (i2 > 0) {
                int read = a2.read(bArr, available - i2, i2);
                if (read == -1) {
                    break;
                }
                i2 -= read;
            }
            a2.close();
            return bArr;
        } catch (Exception e2) {
            Log.e("unity", "read file from obb failed: ".concat(String.valueOf(str)), e2);
            return null;
        }
    }
}
