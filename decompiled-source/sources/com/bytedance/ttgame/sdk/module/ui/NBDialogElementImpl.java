package com.bytedance.ttgame.sdk.module.ui;

import android.content.Context;
import android.os.Bundle;
import com.bytedance.ttgame.gsdk_base_common_ui.R;
import kotlin.Metadata;
/* compiled from: NBDialogDSL.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0010X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0015\u001a\u00020\u0016X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001a\u0010\u001b\u001a\u00020\u0016X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0018\"\u0004\b\u001d\u0010\u001aR\u001c\u0010\u001e\u001a\u0004\u0018\u00010\u001fX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#¨\u0006$"}, d2 = {"Lcom/bytedance/ttgame/sdk/module/ui/NBDialogElementImpl;", "Lcom/bytedance/ttgame/sdk/module/ui/NBDialogElement;", "()V", "bundle", "Landroid/os/Bundle;", "getBundle", "()Landroid/os/Bundle;", "setBundle", "(Landroid/os/Bundle;)V", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "forceWidthHeight", "", "getForceWidthHeight", "()Z", "setForceWidthHeight", "(Z)V", "layout", "", "getLayout", "()I", "setLayout", "(I)V", "style", "getStyle", "setStyle", "tag", "", "getTag", "()Ljava/lang/String;", "setTag", "(Ljava/lang/String;)V", "android_gsdk_base_common_ui_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes8.dex */
public final class NBDialogElementImpl implements NBDialogElement {
    private Context context;
    private boolean forceWidthHeight;
    private int layout;
    private String tag;
    private Bundle bundle = new Bundle();
    private int style = R.style.gsdk_base_dialog_style;

    @Override // com.bytedance.ttgame.sdk.module.ui.NBDialogElement
    public int getLayout() {
        return this.layout;
    }

    @Override // com.bytedance.ttgame.sdk.module.ui.NBDialogElement
    public void setLayout(int i2) {
        this.layout = i2;
    }

    @Override // com.bytedance.ttgame.sdk.module.ui.NBDialogElement
    public Context getContext() {
        return this.context;
    }

    @Override // com.bytedance.ttgame.sdk.module.ui.NBDialogElement
    public void setContext(Context context) {
        this.context = context;
    }

    @Override // com.bytedance.ttgame.sdk.module.ui.NBDialogElement
    public Bundle getBundle() {
        return this.bundle;
    }

    @Override // com.bytedance.ttgame.sdk.module.ui.NBDialogElement
    public void setBundle(Bundle bundle) {
        this.bundle = bundle;
    }

    @Override // com.bytedance.ttgame.sdk.module.ui.NBDialogElement
    public int getStyle() {
        return this.style;
    }

    @Override // com.bytedance.ttgame.sdk.module.ui.NBDialogElement
    public void setStyle(int i2) {
        this.style = i2;
    }

    @Override // com.bytedance.ttgame.sdk.module.ui.NBDialogElement
    public String getTag() {
        return this.tag;
    }

    @Override // com.bytedance.ttgame.sdk.module.ui.NBDialogElement
    public void setTag(String str) {
        this.tag = str;
    }

    @Override // com.bytedance.ttgame.sdk.module.ui.NBDialogElement
    public boolean getForceWidthHeight() {
        return this.forceWidthHeight;
    }

    @Override // com.bytedance.ttgame.sdk.module.ui.NBDialogElement
    public void setForceWidthHeight(boolean z) {
        this.forceWidthHeight = z;
    }
}
