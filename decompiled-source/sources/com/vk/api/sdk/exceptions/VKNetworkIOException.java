package com.vk.api.sdk.exceptions;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: VKNetworkIOException.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\b\u0016\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u0011\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004B\u001d\b\u0007\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0007J\b\u0010\b\u001a\u00020\tH\u0002J\b\u0010\n\u001a\u00020\tH\u0002J\b\u0010\u000b\u001a\u00020\u0006H\u0016J\f\u0010\f\u001a\u00020\r*\u00020\tH\u0002¨\u0006\u000f"}, d2 = {"Lcom/vk/api/sdk/exceptions/VKNetworkIOException;", "Ljava/io/IOException;", "throwable", "", "(Ljava/lang/Throwable;)V", "detailMessage", "", "(Ljava/lang/String;Ljava/lang/Throwable;)V", "isNetworkUnavailableCause", "", "isRequestInterruptedCause", "toString", "toInt", "", "Companion", "libapi-sdk-core_release"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes10.dex */
public class VKNetworkIOException extends IOException {
    public static final Companion Companion = new Companion(null);
    public static final long serialVersionUID = -2758493010294573829L;

    public VKNetworkIOException() {
        this(null, null, 3, null);
    }

    public VKNetworkIOException(String str) {
        this(str, null, 2, null);
    }

    private final int toInt(boolean z) {
        return z ? 1 : 0;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VKNetworkIOException(String detailMessage, Throwable th) {
        super(detailMessage, th);
        Intrinsics.checkParameterIsNotNull(detailMessage, "detailMessage");
    }

    public /* synthetic */ VKNetworkIOException(String str, Throwable th, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? "" : str, (i2 & 2) != 0 ? null : th);
    }

    public VKNetworkIOException(Throwable th) {
        this("", th);
    }

    /* compiled from: VKNetworkIOException.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/vk/api/sdk/exceptions/VKNetworkIOException$Companion;", "", "()V", "serialVersionUID", "", "libapi-sdk-core_release"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes10.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    private final boolean isNetworkUnavailableCause() {
        return (getCause() instanceof UnknownHostException) || (getCause() instanceof SocketException);
    }

    private final boolean isRequestInterruptedCause() {
        return (getCause() instanceof InterruptedIOException) || (getCause() instanceof InterruptedException);
    }

    @Override // java.lang.Throwable
    public String toString() {
        StringBuilder append = new StringBuilder().append("NetworkIO(noNetwork=").append(toInt(isNetworkUnavailableCause())).append(',').append("interrupted=").append(toInt(isRequestInterruptedCause())).append(',');
        Throwable cause = getCause();
        return append.append(cause != null ? cause.toString() : null).append(')').toString();
    }
}
