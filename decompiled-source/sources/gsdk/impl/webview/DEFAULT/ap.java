package gsdk.impl.webview.DEFAULT;

import com.bytedance.ttgame.module.webview.api.aidl.Command;
import java.util.HashMap;
/* compiled from: Commands.java */
/* loaded from: classes10.dex */
public abstract class ap {

    /* renamed from: a  reason: collision with root package name */
    private HashMap<String, Command> f20894a = new HashMap<>();

    abstract int a();

    public HashMap<String, Command> b() {
        return this.f20894a;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(Command command) {
        this.f20894a.put(command.name(), command);
    }
}
