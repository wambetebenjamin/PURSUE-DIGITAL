package com.bytedance.ttgame.framework.module.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.bytedance.ttgame.gsdk_base_utils.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes3.dex */
public class PermissionMediator {
    private static final String TAG = "PermissionMediator";
    private static int sIncRequestCode;
    private static Map<Integer, OnPermissionRequestListener> mListenerMap = new HashMap();
    private static Map<String, Map<String, Integer>> mPermissionCountMap = new HashMap();
    private static Map<String, Integer> mBackCountMap = new HashMap();

    /* loaded from: classes3.dex */
    public static abstract class DefaultPermissionRequest implements OnPermissionRequestListener {
        @Override // com.bytedance.ttgame.framework.module.util.PermissionMediator.OnPermissionRequestListener
        public void onPermissionRequest(boolean z, String str) {
        }

        @Override // com.bytedance.ttgame.framework.module.util.PermissionMediator.OnPermissionRequestListener
        public void onPermissionRequest(boolean z, String[] strArr, int[] iArr) {
        }
    }

    /* loaded from: classes3.dex */
    public interface OnPermissionRequestListener {
        void onPermissionRequest(boolean z, String str);

        void onPermissionRequest(boolean z, String[] strArr, int[] iArr);
    }

    public static void dispatchPermissionResultNew(String str, Activity activity, int i2, String[] strArr, int[] iArr, String str2, String str3, String str4) {
        OnPermissionRequestListener onPermissionRequestListener;
        boolean z;
        if (strArr.length == 0 || iArr.length == 0 || (onPermissionRequestListener = mListenerMap.get(Integer.valueOf(i2))) == null) {
            return;
        }
        boolean z2 = false;
        if (strArr.length == 1) {
            z = iArr[0] == 0;
            onPermissionRequestListener.onPermissionRequest(z, strArr[0]);
        } else {
            int length = iArr.length;
            int i3 = 0;
            while (true) {
                if (i3 >= length) {
                    z2 = true;
                    break;
                } else if (iArr[i3] != 0) {
                    onPermissionRequestListener.onPermissionRequest(false, strArr, iArr);
                    break;
                } else {
                    i3++;
                }
            }
            if (z2) {
                onPermissionRequestListener.onPermissionRequest(true, strArr, iArr);
            }
            z = z2;
        }
        if (!z && !TextUtils.isEmpty(str3) && !TextUtils.isEmpty(str4) && !TextUtils.isEmpty(str2)) {
            showDialog(activity, permissionIsRefuseAndHide(activity, strArr, iArr), permissionIsRefuseOnce(str, activity, strArr, iArr), str2, str3, str4, false);
        }
        mListenerMap.remove(Integer.valueOf(i2));
    }

