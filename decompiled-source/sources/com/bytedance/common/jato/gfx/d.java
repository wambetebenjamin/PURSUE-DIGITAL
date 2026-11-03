package com.bytedance.common.jato.gfx;

import android.graphics.SurfaceTexture;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.TextureView;
import java.lang.reflect.Field;
/* compiled from: TextureViewUtil.java */
/* loaded from: classes.dex */
public class d {

    /* renamed from: a  reason: collision with root package name */
    private static final String f4746a = "TextureViewUtil";

    /* renamed from: b  reason: collision with root package name */
    private static final String f4747b = "oppo";

    /* renamed from: c  reason: collision with root package name */
    private static final String f4748c = "oneplus";

    /* renamed from: d  reason: collision with root package name */
    private static final String f4749d = "realme";

    /* renamed from: e  reason: collision with root package name */
    private static final String f4750e = "android.view.OppoBaseTextureView";

    /* renamed from: f  reason: collision with root package name */
    private static final String f4751f = "mOnFrameAvailableHandler";

    /* renamed from: g  reason: collision with root package name */
    private static Class f4752g;

    /* renamed from: h  reason: collision with root package name */
    private static Field f4753h;

    static {
        String str = Build.BRAND;
        if (str != null) {
            String lowerCase = str.toLowerCase();
            if (TextUtils.equals(lowerCase, f4747b) || TextUtils.equals(lowerCase, f4748c) || TextUtils.equals(lowerCase, f4749d)) {
                try {
                    f4752g = Class.forName(f4750e);
                } catch (ClassNotFoundException unused) {
                }
            }
        }
        try {
            Field declaredField = SurfaceTexture.class.getDeclaredField(f4751f);
            f4753h = declaredField;
            declaredField.setAccessible(true);
            Log.d(f4746a, "Init Handler field success");
        } catch (NoSuchFieldException e2) {
            Log.d(f4746a, "Failed to init Handler field " + e2);
        }
    }

    public static void a(TextureView textureView, boolean z) {
        if (textureView == null || f4753h == null) {
            return;
        }
        Class cls = f4752g;
        if (cls != null && cls.isInstance(textureView)) {
            Log.d(f4746a, "Ignore OppoBaseTextureView");
            return;
        }
        SurfaceTexture surfaceTexture = textureView.getSurfaceTexture();
        if (surfaceTexture != null) {
            a(surfaceTexture, z);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: TextureViewUtil.java */
    /* loaded from: classes.dex */
    public static class a extends Handler {

        /* renamed from: a  reason: collision with root package name */
        private Handler f4754a;

        /* renamed from: b  reason: collision with root package name */
        private boolean f4755b;

        public a(Handler handler, boolean z) {
            super(handler.getLooper());
            this.f4754a = handler;
            this.f4755b = z;
        }

        @Override // android.os.Handler
        public boolean sendMessageAtTime(Message message, long j2) {
            if (this.f4755b && Build.VERSION.SDK_INT >= 22) {
                message.setAsynchronous(true);
            }
            return this.f4754a.sendMessageAtFrontOfQueue(message);
        }
    }

    private static void a(SurfaceTexture surfaceTexture, boolean z) {
        try {
            Handler handler = (Handler) f4753h.get(surfaceTexture);
            if (handler == null) {
                Log.d(f4746a, "Handle object is null");
            } else if (handler instanceof a) {
            } else {
                f4753h.set(surfaceTexture, new a(handler, z));
                Log.d(f4746a, "Replace SurfaceTexutre handler success");
            }
        } catch (Exception e2) {
            Log.w(f4746a, "Replace SurfaceTexutre handler failed " + e2);
        }
    }
}
