package com.monitor.cloudmessage;

import android.content.Context;
import com.monitor.cloudmessage.callback.c;
import com.monitor.cloudmessage.callback.d;
import com.monitor.cloudmessage.callback.f;
import com.monitor.cloudmessage.callback.g;
import com.monitor.cloudmessage.callback.h;
import com.monitor.cloudmessage.callback.i;
import com.monitor.cloudmessage.callback.j;
import java.io.File;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
/* compiled from: CloudMessageManager.java */
/* loaded from: classes9.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private static volatile a f17327a;

    public static File dumpFileDir() {
        return null;
    }

    public static String getAid() {
        return "";
    }

    public static String[] getBlackListForCloudContrlInf() {
        return null;
    }

    public static String getDumpFileDir() {
        return "";
    }

    public static String getUid() {
        return "";
    }

    public static String getUpdateVersionCode() {
        return "";
    }

    public static void init(Context context) {
    }

    public static boolean isFetchDataWithSocket() {
        return false;
    }

    public static void setABTestConsumerSafely(com.monitor.cloudmessage.callback.a aVar) {
    }

    public static void setAid(String str) {
    }

    public static void setAlogConsumerSafely(c cVar) {
    }

    public static void setBlackListForCloudContrlInf(String[] strArr) {
    }

    public static void setDumpFileDir(String str) {
    }

    public static void setFetchDataWithSocket(boolean z) {
    }

    public static void setMonitorLogConsumerSafely(f fVar) {
    }

    public static void setPatchMessageConsumerSafely(g gVar) {
    }

    public static void setPluginMessageComsumerSafely(h hVar) {
    }

    public static void setRouteConsumerSafely(i iVar) {
    }

    public static void setTemplateConsumerSafely(j jVar) {
    }

    public static void setUid(String str) {
    }

    public static void setUpdateVersionCode(String str) {
    }

    public HashMap<String, String> getCommonParams() {
        return null;
    }

    public Context getContext() {
        return null;
    }

    public Enumeration getObverserList() {
        return null;
    }

    public void handleCloudMessage(com.monitor.cloudmessage.entity.a aVar) {
    }

    public void handleCloudMessage(String str) {
    }

    public void handleCloudMessage(byte[] bArr, Map<String, String> map) {
    }

    public void registerCloudMessageObverser(com.monitor.cloudmessage.obversable.b bVar) {
    }

    public void setCommandReiveObserver(d dVar) {
    }

    public void setCommonParams(HashMap<String, String> hashMap) {
    }

    public void unregisterCloudMessageObverser(com.monitor.cloudmessage.obversable.b bVar) {
    }

    private a() {
    }

    public static a getInstance() {
        if (f17327a == null) {
            synchronized (a.class) {
                if (f17327a == null) {
                    f17327a = new a();
                }
            }
        }
        return f17327a;
    }
}
