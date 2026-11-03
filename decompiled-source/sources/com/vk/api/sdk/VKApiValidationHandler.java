package com.vk.api.sdk;

import androidx.exifinterface.media.ExifInterface;
import java.util.concurrent.CountDownLatch;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
/* compiled from: VKApiValidationHandler.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001:\u0002\u000e\u000fJ\u001e\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007H&J\u001e\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\n0\u0007H&J\u001e\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\r0\u0007H&¨\u0006\u0010"}, d2 = {"Lcom/vk/api/sdk/VKApiValidationHandler;", "", "handleCaptcha", "", "img", "", "cb", "Lcom/vk/api/sdk/VKApiValidationHandler$Callback;", "handleConfirm", "confirmationText", "", "handleValidation", "validationUrl", "Lcom/vk/api/sdk/VKApiValidationHandler$Credentials;", "Callback", "Credentials", "libapi-sdk-core_release"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes10.dex */
public interface VKApiValidationHandler {
    void handleCaptcha(String str, Callback<String> callback);

    void handleConfirm(String str, Callback<Boolean> callback);

    void handleValidation(String str, Callback<Credentials> callback);

    /* compiled from: VKApiValidationHandler.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0006\u0010\f\u001a\u00020\rJ\u0013\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0006\u001a\u00028\u0000¢\u0006\u0002\u0010\nR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0006\u001a\u0004\u0018\u00018\u0000X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u000b\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u0006\u000f"}, d2 = {"Lcom/vk/api/sdk/VKApiValidationHandler$Callback;", ExifInterface.GPS_DIRECTION_TRUE, "", "latch", "Ljava/util/concurrent/CountDownLatch;", "(Ljava/util/concurrent/CountDownLatch;)V", "value", "getValue", "()Ljava/lang/Object;", "setValue", "(Ljava/lang/Object;)V", "Ljava/lang/Object;", "cancel", "", "submit", "libapi-sdk-core_release"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes10.dex */
    public static final class Callback<T> {
        private final CountDownLatch latch;
        private volatile T value;

        public Callback(CountDownLatch latch) {
            Intrinsics.checkParameterIsNotNull(latch, "latch");
            this.latch = latch;
        }

        public final T getValue() {
            return this.value;
        }

        public final void setValue(T t) {
            this.value = t;
        }

        public final void cancel() {
            this.latch.countDown();
        }

        public final void submit(T t) {
            this.value = t;
            this.latch.countDown();
        }
    }

    /* compiled from: VKApiValidationHandler.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\u0018\u00002\u00020\u0001B#\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\nR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0015\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0011"}, d2 = {"Lcom/vk/api/sdk/VKApiValidationHandler$Credentials;", "", "secret", "", "token", "uid", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;)V", "isValid", "", "()Z", "getSecret", "()Ljava/lang/String;", "getToken", "getUid", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "libapi-sdk-core_release"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes10.dex */
    public static final class Credentials {
        private final boolean isValid;
        private final String secret;
        private final String token;
        private final Integer uid;

        public Credentials(String str, String str2, Integer num) {
            this.secret = str;
            this.token = str2;
            this.uid = num;
            String str3 = str2;
            boolean z = false;
            if (!(str3 == null || StringsKt.isBlank(str3))) {
                String str4 = str;
                if (!(str4 == null || str4.length() == 0)) {
                    z = true;
                }
            }
            this.isValid = z;
        }

        public final String getSecret() {
            return this.secret;
        }

        public final String getToken() {
            return this.token;
        }

        public final Integer getUid() {
            return this.uid;
        }

        public final boolean isValid() {
            return this.isValid;
        }
    }
}
