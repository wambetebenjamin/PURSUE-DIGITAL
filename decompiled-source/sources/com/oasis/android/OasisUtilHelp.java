package com.oasis.android;

import android.app.Application;
import android.content.Context;
import com.oasis.Logger.Logger;
/* loaded from: classes9.dex */
public class OasisUtilHelp {

    /* renamed from: a  reason: collision with root package name */
    private static Application f17402a;

    public static synchronized String getAppName(Context context) {
        String string;
        synchronized (OasisUtilHelp.class) {
            try {
                string = context.getResources().getString(context.getPackageManager().getPackageInfo(context.getPackageName(), 0).applicationInfo.labelRes);
            } catch (Exception e2) {
                e2.printStackTrace();
                return null;
            }
        }
        return string;
    }

    public static void initialize(Application application) {
        f17402a = application;
    }

    public static boolean isUseGPM() {
        Application application = f17402a;
        if (application != null) {
            String string = application.getString(R.string.use_gpm);
            Logger.d("OasisUtilHelp", "isUseGPM:" + string);
            return string.equals("1");
        }
        return false;
    }
}
