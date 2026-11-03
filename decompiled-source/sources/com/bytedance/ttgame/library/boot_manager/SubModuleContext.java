package com.bytedance.ttgame.library.boot_manager;

import android.content.Context;
import com.bytedance.ttgame.core.SdkConfig;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: SubModuleContext.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\t\u0010\b\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0010\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/bytedance/ttgame/library/boot_manager/SubModuleContext;", "", "appContext", "Landroid/content/Context;", "sdkConfig", "Lcom/bytedance/ttgame/core/SdkConfig;", "(Landroid/content/Context;Lcom/bytedance/ttgame/core/SdkConfig;)V", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "boot_manager_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SubModuleContext {
    public final Context appContext;
    public final SdkConfig sdkConfig;

    public static /* synthetic */ SubModuleContext copy$default(SubModuleContext subModuleContext, Context context, SdkConfig sdkConfig, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            context = subModuleContext.appContext;
        }
        if ((i2 & 2) != 0) {
            sdkConfig = subModuleContext.sdkConfig;
        }
        return subModuleContext.copy(context, sdkConfig);
    }

    public final Context component1() {
        return this.appContext;
    }

    public final SdkConfig component2() {
        return this.sdkConfig;
    }

    public final SubModuleContext copy(Context appContext, SdkConfig sdkConfig) {
        Intrinsics.checkNotNullParameter(appContext, "appContext");
        Intrinsics.checkNotNullParameter(sdkConfig, "sdkConfig");
        return new SubModuleContext(appContext, sdkConfig);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof SubModuleContext) {
            SubModuleContext subModuleContext = (SubModuleContext) obj;
            return Intrinsics.areEqual(this.appContext, subModuleContext.appContext) && Intrinsics.areEqual(this.sdkConfig, subModuleContext.sdkConfig);
        }
        return false;
    }

    public int hashCode() {
        return (this.appContext.hashCode() * 31) + this.sdkConfig.hashCode();
    }

    public String toString() {
        return "SubModuleContext(appContext=" + this.appContext + ", sdkConfig=" + this.sdkConfig + ')';
    }

    public SubModuleContext(Context appContext, SdkConfig sdkConfig) {
        Intrinsics.checkNotNullParameter(appContext, "appContext");
        Intrinsics.checkNotNullParameter(sdkConfig, "sdkConfig");
        this.appContext = appContext;
        this.sdkConfig = sdkConfig;
    }
}
