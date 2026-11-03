package com.bytedance.frameworks.baselib.network.config.hostpathmatch;

import android.text.TextUtils;
import android.util.Pair;
import com.bytedance.common.utility.Logger;
import com.bytedance.frameworks.baselib.network.config.hostpathmatch.c;
import com.facebook.react.uimanager.ViewProps;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONObject;
/* compiled from: TTHostPathRuleBaseTNCConfig.java */
/* loaded from: classes2.dex */
public abstract class b<T extends c> {

    /* renamed from: b  reason: collision with root package name */
    private static final String f7463b = "TTHostPathMatchRule";

    /* renamed from: a  reason: collision with root package name */
    protected final AtomicBoolean f7464a = new AtomicBoolean(false);

    /* renamed from: c  reason: collision with root package name */
    private final List<Pair<a, T>> f7465c = new CopyOnWriteArrayList();

    /* renamed from: d  reason: collision with root package name */
    private T f7466d;

    public abstract T a(Object obj);

    public abstract String a();

    boolean a(JSONObject jSONObject) {
        return true;
    }

    public boolean b(JSONObject jSONObject) {
        this.f7464a.set(false);
        this.f7465c.clear();
        if (jSONObject == null || jSONObject.length() == 0) {
            return false;
        }
        JSONObject optJSONObject = jSONObject.optJSONObject(a());
        if (optJSONObject == null || optJSONObject.length() == 0) {
            return true;
        }
        this.f7464a.set(optJSONObject.optInt(ViewProps.ENABLED, 0) > 0);
        if (this.f7464a.get()) {
            if (a(optJSONObject)) {
                Object opt = optJSONObject.opt("default");
                if (opt != null) {
                    this.f7466d = a(opt);
                }
                JSONArray optJSONArray = optJSONObject.optJSONArray("rule_list");
                if (optJSONArray == null || optJSONArray.length() == 0) {
                    return false;
                }
                for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                    JSONObject optJSONObject2 = optJSONArray.optJSONObject(i2);
                    if (optJSONObject2 != null && optJSONObject2.length() != 0) {
                        a aVar = new a();
                        aVar.f7457a = a(optJSONObject2.optJSONArray("host_eq"));
                        aVar.f7458b = a(optJSONObject2.optJSONArray("host_wc"));
                        if ((aVar.f7457a == null || aVar.f7457a.isEmpty()) && (aVar.f7458b == null || aVar.f7458b.isEmpty())) {
                            if (Logger.debug()) {
                                Logger.d(f7463b, "both host_eq and host_wc empty.");
                            }
                        } else {
                            aVar.f7459c = a(optJSONObject2.optJSONArray("path_eq"));
                            aVar.f7460d = a(optJSONObject2.optJSONArray("path_prefix"));
                            aVar.f7461e = a(optJSONObject2.optJSONArray("path_contain"));
                            Set<String> a2 = a(optJSONObject2.optJSONArray("path_pattern"));
                            if (a2 != null) {
                                aVar.f7462f = new HashSet();
                                for (String str : a2) {
                                    try {
                                        aVar.f7462f.add(Pattern.compile(str));
                                    } catch (Throwable unused) {
                                    }
                                }
                            }
                            if ((aVar.f7459c == null || aVar.f7459c.isEmpty()) && ((aVar.f7460d == null || aVar.f7460d.isEmpty()) && ((aVar.f7461e == null || aVar.f7461e.isEmpty()) && (aVar.f7462f == null || aVar.f7462f.isEmpty())))) {
                                if (Logger.debug()) {
                                    Logger.d(f7463b, "no path filter exist.");
                                }
                            } else {
                                Object opt2 = optJSONObject2.opt("value");
                                if (opt2 == null) {
                                    if (Logger.debug()) {
                                        Logger.d(f7463b, "value is null.");
                                    }
                                } else {
                                    T a3 = a(opt2);
                                    if (a3 == null) {
                                        if (Logger.debug()) {
                                            Logger.d(f7463b, "ParseHostPathValue failed.");
                                        }
                                    } else {
                                        String optString = optJSONObject2.optString("sign");
                                        if (!TextUtils.isEmpty(optString)) {
                                            a3.f7467a = optString;
                                        }
                                        this.f7465c.add(new Pair<>(aVar, a3));
                                    }
                                }
                            }
                        }
                    }
                }
                return true;
            }
            return false;
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:114:0x013a A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:118:0x0034 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public T a(java.lang.String r11) {
        /*
            Method dump skipped, instructions count: 334
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.frameworks.baselib.network.config.hostpathmatch.b.a(java.lang.String):com.bytedance.frameworks.baselib.network.config.hostpathmatch.c");
    }

    public void a(boolean z) {
        this.f7464a.set(z);
    }

    public boolean b() {
        return this.f7464a.get();
    }

    public List<Pair<a, T>> c() {
        return this.f7465c;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Set<String> a(JSONArray jSONArray) {
        if (jSONArray == null || jSONArray.length() == 0) {
            return null;
        }
        HashSet hashSet = new HashSet();
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            String optString = jSONArray.optString(i2);
            if (!TextUtils.isEmpty(optString)) {
                hashSet.add(optString);
            }
        }
        return hashSet;
    }
}
