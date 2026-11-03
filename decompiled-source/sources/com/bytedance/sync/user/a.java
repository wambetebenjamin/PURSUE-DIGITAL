package com.bytedance.sync.user;

import android.content.Context;
import android.text.TextUtils;
import com.bytedance.sync.interfaze.e;
import com.bytedance.sync.l;
import com.ss.android.ug.bus.c;
import java.util.ArrayList;
import java.util.List;
/* compiled from: AccountEventSynchronizer.java */
/* loaded from: classes3.dex */
public class a implements e {

    /* renamed from: a  reason: collision with root package name */
    private final com.bytedance.sync.interfaze.b f13697a;

    /* renamed from: b  reason: collision with root package name */
    private final Context f13698b;

    /* renamed from: c  reason: collision with root package name */
    private String f13699c;

    /* renamed from: d  reason: collision with root package name */
    private final List<c.a<Void>> f13700d = new ArrayList();

    /* renamed from: e  reason: collision with root package name */
    private final List<c.a<Void>> f13701e = new ArrayList();

    public a(Context context, com.bytedance.sync.interfaze.b bVar) {
        this.f13698b = context;
        this.f13697a = bVar;
    }

    @Override // com.bytedance.sync.interfaze.e
    public void startListenAccountChangeEvent() {
        this.f13697a.registerOnAccountSwitchListener(new c.a<com.ss.android.ug.bus.account.event.c>() { // from class: com.bytedance.sync.user.a.1
            @Override // com.ss.android.ug.bus.c.a
            public void onCall(com.ss.android.ug.bus.account.event.c cVar) {
                a.this.a(true);
            }
        });
        this.f13697a.registerOnLoginListener(new c.a<com.ss.android.ug.bus.account.event.a>() { // from class: com.bytedance.sync.user.a.2
            @Override // com.ss.android.ug.bus.c.a
            public void onCall(com.ss.android.ug.bus.account.event.a aVar) {
                a.this.a(true);
            }
        });
        this.f13697a.registerOnLogoutListener(new c.a<com.ss.android.ug.bus.account.event.b>() { // from class: com.bytedance.sync.user.a.3
            @Override // com.ss.android.ug.bus.c.a
            public void onCall(com.ss.android.ug.bus.account.event.b bVar) {
                a.this.a(false);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z) {
        synchronized (this) {
            for (c.a<Void> aVar : this.f13700d) {
                if (aVar != null) {
                    aVar.onCall(null);
                }
            }
        }
        l.inst().monitorAccountChanged(z);
    }

    @Override // com.bytedance.sync.interfaze.e
    public void updateDid(String str) {
        this.f13699c = str;
        com.bytedance.sync.settings.c.inst(this.f13698b).setDid(str);
        synchronized (this) {
            for (c.a<Void> aVar : this.f13701e) {
                if (aVar != null) {
                    aVar.onCall(null);
                }
            }
        }
    }

    @Override // com.bytedance.sync.interfaze.e
    public C0209a getDeviceInfo() {
        String str = this.f13699c;
        if (TextUtils.isEmpty(str)) {
            str = com.bytedance.sync.settings.c.inst(this.f13698b).getSavedDid();
        }
        String secUid = this.f13697a.getSecUid();
        if (TextUtils.isEmpty(secUid)) {
            secUid = "0";
        }
        return new C0209a(str, secUid);
    }

    @Override // com.bytedance.sync.interfaze.e
    public void addAccountLoginOrSwitchEventListener(c.a<Void> aVar) {
        synchronized (this) {
            this.f13700d.add(aVar);
        }
    }

    @Override // com.bytedance.sync.interfaze.e
    public void removeAccountLoginOrSwitchEventListener(c.a<Void> aVar) {
        synchronized (this) {
            this.f13700d.remove(aVar);
        }
    }

    @Override // com.bytedance.sync.interfaze.e
    public void addDeviceInfoChangedListener(c.a<Void> aVar) {
        synchronized (this) {
            this.f13700d.add(aVar);
            this.f13701e.add(aVar);
        }
    }

    /* compiled from: AccountEventSynchronizer.java */
    /* renamed from: com.bytedance.sync.user.a$a  reason: collision with other inner class name */
    /* loaded from: classes3.dex */
    public static class C0209a {
        public String did;
        public String uid;

        public C0209a(String str, String str2) {
            this.did = str;
            this.uid = str2;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            C0209a c0209a = (C0209a) obj;
            String str = this.did;
            if (str == null ? c0209a.did == null : str.equals(c0209a.did)) {
                String str2 = this.uid;
                String str3 = c0209a.uid;
                return str2 != null ? str2.equals(str3) : str3 == null;
            }
            return false;
        }

        public int hashCode() {
            String str = this.did;
            int hashCode = (str != null ? str.hashCode() : 0) * 31;
            String str2 = this.uid;
            return hashCode + (str2 != null ? str2.hashCode() : 0);
        }

        public String getDid() {
            return this.did;
        }

        public String getUid() {
            return this.uid;
        }
    }
}
