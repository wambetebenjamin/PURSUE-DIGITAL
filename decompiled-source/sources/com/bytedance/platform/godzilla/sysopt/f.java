package com.bytedance.platform.godzilla.sysopt;

import android.app.Application;
import android.os.Build;
/* compiled from: IvimageOptPlugin.java */
/* loaded from: classes2.dex */
public class f extends com.bytedance.platform.godzilla.plugin.a {
    @Override // com.bytedance.platform.godzilla.plugin.a
    public void destroy() {
    }

    @Override // com.bytedance.platform.godzilla.plugin.a
    public String getName() {
        return "IvimageOptPlugin";
    }

    @Override // com.bytedance.platform.godzilla.plugin.a
    public void init(Application application) {
    }

    @Override // com.bytedance.platform.godzilla.plugin.a
    public void stop() {
    }

    @Override // com.bytedance.platform.godzilla.plugin.a
    public void start() {
        com.bytedance.platform.godzilla.common.k.e(getName(), "start");
        if (Build.DISPLAY.contains("Flyme") || Build.USER.equals("flyme")) {
            Class<?> cls = null;
            try {
                try {
                    cls = Class.forName("ivimagesr.o000ooo");
                    com.bytedance.platform.godzilla.common.k.e("GODZILLA", "Class found = " + cls.getName());
                    if (cls == null) {
                        return;
                    }
                } catch (ClassNotFoundException e2) {
                    com.bytedance.platform.godzilla.common.k.e("GODZILLA", e2.toString());
                    if (cls == null) {
                        return;
                    }
                }
                com.bytedance.platform.godzilla.common.k.e("GODZILLA", "Register opt");
                IvimageOpt.start();
            } catch (Throwable th) {
                if (cls != null) {
                    com.bytedance.platform.godzilla.common.k.e("GODZILLA", "Register opt");
                    IvimageOpt.start();
                }
                throw th;
            }
        }
    }
}
