package com.facebook.share.internal;

import com.facebook.internal.DialogFeature;
import com.facebook.internal.NativeProtocol;
/* loaded from: classes8.dex */
public enum OpenGraphMessageDialogFeature implements DialogFeature {
    OG_MESSAGE_DIALOG(NativeProtocol.PROTOCOL_VERSION_20140204);
    
    private int minVersion;

    @Override // com.facebook.internal.DialogFeature
    public String getAction() {
        return NativeProtocol.ACTION_OGMESSAGEPUBLISH_DIALOG;
    }

    OpenGraphMessageDialogFeature(int minVersion) {
        this.minVersion = minVersion;
    }

    @Override // com.facebook.internal.DialogFeature
    public int getMinVersion() {
        return this.minVersion;
    }
}
