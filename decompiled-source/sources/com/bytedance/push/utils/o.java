package com.bytedance.push.utils;

import android.util.Base64;
import android.util.Log;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.X509EncodedKeySpec;
/* compiled from: SignUtils.java */
/* loaded from: classes2.dex */
public class o {

    /* compiled from: SignUtils.java */
    /* loaded from: classes2.dex */
    public static class a {
        public String msg;
        public boolean result;
    }

    public static a verifySign(String str, String str2, String str3) {
        a aVar = new a();
        try {
            h.v("Sign", "c = " + str + ", key = " + str2 + ", sig = " + str3);
            PublicKey generatePublic = KeyFactory.getInstance("EC").generatePublic(new X509EncodedKeySpec(Base64.decode(str2, 0)));
            byte[] bytes = str.getBytes("UTF-8");
            byte[] decode = Base64.decode(str3, 8);
            Signature signature = Signature.getInstance("SHA256withECDSA");
            signature.initVerify(generatePublic);
            signature.update(bytes);
            aVar.result = signature.verify(decode);
            if (aVar.result) {
                aVar.msg = "success";
            } else {
                aVar.msg = "verify failed when call verify method";
            }
        } catch (Throwable th) {
            ((com.bytedance.push.interfaze.q) com.ss.android.ug.bus.b.getService(com.bytedance.push.interfaze.q.class)).ensureNotReachHere(th);
            aVar.msg = "exception : " + Log.getStackTraceString(th);
            aVar.result = false;
        }
        return aVar;
    }
}
