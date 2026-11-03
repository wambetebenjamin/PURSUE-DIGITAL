package com.bytedance.bdinstall;

import android.text.TextUtils;
import android.util.Pair;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;
/* compiled from: DefaultClient.java */
/* renamed from: com.bytedance.bdinstall.r  reason: case insensitive filesystem */
/* loaded from: classes.dex */
public class C0387r implements ai {

    /* renamed from: a  reason: collision with root package name */
    private static final String[] f3964a = {"GET", "POST"};

    /* renamed from: b  reason: collision with root package name */
    private static final int f3965b = 10240;

    /* renamed from: c  reason: collision with root package name */
    private static final int f3966c = 0;

    /* renamed from: d  reason: collision with root package name */
    private static final int f3967d = 1;

    /* renamed from: e  reason: collision with root package name */
    private static final String f3968e = "application/json; charset=utf-8";

    @Override // com.bytedance.bdinstall.ai
    public String get(String str, Map<String, String> map) {
        return a(0, str, (HashMap) map, null);
    }

    @Override // com.bytedance.bdinstall.ai
    public String post(String str, byte[] bArr, Map<String, String> map) {
        return a(1, str, (HashMap) map, bArr);
    }

    @Override // com.bytedance.bdinstall.ai
    public String post(String str, byte[] bArr, String str2) {
        HashMap hashMap = new HashMap();
        if (!TextUtils.isEmpty(str2)) {
            hashMap.put("Content-Type", str2);
        }
        return post(str, bArr, hashMap);
    }

    @Override // com.bytedance.bdinstall.ai
    public String post(String str, List<Pair<String, String>> list) {
        JSONObject jSONObject = new JSONObject();
        if (list != null) {
            for (Pair<String, String> pair : list) {
                jSONObject.put((String) pair.first, pair.second);
            }
        }
        return post(str, jSONObject.toString().getBytes(), "application/json; charset=utf-8");
    }

    @Override // com.bytedance.bdinstall.ai
    public byte[] postStream(String str, byte[] bArr, Map<String, String> map) {
        return a(1, str, map, bArr, 1).f3971c;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: DefaultClient.java */
    /* renamed from: com.bytedance.bdinstall.r$a */
    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        int f3969a;

        /* renamed from: b  reason: collision with root package name */
        String f3970b;

        /* renamed from: c  reason: collision with root package name */
        byte[] f3971c;

        a(int i2) {
            this.f3969a = i2;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:88:0x01f8  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0205 A[Catch: all -> 0x0208, TRY_ENTER, TryCatch #2 {all -> 0x0208, blocks: (B:86:0x01f1, B:91:0x0205, B:92:0x0207), top: B:99:0x01f1 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static com.bytedance.bdinstall.C0387r.a a(int r7, java.lang.String r8, java.util.Map<java.lang.String, java.lang.String> r9, byte[] r10, int r11) {
        /*
            Method dump skipped, instructions count: 534
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.bdinstall.C0387r.a(int, java.lang.String, java.util.Map, byte[], int):com.bytedance.bdinstall.r$a");
    }

    /* JADX WARN: Removed duplicated region for block: B:75:0x01ba A[DONT_GENERATE] */
    /* JADX WARN: Removed duplicated region for block: B:81:0x01ca  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x01e1 A[Catch: all -> 0x01e4, TRY_ENTER, TryCatch #3 {all -> 0x01e4, blocks: (B:73:0x01b3, B:83:0x01e1, B:84:0x01e3), top: B:102:0x01b3 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String a(int r7, java.lang.String r8, java.util.HashMap<java.lang.String, java.lang.String> r9, byte[] r10) {
        /*
            Method dump skipped, instructions count: 496
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.bdinstall.C0387r.a(int, java.lang.String, java.util.HashMap, byte[]):java.lang.String");
    }
}
