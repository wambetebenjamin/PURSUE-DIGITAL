package com.oasis.android;

import android.app.Activity;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.oasis.unity.OasisPlayerActivity;
/* loaded from: classes9.dex */
public class SplashManager {
    public static String Tag = "com.oasis.android.SplashManager";

    /* renamed from: a  reason: collision with root package name */
    private static volatile ImageView f17436a;

    /* loaded from: classes9.dex */
    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Activity activity = ActivityManager.getActivity();
            if (activity instanceof OasisPlayerActivity) {
                ((OasisPlayerActivity) activity).setAllViewsVisible();
                Log.d(SplashManager.Tag, "setAllViewsVisible ok.");
            } else {
                Log.e(SplashManager.Tag, new Exception("activity not OasisPlayerActivity!").toString());
            }
            if (SplashManager.f17436a != null) {
                ((ViewGroup) SplashManager.f17436a.getParent()).removeView(SplashManager.f17436a);
                SplashManager.f17436a = null;
            }
        }
    }

    public static void closeStaticSplashView() {
        Log.d(Tag, "closeStaticSplashView()");
        if (f17436a != null) {
            ActivityManager.getActivity().runOnUiThread(new a());
        } else {
            Log.e(Tag, new Exception("staticSplashImageView is null!").toString());
        }
    }

    public static void setStaticSplashImageView(ImageView imageView) {
        f17436a = imageView;
    }
}
