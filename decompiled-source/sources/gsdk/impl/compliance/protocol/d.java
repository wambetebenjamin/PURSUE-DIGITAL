package gsdk.impl.compliance.protocol;

import android.app.Activity;
import android.content.Intent;
import com.bytedance.ttgame.sdk.module.bridge.ICommonBridgeInterface;
import com.bytedance.ttgame.sdk.module.bridge.IResultCallback;
import java.util.HashMap;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: ProtocolRNBridge.kt */
/* loaded from: classes10.dex */
public final class d extends ICommonBridgeInterface {

    /* renamed from: a  reason: collision with root package name */
    public static final a f19958a = new a(null);

    @Override // com.bytedance.ttgame.sdk.module.bridge.ICommonBridgeInterface
    public String getName() {
        return "dynamic_gsdk_compliance_protocol";
    }

    @Override // com.bytedance.ttgame.sdk.module.bridge.ICommonBridgeInterface
    public boolean onActivityResult(Activity activity, int i2, int i3, Intent intent) {
        return false;
    }

    /* compiled from: ProtocolRNBridge.kt */
    /* loaded from: classes10.dex */
    public static final class a {
        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private a() {
        }
    }

    public d(Map<String, ? extends Object> map) {
    }

    @Override // com.bytedance.ttgame.sdk.module.bridge.ICommonBridgeInterface
    public void onBridgeCalled(String str, HashMap<?, ?> hashMap, IResultCallback iResultCallback) {
        if (Intrinsics.areEqual(str, gsdk.impl.account.toutiao.f.p)) {
            k.f19988a.b();
        } else if (Intrinsics.areEqual(str, "closePanelAndClearAllDialog")) {
            k.f19988a.a();
        }
    }
}
