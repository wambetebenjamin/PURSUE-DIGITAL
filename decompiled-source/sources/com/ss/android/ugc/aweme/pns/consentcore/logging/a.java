package com.ss.android.ugc.aweme.pns.consentcore.logging;

import android.os.Bundle;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.ss.android.common.lib.AppLogNewUtils;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: ConsentSDKLogger.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u0005¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0002JF\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u0006¨\u0006\u0013"}, d2 = {"Lcom/ss/android/ugc/aweme/pns/consentcore/logging/ConsentSDKLogger;", "", "()V", "onEventV3", "", "eventName", "", "data", "Landroid/os/Bundle;", "trackAPI", "apiName", "apiStatusCode", "apiStatusMsg", "consentKey", "consentStatus", "collectionFlow", "enterFrom", RemoteConfigConstants.RequestFieldKey.APP_ID, "Companion", "pns_consent_sdk_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes9.dex */
public final class a {

    /* renamed from: a  reason: collision with root package name */
    public static final C0343a f18716a = new C0343a(null);

    /* renamed from: b  reason: collision with root package name */
    private static final String f18717b = "pns_consent_sdk";

    /* renamed from: c  reason: collision with root package name */
    private static final String f18718c = "enter_from";

    /* renamed from: d  reason: collision with root package name */
    private static final String f18719d = "api_status_code";

    /* renamed from: e  reason: collision with root package name */
    private static final String f18720e = "api_name";

    /* renamed from: f  reason: collision with root package name */
    private static final String f18721f = "consent_status";

    /* renamed from: g  reason: collision with root package name */
    private static final String f18722g = "collection_flow";

    /* renamed from: h  reason: collision with root package name */
    private static final String f18723h = "api_status_msg";

    /* renamed from: i  reason: collision with root package name */
    private static final String f18724i = "consent_key";

    /* renamed from: j  reason: collision with root package name */
    private static final String f18725j = "app_id";

    /* compiled from: ConsentSDKLogger.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\t\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/ss/android/ugc/aweme/pns/consentcore/logging/ConsentSDKLogger$Companion;", "", "()V", "API_NAME", "", "API_STATUS_CODE", "API_STATUS_MSG", "APP_ID", "COLLECTION_FLOW", "CONSENT_KEY", "CONSENT_STATUS", "ENTER_FROM", "PNS_CONSENT_SDK", "pns_consent_sdk_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    /* renamed from: com.ss.android.ugc.aweme.pns.consentcore.logging.a$a  reason: collision with other inner class name */
    /* loaded from: classes9.dex */
    public static final class C0343a {
        public /* synthetic */ C0343a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private C0343a() {
        }
    }

    public final void a(String apiName, String apiStatusCode, String apiStatusMsg, String consentKey, String consentStatus, String collectionFlow, String enterFrom, String appId) {
        Intrinsics.checkNotNullParameter(apiName, "apiName");
        Intrinsics.checkNotNullParameter(apiStatusCode, "apiStatusCode");
        Intrinsics.checkNotNullParameter(apiStatusMsg, "apiStatusMsg");
        Intrinsics.checkNotNullParameter(consentKey, "consentKey");
        Intrinsics.checkNotNullParameter(consentStatus, "consentStatus");
        Intrinsics.checkNotNullParameter(collectionFlow, "collectionFlow");
        Intrinsics.checkNotNullParameter(enterFrom, "enterFrom");
        Intrinsics.checkNotNullParameter(appId, "appId");
        Bundle bundle = new Bundle();
        bundle.putString("api_name", apiName);
        bundle.putString(f18719d, apiStatusCode);
        bundle.putString(f18723h, apiStatusMsg);
        bundle.putString(f18724i, consentKey);
        bundle.putString(f18721f, consentStatus);
        bundle.putString(f18722g, collectionFlow);
        bundle.putString("app_id", appId);
        bundle.putString(f18718c, enterFrom);
        Unit unit = Unit.INSTANCE;
        a(f18717b, bundle);
    }

    private final void a(String str, Bundle bundle) {
        try {
            AppLogNewUtils.onEventV3Bundle(str, bundle);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
