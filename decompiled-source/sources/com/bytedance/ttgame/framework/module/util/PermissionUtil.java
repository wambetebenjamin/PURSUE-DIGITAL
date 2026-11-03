package com.bytedance.ttgame.framework.module.util;

import android.app.Activity;
import java.util.List;
import pub.devrel.easypermissions.EasyPermissions;
/* loaded from: classes3.dex */
public class PermissionUtil {

    /* loaded from: classes3.dex */
    public interface IPermissionCallback {
        void onPermissionsDenied(int i2, List<String> list);

        void onPermissionsGranted(int i2, List<String> list);
    }

    public static void checkAndRequest(Activity activity, String str, int i2, String... strArr) {
        if (hasPermissions(activity, strArr)) {
            return;
        }
        requestPermissions(activity, str, i2, strArr);
    }

    public static boolean hasPermissions(Activity activity, String[] strArr) {
        return EasyPermissions.hasPermissions(activity, strArr);
    }

    public static void requestPermissions(Activity activity, String str, int i2, String... strArr) {
        EasyPermissions.requestPermissions(activity, str, i2, strArr);
    }

    public static void onRequestPermissionsResult(int i2, String[] strArr, int[] iArr, Object... objArr) {
        if (objArr[0] instanceof IPermissionCallback) {
            final IPermissionCallback iPermissionCallback = (IPermissionCallback) objArr[0];
            EasyPermissions.onRequestPermissionsResult(i2, strArr, iArr, new EasyPermissions.PermissionCallbacks() { // from class: com.bytedance.ttgame.framework.module.util.PermissionUtil.1
                @Override // androidx.core.app.ActivityCompat.OnRequestPermissionsResultCallback
                public void onRequestPermissionsResult(int i3, String[] strArr2, int[] iArr2) {
                }

                @Override // pub.devrel.easypermissions.EasyPermissions.PermissionCallbacks
                public void onPermissionsGranted(int i3, List<String> list) {
                    IPermissionCallback.this.onPermissionsGranted(i3, list);
                }

                @Override // pub.devrel.easypermissions.EasyPermissions.PermissionCallbacks
                public void onPermissionsDenied(int i3, List<String> list) {
                    IPermissionCallback.this.onPermissionsDenied(i3, list);
                }
            });
            return;
        }
        EasyPermissions.onRequestPermissionsResult(i2, strArr, iArr, objArr);
    }
}
