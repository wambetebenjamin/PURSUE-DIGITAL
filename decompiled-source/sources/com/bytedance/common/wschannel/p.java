package com.bytedance.common.wschannel;

import android.content.Context;
import android.text.TextUtils;
import com.bytedance.common.utility.Logger;
import com.bytedance.common.wschannel.WsChannelMultiProcessSharedProvider;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
/* compiled from: WsChannelSettings.java */
/* loaded from: classes2.dex */
public class p {

    /* renamed from: a  reason: collision with root package name */
    private static final String f5873a = "com.bytedance.common.wschannel.p";

    /* renamed from: b  reason: collision with root package name */
    private static volatile p f5874b;

    /* renamed from: d  reason: collision with root package name */
    private static a f5875d;

    /* renamed from: c  reason: collision with root package name */
    private final WsChannelMultiProcessSharedProvider.b f5876c;

    /* compiled from: WsChannelSettings.java */
    /* loaded from: classes2.dex */
    public interface a {
        Map<String, String> a(String str, Map<String, List<String>> map);

        Map<String, String> a(Map<String, String> map);
    }

    private p(Context context) {
        this.f5876c = WsChannelMultiProcessSharedProvider.getMultiprocessShared(context.getApplicationContext());
    }

    public static p a(Context context) {
        if (f5874b == null) {
            synchronized (p.class) {
                if (f5874b == null) {
                    f5874b = new p(context);
                }
            }
        }
        return f5874b;
    }

    public boolean a() {
        return this.f5876c.a(WsConstants.KEY_ENABLE_OFFLINE_DETECT, false);
    }

    public void a(boolean z) {
        this.f5876c.a().a(WsConstants.KEY_ENABLE_OFFLINE_DETECT, z).b();
    }

    public static void a(a aVar) {
        f5875d = aVar;
    }

    public static a b() {
        return f5875d;
    }

    public boolean c() {
        return this.f5876c.a(WsConstants.KEY_FRONTIER_ENABLED, true);
    }

    public void b(boolean z) {
        this.f5876c.a().a(WsConstants.KEY_FRONTIER_ENABLED, z).b();
    }

    public String d() {
        return this.f5876c.a(WsConstants.KEY_WS_APPS, "");
    }

    public void a(String str) {
        this.f5876c.a().a(WsConstants.KEY_WS_APPS, str).b();
    }

    public boolean e() {
        return this.f5876c.a(WsConstants.KEY_OK_IMPL_ENABLE, true);
    }

    public void c(boolean z) {
        this.f5876c.a().a(WsConstants.KEY_OK_IMPL_ENABLE, z).b();
    }

    public long f() {
        return this.f5876c.a(WsConstants.KEY_IO_LIMIT, WsConstants.DEFAULT_IO_LIMIT);
    }

    public void a(long j2) {
        if (j2 <= 0) {
            return;
        }
        this.f5876c.a().a(WsConstants.KEY_IO_LIMIT, j2).b();
    }

    public void b(long j2) {
        this.f5876c.a().a(WsConstants.KEY_RETRY_SEND_MSG_DELAY, j2).b();
    }

    public long g() {
        return this.f5876c.a(WsConstants.KEY_RETRY_SEND_MSG_DELAY, 0L);
    }

    public void d(boolean z) {
        this.f5876c.a().a(WsConstants.KEY_ENABLE_REPORT_APP_STATE, z).a();
    }

    public boolean h() {
        return this.f5876c.a(WsConstants.KEY_ENABLE_REPORT_APP_STATE, false);
    }

    public void a(ArrayList<Integer> arrayList) {
        if (arrayList == null) {
            return;
        }
        try {
            JSONArray jSONArray = new JSONArray();
            Iterator<Integer> it = arrayList.iterator();
            while (it.hasNext()) {
                jSONArray.put(it.next().intValue());
            }
            this.f5876c.a().a(WsConstants.KEY_AUTO_CONNECT_DISABLED, jSONArray.toString()).b();
            if (Logger.debug()) {
                Logger.d(f5873a, "setAutoConnectDisabledChannelIds: " + jSONArray);
            }
        } catch (Exception e2) {
            Logger.e(f5873a, e2.getMessage());
        }
    }

    public ArrayList<Integer> i() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        String a2 = this.f5876c.a(WsConstants.KEY_AUTO_CONNECT_DISABLED, "");
        if (TextUtils.isEmpty(a2)) {
            return arrayList;
        }
        try {
            JSONArray jSONArray = new JSONArray(a2);
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                arrayList.add(Integer.valueOf(jSONArray.getInt(i2)));
            }
            if (Logger.debug()) {
                Logger.d(f5873a, "getAutoConnectDisabledChannelIds: " + arrayList);
            }
        } catch (JSONException e2) {
            Logger.e(f5873a, e2.getMessage());
        }
        return arrayList;
    }
}
