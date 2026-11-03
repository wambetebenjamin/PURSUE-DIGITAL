package gsdk.impl.core.DEFAULT;

import android.content.Context;
import android.text.TextUtils;
import com.bytedance.ttgame.core.SdkConfig;
import com.bytedance.ttgame.framework.module.util.StringUtil;
import com.bytedance.ttgame.sdk.module.utils.AppInfoUtil;
import com.bytedance.ttgame.sdk.module.utils.NumberUtils;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: MiniSdkAppContext.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\r\u0018\u00002\u00020\u0001B\u001d\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u001a\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005J\n\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016J\n\u0010\f\u001a\u0004\u0018\u00010\u000bH\u0016J\b\u0010\r\u001a\u00020\u000eH\u0016J\n\u0010\u000f\u001a\u0004\u0018\u00010\u000bH\u0016J\n\u0010\u0010\u001a\u0004\u0018\u00010\u000bH\u0016J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\n\u0010\u0013\u001a\u0004\u0018\u00010\u000bH\u0016J\n\u0010\u0014\u001a\u0004\u0018\u00010\u000bH\u0016J\n\u0010\u0015\u001a\u0004\u0018\u00010\u0003H\u0016J\n\u0010\u0016\u001a\u0004\u0018\u00010\u000bH\u0016J\n\u0010\u0017\u001a\u0004\u0018\u00010\u000bH\u0016J\n\u0010\u0018\u001a\u0004\u0018\u00010\u000bH\u0016J\b\u0010\u0019\u001a\u00020\u0012H\u0016J\n\u0010\u001a\u001a\u0004\u0018\u00010\u000bH\u0016J\n\u0010\u001b\u001a\u0004\u0018\u00010\u000bH\u0016J\b\u0010\u001c\u001a\u00020\u0012H\u0016J\n\u0010\u001d\u001a\u0004\u0018\u00010\u000bH\u0016J\b\u0010\u001e\u001a\u00020\u0012H\u0016R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lcom/bytedance/ttgame/main/internal/init/MiniSdkAppContext;", "Lcom/ss/android/common/AppContext;", "mContext", "Landroid/content/Context;", "sdkConfig", "Lcom/bytedance/ttgame/core/SdkConfig;", "(Landroid/content/Context;Lcom/bytedance/ttgame/core/SdkConfig;)V", "SdkAppContext", "", "context", "getAbClient", "", "getAbFeature", "getAbFlag", "", "getAbGroup", "getAbVersion", "getAid", "", "getAppName", "getChannel", "getContext", "getDeviceId", "getFeedbackAppKey", "getManifestVersion", "getManifestVersionCode", "getStringAppName", "getTweakedChannel", "getUpdateVersionCode", "getVersion", "getVersionCode", "core_impl_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes10.dex */
public final class d implements com.ss.android.common.a {

    /* renamed from: a  reason: collision with root package name */
    private Context f20037a;

    /* renamed from: b  reason: collision with root package name */
    private SdkConfig f20038b;

    public d() {
        this(null, null, 3, null);
    }

    @Override // com.ss.android.common.a
    public String getAbClient() {
        return null;
    }

    @Override // com.ss.android.common.a
    public String getAbFeature() {
        return null;
    }

    @Override // com.ss.android.common.a
    public long getAbFlag() {
        return 0L;
    }

    @Override // com.ss.android.common.a
    public String getAbGroup() {
        return null;
    }

    @Override // com.ss.android.common.a
    public String getAbVersion() {
        return null;
    }

    @Override // com.ss.android.common.a
    public String getDeviceId() {
        return "";
    }

    @Override // com.ss.android.common.a
    public String getFeedbackAppKey() {
        return null;
    }

    public d(Context context, SdkConfig sdkConfig) {
        this.f20037a = context;
        this.f20038b = sdkConfig;
    }

    public /* synthetic */ d(Context context, SdkConfig sdkConfig, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? null : context, (i2 & 2) != 0 ? null : sdkConfig);
    }

    public final void a(Context context, SdkConfig sdkConfig) {
        this.f20037a = context;
        this.f20038b = sdkConfig;
    }

    @Override // com.ss.android.common.a
    public Context getContext() {
        return this.f20037a;
    }

    @Override // com.ss.android.common.a
    public String getStringAppName() {
        return AppInfoUtil.getAppName(this.f20037a);
    }

    @Override // com.ss.android.common.a
    public String getAppName() {
        SdkConfig sdkConfig = this.f20038b;
        Intrinsics.checkNotNull(sdkConfig);
        return sdkConfig.appName;
    }

    @Override // com.ss.android.common.a
    public String getVersion() {
        return AppInfoUtil.getAppVersionName(this.f20037a);
    }

    @Override // com.ss.android.common.a
    public String getChannel() {
        SdkConfig sdkConfig = this.f20038b;
        if (!TextUtils.isEmpty(sdkConfig != null ? sdkConfig.channel_data : null)) {
            SdkConfig sdkConfig2 = this.f20038b;
            if (sdkConfig2 != null) {
                return sdkConfig2.channel_data;
            }
            return null;
        }
        SdkConfig sdkConfig3 = this.f20038b;
        if (StringUtil.isNullOrEmpty(sdkConfig3 != null ? sdkConfig3.channel : null)) {
            return SdkConfig.APPLOG_CHANNEL;
        }
        SdkConfig sdkConfig4 = this.f20038b;
        Intrinsics.checkNotNull(sdkConfig4);
        return sdkConfig4.channel;
    }

    @Override // com.ss.android.common.a
    public String getTweakedChannel() {
        SdkConfig sdkConfig = this.f20038b;
        Intrinsics.checkNotNull(sdkConfig);
        if (!TextUtils.isEmpty(sdkConfig.channel_data)) {
            SdkConfig sdkConfig2 = this.f20038b;
            Intrinsics.checkNotNull(sdkConfig2);
            return sdkConfig2.channel_data;
        }
        SdkConfig sdkConfig3 = this.f20038b;
        if (StringUtil.isNullOrEmpty(sdkConfig3 != null ? sdkConfig3.channel : null)) {
            return null;
        }
        SdkConfig sdkConfig4 = this.f20038b;
        Intrinsics.checkNotNull(sdkConfig4);
        return sdkConfig4.channel;
    }

    @Override // com.ss.android.common.a
    public int getVersionCode() {
        return AppInfoUtil.getAppVersionCode(this.f20037a);
    }

    @Override // com.ss.android.common.a
    public int getUpdateVersionCode() {
        SdkConfig sdkConfig = this.f20038b;
        Intrinsics.checkNotNull(sdkConfig);
        if (sdkConfig.updateVersionCode == 0) {
            return AppInfoUtil.getAppVersionCode(this.f20037a);
        }
        SdkConfig sdkConfig2 = this.f20038b;
        Intrinsics.checkNotNull(sdkConfig2);
        return sdkConfig2.updateVersionCode;
    }

    @Override // com.ss.android.common.a
    public int getManifestVersionCode() {
        return AppInfoUtil.getAppVersionCode(this.f20037a);
    }

    @Override // com.ss.android.common.a
    public String getManifestVersion() {
        return AppInfoUtil.getAppVersionName(this.f20037a);
    }

    @Override // com.ss.android.common.a
    public int getAid() {
        SdkConfig sdkConfig = this.f20038b;
        Intrinsics.checkNotNull(sdkConfig);
        return NumberUtils.getInteger(sdkConfig.appId, 1807);
    }
}
