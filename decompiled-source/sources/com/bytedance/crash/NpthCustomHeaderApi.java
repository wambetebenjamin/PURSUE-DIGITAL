package com.bytedance.crash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
/* loaded from: classes2.dex */
public class NpthCustomHeaderApi {

    /* renamed from: a  reason: collision with root package name */
    private static CopyOnWriteArrayList<ICustomHeaderService> f6101a = new CopyOnWriteArrayList<>();

    /* loaded from: classes2.dex */
    public interface ICustomHeaderService {
        Map<String, String> a();
    }

    public static void a(ICustomHeaderService iCustomHeaderService) {
        if (iCustomHeaderService == null) {
            return;
        }
        f6101a.add(iCustomHeaderService);
    }

    public static void b(ICustomHeaderService iCustomHeaderService) {
        if (iCustomHeaderService == null) {
            return;
        }
        f6101a.remove(iCustomHeaderService);
    }

    public static Map<String, String> a() {
        if (f6101a.isEmpty()) {
            return null;
        }
        ArrayList<ICustomHeaderService> arrayList = new ArrayList(f6101a);
        HashMap hashMap = new HashMap();
        for (ICustomHeaderService iCustomHeaderService : arrayList) {
            hashMap.putAll(iCustomHeaderService.a());
        }
        return hashMap;
    }
}
