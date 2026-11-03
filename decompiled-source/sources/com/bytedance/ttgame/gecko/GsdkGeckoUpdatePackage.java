package com.bytedance.ttgame.gecko;

import com.bytedance.geckox.model.UpdatePackage;
/* loaded from: classes3.dex */
public class GsdkGeckoUpdatePackage {
    private UpdatePackage rawUpdatePackage;

    public GsdkGeckoUpdatePackage(UpdatePackage updatePackage) {
        this.rawUpdatePackage = updatePackage;
    }

    public String getChannel() {
        return this.rawUpdatePackage.getChannel();
    }
}
