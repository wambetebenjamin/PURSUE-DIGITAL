package com.bytedance.frameworks.baselib.network.http;

import android.text.TextUtils;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONObject;
/* compiled from: LongConnectionConfig.java */
/* loaded from: classes2.dex */
public class i {

    /* renamed from: a  reason: collision with root package name */
    public static final String f7843a = "frontier_domain_config";

    /* renamed from: b  reason: collision with root package name */
    private static final String f7844b = "domain";

    /* renamed from: c  reason: collision with root package name */
    private static final CopyOnWriteArrayList<String> f7845c = new CopyOnWriteArrayList<>();

    public static void a(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        String[] split = str.split(",");
        CopyOnWriteArrayList<String> copyOnWriteArrayList = f7845c;
        copyOnWriteArrayList.clear();
        copyOnWriteArrayList.addAll(Arrays.asList(split));
    }

    public static String a(JSONObject jSONObject) {
        JSONObject optJSONObject = jSONObject.optJSONObject(f7843a);
        f7845c.clear();
        if (optJSONObject == null) {
            return null;
        }
        Iterator<String> keys = optJSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            if (!TextUtils.isEmpty(next)) {
                f7845c.add(next);
            }
        }
        if (f7845c.isEmpty()) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        int i2 = 0;
        while (true) {
            CopyOnWriteArrayList<String> copyOnWriteArrayList = f7845c;
            if (i2 < copyOnWriteArrayList.size()) {
                sb.append(copyOnWriteArrayList.get(i2));
                if (i2 != copyOnWriteArrayList.size() - 1) {
                    sb.append(',');
                }
                i2++;
            } else {
                return sb.toString();
            }
        }
    }

    public static boolean b(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return f7845c.contains(str);
    }
}
