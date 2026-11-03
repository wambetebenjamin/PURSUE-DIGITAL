package com.bytedance.ttgame.module.account.passport;

import android.app.Activity;
import com.bytedance.ttgame.framework.module.spi.IModuleApi;
import java.lang.ref.WeakReference;
/* loaded from: classes4.dex */
public interface IBdTuringService extends IModuleApi {
    void initDBTuring();

    void registerDialogContext(WeakReference<Activity> weakReference);

    default void unRegisterDialogContext() {
    }
}
