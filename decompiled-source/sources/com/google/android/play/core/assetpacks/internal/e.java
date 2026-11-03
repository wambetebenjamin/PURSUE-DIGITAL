package com.google.android.play.core.assetpacks.internal;

import android.os.IBinder;
import android.os.IInterface;
/* compiled from: com.google.android.play:asset-delivery@@2.1.0 */
/* loaded from: classes6.dex */
public abstract class e extends b implements f {
    public static f b(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.play.core.assetpacks.protocol.IAssetModuleService");
        return queryLocalInterface instanceof f ? (f) queryLocalInterface : new d(iBinder);
    }
}
