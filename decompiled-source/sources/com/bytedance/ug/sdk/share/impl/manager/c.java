package com.bytedance.ug.sdk.share.impl.manager;

import android.content.Context;
import android.text.TextUtils;
import com.bytedance.ug.sdk.share.api.depend.g;
import com.bytedance.ug.sdk.share.api.depend.m;
import com.bytedance.ug.sdk.share.api.depend.p;
import com.bytedance.ug.sdk.share.api.depend.r;
import com.bytedance.ug.sdk.share.api.depend.u;
import com.bytedance.ug.sdk.share.impl.utils.j;
import java.util.concurrent.ConcurrentHashMap;
/* compiled from: DependManager.java */
/* loaded from: classes8.dex */
public class c {

    /* renamed from: b  reason: collision with root package name */
    private static m f15797b;

    /* renamed from: d  reason: collision with root package name */
    private static r f15799d;

    /* renamed from: f  reason: collision with root package name */
    private static g f15801f;

    /* renamed from: h  reason: collision with root package name */
    private static p f15803h;

    /* renamed from: i  reason: collision with root package name */
    private static u f15804i;

    /* renamed from: a  reason: collision with root package name */
    private static final ConcurrentHashMap<String, com.bytedance.ug.sdk.share.impl.share.api.b> f15796a = new ConcurrentHashMap<>();

    /* renamed from: c  reason: collision with root package name */
    private static boolean f15798c = true;

    /* renamed from: e  reason: collision with root package name */
    private static boolean f15800e = true;

    /* renamed from: g  reason: collision with root package name */
    private static boolean f15802g = true;

    public static com.bytedance.ug.sdk.share.impl.share.api.b getChannel(com.bytedance.ug.sdk.share.api.panel.d dVar) {
        String str = com.bytedance.ug.sdk.share.impl.constant.a.sShareConfig.get(dVar);
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        com.bytedance.ug.sdk.share.impl.share.api.b bVar = f15796a.get(str);
        if (bVar != null) {
            return bVar;
        }
        try {
            bVar = (com.bytedance.ug.sdk.share.impl.share.api.b) Class.forName(str).getConstructor(Context.class).newInstance(d.getInstance().getAppContext());
        } catch (Throwable th) {
            j.e(th.toString());
        }
        if (bVar != null) {
            f15796a.put(str, bVar);
        }
        return bVar;
    }

    public static m getQrScanConfig() {
        if (f15798c) {
            m mVar = f15797b;
            if (mVar != null) {
                return mVar;
            }
            try {
                f15797b = (m) Class.forName("com.bytedance.ug.sdk.share.keep.impl.QrScanConfigImpl").newInstance();
            } catch (Throwable unused) {
                f15798c = false;
            }
            return f15797b;
        }
        return null;
    }

    public static r getVideoWatermarkConfig() {
        r rVar = f15799d;
        if (rVar != null) {
            return rVar;
        }
        try {
            f15799d = (r) Class.forName("com.bytedance.ug.sdk.share.keep.impl.VideoWatermarkConfigImpl").newInstance();
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return f15799d;
    }

    public static g getImageTokenConfig() {
        if (f15800e) {
            g gVar = f15801f;
            if (gVar != null) {
                return gVar;
            }
            try {
                f15801f = (g) Class.forName("com.bytedance.ug.sdk.share.keep.impl.ImageTokenConfigImpl").newInstance();
            } catch (Throwable unused) {
                f15800e = false;
            }
            return f15801f;
        }
        return null;
    }

    public static u a() {
        u uVar = f15804i;
        if (uVar != null) {
            return uVar;
        }
        try {
            f15804i = (u) Class.forName("com.bytedance.ug.sdk.share.keep.impl.ShareTokenRuleConfigImpl").newInstance();
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return f15804i;
    }

    public static p getUIConfig() {
        if (f15802g) {
            p pVar = f15803h;
            if (pVar != null) {
                return pVar;
            }
            try {
                f15803h = (p) Class.forName("com.bytedance.ug.sdk.share.keep.impl.UIConfigImpl").newInstance();
            } catch (Throwable unused) {
                f15802g = false;
            }
            return f15803h;
        }
        return null;
    }
}
