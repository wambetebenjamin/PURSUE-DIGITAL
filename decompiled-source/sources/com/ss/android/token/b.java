package com.ss.android.token;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.bytedance.librarian.c;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
/* compiled from: TTTokenConfig.java */
/* loaded from: classes9.dex */
public class b {

    /* renamed from: e  reason: collision with root package name */
    private static final String f18527e = "com_ss_android_token_sp_host";

    /* renamed from: f  reason: collision with root package name */
    private static final String f18528f = "ss_app_config";

    /* renamed from: g  reason: collision with root package name */
    private static final String f18529g = "share_cookie_host_list";

    /* renamed from: a  reason: collision with root package name */
    private String f18530a;

    /* renamed from: c  reason: collision with root package name */
    private String f18532c;

    /* renamed from: j  reason: collision with root package name */
    private a f18536j;
    private InterfaceC0339b k;

    /* renamed from: b  reason: collision with root package name */
    private Set<String> f18531b = new CopyOnWriteArraySet();

    /* renamed from: d  reason: collision with root package name */
    private boolean f18533d = false;

    /* renamed from: h  reason: collision with root package name */
    private boolean f18534h = false;

    /* renamed from: i  reason: collision with root package name */
    private long f18535i = 600000;

    /* compiled from: TTTokenConfig.java */
    /* loaded from: classes9.dex */
    public interface a {
        boolean inBlockList(String str);
    }

    /* compiled from: TTTokenConfig.java */
    @Deprecated
    /* renamed from: com.ss.android.token.b$b  reason: collision with other inner class name */
    /* loaded from: classes9.dex */
    public interface InterfaceC0339b {
        boolean isLocalTest();
    }

    private SharedPreferences e() {
        Context b2 = d.b();
        if (b2 != null) {
            return b2.getSharedPreferences(f18527e, 0);
        }
        return null;
    }

    private SharedPreferences f() {
        Context b2 = d.b();
        if (b2 != null) {
            return b2.getSharedPreferences(f18528f, 0);
        }
        return null;
    }

    public boolean hasCallAddHostList() {
        return this.f18533d;
    }

    public b setTokenSaveName(String str) {
        this.f18532c = str;
        return this;
    }

    public String getTokenSaveName() {
        return this.f18532c;
    }

    public b() {
        String str = "https://" + d.d();
        this.f18530a = str;
        String topDomain = i.getTopDomain(str);
        if (topDomain != null) {
            this.f18531b.add(topDomain);
        }
    }

    public String getBeatHost() {
        return this.f18530a;
    }

    public Set<String> getHostList() {
        return this.f18531b;
    }

    public boolean isHostListValid() {
        this.f18531b.remove(null);
        return !this.f18531b.isEmpty();
    }

    public void saveHostList() {
        SharedPreferences e2 = e();
        if (e2 == null || !isHostListValid()) {
            return;
        }
        e2.edit().putStringSet(f18529g, this.f18531b).apply();
    }

    public void addHostListFromLocalAndTTNet() {
        addFromLocal();
        addFromTTNet();
        saveHostList();
    }

    public void addFromLocal() {
        Set<String> stringSet;
        SharedPreferences e2 = e();
        if (e2 == null || (stringSet = e2.getStringSet(f18529g, null)) == null) {
            return;
        }
        this.f18531b.addAll(stringSet);
    }

    public void addFromTTNet() {
        SharedPreferences f2 = f();
        if (f2 != null) {
            String string = f2.getString(f18529g, "");
            HashSet hashSet = new HashSet();
            if (!TextUtils.isEmpty(string)) {
                String[] split = string.split(",");
                if (split.length > 0) {
                    for (String str : split) {
                        if (!TextUtils.isEmpty(str)) {
                            if (str.length() > 1 && str.startsWith(c.a.DOT)) {
                                str = str.substring(1);
                            }
                            hashSet.add(str);
                        }
                    }
                }
            }
            this.f18531b.addAll(hashSet);
        }
    }

    public b addHostList(Collection<String> collection) {
        a(collection);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public long a() {
        return this.f18535i;
    }

    public b setUpdateInterval(long j2) {
        this.f18535i = j2;
        return this;
    }

    public b setTokenSign(boolean z) {
        this.f18534h = z;
        return this;
    }

    protected boolean b() {
        return this.f18534h;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public a c() {
        return this.f18536j;
    }

    public b setBlackList(a aVar) {
        this.f18536j = aVar;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(Collection<String> collection) {
        if (collection == null || collection.size() <= 0) {
            return;
        }
        this.f18533d = true;
        this.f18531b.addAll(collection);
        saveHostList();
    }

    @Deprecated
    public b setLocalTest(InterfaceC0339b interfaceC0339b) {
        this.k = interfaceC0339b;
        return this;
    }

    protected InterfaceC0339b d() {
        return this.k;
    }
}
