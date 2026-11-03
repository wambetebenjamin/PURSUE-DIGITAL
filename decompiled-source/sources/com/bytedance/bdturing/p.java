package com.bytedance.bdturing;

import com.bytedance.ttgame.module.webview.api.WebViewConfig;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: UserMotionManager.java */
/* loaded from: classes.dex */
public class p {

    /* renamed from: a  reason: collision with root package name */
    private static ReentrantLock f4298a = new ReentrantLock();

    /* renamed from: b  reason: collision with root package name */
    private static List<JSONObject> f4299b = new LinkedList();

    public static void a(long j2, float f2, float f3, int i2, float f4, float f5) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("timestamp", j2);
            jSONObject.put("force", f2);
            jSONObject.put("majorRadius", f3);
            jSONObject.put("phase", i2);
            jSONObject.put(WebViewConfig.LOCATION_X, f4);
            jSONObject.put(WebViewConfig.LOCATION_Y, f5);
            try {
                f4298a.lockInterruptibly();
                f4299b.add(jSONObject);
            } catch (InterruptedException unused) {
            } catch (Throwable th) {
                f4298a.unlock();
                throw th;
            }
            f4298a.unlock();
        } catch (JSONException e2) {
            h.printException(e2);
        }
    }

    public static void a() {
        try {
            f4298a.lockInterruptibly();
            f4299b.clear();
        } catch (Exception unused) {
        } catch (Throwable th) {
            f4298a.unlock();
            throw th;
        }
        f4298a.unlock();
    }

    public static void a(com.bytedance.bdturing.methods.l lVar) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("os_name", "android");
            f4298a.lockInterruptibly();
            jSONObject.put("touch", new JSONArray((Collection) f4299b));
            h.d("onTouch", "responseGetTouchToJs motion list size " + f4299b.size());
            f4299b.clear();
            h.d("onTouch", "responseGetTouchToJs end motion list size " + f4299b.size());
        } catch (Exception unused) {
        } catch (Throwable th) {
            f4298a.unlock();
            throw th;
        }
        f4298a.unlock();
        try {
            lVar.response(1, jSONObject);
        } catch (Exception unused2) {
        }
    }
}
