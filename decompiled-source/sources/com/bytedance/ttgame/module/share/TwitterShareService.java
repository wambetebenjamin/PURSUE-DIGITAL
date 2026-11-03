package com.bytedance.ttgame.module.share;

import com.bytedance.ttgame.library.module_manager.ModuleManager;
import com.bytedance.ttgame.module.apimonitor.ModuleApiMonitor;
import com.bytedance.ttgame.module.rn.api.IRNEngineService;
import com.meituan.robust.Constants;
import gsdk.impl.share.twitter.a;
import kotlin.Metadata;
/* compiled from: TwitterShareService.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"Lcom/bytedance/ttgame/module/share/TwitterShareService;", "Lcom/bytedance/ttgame/module/share/ITwitterShareService;", "()V", "initTwitterShareModule", "", "share_impl_twitter_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes7.dex */
public final class TwitterShareService implements ITwitterShareService {
    public final ModuleApiMonitor moduleApiMonitor = new ModuleApiMonitor();

    @Override // com.bytedance.ttgame.module.share.ITwitterShareService
    public void initTwitterShareModule() {
        this.moduleApiMonitor.onApiEnter("share:impl:twitter", "com.bytedance.ttgame.module.share.ITwitterShareService", "com.bytedance.ttgame.module.share.TwitterShareService", "initTwitterShareModule", new String[0], Constants.VOID);
        IRNEngineService iRNEngineService = (IRNEngineService) ModuleManager.getService$default(ModuleManager.INSTANCE, IRNEngineService.class, false, (String) null, 6, (Object) null);
        if (iRNEngineService != null) {
            iRNEngineService.registerRNAPI(new a());
        }
    }
}
