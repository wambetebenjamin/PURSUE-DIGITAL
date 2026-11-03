package com.bytedance.react.framework.core;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import androidx.fragment.app.FragmentActivity;
import com.bytedance.react.framework.RNConfig;
import com.bytedance.react.framework.window.BRNWindowFragment;
import com.bytedance.react.framework.window.BRNWindowSupportFragment;
import com.bytedance.react.framework.window.BRNWindowView;
import com.bytedance.router.k;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* loaded from: classes2.dex */
public class BRNWindowManager {
    private static List<IBRNWindow> mList = Collections.synchronizedList(new ArrayList());

    /* loaded from: classes2.dex */
    public interface IOperationWindowListener {
        void onOperationResult(boolean z);
    }

    public static String showWindow(Activity activity, String str, Bundle bundle, String str2, String str3, String str4, String str5, Bundle bundle2, int i2) {
        String valueOf;
        IBRNWindow newInstance;
        if (str2 == null || str3 == null) {
            return null;
        }
        if (Build.VERSION.SDK_INT >= 17) {
            valueOf = String.valueOf(SystemClock.elapsedRealtimeNanos());
        } else {
            valueOf = String.valueOf(System.currentTimeMillis());
        }
        String str6 = valueOf;
        bundle.putString(RNConfig.WINDOW_ID, str6);
        if (str.contains("window")) {
            BRNWindowView bRNWindowView = new BRNWindowView(activity);
            bRNWindowView.createRNWindowFullScreen(str, str2, str3, str4, str5, bundle2, i2, bundle);
            bRNWindowView.setWindowToken(str6);
            mList.add(bRNWindowView);
            bRNWindowView.showWindow(activity);
        } else if (str.contains("rndialog")) {
            bundle.putString("window", "1");
            if (activity instanceof FragmentActivity) {
                newInstance = BRNWindowSupportFragment.newInstance(str, str2, str3, str4, str5, bundle2, i2, bundle, str6);
            } else {
                newInstance = BRNWindowFragment.newInstance(str, str2, str3, str4, str5, bundle2, i2, bundle, str6);
            }
            mList.add(newInstance);
            newInstance.showWindow(activity);
        } else {
            try {
                k.buildRoute(activity, str).withParam(RNConfig.RN_URL, str).withParam(RNConfig.PARAM_BUNDLE, bundle).withParam(RNConfig.BUNDLE_NAME, str4).withParam(RNConfig.COMMON_BUNDLE, str5).withParam(RNConfig.FRORM_WHERE, i2).withParam(RNConfig.COMMON_INFO, bundle2).open();
            } catch (Throwable unused) {
            }
        }
        return str6;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static IBRNWindow getWindowWithLast(String str) {
        if (TextUtils.isEmpty(str)) {
            if (mList.size() > 0) {
                List<IBRNWindow> list = mList;
                return list.get(list.size() - 1);
            }
            return null;
        }
        for (IBRNWindow iBRNWindow : mList) {
            if (str.equals(iBRNWindow.getWindowToken())) {
                return iBRNWindow;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static IBRNWindow removeWindowWithLast(String str) {
        IBRNWindow windowWithLast = getWindowWithLast(str);
        if (windowWithLast != null) {
            mList.remove(windowWithLast);
            return windowWithLast;
        }
        return null;
    }

    public static IBRNWindow getWindow(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        for (IBRNWindow iBRNWindow : mList) {
            if (str.equals(iBRNWindow.getWindowToken())) {
                return iBRNWindow;
            }
        }
        return null;
    }

    public static void removeWindow(IBRNWindow iBRNWindow) {
        mList.remove(iBRNWindow);
    }

    public static void addWindow(IBRNWindow iBRNWindow) {
        if (getWindow(iBRNWindow.getWindowToken()) == null) {
            mList.add(iBRNWindow);
        }
    }

    public static void sendEventToRN(String str, String str2, String str3) {
        IBRNWindow windowWithLast = getWindowWithLast(str3);
        if (windowWithLast != null) {
            windowWithLast.sentMessageToRN(str, str2);
        }
    }

    public static boolean closeWindow(Activity activity, final String str, final IOperationWindowListener iOperationWindowListener) {
        if (activity == null) {
            RNLogUtil.reportLog("rn_page_close", "error", "activiry is null&token:" + str);
            if (iOperationWindowListener != null) {
                iOperationWindowListener.onOperationResult(false);
            }
            return false;
        }
        activity.runOnUiThread(new Runnable() { // from class: com.bytedance.react.framework.core.BRNWindowManager.1
            @Override // java.lang.Runnable
            public void run() {
                IBRNWindow removeWindowWithLast = BRNWindowManager.removeWindowWithLast(str);
                if (removeWindowWithLast != null) {
                    removeWindowWithLast.close();
                    IOperationWindowListener iOperationWindowListener2 = iOperationWindowListener;
                    if (iOperationWindowListener2 != null) {
                        iOperationWindowListener2.onOperationResult(true);
                        return;
                    }
                    return;
                }
                IOperationWindowListener iOperationWindowListener3 = iOperationWindowListener;
                if (iOperationWindowListener3 != null) {
                    iOperationWindowListener3.onOperationResult(false);
                }
            }
        });
        RNLogUtil.reportLog("rn_page_close", "end", str);
        return true;
    }

    public static boolean show(Activity activity, final String str, final IOperationWindowListener iOperationWindowListener) {
        if (activity != null) {
            activity.runOnUiThread(new Runnable() { // from class: com.bytedance.react.framework.core.BRNWindowManager.2
                @Override // java.lang.Runnable
                public void run() {
                    IBRNWindow windowWithLast = BRNWindowManager.getWindowWithLast(str);
                    if (windowWithLast != null) {
                        boolean show = windowWithLast.show();
                        IOperationWindowListener iOperationWindowListener2 = iOperationWindowListener;
                        if (iOperationWindowListener2 != null) {
                            iOperationWindowListener2.onOperationResult(show);
                            return;
                        }
                        return;
                    }
                    IOperationWindowListener iOperationWindowListener3 = iOperationWindowListener;
                    if (iOperationWindowListener3 != null) {
                        iOperationWindowListener3.onOperationResult(false);
                    }
                }
            });
            return true;
        }
        if (iOperationWindowListener != null) {
            iOperationWindowListener.onOperationResult(false);
        }
        return false;
    }

    public static boolean hide(Activity activity, final String str, final IOperationWindowListener iOperationWindowListener) {
        if (activity != null) {
            activity.runOnUiThread(new Runnable() { // from class: com.bytedance.react.framework.core.BRNWindowManager.3
                @Override // java.lang.Runnable
                public void run() {
                    IBRNWindow windowWithLast = BRNWindowManager.getWindowWithLast(str);
                    if (windowWithLast != null) {
                        boolean hide = windowWithLast.hide();
                        IOperationWindowListener iOperationWindowListener2 = iOperationWindowListener;
                        if (iOperationWindowListener2 != null) {
                            iOperationWindowListener2.onOperationResult(hide);
                            return;
                        }
                        return;
                    }
                    IOperationWindowListener iOperationWindowListener3 = iOperationWindowListener;
                    if (iOperationWindowListener3 != null) {
                        iOperationWindowListener3.onOperationResult(false);
                    }
                }
            });
            return true;
        }
        if (iOperationWindowListener != null) {
            iOperationWindowListener.onOperationResult(false);
        }
        return false;
    }

    public static void popAll(Activity activity) {
        if (activity != null) {
            activity.runOnUiThread(new Runnable() { // from class: com.bytedance.react.framework.core.BRNWindowManager.4
                @Override // java.lang.Runnable
                public void run() {
                    if (BRNWindowManager.mList != null) {
                        List<IBRNWindow> openWindowList = BRNWindowManager.getOpenWindowList();
                        for (IBRNWindow iBRNWindow : openWindowList) {
                            iBRNWindow.close();
                        }
                        openWindowList.clear();
                        BRNWindowManager.mList.clear();
                    }
                }
            });
        }
    }

    public static List<IBRNWindow> getOpenWindowList() {
        ArrayList arrayList = new ArrayList();
        if (BRNPreloadViewManager.getInstance().needPreload()) {
            for (IBRNWindow iBRNWindow : mList) {
                if (!BRNPreloadViewManager.getInstance().isNotUsePreloadWindow(iBRNWindow.getWindowToken())) {
                    arrayList.add(iBRNWindow);
                }
            }
        } else {
            arrayList.addAll(mList);
        }
        return arrayList;
    }
}
