package com.bytedance.apm;

import android.content.SharedPreferences;
import android.util.Base64;
import com.bytedance.services.apm.api.q;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import org.json.JSONObject;
/* compiled from: ZstdDictManager.java */
/* loaded from: classes.dex */
public class o implements q {

    /* renamed from: a  reason: collision with root package name */
    public static final String f1237a = "slardar-zstd-version";

    /* renamed from: b  reason: collision with root package name */
    private static final String f1238b = "dict_interval";

    /* renamed from: c  reason: collision with root package name */
    private static final String f1239c = "dict_version";

    /* renamed from: d  reason: collision with root package name */
    private String f1240d;

    /* renamed from: e  reason: collision with root package name */
    private volatile boolean f1241e;

    /* renamed from: f  reason: collision with root package name */
    private byte[] f1242f;

    /* renamed from: g  reason: collision with root package name */
    private long f1243g;

    /* renamed from: h  reason: collision with root package name */
    private String f1244h;

    public byte[] a() {
        d();
        byte[] bArr = this.f1242f;
        if (bArr == null || bArr.length <= 0) {
            if (bArr == null || System.currentTimeMillis() - this.f1243g > com.bytedance.common.utility.date.a.WEEK) {
                e();
            }
            return this.f1242f;
        }
        return bArr;
    }

    @Override // com.bytedance.services.apm.api.q
    public void a(String str) {
        this.f1240d = "https://" + str + com.bytedance.apm6.util.constant.d.f2384a;
    }

    private void d() {
        if (this.f1241e) {
            return;
        }
        this.f1241e = true;
        this.f1242f = com.bytedance.apm6.util.d.readFileToByteArray(new File(com.bytedance.apm6.foundation.context.a.getContext().getFilesDir(), "monitor_dict"));
        SharedPreferences sharedPreferences = com.bytedance.apm6.foundation.context.a.getContext().getSharedPreferences(f1238b, 0);
        this.f1243g = sharedPreferences.getLong(f1238b, 0L);
        this.f1244h = sharedPreferences.getString(f1239c, null);
    }

    public String b() {
        d();
        return this.f1244h;
    }

    private void e() {
        HashMap hashMap = new HashMap(com.bytedance.apm6.foundation.context.a.getUrlParams());
        hashMap.put("slardar_zstd_dict_type", "monitor");
        hashMap.put("version", "1");
        hashMap.put(f1237a, this.f1244h);
        HashMap hashMap2 = new HashMap();
        hashMap2.put(f1237a, this.f1244h);
        try {
            com.bytedance.services.apm.api.d doGet = com.bytedance.apm6.foundation.context.a.doGet(com.bytedance.apm6.util.m.addParamsToURL(this.f1240d, hashMap), hashMap2);
            if (doGet != null && doGet.getResponseBytes() != null) {
                JSONObject jSONObject = new JSONObject(new String(doGet.getResponseBytes()));
                this.f1244h = jSONObject.getString(f1239c);
                byte[] decode = Base64.decode(jSONObject.getString("dict"), 0);
                a(new File(com.bytedance.apm6.foundation.context.a.getContext().getFilesDir(), "monitor_dict"), decode);
                this.f1242f = decode;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        this.f1243g = System.currentTimeMillis();
        com.bytedance.apm6.foundation.context.a.getContext().getSharedPreferences(f1238b, 0).edit().putLong(f1238b, this.f1243g).putString(f1239c, this.f1244h).commit();
    }

    private static void a(File file, byte[] bArr) {
        if (file == null || bArr == null || bArr.length == 0) {
            return;
        }
        file.getParentFile().mkdirs();
        FileOutputStream fileOutputStream = null;
        try {
            if (file.exists()) {
                file.delete();
            }
            file.exists();
            FileOutputStream fileOutputStream2 = new FileOutputStream(file);
            try {
                fileOutputStream2.write(bArr);
                fileOutputStream2.flush();
                com.bytedance.apm6.util.e.safeClose(fileOutputStream2);
            } catch (Exception unused) {
                fileOutputStream = fileOutputStream2;
                com.bytedance.apm6.util.e.safeClose(fileOutputStream);
            } catch (Throwable th) {
                th = th;
                fileOutputStream = fileOutputStream2;
                com.bytedance.apm6.util.e.safeClose(fileOutputStream);
                throw th;
            }
        } catch (Exception unused2) {
        } catch (Throwable th2) {
            th = th2;
        }
    }
}
