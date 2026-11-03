package com.bytedance.notification.activity;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import com.bytedance.common.notification.R;
import com.bytedance.common.push.notification.BaseBannerActivity;
import com.bytedance.notification.helper.b;
import com.bytedance.notification.supporter.d;
import com.bytedance.push.utils.h;
import com.ss.android.socialbase.downloader.utils.DownloadExpSwitchCode;
import java.lang.ref.WeakReference;
import java.util.List;
/* loaded from: classes2.dex */
public class BannerActivity extends BaseBannerActivity {
    private final String TAG = "BannerActivityTag";
    private boolean mNeedFinishPending = true;
    private int mDisMissAnimal = R.anim.banner_out;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        h.d("BannerActivityTag", "onCreate of BannerActivity, sIsShowing is " + b.a().g());
        if (b.a().g()) {
            h.w("BannerActivityTag", "onCreate of BannerActivity, sIsShowing is true,finish self");
            finish();
            return;
        }
        b.a().a(new WeakReference<>(this));
        b.a().a(true);
        WeakReference<View> c2 = b.a().c();
        if (c2 == null) {
            h.w("BannerActivityTag", "bannerViewRef is null,finish self");
            finish();
            return;
        }
        View view = c2.get();
        if (view == null) {
            h.w("BannerActivityTag", "bannerView is null,finish self");
            finish();
            return;
        }
        Window window = getWindow();
        try {
            window.setGravity(49);
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.x = 35;
            attributes.y = b.a().d() + getResources().getDimensionPixelSize(getResources().getIdentifier("status_bar_height", "dimen", "android"));
            attributes.height = -2;
            attributes.width = -1;
            window.setAttributes(attributes);
            window.setFlags(32, 32);
            setContentView(view);
            overridePendingTransition(R.anim.banner_in, R.anim.banner_silent);
            h.d("onCreate of BannerActivity, sDuration is " + b.a().e());
            view.postDelayed(new Runnable() { // from class: com.bytedance.notification.activity.BannerActivity.1
                @Override // java.lang.Runnable
                public void run() {
                    h.d("finish BannerActivity");
                    BannerActivity.this.finish();
                }
            }, b.a().e());
            d.get().a(b.a().f(), true, "success", "actual");
        } catch (Throwable th) {
            h.e("BannerActivityTag", "exception in banner,finish self ", th);
            finish();
        }
    }

    public void finishSelf(boolean z, int i2) {
        Log.d("BannerActivityTag", "finishSelf");
        this.mNeedFinishPending = z;
        this.mDisMissAnimal = i2;
        if (b.a().g()) {
            finish();
        }
    }

    @Override // android.app.Activity
    public void finish() {
        List<ActivityManager.AppTask> appTasks;
        ActivityManager.AppTask appTask;
        b.a().h();
        super.finish();
        Log.d("BannerActivityTag", "on finish, mNeedFinishPending is " + this.mNeedFinishPending + " mDisMissAnimal is " + this.mDisMissAnimal);
        if (this.mNeedFinishPending) {
            overridePendingTransition(0, this.mDisMissAnimal);
        }
        if (Build.VERSION.SDK_INT < 23 || (appTasks = ((ActivityManager) getSystemService(ActivityManager.class)).getAppTasks()) == null) {
            return;
        }
        Log.d("BannerActivityTag", "tasks size is " + appTasks.size());
        if (appTasks.size() <= 0 || (appTask = appTasks.get(0)) == null) {
            return;
        }
        h.d("BannerActivityTag", "set topTask excludeFrom Recents to true");
        appTask.setExcludeFromRecents(true);
    }

    public static void showBannerView(Context context, View view, int i2, long j2) {
        Class cls;
        b.a().b(new WeakReference<>(view));
        b.a().a(i2);
        b.a().a(j2);
        try {
            if (com.ss.android.message.util.b.isSmpProcess(context)) {
                cls = SmpBannerActivity.class;
            } else {
                cls = BannerActivity.class;
            }
            Intent intent = new Intent(context, cls);
            intent.addFlags(DownloadExpSwitchCode.BUGFIX_GETPACKAGEINFO_BY_UNZIP);
            context.startActivity(intent);
        } catch (Throwable th) {
            h.e("showBannerView", th.getMessage());
        }
    }

    public static void setBannerView(View view, int i2, long j2, long j3) {
        b.a().b(new WeakReference<>(view));
        b.a().a(i2);
        b.a().a(j2);
        b.a().b(j3);
    }
}
