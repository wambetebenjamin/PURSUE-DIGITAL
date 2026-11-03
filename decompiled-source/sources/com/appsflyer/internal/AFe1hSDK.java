package com.appsflyer.internal;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\b\u0086\b\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\t\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\t\u0012\b\b\u0002\u0010\u0014\u001a\u00020\f¢\u0006\u0004\b\u0015\u0010\u0016J\u001a\u0010\u0004\u001a\u00020\u00032\b\u0010\u0002\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0007\u001a\u00020\u0006HÖ\u0001¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\n\u001a\u00020\tH×\u0001¢\u0006\u0004\b\n\u0010\u000bR\u0011\u0010\u000f\u001a\u00020\fX\u0007¢\u0006\u0006\n\u0004\b\r\u0010\u000eR\u0011\u0010\r\u001a\u00020\tX\u0007¢\u0006\u0006\n\u0004\b\u0010\u0010\u0011R\u0013\u0010\u0012\u001a\u0004\u0018\u00010\tX\u0007¢\u0006\u0006\n\u0004\b\u0012\u0010\u0011"}, d2 = {"Lcom/appsflyer/internal/AFe1hSDK;", "", "p0", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "", "toString", "()Ljava/lang/String;", "Lcom/appsflyer/internal/AFe1iSDK;", "AFInAppEventType", "Lcom/appsflyer/internal/AFe1iSDK;", "values", "AFInAppEventParameterName", "Ljava/lang/String;", "valueOf", "p1", "p2", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lcom/appsflyer/internal/AFe1iSDK;)V"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes.dex */
public final class AFe1hSDK {
    public final String AFInAppEventParameterName;
    public final AFe1iSDK AFInAppEventType;
    public final String valueOf;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof AFe1hSDK) {
            AFe1hSDK aFe1hSDK = (AFe1hSDK) obj;
            return Intrinsics.areEqual(this.AFInAppEventParameterName, aFe1hSDK.AFInAppEventParameterName) && Intrinsics.areEqual(this.valueOf, aFe1hSDK.valueOf) && this.AFInAppEventType == aFe1hSDK.AFInAppEventType;
        }
        return false;
    }

    public final int hashCode() {
        int hashCode = this.AFInAppEventParameterName.hashCode() * 31;
        String str = this.valueOf;
        return ((hashCode + (str == null ? 0 : str.hashCode())) * 31) + this.AFInAppEventType.hashCode();
    }

    public final String toString() {
        return new StringBuilder("AFe1hSDK(AFInAppEventType=").append(this.AFInAppEventParameterName).append(", valueOf=").append(this.valueOf).append(", values=").append(this.AFInAppEventType).append(')').toString();
    }

    public AFe1hSDK(String str, String str2, AFe1iSDK aFe1iSDK) {
        Intrinsics.checkNotNullParameter(str, "");
        Intrinsics.checkNotNullParameter(aFe1iSDK, "");
        this.AFInAppEventParameterName = str;
        this.valueOf = str2;
        this.AFInAppEventType = aFe1iSDK;
    }
}
