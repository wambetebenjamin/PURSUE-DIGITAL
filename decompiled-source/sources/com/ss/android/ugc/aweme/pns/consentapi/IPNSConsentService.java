package com.ss.android.ugc.aweme.pns.consentapi;

import com.bytedance.apm.util.i;
import com.bytedance.bdturing.methods.l;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.ss.android.ugc.aweme.pns.consentapi.network.IConsentApi;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: IPNSConsentService.kt */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\bg\u0018\u00002\u00020\u0001:\u0001\u001dJ(\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0016\b\u0002\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0007H&J\u001c\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\u0005H&J \u0010\r\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0005H&J\u0010\u0010\u0013\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\u0015H&J\u001a\u0010\u0016\u001a\u00020\t2\b\b\u0002\u0010\u0017\u001a\u00020\u00182\u0006\u0010\f\u001a\u00020\u0005H&J\u0010\u0010\u0019\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\u0015H&J(\u0010\u001a\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u001b\u001a\u00020\u00032\u0006\u0010\u001c\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u0005H&¨\u0006\u001e"}, d2 = {"Lcom/ss/android/ugc/aweme/pns/consentapi/IPNSConsentService;", "", "checkConsentStatus", "Lcom/ss/android/ugc/aweme/pns/consentapi/IPNSConsentService$ConsentStatusEnum;", "consentKey", "", "extra", "", "fetchConsentRecord", "", l.VALUE_CALLBACK, "Lcom/ss/android/ugc/aweme/pns/consentapi/IPNSAPICallback;", "enterFrom", "initConsentSDK", "consentApi", "Lcom/ss/android/ugc/aweme/pns/consentapi/network/IConsentApi;", "consentStorageService", "Lcom/ss/android/ugc/aweme/pns/consentapi/IPNSConsentStorageService;", RemoteConfigConstants.RequestFieldKey.APP_ID, "registerConsentRecordsUpdateListener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/ss/android/ugc/aweme/pns/consentapi/IPNSConsentRecordsUpdateListener;", "resetRecords", "isKidsMode", "", "unregisterConsentStatusListener", "updateConsentStatus", "consentStatusEnum", "consentCollectionFlow", "ConsentStatusEnum", "pns_consent_sdk_api_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes9.dex */
public interface IPNSConsentService {
    a checkConsentStatus(String str, Map<String, String> map);

    void fetchConsentRecord(com.ss.android.ugc.aweme.pns.consentapi.b bVar, String str);

    void initConsentSDK(IConsentApi iConsentApi, e eVar, String str);

    void registerConsentRecordsUpdateListener(d dVar);

    void resetRecords(boolean z, String str);

    void unregisterConsentStatusListener(d dVar);

    void updateConsentStatus(String str, a aVar, String str2, String str3);

    /* compiled from: IPNSConsentService.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\t\b\u0086\u0001\u0018\u0000 \u000b2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u000bB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\f"}, d2 = {"Lcom/ss/android/ugc/aweme/pns/consentapi/IPNSConsentService$ConsentStatusEnum;", "", i.TAG, "", "(Ljava/lang/String;II)V", "getI", "()I", "APPROVE", "REJECT", "CONSENT_UNDECIDED", "CONSENT_NOT_PRESENT_IN_CACHE", "Companion", "pns_consent_sdk_api_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    /* loaded from: classes9.dex */
    public enum a {
        APPROVE(1),
        REJECT(2),
        CONSENT_UNDECIDED(3),
        CONSENT_NOT_PRESENT_IN_CACHE(4);
        

        /* renamed from: a  reason: collision with root package name */
        public static final C0341a f18669a = new C0341a(null);

        /* renamed from: f  reason: collision with root package name */
        private final int f18675f;

        a(int i2) {
            this.f18675f = i2;
        }

        public final int a() {
            return this.f18675f;
        }

        /* compiled from: IPNSConsentService.kt */
        @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/ss/android/ugc/aweme/pns/consentapi/IPNSConsentService$ConsentStatusEnum$Companion;", "", "()V", FirebaseAnalytics.Event.SEARCH, "Lcom/ss/android/ugc/aweme/pns/consentapi/IPNSConsentService$ConsentStatusEnum;", "status", "", "pns_consent_sdk_api_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
        /* renamed from: com.ss.android.ugc.aweme.pns.consentapi.IPNSConsentService$a$a  reason: collision with other inner class name */
        /* loaded from: classes9.dex */
        public static final class C0341a {
            public /* synthetic */ C0341a(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private C0341a() {
            }

            public final a a(String status) {
                Intrinsics.checkNotNullParameter(status, "status");
                a[] values = a.values();
                int length = values.length;
                int i2 = 0;
                while (i2 < length) {
                    a aVar = values[i2];
                    i2++;
                    String name = aVar.name();
                    Objects.requireNonNull(name, "null cannot be cast to non-null type java.lang.String");
                    String lowerCase = name.toLowerCase(Locale.ROOT);
                    Intrinsics.checkNotNullExpressionValue(lowerCase, "(this as java.lang.Strin….toLowerCase(Locale.ROOT)");
                    String lowerCase2 = status.toLowerCase(Locale.ROOT);
                    Intrinsics.checkNotNullExpressionValue(lowerCase2, "(this as java.lang.Strin….toLowerCase(Locale.ROOT)");
                    if (Intrinsics.areEqual(lowerCase, lowerCase2)) {
                        return aVar;
                    }
                }
                return null;
            }
        }
    }

    /* compiled from: IPNSConsentService.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
    /* loaded from: classes9.dex */
    public static final class b {
        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ a a(IPNSConsentService iPNSConsentService, String str, Map map, int i2, Object obj) {
            if (obj == null) {
                if ((i2 & 2) != 0) {
                    map = null;
                }
                return iPNSConsentService.checkConsentStatus(str, map);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: checkConsentStatus");
        }

        public static /* synthetic */ void a(IPNSConsentService iPNSConsentService, com.ss.android.ugc.aweme.pns.consentapi.b bVar, String str, int i2, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: fetchConsentRecord");
            }
            if ((i2 & 1) != 0) {
                bVar = null;
            }
            iPNSConsentService.fetchConsentRecord(bVar, str);
        }

        public static /* synthetic */ void a(IPNSConsentService iPNSConsentService, boolean z, String str, int i2, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: resetRecords");
            }
            if ((i2 & 1) != 0) {
                z = false;
            }
            iPNSConsentService.resetRecords(z, str);
        }
    }
}
