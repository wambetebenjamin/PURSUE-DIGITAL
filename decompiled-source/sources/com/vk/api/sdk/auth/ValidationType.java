package com.vk.api.sdk.auth;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: ValidationType.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\b\u0086\u0001\u0018\u0000 \r2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\rB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\f¨\u0006\u000e"}, d2 = {"Lcom/vk/api/sdk/auth/ValidationType;", "", "jsonValue", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getJsonValue", "()Ljava/lang/String;", "SMS", "APP", "LIBVERIFY", "CALL_RESET", "PHONE", "URL", "Companion", "libapi-sdk-core_release"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes10.dex */
public enum ValidationType {
    SMS("2fa_sms"),
    APP("2fa_app"),
    LIBVERIFY("2fa_libverify"),
    CALL_RESET("2fa_callreset"),
    PHONE("phone"),
    URL("");
    
    public static final Companion Companion = new Companion(null);
    private final String jsonValue;

    ValidationType(String jsonValue) {
        Intrinsics.checkParameterIsNotNull(jsonValue, "jsonValue");
        this.jsonValue = jsonValue;
    }

    public final String getJsonValue() {
        return this.jsonValue;
    }

    /* compiled from: ValidationType.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/vk/api/sdk/auth/ValidationType$Companion;", "", "()V", "parse", "Lcom/vk/api/sdk/auth/ValidationType;", "jsonValue", "", "libapi-sdk-core_release"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes10.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final ValidationType parse(String jsonValue) {
            ValidationType validationType;
            Intrinsics.checkParameterIsNotNull(jsonValue, "jsonValue");
            ValidationType[] values = ValidationType.values();
            int length = values.length;
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    validationType = null;
                    break;
                }
                validationType = values[i2];
                if (Intrinsics.areEqual(validationType.getJsonValue(), jsonValue)) {
                    break;
                }
                i2++;
            }
            return validationType != null ? validationType : ValidationType.URL;
        }
    }
}
