package com.bytedance.ttgame.module.account;

import com.bytedance.ttgame.library.boot_manager.BootTask;
import com.bytedance.ttgame.library.boot_manager.SubModuleContext;
import gsdk.impl.account.toutiao.c;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\b\u0010\u0007\u001a\u00020\bH\u0014¨\u0006\t"}, d2 = {"Lcom/bytedance/ttgame/module/account/Task;", "Lcom/bytedance/ttgame/library/boot_manager/BootTask;", "subModuleContext", "Lcom/bytedance/ttgame/library/boot_manager/SubModuleContext;", "(Lcom/bytedance/ttgame/library/boot_manager/SubModuleContext;)V", "init", "", "triggerOn", "", "account_impl_toutiao_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class Task extends BootTask {
    @Override // com.bytedance.ttgame.library.boot_manager.BootTask
    protected int triggerOn() {
        return 1;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Task(SubModuleContext subModuleContext) {
        super(subModuleContext);
        Intrinsics.checkNotNull(subModuleContext);
    }

    @Override // com.bytedance.ttgame.library.boot_manager.BootTask
    public void init() {
        c cVar = c.f19459a;
        SubModuleContext subModuleContext = BootTask.moduleContext;
        cVar.a(subModuleContext != null ? subModuleContext.appContext : null);
    }
}
