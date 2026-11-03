package com.bytedance.crash.game;

import android.text.TextUtils;
import com.bytedance.crash.IScriptCallback;
import com.bytedance.crash.util.k;
import com.bytedance.crash.util.q;
import java.io.File;
import org.json.JSONObject;
/* compiled from: GameScriptStack.java */
/* loaded from: classes2.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    private static final String f6753a = "game_script_stack.txt";

    /* renamed from: b  reason: collision with root package name */
    private static IScriptCallback f6754b;

    public static void a(IScriptCallback iScriptCallback) {
        f6754b = iScriptCallback;
    }

    public static void a(File file) {
        IScriptCallback iScriptCallback = f6754b;
        if (iScriptCallback != null) {
            try {
                String scriptStackWhenCrash = iScriptCallback.getScriptStackWhenCrash();
                if (scriptStackWhenCrash != null) {
                    k.c(new File(file, f6753a), scriptStackWhenCrash);
                }
            } catch (Throwable unused) {
            }
        }
    }

    public static void a(JSONObject jSONObject, File file) {
        File file2 = new File(file, f6753a);
        if (file2.exists()) {
            String d2 = k.d(file2);
            if (TextUtils.isEmpty(d2)) {
                return;
            }
            q.a(jSONObject, "game_script_stack", (Object) d2);
        }
    }
}
