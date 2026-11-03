package com.ss.android.deviceregister.core.cache.internal;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import com.bytedance.common.utility.Logger;
import com.bytedance.common.utility.concurrent.e;
import com.ss.android.deviceregister.u;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
/* compiled from: AccountCacheHelper.java */
/* loaded from: classes9.dex */
public class a extends b {

    /* renamed from: e  reason: collision with root package name */
    private static final String f18175e = "AccountCacheHelper";

    /* renamed from: a  reason: collision with root package name */
    private final AccountManager f18176a;

    /* renamed from: b  reason: collision with root package name */
    private Account f18177b;

    /* renamed from: c  reason: collision with root package name */
    private final ConcurrentHashMap<String, String> f18178c = new ConcurrentHashMap<>();

    /* renamed from: d  reason: collision with root package name */
    private volatile Set<String> f18179d = null;

    public a(Context context) {
        this.f18176a = AccountManager.get(context);
    }

    @Override // com.ss.android.deviceregister.core.cache.internal.b
    protected void a(String str, String str2) {
        u.b(u.f18267a, "AccountCacheHelper#cacheString key=" + str + " value=" + str2 + " mAccount=" + this.f18177b);
        if (this.f18177b == null) {
            this.f18178c.put(str, str2);
        } else if (str == null || str2 == null) {
        } else {
            try {
                if (Logger.debug()) {
                    com.ss.android.common.util.b.d("AccountCacheHelper cache string : key = " + str + ",value = " + str2);
                }
                this.f18176a.setUserData(this.f18177b, str, str2);
            } catch (Throwable th) {
                if (Logger.debug()) {
                    com.ss.android.common.util.b.e("save string error,please fix it : ", th);
                }
                th.printStackTrace();
            }
        }
    }

    @Override // com.ss.android.deviceregister.core.cache.internal.b
    protected String a(String str) {
        Account account = this.f18177b;
        if (account == null) {
            return null;
        }
        try {
            String userData = this.f18176a.getUserData(account, str);
            if (Logger.debug()) {
                com.ss.android.common.util.b.d("AccountCacheHelper get cached string : key = " + str + ",value = " + userData);
            }
            return userData;
        } catch (Throwable th) {
            if (Logger.debug()) {
                com.ss.android.common.util.b.e("get string error,please fix it : ");
            }
            th.printStackTrace();
            return null;
        }
    }

    public void setAccount(final Account account) {
        if (account != null) {
            synchronized (this) {
                this.f18177b = account;
                if (this.f18178c.size() <= 0) {
                    return;
                }
                if (this.f18179d != null) {
                    for (String str : this.f18179d) {
                        try {
                            this.f18176a.setUserData(this.f18177b, str, null);
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                }
                e.submitRunnable(new Runnable() { // from class: com.ss.android.deviceregister.core.cache.internal.a.1
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            if (a.this.f18178c != null && a.this.f18178c.size() > 0 && a.this.f18176a != null) {
                                for (Map.Entry entry : a.this.f18178c.entrySet()) {
                                    if (entry != null) {
                                        a.this.f18176a.setUserData(account, (String) entry.getKey(), (String) entry.getValue());
                                    }
                                }
                                a.this.f18178c.clear();
                            }
                        } catch (Exception e3) {
                            e3.printStackTrace();
                        }
                    }
                });
            }
        }
    }

    @Override // com.ss.android.deviceregister.core.cache.internal.b
    public void clear(String str) {
        AccountManager accountManager;
        ConcurrentHashMap<String, String> concurrentHashMap = this.f18178c;
        if (concurrentHashMap != null && concurrentHashMap.containsKey(str)) {
            this.f18178c.remove(str);
        }
        try {
            synchronized (this) {
                if (this.f18177b == null) {
                    if (this.f18179d == null) {
                        this.f18179d = new CopyOnWriteArraySet();
                    }
                    if (!this.f18179d.contains(str)) {
                        this.f18179d.add(str);
                    }
                }
            }
            Account account = this.f18177b;
            if (account != null && (accountManager = this.f18176a) != null) {
                accountManager.setUserData(account, str, null);
            }
        } catch (Exception unused) {
        }
        u.b(u.f18267a, "AccountCacheHelper#clear key=" + str + " mAccount=" + this.f18177b + " getCachedString(key)=" + a(str));
        super.clear(str);
    }
}
