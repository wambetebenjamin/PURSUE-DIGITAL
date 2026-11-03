package gsdk.impl.compliance.protocol;

import javax.inject.Singleton;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: PNSConsentStorageServiceImpl.kt */
@Singleton
/* loaded from: classes10.dex */
public final class i implements com.ss.android.ugc.aweme.pns.consentapi.e {

    /* renamed from: a  reason: collision with root package name */
    private final String f19984a = "PNSConsent";

    @Override // com.ss.android.ugc.aweme.pns.consentapi.e
    public void b(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        j.a(this.f19984a, "clearAllValue: ");
    }

    @Override // com.ss.android.ugc.aweme.pns.consentapi.e
    public String a(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        j.a(this.f19984a, "readValue: ");
        return "";
    }

    @Override // com.ss.android.ugc.aweme.pns.consentapi.e
    public void a(String key, String value) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(value, "value");
        j.a(this.f19984a, "saveValue: ");
    }
}
