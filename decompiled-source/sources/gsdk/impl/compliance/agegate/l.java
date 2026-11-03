package gsdk.impl.compliance.agegate;

import android.app.Dialog;
import android.content.Context;
import com.bytedance.ttgame.framework.module.spi.IModuleApi;
import com.bytedance.ttgame.library.module_manager.ModuleManager;
import com.bytedance.ttgame.main.internal.IMainInternalService;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: AgeGateBaseDialog.kt */
/* loaded from: classes10.dex */
public class l extends Dialog {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public l(Context context, int i2) {
        super(context, i2);
        Intrinsics.checkNotNullParameter(context, "context");
        IModuleApi service$default = ModuleManager.getService$default(ModuleManager.INSTANCE, IMainInternalService.class, false, (String) null, 6, (Object) null);
        Intrinsics.checkNotNull(service$default);
        ((IMainInternalService) service$default).changeContextLocale(context);
    }
}