    public static void dispatchPermissionResultNew(String str, Activity activity, int i2, String[] strArr, int[] iArr, String str2, String str3, String str4, boolean z) {
        OnPermissionRequestListener onPermissionRequestListener;
        boolean z2;
        if (strArr.length == 0 || iArr.length == 0 || (onPermissionRequestListener = mListenerMap.get(Integer.valueOf(i2))) == null) {
            return;
        }
        boolean z3 = false;
        if (strArr.length == 1) {
            z2 = iArr[0] == 0;
            onPermissionRequestListener.onPermissionRequest(z2, strArr[0]);
        } else {
            int length = iArr.length;
            int i3 = 0;
            while (true) {
                if (i3 >= length) {
                    z3 = true;
                    break;
                } else if (iArr[i3] != 0) {
                    onPermissionRequestListener.onPermissionRequest(false, strArr, iArr);
                    break;
                } else {
                    i3++;
                }
            }
            if (z3) {
                onPermissionRequestListener.onPermissionRequest(true, strArr, iArr);
            }
            z2 = z3;
        }
        if (!z2 && !TextUtils.isEmpty(str3) && !TextUtils.isEmpty(str4) && !TextUtils.isEmpty(str2)) {
            showDialog(activity, permissionIsRefuseAndHide(activity, strArr, iArr), permissionIsRefuseOnce(str, activity, strArr, iArr), str2, str3, str4, z);
        }
        mListenerMap.remove(Integer.valueOf(i2));
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x000e, code lost:
        if (r11[0] == 0) goto L21;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void dispatchPermissionResultNew(java.lang.String r8, android.app.Activity r9, java.lang.String[] r10, int[] r11, java.lang.String r12, java.lang.String r13, java.lang.String r14) {
        /*
            int r0 = r10.length
            if (r0 == 0) goto L46
            int r0 = r11.length
            if (r0 != 0) goto L7
            goto L46
        L7:
            int r0 = r10.length
            r1 = 0
            r2 = 1
            if (r0 != r2) goto L11
            r0 = r11[r1]
            if (r0 != 0) goto L1e
            goto L1d
        L11:
            int r0 = r11.length
            r3 = r1
        L13:
            if (r3 >= r0) goto L1d
            r4 = r11[r3]
            if (r4 == 0) goto L1a
            goto L1e
        L1a:
            int r3 = r3 + 1
            goto L13
        L1d:
            r1 = r2
        L1e:
            if (r1 != 0) goto L43
            boolean r0 = android.text.TextUtils.isEmpty(r13)
            if (r0 != 0) goto L43
            boolean r0 = android.text.TextUtils.isEmpty(r14)
            if (r0 != 0) goto L43
            boolean r0 = android.text.TextUtils.isEmpty(r12)
            if (r0 != 0) goto L43
            boolean r2 = permissionIsRefuseAndHide(r9, r10, r11)
            boolean r3 = permissionIsRefuseOnce(r8, r9, r10, r11)
            r7 = 1
            r1 = r9
            r4 = r12
            r5 = r13
            r6 = r14
            showDialog(r1, r2, r3, r4, r5, r6, r7)
            return
        L43:
            r9.finish()
        L46:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.ttgame.framework.module.util.PermissionMediator.dispatchPermissionResultNew(java.lang.String, android.app.Activity, java.lang.String[], int[], java.lang.String, java.lang.String, java.lang.String):void");
    }

    public static boolean permissionIsRefuseOnce(String str, Activity activity, String[] strArr, int[] iArr) {
        Log.d(TAG, "-----permissionIsRefuseOnce: handle permission refuse logic");
        if (strArr.length > 0) {
            if (mPermissionCountMap.containsKey(str)) {
                Map<String, Integer> map = mPermissionCountMap.get(str);
                if (map == null) {
                    map = new HashMap<>();
                }
                if (!map.containsKey(strArr[0])) {
                    map.put(strArr[0], 0);
                }
                map.put(strArr[0], Integer.valueOf(map.get(strArr[0]).intValue() + 1));
                mPermissionCountMap.put(str, map);
                return map.get(strArr[0]).intValue() < 2;
            }
            HashMap hashMap = new HashMap();
            hashMap.put(strArr[0], 1);
            mPermissionCountMap.put(str, hashMap);
            return true;
        }
        return false;
    }

    public static void dispatchPermissionResult(Activity activity, int i2, String[] strArr, int[] iArr) {
        dispatchPermissionResult(activity, i2, strArr, iArr, (String) null);
    }

    public static void dispatchPermissionResult(Activity activity, int i2, String[] strArr, int[] iArr, String str) {
        boolean z;
        if (strArr.length == 0 || iArr.length == 0) {
            return;
        }
        boolean z2 = false;
        for (int i3 = 0; i3 < strArr.length; i3++) {
            if (iArr[i3] != 0 && !ActivityCompat.shouldShowRequestPermissionRationale(activity, strArr[i3])) {
                showGrantToast(activity.getApplicationContext(), strArr[i3]);
            }
        }
        OnPermissionRequestListener onPermissionRequestListener = mListenerMap.get(Integer.valueOf(i2));
        if (onPermissionRequestListener != null) {
            if (strArr.length == 1) {
                z = iArr[0] == 0;
                onPermissionRequestListener.onPermissionRequest(z, strArr[0]);
            } else {
                int length = iArr.length;
                int i4 = 0;
                while (true) {
                    if (i4 >= length) {
                        z2 = true;
                        break;
                    } else if (iArr[i4] != 0) {
                        onPermissionRequestListener.onPermissionRequest(false, strArr, iArr);
                        break;
                    } else {
                        i4++;
                    }
                }
                if (z2) {
                    onPermissionRequestListener.onPermissionRequest(true, strArr, iArr);
                }
                z = z2;
            }
            if (!z && !TextUtils.isEmpty(str)) {
                showDialog(activity, permissionIsRefuseAndHide(activity, strArr, iArr), str);
            }
            mListenerMap.remove(Integer.valueOf(i2));
        }
    }

    public static void dispatchPermissionResult(Activity activity, int i2, String[] strArr, int[] iArr, String str, String str2) {
        OnPermissionRequestListener onPermissionRequestListener;
        boolean z;
        boolean z2;
        if (strArr.length == 0 || iArr.length == 0 || (onPermissionRequestListener = mListenerMap.get(Integer.valueOf(i2))) == null) {
            return;
        }
        if (strArr.length == 1) {
            z2 = iArr[0] == 0;
            onPermissionRequestListener.onPermissionRequest(z2, strArr[0]);
        } else {
            int length = iArr.length;
            int i3 = 0;
            while (true) {
                if (i3 >= length) {
                    z = true;
                    break;
                } else if (iArr[i3] != 0) {
                    onPermissionRequestListener.onPermissionRequest(false, strArr, iArr);
                    z = false;
                    break;
                } else {
                    i3++;
                }
            }
            if (z) {
                onPermissionRequestListener.onPermissionRequest(true, strArr, iArr);
            }
            z2 = z;
        }
        if (!z2 && !TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            showDialog(activity, permissionIsRefuseAndHide(activity, strArr, iArr), str, str2, false);
        }
        mListenerMap.remove(Integer.valueOf(i2));
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x000e, code lost:
        if (r7[0] == 0) goto L19;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void dispatchPermissionResult(android.app.Activity r5, java.lang.String[] r6, int[] r7, java.lang.String r8, java.lang.String r9) {
        /*
            int r0 = r6.length
            if (r0 == 0) goto L37
            int r0 = r7.length
            if (r0 != 0) goto L7
            goto L37
        L7:
            int r0 = r6.length
            r1 = 0
            r2 = 1
            if (r0 != r2) goto L11
            r0 = r7[r1]
            if (r0 != 0) goto L1e
            goto L1d
        L11:
            int r0 = r7.length
            r3 = r1
        L13:
            if (r3 >= r0) goto L1d
            r4 = r7[r3]
            if (r4 == 0) goto L1a
            goto L1e
        L1a:
            int r3 = r3 + 1
            goto L13
        L1d:
            r1 = r2
        L1e:
            if (r1 != 0) goto L34
            boolean r0 = android.text.TextUtils.isEmpty(r8)
            if (r0 != 0) goto L34
            boolean r0 = android.text.TextUtils.isEmpty(r9)
            if (r0 != 0) goto L34
            boolean r6 = permissionIsRefuseAndHide(r5, r6, r7)
            showDialog(r5, r6, r8, r9, r2)
            return
        L34:
            r5.finish()
        L37:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.ttgame.framework.module.util.PermissionMediator.dispatchPermissionResult(android.app.Activity, java.lang.String[], int[], java.lang.String, java.lang.String):void");
    }

    public static void checkPermission(Activity activity, String str, OnPermissionRequestListener onPermissionRequestListener) {
        checkPermission(activity, new String[]{str}, onPermissionRequestListener);
    }

    public static void checkPermission(Activity activity, String[] strArr, OnPermissionRequestListener onPermissionRequestListener) {
        if (activity == null || strArr == null || strArr.length <= 0) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (String str : strArr) {
            if (!TextUtils.isEmpty(str)) {
                try {
                    if (ContextCompat.checkSelfPermission(activity, str) != 0) {
                        arrayList.add(str);
                    }
                } catch (Exception unused) {
                    arrayList.add(str);
                }
            }
        }
        if (arrayList.isEmpty()) {
            if (onPermissionRequestListener != null) {
                if (strArr.length == 1) {
                    onPermissionRequestListener.onPermissionRequest(true, strArr[0]);
                    return;
                } else {
                    onPermissionRequestListener.onPermissionRequest(true, strArr, null);
                    return;
                }
            }
            return;
        }
        int i2 = sIncRequestCode + 1;
        sIncRequestCode = i2;
        if (i2 > 255) {
            sIncRequestCode = 0;
        }
        mListenerMap.put(Integer.valueOf(sIncRequestCode), onPermissionRequestListener);
        ActivityCompat.requestPermissions(activity, (String[]) arrayList.toArray(new String[arrayList.size()]), sIncRequestCode);
    }

    public static boolean permissionIsRefuseAndHide(Activity activity, String str) {
        return (ContextCompat.checkSelfPermission(activity, str) == 0 || ActivityCompat.shouldShowRequestPermissionRationale(activity, str)) ? false : true;
    }

    public static boolean permissionIsRefuseAndHide(Activity activity, String[] strArr, int[] iArr) {
        for (int i2 = 0; i2 < strArr.length; i2++) {
            if (iArr[i2] != 0 && !ActivityCompat.shouldShowRequestPermissionRationale(activity, strArr[i2])) {
                return true;
            }
        }
        return false;
    }

    public static void permissionsIsRefusedPermanent(Activity activity, String[] strArr, int[] iArr, boolean[] zArr) {
        for (int i2 = 0; i2 < strArr.length; i2++) {
            if (iArr[i2] != 0) {
                zArr[i2] = !ActivityCompat.shouldShowRequestPermissionRationale(activity, strArr[i2]);
            }
        }
    }

    public static boolean hasPermissionRefusedPermanent(boolean[] zArr) {
        for (boolean z : zArr) {
            if (z) {
                return true;
            }
        }
        return false;
    }

    public static void showGrantToast(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if ("android.permission.CAMERA".equals(str)) {
            Toast.makeText(context, context.getString(R.string.gsdk_webview_upload_permission_video), 0).show();
        } else if ("android.permission.WRITE_EXTERNAL_STORAGE".equals(str) || "android.permission.READ_EXTERNAL_STORAGE".equals(str)) {
            Toast.makeText(context, context.getString(R.string.gsdk_webview_upload_permission_file), 0).show();
        }
    }

    public static void showDialog(final Activity activity, boolean z, String str) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage(str);
        builder.setCancelable(true);
        if (z) {
            builder.setPositiveButton(R.string.gsdk_webview_settings, new DialogInterface.OnClickListener() { // from class: com.bytedance.ttgame.framework.module.util.PermissionMediator.1
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i2) {
                    Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
                    intent.setData(Uri.fromParts("package", activity.getPackageName(), null));
                    activity.startActivity(intent);
                }
            });
            builder.setNegativeButton(R.string.gsdk_webview_cancel, new DialogInterface.OnClickListener() { // from class: com.bytedance.ttgame.framework.module.util.PermissionMediator.2
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i2) {
                    dialogInterface.dismiss();
                }
            });
        } else {
            builder.setPositiveButton(R.string.gsdk_webview_confirm, new DialogInterface.OnClickListener() { // from class: com.bytedance.ttgame.framework.module.util.PermissionMediator.3
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i2) {
                    dialogInterface.dismiss();
                }
            });
        }
        builder.create().show();
    }

    public static void showDialog(final Activity activity, boolean z, String str, String str2, final boolean z2) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        if (z) {
            builder.setMessage(str2);
        } else {
            builder.setMessage(str);
        }
        builder.setCancelable(true);
        if (z) {
            builder.setPositiveButton(activity.getApplicationContext().getString(R.string.gsdk_webview_settings), new DialogInterface.OnClickListener() { // from class: com.bytedance.ttgame.framework.module.util.PermissionMediator.4
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i2) {
                    Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
                    intent.setData(Uri.fromParts("package", activity.getPackageName(), null));
                    activity.startActivity(intent);
                    if (z2) {
                        activity.finish();
                    }
                }
            });
            builder.setNegativeButton(activity.getApplicationContext().getString(R.string.gsdk_webview_cancel), new DialogInterface.OnClickListener() { // from class: com.bytedance.ttgame.framework.module.util.PermissionMediator.5
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i2) {
                    dialogInterface.dismiss();
                    if (z2) {
                        activity.finish();
                    }
                }
            });
        } else {
            builder.setPositiveButton(activity.getApplicationContext().getString(R.string.gsdk_webview_confirm), new DialogInterface.OnClickListener() { // from class: com.bytedance.ttgame.framework.module.util.PermissionMediator.6
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i2) {
                    dialogInterface.dismiss();
                    if (z2) {
                        activity.finish();
                    }
                }
            });
        }
        AlertDialog create = builder.create();
        create.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.bytedance.ttgame.framework.module.util.PermissionMediator.7
            @Override // android.content.DialogInterface.OnCancelListener
            public void onCancel(DialogInterface dialogInterface) {
                if (z2) {
                    activity.finish();
                }
            }
        });
        create.show();
    }

    public static void showDialog(final Activity activity, boolean z, boolean z2, String str, String str2, String str3, final boolean z3) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        if (z2) {
            builder.setMessage(str);
        } else if (z) {
            builder.setMessage(str3);
        } else {
            builder.setMessage(str2);
        }
        builder.setCancelable(true);
        if (z) {
            builder.setPositiveButton(activity.getApplicationContext().getString(R.string.gsdk_webview_settings), new DialogInterface.OnClickListener() { // from class: com.bytedance.ttgame.framework.module.util.PermissionMediator.8
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i2) {
                    Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
                    intent.setData(Uri.fromParts("package", activity.getPackageName(), null));
                    activity.startActivity(intent);
                    if (z3) {
                        activity.finish();
                    }
                }
            });
            builder.setNegativeButton(activity.getApplicationContext().getString(R.string.gsdk_webview_cancel), new DialogInterface.OnClickListener() { // from class: com.bytedance.ttgame.framework.module.util.PermissionMediator.9
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i2) {
                    dialogInterface.dismiss();
                    if (z3) {
                        activity.finish();
                    }
                }
            });
        } else {
            builder.setPositiveButton(activity.getApplicationContext().getString(R.string.gsdk_webview_confirm), new DialogInterface.OnClickListener() { // from class: com.bytedance.ttgame.framework.module.util.PermissionMediator.10
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i2) {
                    dialogInterface.dismiss();
                    if (z3) {
                        activity.finish();
                    }
                }
            });
        }
        AlertDialog create = builder.create();
        create.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.bytedance.ttgame.framework.module.util.PermissionMediator.11
            @Override // android.content.DialogInterface.OnCancelListener
            public void onCancel(DialogInterface dialogInterface) {
                if (z3) {
                    activity.finish();
                }
            }
        });
        create.show();
    }
}
