package compatible.standard;

import com.bytedance.android.bytehook.ByteHook;
import com.bytedance.shadowhook.ShadowHook;
/* compiled from: InterfaceCompatWrapper.java */
/* loaded from: classes10.dex */
public class a {
    public static int a() {
        return ByteHook.init(new ByteHook.b().build());
    }

    public static int b() {
        return ShadowHook.init(new ShadowHook.b().a());
    }
}
