package com.bytedance.ttgame.main.internal.permission;

import android.content.Context;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
/* loaded from: classes3.dex */
public class PermissionsSets {
    public static final Set<String> ACCURATE_LOCATION_PERMISSIONS;
    public static final Set<String> CAMERA_PERMISSIONS;
    public static final Set<String> CONTACTS_PERMISSIONS;
    public static final Set<String> MICROPHONE_PERMISSIONS;
    public static final Set<String> PHONE_PERMISSIONS;
    public static final Set<String> STORAGE_PERMISSIONS;
    public static final Map<Integer, Set<String>> permissionsMap;

    static {
        HashMap hashMap = new HashMap();
        permissionsMap = hashMap;
        HashSet hashSet = new HashSet();
        CAMERA_PERMISSIONS = hashSet;
        hashSet.add("android.permission.CAMERA");
        hashMap.put(1, hashSet);
        HashSet hashSet2 = new HashSet();
        MICROPHONE_PERMISSIONS = hashSet2;
        hashSet2.add("android.permission.RECORD_AUDIO");
        hashMap.put(4, hashSet2);
        HashSet hashSet3 = new HashSet();
        ACCURATE_LOCATION_PERMISSIONS = hashSet3;
        hashSet3.add("android.permission.ACCESS_FINE_LOCATION");
        hashMap.put(6, hashSet3);
        HashSet hashSet4 = new HashSet();
        CONTACTS_PERMISSIONS = hashSet4;
        hashSet4.add("android.permission.READ_CONTACTS");
        hashSet4.add("android.permission.WRITE_CONTACTS");
        hashSet4.add("android.permission.GET_ACCOUNTS");
        hashMap.put(7, hashSet4);
        HashSet hashSet5 = new HashSet();
        PHONE_PERMISSIONS = hashSet5;
        hashSet5.add("android.permission.READ_PHONE_STATE");
        hashSet5.add("android.permission.CALL_PHONE");
        hashSet5.add("android.permission.READ_CALL_LOG");
        hashSet5.add("android.permission.WRITE_CALL_LOG");
        hashSet5.add("com.android.voicemail.permission.ADD_VOICEMAIL");
        hashSet5.add("android.permission.USE_SIP");
        hashSet5.add("android.permission.PROCESS_OUTGOING_CALLS");
        hashMap.put(8, hashSet5);
        HashSet hashSet6 = new HashSet();
        STORAGE_PERMISSIONS = hashSet6;
        hashSet6.add("android.permission.READ_EXTERNAL_STORAGE");
        hashSet6.add("android.permission.WRITE_EXTERNAL_STORAGE");
        hashSet6.add("android.permission.ACCESS_MEDIA_LOCATION");
        hashMap.put(10, hashSet6);
    }

    public static boolean getPermissionsSetGranted(Context context, Integer num) {
        for (String str : permissionsMap.get(num)) {
            if (PermissionStateUtils.hasPermission(context, str)) {
                return true;
            }
        }
        return false;
    }

    public static boolean getCameraPermissionGranted(Context context) {
        for (String str : CAMERA_PERMISSIONS) {
            if (PermissionStateUtils.hasPermission(context, str)) {
                return true;
            }
        }
        return false;
    }

    public static boolean getMicrophonePermissionGranted(Context context) {
        for (String str : MICROPHONE_PERMISSIONS) {
            if (PermissionStateUtils.hasPermission(context, str)) {
                return true;
            }
        }
        return false;
    }

    public static boolean getGPSPermissionGranted(Context context) {
        for (String str : ACCURATE_LOCATION_PERMISSIONS) {
            if (PermissionStateUtils.hasPermission(context, str)) {
                return true;
            }
        }
        return false;
    }

    public static boolean getContactsPermissionGranted(Context context) {
        for (String str : CONTACTS_PERMISSIONS) {
            if (PermissionStateUtils.hasPermission(context, str)) {
                return true;
            }
        }
        return false;
    }

    public static boolean getPhonePermissionGranted(Context context) {
        for (String str : PHONE_PERMISSIONS) {
            if (PermissionStateUtils.hasPermission(context, str)) {
                return true;
            }
        }
        return false;
    }

    public static boolean getStoragePermissionGranted(Context context) {
        for (String str : STORAGE_PERMISSIONS) {
            if (PermissionStateUtils.hasPermission(context, str)) {
                return true;
            }
        }
        return false;
    }
}
