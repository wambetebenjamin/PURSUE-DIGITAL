package com.bytedance.pipo.game.impl.util;

import android.util.Base64;
import com.bytedance.ttgame.tps.TpsSdkClientEx;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Map;
import org.json.JSONObject;
/* compiled from: CryptUtils.java */
/* loaded from: classes2.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    public static String f10531a = "CryptUtils";

    /* renamed from: b  reason: collision with root package name */
    private static final String f10532b = "design_type";

    /* renamed from: c  reason: collision with root package name */
    private static final String f10533c = "gsdk_pay";

    /* renamed from: d  reason: collision with root package name */
    private static final int f10534d = 1;

    /* renamed from: e  reason: collision with root package name */
    private static boolean f10535e = false;

    public static boolean a() {
        int Init = TpsSdkClientEx.Init("sRPDexql9c6Kf8bDfPigRyfJxJXbcxgJaOVmeEk4kOoleBXz1V136VmfDu605sHkKcj4I/Kh/QD/TiDwOMSuizVhNBY62SYpGRa/FcNklobFRz3NOEHIBLf5Zfnb+lelstezXfeoXAZK6Qf7CpiUSgxriKutG6RQ20n6grsqrzVAwHziIqD8W6d3evc7A+h00AdzYxG7FHXF7jBpFNBxNVcnXduszv6mb1/BunWoxO/2D1ZLIa0t8zvy2PsaFOEAMzvZm41IRPwnsyiDxyzG9tUSUNLGVfB0XWJ0/jevuj6v7DHHKekwckIC6/Q7XJcmV5txO9dEu9U1umoCtjbBAw==".getBytes(), 1);
        if (Init != 0) {
            f10535e = false;
            com.bytedance.pipo.game.impl.net.b.a(f10531a, "tps client init error: " + Init);
            return false;
        }
        f10535e = true;
        com.bytedance.pipo.game.impl.net.b.a(f10531a, "tps client init success.");
        return true;
    }

    public static String a(Map<String, String> map) {
        return a(new JSONObject(map));
    }

    public static byte[] a(byte[] bArr) {
        return TpsSdkClientEx.Encrypt(bArr);
    }

    public static byte[] a(String str) {
        return TpsSdkClientEx.Decrypt(b(str));
    }

    public static String b(byte[] bArr) {
        if (bArr == null) {
            com.bytedance.pipo.game.impl.net.b.a(f10531a, "enData is null, can't encode.");
            return null;
        }
        return new String(Base64.encode(bArr, 0), Charset.forName("UTF-8"));
    }

    public static byte[] b(String str) {
        if (str == null) {
            com.bytedance.pipo.game.impl.net.b.a(f10531a, "encryptMessage is null, can't decode.");
            return null;
        }
        return Base64.decode(str, 0);
    }

    public static String a(JSONObject jSONObject) {
        if (!f10535e) {
            a();
        }
        String jSONObject2 = jSONObject.toString();
        com.bytedance.pipo.game.impl.net.b.a(f10531a, "json string: " + jSONObject2);
        byte[] bytes = jSONObject2.getBytes();
        com.bytedance.pipo.game.impl.net.b.a(f10531a, "string encode to byte array: " + Arrays.toString(bytes));
        byte[] a2 = a(bytes);
        if (bytes == null) {
            com.bytedance.pipo.game.impl.net.b.a(f10531a, "parameters encrypted is null, maybe TpsClientEx didn't initialize");
            return null;
        }
        com.bytedance.pipo.game.impl.net.b.a(f10531a, "encrypted Array: " + Arrays.toString(a2));
        String b2 = b(a2);
        com.bytedance.pipo.game.impl.net.b.a(f10531a, "encrypted string: " + b2);
        return b2;
    }
}
