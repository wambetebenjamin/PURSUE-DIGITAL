package com.bytedance.ttgame.module.rn;

import com.bytedance.react.framework.core.BRNManager;
import timber.log.Timber;
/* loaded from: classes6.dex */
public class BRNEngineLog implements BRNManager.LoggerHelper {
    @Override // com.bytedance.react.framework.core.BRNManager.LoggerHelper
    public void e(String str, String str2) {
        Timber.tag(str).e(str2, new Object[0]);
    }

    @Override // com.bytedance.react.framework.core.BRNManager.LoggerHelper
    public void d(String str, String str2) {
        Timber.tag(str).d(str2, new Object[0]);
    }

    @Override // com.bytedance.react.framework.core.BRNManager.LoggerHelper
    public void i(String str, String str2) {
        Timber.tag(str).i(str2, new Object[0]);
    }
}
