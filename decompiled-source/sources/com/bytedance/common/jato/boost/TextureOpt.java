package com.bytedance.common.jato.boost;

import java.io.File;
/* loaded from: classes.dex */
public class TextureOpt {
    private static native int nativeUnblockJNISurfaceTexture();

    public static int a() {
        if (com.bytedance.common.jato.d.loadLibrary()) {
            if (new File("/data/local/tmp/disable_sf_opt").exists()) {
                return 0;
            }
            return nativeUnblockJNISurfaceTexture();
        }
        return -1;
    }
}
