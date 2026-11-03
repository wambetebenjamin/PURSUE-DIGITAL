package com.google.android.play.core.assetpacks;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import java.util.Locale;
/* compiled from: com.google.android.play:asset-delivery@@2.1.0 */
/* loaded from: classes6.dex */
public class AssetPackException extends ApiException {
    /* JADX INFO: Access modifiers changed from: package-private */
    public AssetPackException(int i2) {
        super(new Status(i2, String.format(Locale.getDefault(), "Asset Pack Download Error(%d): %s", Integer.valueOf(i2), com.google.android.play.core.assetpacks.model.a.a(i2))));
        if (i2 == 0) {
            throw new IllegalArgumentException("errorCode should not be 0.");
        }
    }

    public int getErrorCode() {
        return super.getStatusCode();
    }
}
