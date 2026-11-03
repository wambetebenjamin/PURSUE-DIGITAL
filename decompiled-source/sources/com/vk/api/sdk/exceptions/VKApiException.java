package com.vk.api.sdk.exceptions;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: VKApiException.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0004\b\u0016\u0018\u0000 \n2\u00060\u0001j\u0002`\u0002:\u0001\nB\u000f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005B\u0017\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bB\u000f\b\u0016\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\t¨\u0006\u000b"}, d2 = {"Lcom/vk/api/sdk/exceptions/VKApiException;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "detailMessage", "", "(Ljava/lang/String;)V", "throwable", "", "(Ljava/lang/String;Ljava/lang/Throwable;)V", "(Ljava/lang/Throwable;)V", "Companion", "libapi-sdk-core_release"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes10.dex */
public class VKApiException extends Exception {
    public static final Companion Companion = new Companion(null);
    public static final long serialVersionUID = 1221900559703281428L;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VKApiException(String detailMessage) {
        super(detailMessage);
        Intrinsics.checkParameterIsNotNull(detailMessage, "detailMessage");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VKApiException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
        Intrinsics.checkParameterIsNotNull(detailMessage, "detailMessage");
        Intrinsics.checkParameterIsNotNull(throwable, "throwable");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VKApiException(Throwable throwable) {
        super(throwable);
        Intrinsics.checkParameterIsNotNull(throwable, "throwable");
    }

    /* compiled from: VKApiException.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/vk/api/sdk/exceptions/VKApiException$Companion;", "", "()V", "serialVersionUID", "", "libapi-sdk-core_release"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes10.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }
}
