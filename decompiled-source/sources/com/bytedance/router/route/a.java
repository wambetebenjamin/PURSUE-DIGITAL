package com.bytedance.router.route;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.ss.android.socialbase.downloader.utils.DownloadExpSwitchCode;
/* compiled from: ActivityRoute.java */
/* loaded from: classes2.dex */
public class a extends g {
    @Override // com.bytedance.router.route.g
    public void openComponent(Context context, Intent intent) {
        com.bytedance.router.c a2 = a();
        if (a2.getData() != null) {
            intent.setData(a2.getData());
        }
        if (context instanceof Activity) {
            if (a2.hasRequestCode()) {
                ((Activity) context).startActivityForResult(intent, a2.getRequestCode());
            } else {
                context.startActivity(intent);
            }
            if (a2.getEnterAnim() == -1 && a2.getExitAnim() == -1) {
                return;
            }
            ((Activity) context).overridePendingTransition(a().getEnterAnim(), a().getExitAnim());
            return;
        }
        intent.addFlags(DownloadExpSwitchCode.BUGFIX_GETPACKAGEINFO_BY_UNZIP);
        context.startActivity(intent);
        if (a2.hasRequestCode()) {
            com.bytedance.router.util.b.e("SmartRoute.open(int requestCode):the context must be Activity !!!");
        }
        if (a2.getEnterAnim() == -1 && a2.getExitAnim() == -1) {
            return;
        }
        com.bytedance.router.util.b.e("SmartRoute.withAnimation(int enterAnim, int exitAnim):the context must be Activity !!!");
    }
}
