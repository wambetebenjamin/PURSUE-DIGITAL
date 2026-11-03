package com.ss.android.ugc.aweme.pns.consentapi;

import com.ss.android.ugc.aweme.pns.consentapi.IPNSConsentService;
import com.ttnet.org.chromium.base.BaseSwitches;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: IPNSConsentHandlerService.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\bf\u0018\u00002\u00020\u0001:\u0001\u0015J&\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0014\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007H&J\b\u0010\t\u001a\u00020\u0003H\u0016J&\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u00052\u0014\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007H\u0016J\b\u0010\f\u001a\u00020\rH\u0016J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0010\u001a\u00020\u000fH\u0016J\b\u0010\u0011\u001a\u00020\u000fH\u0016J\b\u0010\u0012\u001a\u00020\u000fH\u0016J\u0010\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\bH&¨\u0006\u0016"}, d2 = {"Lcom/ss/android/ugc/aweme/pns/consentapi/IPNSConsentHandlerService;", "", "canShow", "", "statusEnum", "Lcom/ss/android/ugc/aweme/pns/consentapi/IPNSConsentService$ConsentStatusEnum;", "extra", "", "", "forceShow", "getConsentStatus", "status", "getPriority", "Lcom/ss/android/ugc/aweme/pns/consentapi/IPNSConsentHandlerService$PRIORITY;", "onApproved", "", "onDismiss", "onDisplay", "onRejected", "shouldHandleConsent", "consentKey", "PRIORITY", "pns_consent_sdk_api_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes9.dex */
public interface c {
    b a();

    boolean a(IPNSConsentService.a aVar, Map<String, String> map);

    boolean a(String str);

    IPNSConsentService.a b(IPNSConsentService.a aVar, Map<String, String> map);

    boolean b();

    void c();

    void d();

    void e();

    void f();

    /* compiled from: IPNSConsentHandlerService.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\t"}, d2 = {"Lcom/ss/android/ugc/aweme/pns/consentapi/IPNSConsentHandlerService$PRIORITY;", "", BaseSwitches.V, "", "(Ljava/lang/String;II)V", "getV", "()I", "DEVICE_CONSENT", "DEFAULT", "pns_consent_sdk_api_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    /* loaded from: classes9.dex */
    public enum b {
        DEVICE_CONSENT(0),
        DEFAULT(99);
        

        /* renamed from: c  reason: collision with root package name */
        private final int f18682c;

        b(int i2) {
            this.f18682c = i2;
        }

        public final int a() {
            return this.f18682c;
        }
    }

    /* compiled from: IPNSConsentHandlerService.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
    /* loaded from: classes9.dex */
    public static final class a {
        public static IPNSConsentService.a a(c cVar, IPNSConsentService.a status, Map<String, String> map) {
            Intrinsics.checkNotNullParameter(cVar, "this");
            Intrinsics.checkNotNullParameter(status, "status");
            return status;
        }

        public static boolean b(c cVar) {
            Intrinsics.checkNotNullParameter(cVar, "this");
            return false;
        }

        public static void c(c cVar) {
            Intrinsics.checkNotNullParameter(cVar, "this");
        }

        public static void d(c cVar) {
            Intrinsics.checkNotNullParameter(cVar, "this");
        }

        public static void e(c cVar) {
            Intrinsics.checkNotNullParameter(cVar, "this");
        }

        public static void f(c cVar) {
            Intrinsics.checkNotNullParameter(cVar, "this");
        }

        public static b a(c cVar) {
            Intrinsics.checkNotNullParameter(cVar, "this");
            return b.DEFAULT;
        }
    }
}
