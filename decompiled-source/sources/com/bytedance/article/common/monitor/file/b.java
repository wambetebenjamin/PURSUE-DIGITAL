package com.bytedance.article.common.monitor.file;

import android.text.TextUtils;
import java.util.concurrent.ConcurrentHashMap;
/* compiled from: FileUploadManager.java */
@Deprecated
/* loaded from: classes.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    private static ConcurrentHashMap<String, c> f3628a = new ConcurrentHashMap<>();

    public static void registerFileCallBack(String str, c cVar) {
        if (TextUtils.isEmpty(str) || cVar == null) {
            throw new IllegalArgumentException("type not be null or callback not be null");
        }
        if (f3628a.containsKey(str)) {
            return;
        }
        f3628a.put(str, cVar);
    }

    public static void registerFileCallBack(String str, d dVar) {
        registerFileCallBack(str, (c) dVar);
    }

    public static void unRegisterFileCallBack(String str) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("type must not be null");
        }
        f3628a.remove(str);
    }

    public static c getFileCallBackForType(String str) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("type must not be null");
        }
        return f3628a.get(str);
    }
}
