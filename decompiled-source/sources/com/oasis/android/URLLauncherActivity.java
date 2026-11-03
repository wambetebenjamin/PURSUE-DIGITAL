package com.oasis.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.oasis.Logger.Logger;
import com.oasis.unity.OasisPlayerActivity;
/* loaded from: classes9.dex */
public final class URLLauncherActivity extends Activity {

    /* renamed from: b  reason: collision with root package name */
    private static String f17447b;

    /* renamed from: c  reason: collision with root package name */
    private static URLLauncherListener f17448c;

    /* renamed from: d  reason: collision with root package name */
    private static a f17449d = a.None;

    /* renamed from: a  reason: collision with root package name */
    private Handler f17450a = new b(this);

    /* loaded from: classes9.dex */
    private enum a {
        None,
        InitFromUri,
        InitFinish
    }

    /* loaded from: classes9.dex */
    private static class b extends Handler {

        /* renamed from: a  reason: collision with root package name */
        private Activity f17455a;

        b(URLLauncherActivity uRLLauncherActivity) {
            this.f17455a = uRLLauncherActivity;
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (this.f17455a != null) {
                this.f17455a.startActivity(new Intent(this.f17455a.getApplication(), OasisPlayerActivity.class));
                this.f17455a.finish();
                this.f17455a = null;
            }
        }
    }

    public static String getUri() {
        return f17447b;
    }

    public static void initLauncherListener(URLLauncherListener uRLLauncherListener) {
        Logger.i("URLLauncher", "initLauncherListener");
        f17448c = uRLLauncherListener;
        if (f17449d == a.InitFromUri) {
            f17448c.onLaunch(f17447b);
        }
        f17449d = a.InitFinish;
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        Logger.i("URLLauncher", "URLLauncherActivity onCreate");
        super.onCreate(bundle);
        f17447b = getIntent().getData().toString();
        Logger.i("URLLauncher", "uri:" + f17447b);
        this.f17450a.sendEmptyMessageDelayed(0, 10L);
        if (f17449d == a.None) {
            Logger.i("URLLauncher", "launcherListener is null");
            f17449d = a.InitFromUri;
        } else if (f17449d == a.InitFinish) {
            f17448c.onLaunch(f17447b);
        }
    }
}
