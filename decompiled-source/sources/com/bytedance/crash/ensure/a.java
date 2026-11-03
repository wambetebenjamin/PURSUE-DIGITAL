package com.bytedance.crash.ensure;

import com.bytedance.crash.Ensure;
import com.bytedance.crash.monitor.h;
import com.bytedance.news.common.service.manager.e;
import com.bytedance.services.apm.api.IEnsure;
import com.bytedance.services.apm.api.b;
import java.util.Collection;
import java.util.Map;
/* compiled from: EnsureApi.java */
/* loaded from: classes2.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private static final int f6633a = 5;

    /* renamed from: b  reason: collision with root package name */
    private static boolean f6634b = false;

    public static void a() {
        if (f6634b) {
            return;
        }
        f6634b = true;
        try {
            IEnsure iEnsure = new IEnsure() { // from class: com.bytedance.crash.ensure.EnsureApi$1
                @Override // com.bytedance.services.apm.api.IEnsure
                public boolean ensureTrue(boolean z) {
                    return Ensure.ensureTrue(z);
                }

                @Override // com.bytedance.services.apm.api.IEnsure
                public boolean ensureTrue(boolean z, String str) {
                    return Ensure.ensureTrue(z, str);
                }

                @Override // com.bytedance.services.apm.api.IEnsure
                public boolean ensureTrue(boolean z, String str, Map<String, String> map) {
                    return Ensure.ensureTrue(z, str, map);
                }

                @Override // com.bytedance.services.apm.api.IEnsure
                public boolean ensureFalse(boolean z) {
                    return Ensure.ensureFalse(z);
                }

                @Override // com.bytedance.services.apm.api.IEnsure
                public boolean ensureFalse(boolean z, String str) {
                    return Ensure.ensureFalse(z, str);
                }

                @Override // com.bytedance.services.apm.api.IEnsure
                public boolean ensureFalse(boolean z, String str, Map<String, String> map) {
                    return Ensure.ensureFalse(z, str, map);
                }

                @Override // com.bytedance.services.apm.api.IEnsure
                public void ensureNotReachHere() {
                    h.b().c(5);
                }

                @Override // com.bytedance.services.apm.api.IEnsure
                public void ensureNotReachHere(String str) {
                    h.b().a(str, 5);
                }

                @Override // com.bytedance.services.apm.api.IEnsure
                public void ensureNotReachHere(String str, Map<String, String> map) {
                    h.b().a(str, map, 5);
                }

                @Override // com.bytedance.services.apm.api.IEnsure
                public void ensureNotReachHere(Throwable th) {
                    Ensure.ensureNotReachHere(th);
                }

                @Override // com.bytedance.services.apm.api.IEnsure
                public void ensureNotReachHere(Throwable th, String str) {
                    Ensure.ensureNotReachHere(th, str);
                }

                @Override // com.bytedance.services.apm.api.IEnsure
                public void ensureNotReachHere(Throwable th, String str, Map<String, String> map) {
                    Ensure.ensureNotReachHere(th, str, map);
                }

                @Override // com.bytedance.services.apm.api.IEnsure
                public boolean ensureNotEmpty(Collection collection) {
                    return Ensure.ensureNotEmpty(collection);
                }

                @Override // com.bytedance.services.apm.api.IEnsure
                public boolean ensureNotNull(Object obj) {
                    return Ensure.ensureNotNull(obj);
                }

                @Override // com.bytedance.services.apm.api.IEnsure
                public boolean ensureNotNull(Object obj, String str) {
                    return Ensure.ensureNotNull(obj, str);
                }

                @Override // com.bytedance.services.apm.api.IEnsure
                public void reportLogException(int i2, Throwable th, String str) {
                    ensureNotReachHere(th, str);
                }

                @Override // com.bytedance.services.apm.api.IEnsure
                public void reportLogException(Throwable th) {
                    ensureNotReachHere(th);
                }

                @Override // com.bytedance.services.apm.api.IEnsure
                public void reportLogException(Throwable th, String str) {
                    ensureNotReachHere(th, str);
                }
            };
            e.registerService(IEnsure.class, iEnsure);
            b.setEnsureImpl(iEnsure);
        } catch (Throwable unused) {
        }
    }
}
