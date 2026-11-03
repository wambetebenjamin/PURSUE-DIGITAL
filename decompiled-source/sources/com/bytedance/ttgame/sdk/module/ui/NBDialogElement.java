package com.bytedance.ttgame.sdk.module.ui;

import android.content.Context;
import android.os.Bundle;
import kotlin.Metadata;
/* compiled from: NBDialogDSL.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001R\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\u0004\u0018\u00010\tX¦\u000e¢\u0006\f\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u0018\u0010\u000e\u001a\u00020\u000fX¦\u000e¢\u0006\f\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u0018\u0010\u0014\u001a\u00020\u0015X¦\u000e¢\u0006\f\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u0018\u0010\u001a\u001a\u00020\u0015X¦\u000e¢\u0006\f\u001a\u0004\b\u001b\u0010\u0017\"\u0004\b\u001c\u0010\u0019R\u001a\u0010\u001d\u001a\u0004\u0018\u00010\u001eX¦\u000e¢\u0006\f\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"¨\u0006#"}, d2 = {"Lcom/bytedance/ttgame/sdk/module/ui/NBDialogElement;", "", "bundle", "Landroid/os/Bundle;", "getBundle", "()Landroid/os/Bundle;", "setBundle", "(Landroid/os/Bundle;)V", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "forceWidthHeight", "", "getForceWidthHeight", "()Z", "setForceWidthHeight", "(Z)V", "layout", "", "getLayout", "()I", "setLayout", "(I)V", "style", "getStyle", "setStyle", "tag", "", "getTag", "()Ljava/lang/String;", "setTag", "(Ljava/lang/String;)V", "android_gsdk_base_common_ui_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes8.dex */
public interface NBDialogElement {
    Bundle getBundle();

    Context getContext();

    boolean getForceWidthHeight();

    int getLayout();

    int getStyle();

    String getTag();

    void setBundle(Bundle bundle);

    void setContext(Context context);

    void setForceWidthHeight(boolean z);

    void setLayout(int i2);

    void setStyle(int i2);

    void setTag(String str);
}
