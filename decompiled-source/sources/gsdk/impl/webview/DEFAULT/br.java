package gsdk.impl.webview.DEFAULT;

import kotlin.Metadata;
/* compiled from: SandboxUtil.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\t"}, d2 = {"Lcom/bytedance/ttgame/module/webview/util/MimeType;", "", "value", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", "MIME_TYPE_IMAGE", "MIME_TYPE_VIDEO", "webview_impl_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes10.dex */
public enum br {
    MIME_TYPE_IMAGE("image/*"),
    MIME_TYPE_VIDEO("video/*");
    

    /* renamed from: c  reason: collision with root package name */
    private final String f21011c;

    br(String str) {
        this.f21011c = str;
    }

    public final String a() {
        return this.f21011c;
    }
}
