package com.bytedance.ttgame.sdk.module.ui;

import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import com.facebook.appevents.internal.ViewHierarchyConstants;
import com.facebook.react.uimanager.ViewProps;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: HyperlinkClickableSpan.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0019\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B!\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0006\u0010\u0011\u001a\u00020\u000eJ\u0010\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u0014H\u0016R\u000e\u0010\n\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/bytedance/ttgame/sdk/module/ui/HyperlinkClickableSpan;", "Landroid/text/style/ClickableSpan;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Landroid/view/View$OnClickListener;", ViewProps.COLOR, "", "(Landroid/view/View$OnClickListener;I)V", "underlineText", "", "(Landroid/view/View$OnClickListener;IZ)V", "mColor", "mListener", "mUnderlineText", "onClick", "", ViewHierarchyConstants.VIEW_KEY, "Landroid/view/View;", "onRelease", "updateDrawState", "tp", "Landroid/text/TextPaint;", "android_gsdk_base_common_ui_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes8.dex */
public final class HyperlinkClickableSpan extends ClickableSpan {
    private final int mColor;
    private View.OnClickListener mListener;
    private final boolean mUnderlineText;

    public HyperlinkClickableSpan(View.OnClickListener onClickListener, int i2) {
        this.mColor = i2;
        this.mUnderlineText = true;
        this.mListener = onClickListener;
    }

    public HyperlinkClickableSpan(View.OnClickListener onClickListener, int i2, boolean z) {
        this.mColor = i2;
        this.mUnderlineText = z;
        this.mListener = onClickListener;
    }

    @Override // android.text.style.ClickableSpan
    public void onClick(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        View.OnClickListener onClickListener = this.mListener;
        if (onClickListener == null) {
            return;
        }
        onClickListener.onClick(view);
    }

    @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
    public void updateDrawState(TextPaint tp) {
        Intrinsics.checkNotNullParameter(tp, "tp");
        tp.setColor(this.mColor);
        tp.setUnderlineText(this.mUnderlineText);
    }

    public final void onRelease() {
        if (this.mListener != null) {
            this.mListener = null;
        }
    }
}
