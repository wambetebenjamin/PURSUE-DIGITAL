package com.vk.api.sdk.exceptions;

import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: VKLocalIOException.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0003\u0018\u0000 \u00052\u00020\u0001:\u0001\u0005B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0006"}, d2 = {"Lcom/vk/api/sdk/exceptions/VKLocalIOException;", "Ljava/io/IOException;", "throwable", "", "(Ljava/lang/Throwable;)V", "Companion", "libapi-sdk-core_release"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes10.dex */
public final class VKLocalIOException extends IOException {
    public static final Companion Companion = new Companion(null);
    public static final long serialVersionUID = 3940183432046758205L;

    /* compiled from: VKLocalIOException.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/vk/api/sdk/exceptions/VKLocalIOException$Companion;", "", "()V", "serialVersionUID", "", "libapi-sdk-core_release"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes10.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VKLocalIOException(Throwable throwable) {
        super(throwable);
        Intrinsics.checkParameterIsNotNull(throwable, "throwable");
    }
}
