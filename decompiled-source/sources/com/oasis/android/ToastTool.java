package com.oasis.android;

import android.content.Context;
import android.widget.Toast;
/* loaded from: classes9.dex */
public class ToastTool {

    /* renamed from: b  reason: collision with root package name */
    private static ToastTool f17441b = null;

    /* renamed from: c  reason: collision with root package name */
    static final /* synthetic */ boolean f17442c = true;

    /* renamed from: a  reason: collision with root package name */
    private Context f17443a;

    /* loaded from: classes9.dex */
    class a implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ boolean f17444a;

        /* renamed from: b  reason: collision with root package name */
        final /* synthetic */ String f17445b;

        a(boolean z, String str) {
            this.f17444a = z;
            this.f17445b = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            Toast.makeText(ActivityManager.getActivity(), this.f17445b, 1).show();
        }
    }

    private ToastTool(Context context) {
        this.f17443a = context;
    }

    public static ToastTool createInstance(Context context) {
        if (f17442c || f17441b == null) {
            ToastTool toastTool = new ToastTool(context);
            f17441b = toastTool;
            return toastTool;
        }
        throw new AssertionError();
    }

    public static ToastTool getInstance() {
        if (f17442c || f17441b != null) {
            return f17441b;
        }
        throw new AssertionError();
    }

    public void showToast(String str, boolean z) {
        ActivityManager.getActivity().runOnUiThread(new a(z, str));
    }
}
