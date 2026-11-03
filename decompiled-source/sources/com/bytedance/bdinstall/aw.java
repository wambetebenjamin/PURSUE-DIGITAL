package com.bytedance.bdinstall;

import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: SubpSyncManager.java */
/* loaded from: classes.dex */
public final class aw {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3742a = "aid";

    /* renamed from: b  reason: collision with root package name */
    static final String f3743b = "ug_install_op_pref";

    /* renamed from: c  reason: collision with root package name */
    private static final String f3744c = "install_info_change";

    /* renamed from: d  reason: collision with root package name */
    private static final String f3745d = "key";

    /* renamed from: e  reason: collision with root package name */
    private static final String f3746e = "value";

    /* renamed from: f  reason: collision with root package name */
    private static final Map<String, String> f3747f = new ConcurrentHashMap();

    /* renamed from: g  reason: collision with root package name */
    private static final Map<String, List<a>> f3748g = new ConcurrentHashMap();

    /* renamed from: h  reason: collision with root package name */
    private static final com.bytedance.bdinstall.util.r<aw> f3749h = new com.bytedance.bdinstall.util.r<aw>() { // from class: com.bytedance.bdinstall.aw.1
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.bytedance.bdinstall.util.r
        /* renamed from: a */
        public aw b(Object... objArr) {
            return new aw((Context) objArr[0]);
        }
    };

    /* renamed from: i  reason: collision with root package name */
    private final Context f3750i;

    /* renamed from: j  reason: collision with root package name */
    private AtomicBoolean f3751j;

    /* compiled from: SubpSyncManager.java */
    /* loaded from: classes.dex */
    public interface a {
        void a(String str, String str2);
    }

    private aw(Context context) {
        this.f3751j = new AtomicBoolean(false);
        this.f3750i = context;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static aw a(Context context) {
        return f3749h.c(context);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(final String str, String str2, String str3) {
        try {
            this.f3750i.getSharedPreferences(f3743b, 0).edit().putString(str2 + "_" + str, str3).apply();
            Uri contentUri = BDInstallProvider.getContentUri(this.f3750i, f3744c);
            if (contentUri == null) {
                return;
            }
            final Uri build = contentUri.buildUpon().appendQueryParameter("key", str2).appendQueryParameter("aid", str).appendQueryParameter("value", str3).build();
            final int[] iArr = new int[1];
            new Runnable() { // from class: com.bytedance.bdinstall.aw.2
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        aw.this.f3750i.getContentResolver().notifyChange(build, null);
                    } catch (Exception unused) {
                        int[] iArr2 = iArr;
                        iArr2[0] = iArr2[0] + 1;
                        if (iArr2[0] <= 4) {
                            u.a(this + "retry " + iArr[0] + " times after 1 second");
                            x.b(str).postDelayed(this, 1000L);
                        }
                    }
                }
            }.run();
        } catch (Exception e2) {
            e2.printStackTrace();
            u.b("sendSubpEvent error", e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(String str, String str2, a aVar) {
        a();
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        synchronized (this) {
            a(str2, aVar);
            Map<String, String> map = f3747f;
            if (map.containsKey(str2)) {
                b(map.get(str2), str, aVar);
                return;
            }
            String string = this.f3750i.getSharedPreferences(f3743b, 0).getString(str2 + "_" + str, null);
            if (!TextUtils.isEmpty(string)) {
                b(string, str, aVar);
            }
        }
    }

    private void a(String str, a aVar) {
        Map<String, List<a>> map = f3748g;
        List<a> list = map.get(str);
        if (list == null) {
            list = new ArrayList<>();
            map.put(str, list);
        }
        list.add(aVar);
    }

    private void b(String str, String str2, a aVar) {
        if (aVar != null) {
            aVar.a(str, str2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Uri uri) {
        synchronized (this) {
            String queryParameter = uri.getQueryParameter("key");
            String queryParameter2 = uri.getQueryParameter("value");
            String queryParameter3 = uri.getQueryParameter("aid");
            if (TextUtils.isEmpty(queryParameter)) {
                return;
            }
            f3747f.put(queryParameter, queryParameter2);
            u.a("install_info send to child process onUpdate " + queryParameter2 + " aid ：" + queryParameter3);
            List<a> list = f3748g.get(queryParameter);
            if (list != null) {
                for (a aVar : list) {
                    if (aVar != null) {
                        aVar.a(queryParameter2, queryParameter3);
                    }
                }
            }
        }
    }

    private void a() {
        Uri contentUri;
        if (this.f3751j.compareAndSet(false, true) && (contentUri = BDInstallProvider.getContentUri(this.f3750i, f3744c)) != null) {
            this.f3750i.getContentResolver().registerContentObserver(contentUri, true, new ContentObserver(x.a()) { // from class: com.bytedance.bdinstall.aw.3
                @Override // android.database.ContentObserver
                public void onChange(boolean z, Uri uri) {
                    super.onChange(z);
                    aw.this.a(uri);
                }
            });
        }
    }
}
