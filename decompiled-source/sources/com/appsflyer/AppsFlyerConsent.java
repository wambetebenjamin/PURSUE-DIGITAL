package com.appsflyer;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000f\b\u0086\b\u0018\u0000 !2\u00020\u0001:\u0001!B%\b\u0002\u0012\u0006\u0010\b\u001a\u00020\u0002\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u001f\u0010 J\u0010\u0010\u0003\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0003\u0010\u0004J\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u0002HÇ\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0012\u0010\u0007\u001a\u0004\u0018\u00010\u0002HÇ\u0003¢\u0006\u0004\b\u0007\u0010\u0006J2\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\b\u001a\u00020\u00022\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0002HÇ\u0001¢\u0006\u0004\b\u000b\u0010\fJ\u001a\u0010\r\u001a\u00020\u00022\b\u0010\b\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u0010\u001a\u00020\u000fHÖ\u0001¢\u0006\u0004\b\u0010\u0010\u0011J\u0010\u0010\u0013\u001a\u00020\u0012H×\u0001¢\u0006\u0004\b\u0013\u0010\u0014R\u001c\u0010\u0018\u001a\u0004\u0018\u00010\u00028\u0007X\u0087\u0004¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0006R\u001c\u0010\u001b\u001a\u0004\u0018\u00010\u00028\u0007X\u0087\u0004¢\u0006\f\n\u0004\b\u0019\u0010\u0016\u001a\u0004\b\u001a\u0010\u0006R\u001a\u0010\u001e\u001a\u00020\u00028\u0007X\u0087\u0004¢\u0006\f\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001e\u0010\u0004"}, d2 = {"Lcom/appsflyer/AppsFlyerConsent;", "", "", "component1", "()Z", "component2", "()Ljava/lang/Boolean;", "component3", "p0", "p1", "p2", "copy", "(ZLjava/lang/Boolean;Ljava/lang/Boolean;)Lcom/appsflyer/AppsFlyerConsent;", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "", "toString", "()Ljava/lang/String;", "AFInAppEventParameterName", "Ljava/lang/Boolean;", "getHasConsentForAdsPersonalization", "hasConsentForAdsPersonalization", "valueOf", "getHasConsentForDataUsage", "hasConsentForDataUsage", "AFKeystoreWrapper", "Z", "isUserSubjectToGDPR", "<init>", "(ZLjava/lang/Boolean;Ljava/lang/Boolean;)V", "Companion"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes.dex */
public final class AppsFlyerConsent {
    public static final Companion Companion = new Companion(null);
    private final Boolean AFInAppEventParameterName;
    private final boolean AFKeystoreWrapper;
    private final Boolean valueOf;

    public /* synthetic */ AppsFlyerConsent(boolean z, Boolean bool, Boolean bool2, DefaultConstructorMarker defaultConstructorMarker) {
        this(z, bool, bool2);
    }

    public static /* synthetic */ AppsFlyerConsent copy$default(AppsFlyerConsent appsFlyerConsent, boolean z, Boolean bool, Boolean bool2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            z = appsFlyerConsent.AFKeystoreWrapper;
        }
        if ((i2 & 2) != 0) {
            bool = appsFlyerConsent.valueOf;
        }
        if ((i2 & 4) != 0) {
            bool2 = appsFlyerConsent.AFInAppEventParameterName;
        }
        return appsFlyerConsent.copy(z, bool, bool2);
    }

    @JvmStatic
    public static final AppsFlyerConsent forGDPRUser(boolean z, boolean z2) {
        return Companion.forGDPRUser(z, z2);
    }

    @JvmStatic
    public static final AppsFlyerConsent forNonGDPRUser() {
        return Companion.forNonGDPRUser();
    }

    public final boolean component1() {
        return this.AFKeystoreWrapper;
    }

    public final Boolean component2() {
        return this.valueOf;
    }

    public final Boolean component3() {
        return this.AFInAppEventParameterName;
    }

    public final AppsFlyerConsent copy(boolean z, Boolean bool, Boolean bool2) {
        return new AppsFlyerConsent(z, bool, bool2);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof AppsFlyerConsent) {
            AppsFlyerConsent appsFlyerConsent = (AppsFlyerConsent) obj;
            return this.AFKeystoreWrapper == appsFlyerConsent.AFKeystoreWrapper && Intrinsics.areEqual(this.valueOf, appsFlyerConsent.valueOf) && Intrinsics.areEqual(this.AFInAppEventParameterName, appsFlyerConsent.AFInAppEventParameterName);
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [int] */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX WARN: Type inference failed for: r0v7 */
    public final int hashCode() {
        boolean z = this.AFKeystoreWrapper;
        ?? r0 = z;
        if (z) {
            r0 = 1;
        }
        int i2 = r0 * 31;
        Boolean bool = this.valueOf;
        int hashCode = (i2 + (bool == null ? 0 : bool.hashCode())) * 31;
        Boolean bool2 = this.AFInAppEventParameterName;
        return hashCode + (bool2 != null ? bool2.hashCode() : 0);
    }

    public final String toString() {
        return new StringBuilder("AppsFlyerConsent(isUserSubjectToGDPR=").append(this.AFKeystoreWrapper).append(", hasConsentForDataUsage=").append(this.valueOf).append(", hasConsentForAdsPersonalization=").append(this.AFInAppEventParameterName).append(')').toString();
    }

    private AppsFlyerConsent(boolean z, Boolean bool, Boolean bool2) {
        this.AFKeystoreWrapper = z;
        this.valueOf = bool;
        this.AFInAppEventParameterName = bool2;
    }

    public final boolean isUserSubjectToGDPR() {
        return this.AFKeystoreWrapper;
    }

    public final Boolean getHasConsentForDataUsage() {
        return this.valueOf;
    }

    public final Boolean getHasConsentForAdsPersonalization() {
        return this.AFInAppEventParameterName;
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\n\u0010\u000bJ\u001f\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0002H\u0007¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\b\u001a\u00020\u0005H\u0007¢\u0006\u0004\b\b\u0010\t"}, d2 = {"Lcom/appsflyer/AppsFlyerConsent$Companion;", "", "", "p0", "p1", "Lcom/appsflyer/AppsFlyerConsent;", "forGDPRUser", "(ZZ)Lcom/appsflyer/AppsFlyerConsent;", "forNonGDPRUser", "()Lcom/appsflyer/AppsFlyerConsent;", "<init>", "()V"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final AppsFlyerConsent forGDPRUser(boolean z, boolean z2) {
            return new AppsFlyerConsent(true, Boolean.valueOf(z), Boolean.valueOf(z2), null);
        }

        @JvmStatic
        public final AppsFlyerConsent forNonGDPRUser() {
            return new AppsFlyerConsent(false, null, null, null);
        }
    }
}
