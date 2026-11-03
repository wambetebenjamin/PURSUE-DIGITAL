package com.oasis.android;

import android.util.Log;
import androidx.core.app.ActivityCompat;
import com.oasis.Logger.Logger;
import java.util.concurrent.ConcurrentHashMap;
/* loaded from: classes9.dex */
public class MultiplePermissionMgr {
    public static final String TAG = "MultiplePermissionMgr";
    private static int tot;
    static final ConcurrentHashMap<Integer, PermissionMgrListener> taskMap = new ConcurrentHashMap<>();
    private static boolean isInited = false;

    /* loaded from: classes9.dex */
    class a extends com.oasis.android.a {
        a() {
        }

        @Override // com.oasis.android.a, com.oasis.android.ActivityListener
        public void onRequestPermissionsResult(int i2, String[] strArr, int[] iArr) {
            PermissionMgrListener permissionMgrListener;
            Logger.i(MultiplePermissionMgr.TAG, "onRequestPermissionsResult");
            int length = strArr.length;
            for (int i3 = 0; i3 < length; i3++) {
                Logger.i(MultiplePermissionMgr.TAG, "onRequestPermissionsResult, " + strArr[i3]);
            }
            if (strArr.length == 0 || iArr.length == 0 || (permissionMgrListener = MultiplePermissionMgr.taskMap.get(Integer.valueOf(i2))) == null) {
                return;
            }
            permissionMgrListener.onResult(iArr[0] == 0);
        }
    }

    public static void init() {
        if (isInited) {
            return;
        }
        isInited = true;
        Log.d(TAG, "PermissionMgr init ok!");
        ActivityManager.registerListener(new a());
    }

    public static void requestPermissionSync(String[] strArr, PermissionMgrListener permissionMgrListener) {
        int i2 = tot + 1;
        tot = i2;
        int i3 = i2 % 998244353;
        tot = i3;
        taskMap.put(Integer.valueOf(i3), permissionMgrListener);
        ActivityCompat.requestPermissions(ActivityManager.getActivity(), strArr, tot);
    }
}
