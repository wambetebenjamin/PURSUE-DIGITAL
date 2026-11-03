package com.ss.android.deviceregister.core.cache.internal;

import android.text.TextUtils;
import com.bytedance.common.utility.o;
/* compiled from: CacheHelper.java */
/* loaded from: classes9.dex */
public abstract class b {

    /* renamed from: a  reason: collision with root package name */
    private b f18182a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: CacheHelper.java */
    /* loaded from: classes9.dex */
    public interface a<L> {
        boolean checkValid(L l);

        L getCache();

        boolean isValueEqual(L l, L l2);

        L load(L l, L l2, b bVar);

        void restoreCache(L l);
    }

    protected abstract String a(String str);

    protected abstract void a(String str, String str2);

    private b a() {
        return this.f18182a;
    }

    public void setSuccessor(b bVar) {
        this.f18182a = bVar;
    }

    public String loadDeviceId(String str, String str2) {
        return (String) a(str, str2, new a<String>() { // from class: com.ss.android.deviceregister.core.cache.internal.b.1
            @Override // com.ss.android.deviceregister.core.cache.internal.b.a
            public String getCache() {
                return b.this.a("device_id");
            }

            @Override // com.ss.android.deviceregister.core.cache.internal.b.a
            public boolean checkValid(String str3) {
                return !TextUtils.isEmpty(str3);
            }

            @Override // com.ss.android.deviceregister.core.cache.internal.b.a
            public void restoreCache(String str3) {
                b.this.a("device_id", str3);
            }

            @Override // com.ss.android.deviceregister.core.cache.internal.b.a
            public String load(String str3, String str4, b bVar) {
                return bVar == null ? str3 : bVar.loadDeviceId(str3, str4);
            }

            @Override // com.ss.android.deviceregister.core.cache.internal.b.a
            public boolean isValueEqual(String str3, String str4) {
                return o.equal(str3, str4);
            }
        });
    }

    private <T> T a(T t, T t2, a<T> aVar) {
        if (aVar == null) {
            throw new IllegalArgumentException("agent == null");
        }
        b a2 = a();
        T cache = aVar.getCache();
        boolean checkValid = aVar.checkValid(t);
        boolean checkValid2 = aVar.checkValid(cache);
        if (!checkValid && checkValid2) {
            t = cache;
        }
        if (a2 != null) {
            T load = aVar.load(t, t2, a2);
            if (!aVar.isValueEqual(load, cache)) {
                aVar.restoreCache(load);
            }
            return load;
        }
        boolean z = false;
        if (checkValid || checkValid2) {
            t2 = t;
        } else {
            z = true;
        }
        if ((z && aVar.checkValid(t2)) || (checkValid && !aVar.isValueEqual(t2, cache))) {
            aVar.restoreCache(t2);
        }
        return t2;
    }

    public void clear(String str) {
        b a2 = a();
        if (a2 != null) {
            a2.clear(str);
        }
    }
}
