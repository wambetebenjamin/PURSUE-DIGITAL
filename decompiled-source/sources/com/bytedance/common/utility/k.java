package com.bytedance.common.utility;

import android.util.Pair;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPOutputStream;
/* compiled from: NetworkClient.java */
/* loaded from: classes2.dex */
public abstract class k {
    private static final int BYTE_ARRAY_OUTPUT_STREAM_SIZE = 8192;
    private static k sDefault = new DummyNetworkClient();

    /* compiled from: NetworkClient.java */
    /* loaded from: classes2.dex */
    public static class a {
        public boolean addCommonParams;
        public boolean fetchResponseHeaders;
        public Map<String, String> responseHeaders;
    }

    public abstract String get(String str, Map<String, String> map, a aVar);

    public abstract String post(String str, List<Pair<String, String>> list, Map<String, String> map, a aVar);

    public abstract String post(String str, byte[] bArr, Map<String, String> map, a aVar);

    public byte[] postDataStream(String str, byte[] bArr, Map<String, String> map, a aVar) {
        return null;
    }

    public static void setDefault(k kVar) {
        if (kVar == null || kVar == sDefault) {
            return;
        }
        sDefault = kVar;
    }

    public static k getDefault() {
        return sDefault;
    }

    public static byte[] compressWithgzip(byte[] bArr) {
        GZIPOutputStream gZIPOutputStream = null;
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(8192);
            GZIPOutputStream gZIPOutputStream2 = new GZIPOutputStream(byteArrayOutputStream);
            try {
                gZIPOutputStream2.write(bArr);
                gZIPOutputStream2.close();
                return byteArrayOutputStream.toByteArray();
            } catch (Throwable th) {
                th = th;
                gZIPOutputStream = gZIPOutputStream2;
                if (gZIPOutputStream != null) {
                    gZIPOutputStream.close();
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public String get(String str) {
        a aVar = new a();
        aVar.addCommonParams = true;
        return get(str, null, aVar);
    }

    public String post(String str, List<Pair<String, String>> list) {
        a aVar = new a();
        aVar.addCommonParams = true;
        return post(str, list, (Map<String, String>) null, aVar);
    }

    public String post(String str, byte[] bArr, boolean z, String str2, boolean z2) {
        HashMap hashMap = new HashMap();
        if (z) {
            try {
                bArr = compressWithgzip(bArr);
                hashMap.put("Content-Encoding", "gzip");
            } catch (Exception e2) {
                throw new d(0, e2.getMessage());
            }
        }
        if (!o.isEmpty(str2)) {
            hashMap.put("Content-Type", str2);
        }
        a aVar = new a();
        aVar.addCommonParams = z2;
        return post(str, bArr, hashMap, aVar);
    }
}
