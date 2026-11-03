package gsdk.impl.compliance.protocol;

import com.bytedance.ttgame.base.GSDKError;
import com.bytedance.ttgame.module.compliance.impl.protocol.ProtocolManageEvent;
import org.greenrobot.eventbus.EventBus;
/* compiled from: ProtocolEventManager.kt */
/* loaded from: classes10.dex */
public final class a {

    /* renamed from: a  reason: collision with root package name */
    public static final a f19946a = new a();

    private a() {
    }

    public final void a(int i2, int i3, String str, int i4) {
        b bVar = new b();
        bVar.f19947a = i3;
        bVar.f19948b = new GSDKError(i2, str);
        EventBus.getDefault().post(new ProtocolManageEvent(bVar, i4));
    }
}
