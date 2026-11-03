package gsdk.impl.webview.DEFAULT;

import android.content.Context;
import com.bytedance.ttgame.base.GSDKError;
import com.bytedance.ttgame.module.webview.api.aidl.AsyncCommand;
import com.bytedance.ttgame.module.webview.api.aidl.Command;
import com.bytedance.ttgame.module.webview.api.aidl.IWebAidlResultCallback;
import com.bytedance.ttgame.module.webview.api.aidl.SyncCommand;
import java.util.HashMap;
import java.util.Map;
import timber.log.Timber;
/* compiled from: CommandsManager.java */
/* loaded from: classes10.dex */
public class aq {

    /* renamed from: a  reason: collision with root package name */
    public static final String f20895a = "CommandsManager";

    /* renamed from: b  reason: collision with root package name */
    private static aq f20896b;

    /* renamed from: c  reason: collision with root package name */
    private static an f20897c;

    /* renamed from: d  reason: collision with root package name */
    private static ar f20898d;

    public static aq a() {
        if (f20896b == null) {
            synchronized (aq.class) {
                if (f20896b == null) {
                    f20896b = new aq();
                }
            }
        }
        return f20896b;
    }

    private aq() {
        f20897c = new an();
        f20898d = new ar();
    }

    public void a(int i2, Command command) {
        if (i2 == 0) {
            f20898d.a(command);
            Timber.tag(f20895a).d("uiLevelCommand :" + command.name(), new Object[0]);
        } else if (i2 != 1) {
        } else {
            f20897c.a(command);
            Timber.tag(f20895a).d("baseLevelCommand :" + command.name(), new Object[0]);
        }
    }

    public void a(Context context, int i2, String str, Map map, IWebAidlResultCallback iWebAidlResultCallback) {
        Command command;
        if (i2 != 0) {
            command = i2 != 1 ? null : f20897c.b().get(str);
        } else {
            command = f20898d.b().get(str);
        }
        if (command != null) {
            try {
                Timber.tag(f20895a).d(command.name() + " exists", new Object[0]);
                ((AsyncCommand) command).execute(context, map, iWebAidlResultCallback);
                return;
            } catch (Exception unused) {
                Timber.tag(f20895a).d(command.name() + " is not an AsyncCommand", new Object[0]);
                iWebAidlResultCallback.onError(str, new GSDKError(-1, command.name() + " is not an AsyncCommand"));
                return;
            }
        }
        iWebAidlResultCallback.onError(str, new GSDKError(-1, str + "  not exists"));
        Timber.tag(f20895a).e(str + " not exists", new Object[0]);
    }

    public Map a(Context context, int i2, String str, Map map) {
        Command command;
        if (i2 != 0) {
            command = i2 != 1 ? null : f20897c.b().get(str);
        } else {
            command = f20898d.b().get(str);
        }
        if (command != null) {
            HashMap hashMap = new HashMap();
            try {
                Timber.tag(f20895a).d(command.name() + " exists", new Object[0]);
                return ((SyncCommand) command).execute(context, map);
            } catch (Exception unused) {
                Timber.tag(f20895a).d(command.name() + " is not a SyncCommand", new Object[0]);
                hashMap.put("error_msg", command.name() + " is not a SyncCommand");
                return hashMap;
            }
        }
        HashMap hashMap2 = new HashMap();
        Timber.tag(f20895a).e(str + " not exists", new Object[0]);
        hashMap2.put("error_msg", str + " not exists");
        return hashMap2;
    }
}
