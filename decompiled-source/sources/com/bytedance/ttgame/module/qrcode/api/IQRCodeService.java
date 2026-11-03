package com.bytedance.ttgame.module.qrcode.api;

import android.app.Activity;
import com.bytedance.ttgame.framework.module.spi.IModuleApi;
/* loaded from: classes6.dex */
public interface IQRCodeService extends IModuleApi {
    void scanQRCode(Activity activity, IScanResultCallback iScanResultCallback);
}
