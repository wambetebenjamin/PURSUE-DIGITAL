package com.appsflyer.internal;

import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B+\b\u0002\u0012\u0014\u0010\u0003\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000b\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u000e¢\u0006\u0004\b\u0011\u0010\u0012J\u0015\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0005\u0010\u0006J\u001f\u0010\t\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\b\u0010\u0007\u001a\u0004\u0018\u00010\u0001¢\u0006\u0004\b\t\u0010\nR\"\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000b8\u0002X\u0083\u0004¢\u0006\u0006\n\u0004\b\f\u0010\rR\u0016\u0010\u0010\u001a\u0004\u0018\u00010\u000e8\u0002X\u0083\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u000f"}, d2 = {"Lcom/appsflyer/internal/AFc1oSDK;", "", "", "p0", "", "AFInAppEventParameterName", "(Ljava/lang/String;)Z", "p1", "", "AFInAppEventType", "(Ljava/lang/String;Ljava/lang/Object;)V", "", "AFKeystoreWrapper", "Ljava/util/Map;", "Lcom/appsflyer/internal/AFc1kSDK;", "Lcom/appsflyer/internal/AFc1kSDK;", "values", "<init>", "(Ljava/util/Map;Lcom/appsflyer/internal/AFc1kSDK;)V", "AFa1tSDK"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes.dex */
public final class AFc1oSDK {
    public static final AFa1tSDK AFa1tSDK = new AFa1tSDK(null);
    public final AFc1kSDK AFInAppEventParameterName;
    public final Map<String, Object> AFKeystoreWrapper;

    public /* synthetic */ AFc1oSDK(Map map, AFc1kSDK aFc1kSDK, DefaultConstructorMarker defaultConstructorMarker) {
        this(map, aFc1kSDK);
    }

    @JvmStatic
    public static final AFc1oSDK valueOf(AFa1pSDK aFa1pSDK) {
        return AFa1tSDK.AFInAppEventType(aFa1pSDK);
    }

    @JvmStatic
    public static final AFc1oSDK values(AFc1kSDK aFc1kSDK) {
        return AFa1tSDK.values(aFc1kSDK);
    }

    private AFc1oSDK(Map<String, Object> map, AFc1kSDK aFc1kSDK) {
        this.AFKeystoreWrapper = map;
        this.AFInAppEventParameterName = aFc1kSDK;
    }

    /* synthetic */ AFc1oSDK(Map map, AFc1kSDK aFc1kSDK, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(map, (i2 & 2) != 0 ? null : aFc1kSDK);
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\n\u0010\u000bJ\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0007¢\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\b\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0007H\u0007¢\u0006\u0004\b\b\u0010\t"}, d2 = {"Lcom/appsflyer/internal/AFc1oSDK$AFa1tSDK;", "", "Lcom/appsflyer/internal/AFa1pSDK;", "p0", "Lcom/appsflyer/internal/AFc1oSDK;", "AFInAppEventType", "(Lcom/appsflyer/internal/AFa1pSDK;)Lcom/appsflyer/internal/AFc1oSDK;", "Lcom/appsflyer/internal/AFc1kSDK;", "values", "(Lcom/appsflyer/internal/AFc1kSDK;)Lcom/appsflyer/internal/AFc1oSDK;", "<init>", "()V"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes.dex */
    public static final class AFa1tSDK {
        public /* synthetic */ AFa1tSDK(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private AFa1tSDK() {
        }

        @JvmStatic
        public static AFc1oSDK AFInAppEventType(AFa1pSDK aFa1pSDK) {
            Intrinsics.checkNotNullParameter(aFa1pSDK, "");
            Map<String, Object> AFInAppEventType = aFa1pSDK.AFInAppEventType();
            Intrinsics.checkNotNullExpressionValue(AFInAppEventType, "");
            return new AFc1oSDK(AFInAppEventType, null, 2, null);
        }

        @JvmStatic
        public static AFc1oSDK values(AFc1kSDK aFc1kSDK) {
            Intrinsics.checkNotNullParameter(aFc1kSDK, "");
            return new AFc1oSDK(new LinkedHashMap(), aFc1kSDK, null);
        }
    }

    public final void AFInAppEventType(String str, Object obj) {
        Intrinsics.checkNotNullParameter(str, "");
        this.AFKeystoreWrapper.put(str, obj);
        AFc1kSDK aFc1kSDK = this.AFInAppEventParameterName;
        if (aFc1kSDK != null) {
            aFc1kSDK.valueOf(this.AFKeystoreWrapper);
        }
    }

    public final boolean AFInAppEventParameterName(String str) {
        Intrinsics.checkNotNullParameter(str, "");
        return this.AFKeystoreWrapper.containsKey(str);
    }
}
