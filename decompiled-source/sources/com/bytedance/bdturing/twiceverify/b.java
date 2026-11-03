package com.bytedance.bdturing.twiceverify;

import android.app.Activity;
import java.util.HashMap;
import java.util.Map;
/* compiled from: TwiceVerifyDepend.java */
/* loaded from: classes.dex */
public interface b {

    /* compiled from: TwiceVerifyDepend.java */
    /* loaded from: classes.dex */
    public interface a {
        @Deprecated
        void a();

        void a(int i2, String str);

        void a(String str, String str2, Map<String, String> map);
    }

    void a();

    void a(Activity activity, String str);

    void a(HashMap<String, String> hashMap, a aVar);

    String b();

    void b(HashMap<String, String> hashMap, a aVar);

    String c();
}
