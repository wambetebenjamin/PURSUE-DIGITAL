package com.oasis.android;

import android.animation.Animator;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;
import com.oasis.unity.OasisPlayerActivity;
/* loaded from: classes9.dex */
public class SplashActivity extends Activity {
    public static boolean hasShowed;
    public static Uri launchURI;

    /* renamed from: b  reason: collision with root package name */
    private ImageView f17424b;

    /* renamed from: c  reason: collision with root package name */
    long f17425c;

    /* renamed from: d  reason: collision with root package name */
    long f17426d;

    /* renamed from: e  reason: collision with root package name */
    long f17427e;

    /* renamed from: a  reason: collision with root package name */
    private final float f17423a = 1.0E-4f;

    /* renamed from: f  reason: collision with root package name */
    private final Handler f17428f = new Handler(Looper.getMainLooper());

    /* renamed from: g  reason: collision with root package name */
    final Runnable f17429g = new a();

    /* renamed from: h  reason: collision with root package name */
    final Runnable f17430h = new b();

    /* loaded from: classes9.dex */
    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            SplashActivity.hasShowed = true;
            SplashActivity.this.startActivity(new Intent(SplashActivity.this, OasisPlayerActivity.class));
            SplashActivity.this.overridePendingTransition(0, 0);
            SplashActivity.this.finish();
        }
    }

    /* loaded from: classes9.dex */
    class b implements Runnable {

        /* loaded from: classes9.dex */
        class a extends e {
            a() {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                SplashActivity.this.f17429g.run();
            }
        }

        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            SplashActivity splashActivity = SplashActivity.this;
            long j2 = splashActivity.f17426d;
            if (((float) j2) <= 1.0E-4f) {
                splashActivity.f17429g.run();
            } else if (j2 > 0) {
                splashActivity.f17424b.animate().alphaBy(1.0f).alpha(0.0f).setDuration(SplashActivity.this.f17426d).setListener(new a());
            } else {
                splashActivity.f17429g.run();
            }
        }
    }

    /* loaded from: classes9.dex */
    class c extends e {
        c() {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            SplashActivity splashActivity = SplashActivity.this;
            splashActivity.f17428f.postDelayed(splashActivity.f17430h, splashActivity.f17427e);
        }
    }

    /* loaded from: classes9.dex */
    static /* synthetic */ class d {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f17435a;

        static {
            int[] iArr = new int[f.values().length];
            f17435a = iArr;
            try {
                iArr[f.MATRIX.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f17435a[f.CENTER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f17435a[f.FIT_XY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f17435a[f.FIT_END.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f17435a[f.FIT_START.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f17435a[f.FIT_CENTER.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f17435a[f.CENTER_CROP.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f17435a[f.CENTER_INSIDE.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    /* loaded from: classes9.dex */
    static class e implements Animator.AnimatorListener {
        e() {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0102  */
    /* JADX WARN: Removed duplicated region for block: B:28:? A[RETURN, SYNTHETIC] */
    @Override // android.app.Activity
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void onCreate(android.os.Bundle r5) {
        /*
            Method dump skipped, instructions count: 286
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oasis.android.SplashActivity.onCreate(android.os.Bundle):void");
    }

    @Override // android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        launchURI = intent.getData();
        if (hasShowed) {
            this.f17428f.post(this.f17429g);
        }
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        if (hasShowed) {
            return;
        }
        overridePendingTransition(0, 0);
    }

    @Override // android.app.Activity
    protected void onPostResume() {
        super.onPostResume();
        if (hasShowed) {
            return;
        }
        long j2 = this.f17425c;
        if (((float) j2) > 1.0E-4f) {
            if (j2 > 0) {
                this.f17424b.animate().alphaBy(0.0f).alpha(1.0f).setDuration(this.f17425c).setListener(new c());
                return;
            } else {
                this.f17428f.postDelayed(this.f17430h, this.f17427e);
                return;
            }
        }
        long j3 = this.f17427e;
        if (((float) j3) <= 1.0E-4f) {
            this.f17430h.run();
        } else {
            this.f17428f.postDelayed(this.f17430h, j3);
        }
    }
}
