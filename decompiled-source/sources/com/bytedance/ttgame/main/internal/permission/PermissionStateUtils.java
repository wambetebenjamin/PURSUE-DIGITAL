package com.bytedance.ttgame.main.internal.permission;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import androidx.core.app.ActivityCompat;
import com.bytedance.ttgame.library.module_manager.ModuleManager;
import com.bytedance.ttgame.main.internal.log.IGLogService;
import com.bytedance.ttgame.module.dynamic.api.DynamicPageState;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes3.dex */
public class PermissionStateUtils {
    public static boolean hasPermission(Context context, String str) {
        return ActivityCompat.checkSelfPermission(context, str) == 0;
    }

    public static Map<Integer, Boolean> getAllPermissionsStates(Context context) {
        try {
            return getAllPermissionsStates(context, context.getPackageManager().getPackageInfo(context.getPackageName(), 4096).requestedPermissions);
        } catch (Exception e2) {
            HashMap hashMap = new HashMap();
            ((IGLogService) ModuleManager.INSTANCE.getService(IGLogService.class)).d("getAllPermissionsStates", e2.toString());
            return hashMap;
        }
    }

    public static void changePermissionState(Context context, Integer num, boolean z) {
        ((IGLogService) ModuleManager.INSTANCE.getService(IGLogService.class)).d("changePermissionState", "permission type: " + num + " toState: " + z);
        if (!PermissionsSets.getPermissionsSetGranted(context, num)) {
            goToAppInfoPage(context);
        } else if (!z) {
            goToAppInfoPage(context);
        } else {
            ((IGLogService) ModuleManager.INSTANCE.getService(IGLogService.class)).d("changePermissionState", "permission type:" + num + "already allowed");
        }
    }

    private static void goToAppInfoPage(Context context) {
        Intent intent = new Intent();
        intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.parse("package:" + context.getPackageName()));
        context.startActivity(intent);
    }

    public static Map<Integer, Boolean> getAllPermissionsStates(Context context, String[] strArr) {
        HashMap hashMap = new HashMap();
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        boolean z5 = false;
        boolean z6 = false;
        for (String str : strArr) {
            if (PermissionsSets.PHONE_PERMISSIONS.contains(str)) {
                if (!z) {
                    hashMap.put(8, Boolean.valueOf(PermissionsSets.getPhonePermissionGranted(context)));
                    ((IGLogService) ModuleManager.INSTANCE.getService(IGLogService.class)).d("getAllPermissionsStates", "8");
                    z = true;
                }
            } else if (PermissionsSets.CONTACTS_PERMISSIONS.contains(str)) {
                if (!z2) {
                    hashMap.put(7, Boolean.valueOf(PermissionsSets.getContactsPermissionGranted(context)));
                    ((IGLogService) ModuleManager.INSTANCE.getService(IGLogService.class)).d("getAllPermissionsStates", "7");
                    z2 = true;
                }
            } else if (PermissionsSets.ACCURATE_LOCATION_PERMISSIONS.contains(str)) {
                if (!z3) {
                    hashMap.put(6, Boolean.valueOf(PermissionsSets.getGPSPermissionGranted(context)));
                    ((IGLogService) ModuleManager.INSTANCE.getService(IGLogService.class)).d("getAllPermissionsStates", DynamicPageState.BUNDLE_NOT_FOUND);
                    z3 = true;
                }
            } else if (PermissionsSets.CAMERA_PERMISSIONS.contains(str)) {
                if (!z4) {
                    hashMap.put(1, Boolean.valueOf(PermissionsSets.getCameraPermissionGranted(context)));
                    ((IGLogService) ModuleManager.INSTANCE.getService(IGLogService.class)).d("getAllPermissionsStates", "1");
                    z4 = true;
                }
            } else if (PermissionsSets.MICROPHONE_PERMISSIONS.contains(str)) {
                if (!z5) {
                    hashMap.put(4, Boolean.valueOf(PermissionsSets.getMicrophonePermissionGranted(context)));
                    ((IGLogService) ModuleManager.INSTANCE.getService(IGLogService.class)).d("getAllPermissionsStates", DynamicPageState.NOT_FOUND);
                    z5 = true;
                }
            } else if (PermissionsSets.STORAGE_PERMISSIONS.contains(str) && !z6) {
                hashMap.put(10, Boolean.valueOf(PermissionsSets.getStoragePermissionGranted(context)));
                ((IGLogService) ModuleManager.INSTANCE.getService(IGLogService.class)).d("getAllPermissionsStates", "10");
                z6 = true;
            }
        }
        return hashMap;
    }
}
