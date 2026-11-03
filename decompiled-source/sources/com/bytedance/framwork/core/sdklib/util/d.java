package com.bytedance.framwork.core.sdklib.util;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONObject;
/* compiled from: ListUtils.java */
/* loaded from: classes2.dex */
public class d {

    /* compiled from: ListUtils.java */
    /* loaded from: classes2.dex */
    public interface a<A, B> {
        boolean equals(A a2, B b2);
    }

    public static boolean isEmpty(List<?> list) {
        return list == null || list.size() == 0;
    }

    public static List<String> a(JSONObject jSONObject, String str) {
        try {
            JSONArray optJSONArray = jSONObject.optJSONArray(str);
            if (optJSONArray == null || optJSONArray.length() <= 0) {
                return null;
            }
            int length = optJSONArray.length();
            ArrayList arrayList = new ArrayList(length);
            for (int i2 = 0; i2 < length; i2++) {
                String string = optJSONArray.getString(i2);
                if (!TextUtils.isEmpty(string)) {
                    arrayList.add(string);
                }
            }
            return arrayList;
        } catch (Exception unused) {
            return null;
        }
    }

    public static List<Pattern> b(JSONObject jSONObject, String str) {
        try {
            JSONArray optJSONArray = jSONObject.optJSONArray(str);
            if (optJSONArray == null || optJSONArray.length() <= 0) {
                return null;
            }
            int length = optJSONArray.length();
            ArrayList arrayList = new ArrayList(length);
            for (int i2 = 0; i2 < length; i2++) {
                String string = optJSONArray.getString(i2);
                if (!TextUtils.isEmpty(string)) {
                    arrayList.add(Pattern.compile(string));
                }
            }
            return arrayList;
        } catch (Exception unused) {
            return null;
        }
    }

    public static List<String> c(JSONObject jSONObject, String str) {
        try {
            JSONObject optJSONObject = jSONObject.optJSONObject(str);
            if (com.bytedance.apm6.util.f.isEmpty(optJSONObject)) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            Iterator<String> keys = optJSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                if (optJSONObject.optInt(next) == 1) {
                    arrayList.add(next);
                }
            }
            return arrayList;
        } catch (Exception unused) {
            return null;
        }
    }

    public static List<Pattern> d(JSONObject jSONObject, String str) {
        try {
            JSONObject optJSONObject = jSONObject.optJSONObject(str);
            if (com.bytedance.apm6.util.f.isEmpty(optJSONObject)) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            Iterator<String> keys = optJSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                if (optJSONObject.optInt(next) == 1) {
                    arrayList.add(Pattern.compile(next));
                }
            }
            return arrayList;
        } catch (Exception unused) {
            return null;
        }
    }

    public static <L, O> boolean removeAll(Collection<L> collection, O o, a<? super L, O> aVar) {
        Iterator<L> it = collection.iterator();
        boolean z = false;
        while (it.hasNext()) {
            if (aVar.equals(it.next(), o)) {
                it.remove();
                z = true;
            }
        }
        return z;
    }
}
