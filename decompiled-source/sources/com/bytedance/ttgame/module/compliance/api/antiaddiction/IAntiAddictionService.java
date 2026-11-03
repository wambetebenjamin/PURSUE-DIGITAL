package com.bytedance.ttgame.module.compliance.api.antiaddiction;

import android.app.Activity;
import com.bytedance.ttgame.framework.module.spi.IModuleApi;
import com.bytedance.ttgame.library.api_common.annotations.InternalApi;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import kotlin.Deprecated;
import kotlin.Metadata;
/* compiled from: IAntiAddictionService.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H&J\u001a\u0010\b\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\n\u0010\t\u001a\u0004\u0018\u00010\u0007H&J\b\u0010\n\u001a\u00020\u0003H'J\b\u0010\u000b\u001a\u00020\fH'J\u0010\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\fH'J\u0012\u0010\u000f\u001a\u00020\u00032\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H&J\u0017\u0010\u0010\u001a\u00020\u00032\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H'¢\u0006\u0002\u0010\u0013J\b\u0010\u0014\u001a\u00020\u0003H'¨\u0006\u0015"}, d2 = {"Lcom/bytedance/ttgame/module/compliance/api/antiaddiction/IAntiAddictionService;", "Lcom/bytedance/ttgame/framework/module/spi/IModuleApi;", "getLatestStatus", "", "activity", "Landroid/app/Activity;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/bytedance/ttgame/module/compliance/api/antiaddiction/IAntiAddictionStatusListener;", "getServiceStatus", "getStatusChangedListener", "init", "isActive", "", "setEnableAlert", "enable", "setStatusChangedListener", "start", "initialDelayMillis", "", "(Ljava/lang/Long;)V", "stop", "compliance_api_antiaddiction_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes4.dex */
public interface IAntiAddictionService extends IModuleApi {
    void getLatestStatus(Activity activity, IAntiAddictionStatusListener iAntiAddictionStatusListener);

    void getServiceStatus(Activity activity, IAntiAddictionStatusListener iAntiAddictionStatusListener);

    IAntiAddictionStatusListener getStatusChangedListener();

    @InternalApi
    void init();

    @InternalApi
    boolean isActive();

    @Deprecated(message = "This interface is deprecated because of removing dialog")
    void setEnableAlert(boolean z);

    void setStatusChangedListener(IAntiAddictionStatusListener iAntiAddictionStatusListener);

    @InternalApi
    void start(Long l);

    @InternalApi
    void stop();
}
