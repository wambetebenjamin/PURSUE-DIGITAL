package com.bytedance.sdk.bridge.model;

import android.app.Activity;
import com.bytedance.bdturing.methods.l;
import kotlin.Metadata;
/* compiled from: IBridgeContext.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\n\u0010\u0006\u001a\u0004\u0018\u00010\u0007H&¨\u0006\b"}, d2 = {"Lcom/bytedance/sdk/bridge/model/IBridgeContext;", "", l.VALUE_CALLBACK, "", "bridgeResult", "Lcom/bytedance/sdk/bridge/model/BridgeResult;", "getActivity", "Landroid/app/Activity;", "bridge_release"}, k = 1, mv = {1, 1, 15})
/* loaded from: classes3.dex */
public interface d {
    void callback(b bVar);

    Activity getActivity();
}
