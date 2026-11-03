package com.bytedance.ttgame.sdk.module.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import com.bytedance.ug.sdk.share.impl.network.request.a;
import java.util.Objects;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: ClipboardUtils.kt */
/* loaded from: classes8.dex */
public final class ClipboardUtils {
    public static final ClipboardUtils INSTANCE = new ClipboardUtils();

    private ClipboardUtils() {
    }

    public final String getText(Context context) {
        Object systemService;
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            systemService = context.getSystemService(a.CLIPBOARD_TOKEN);
        } catch (Exception unused) {
        }
        if (systemService == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.content.ClipboardManager");
        }
        ClipData primaryClip = ((ClipboardManager) systemService).getPrimaryClip();
        if (primaryClip != null) {
            if (!(primaryClip.getItemCount() > 0)) {
                primaryClip = null;
            }
            if (primaryClip != null) {
                return primaryClip.getItemAt(0).getText().toString();
            }
        }
        return "";
    }

    public final void clear(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Object systemService = context.getSystemService(a.CLIPBOARD_TOKEN);
        Objects.requireNonNull(systemService, "null cannot be cast to non-null type android.content.ClipboardManager");
        ((ClipboardManager) systemService).setPrimaryClip(ClipData.newPlainText("", ""));
    }

    public final void setText(Context context, CharSequence title, CharSequence text) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(text, "text");
        Object systemService = context.getSystemService(a.CLIPBOARD_TOKEN);
        Objects.requireNonNull(systemService, "null cannot be cast to non-null type android.content.ClipboardManager");
        ((ClipboardManager) systemService).setPrimaryClip(ClipData.newPlainText(title, text));
    }
}
