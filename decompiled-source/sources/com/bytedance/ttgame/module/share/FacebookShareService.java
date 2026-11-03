package com.bytedance.ttgame.module.share;

import android.content.Intent;
import com.bytedance.ttgame.library.module_manager.ModuleManager;
import com.bytedance.ttgame.module.apimonitor.ModuleApiMonitor;
import com.bytedance.ttgame.module.rn.api.IRNEngineService;
import com.meituan.robust.Constants;
import gsdk.impl.share.facebook.c;
import gsdk.impl.share.facebook.d;
import kotlin.Metadata;
/* compiled from: FacebookShareService.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\"\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016J\b\u0010\n\u001a\u00020\u0004H\u0016¨\u0006\u000b"}, d2 = {"Lcom/bytedance/ttgame/module/share/FacebookShareService;", "Lcom/bytedance/ttgame/module/share/IFacebookShareService;", "()V", "handleShareResult", "", "requestCode", "", "resultCode", "intent", "Landroid/content/Intent;", "initFacebookShareModule", "share_impl_facebook_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes7.dex */
public final class FacebookShareService implements IFacebookShareService {
    public final ModuleApiMonitor moduleApiMonitor = new ModuleApiMonitor();

    @Override // com.bytedance.ttgame.module.share.IFacebookShareService
    public void initFacebookShareModule() {
        this.moduleApiMonitor.onApiEnter("share:impl:facebook", "com.bytedance.ttgame.module.share.IFacebookShareService", "com.bytedance.ttgame.module.share.FacebookShareService", "initFacebookShareModule", new String[0], Constants.VOID);
        IRNEngineService iRNEngineService = (IRNEngineService) ModuleManager.getService$default(ModuleManager.INSTANCE, IRNEngineService.class, false, (String) null, 6, (Object) null);
        if (iRNEngineService != null) {
            iRNEngineService.registerRNAPI(new d());
        }
    }

    @Override // com.bytedance.ttgame.module.share.IFacebookShareService
    public void handleShareResult(int i2, int i3, Intent intent) {
        this.moduleApiMonitor.onApiEnter("share:impl:facebook", "com.bytedance.ttgame.module.share.IFacebookShareService", "com.bytedance.ttgame.module.share.FacebookShareService", "handleShareResult", new String[]{Constants.INT, Constants.INT, "android.content.Intent"}, Constants.VOID);
        new c().a(i2, i3, intent);
    }
}
