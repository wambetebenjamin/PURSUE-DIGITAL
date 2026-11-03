package pub.devrel.easypermissions;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import pub.devrel.easypermissions.PermissionRequest;
import pub.devrel.easypermissions.helper.PermissionHelper;
/* loaded from: classes10.dex */
public class EasyPermissions {
    private static final String TAG = "EasyPermissions";

    /* loaded from: classes10.dex */
    public interface PermissionCallbacks extends ActivityCompat.OnRequestPermissionsResultCallback {
        void onPermissionsDenied(int i2, List<String> list);

        void onPermissionsGranted(int i2, List<String> list);
    }

    /* loaded from: classes10.dex */
    public interface RationaleCallbacks {
        void onRationaleAccepted(int i2);

        void onRationaleDenied(int i2);
    }

    public static boolean hasPermissions(Context context, String... strArr) {
        if (Build.VERSION.SDK_INT < 23) {
            Log.w(TAG, "hasPermissions: API version < M, returning true by default");
            return true;
        } else if (context == null) {
            throw new IllegalArgumentException("Can't check permissions for null context");
        } else {
            for (String str : strArr) {
                if (ContextCompat.checkSelfPermission(context, str) != 0) {
                    return false;
                }
            }
            return true;
        }
    }

    public static void requestPermissions(Activity activity, String str, int i2, String... strArr) {
        requestPermissions(new PermissionRequest.Builder(activity, i2, strArr).setRationale(str).build());
    }

    public static void requestPermissions(Fragment fragment, String str, int i2, String... strArr) {
        requestPermissions(new PermissionRequest.Builder(fragment, i2, strArr).setRationale(str).build());
    }

    public static void requestPermissions(PermissionRequest permissionRequest) {
        if (hasPermissions(permissionRequest.getHelper().getContext(), permissionRequest.getPerms())) {
            notifyAlreadyHasPermissions(permissionRequest.getHelper().getHost(), permissionRequest.getRequestCode(), permissionRequest.getPerms());
        } else {
            permissionRequest.getHelper().requestPermissions(permissionRequest.getRationale(), permissionRequest.getPositiveButtonText(), permissionRequest.getNegativeButtonText(), permissionRequest.getTheme(), permissionRequest.getRequestCode(), permissionRequest.getPerms());
        }
    }

    public static void onRequestPermissionsResult(int i2, String[] strArr, int[] iArr, Object... objArr) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (int i3 = 0; i3 < strArr.length; i3++) {
            String str = strArr[i3];
            if (iArr[i3] == 0) {
                arrayList.add(str);
            } else {
                arrayList2.add(str);
            }
        }
        for (Object obj : objArr) {
            if (!arrayList.isEmpty() && (obj instanceof PermissionCallbacks)) {
                ((PermissionCallbacks) obj).onPermissionsGranted(i2, arrayList);
            }
            if (!arrayList2.isEmpty() && (obj instanceof PermissionCallbacks)) {
                ((PermissionCallbacks) obj).onPermissionsDenied(i2, arrayList2);
            }
            if (!arrayList.isEmpty() && arrayList2.isEmpty()) {
                runAnnotatedMethods(obj, i2);
            }
        }
    }

    public static boolean somePermissionPermanentlyDenied(Activity activity, List<String> list) {
        return PermissionHelper.newInstance(activity).somePermissionPermanentlyDenied(list);
    }

    public static boolean somePermissionPermanentlyDenied(Fragment fragment, List<String> list) {
        return PermissionHelper.newInstance(fragment).somePermissionPermanentlyDenied(list);
    }

    public static boolean permissionPermanentlyDenied(Activity activity, String str) {
        return PermissionHelper.newInstance(activity).permissionPermanentlyDenied(str);
    }

    public static boolean permissionPermanentlyDenied(Fragment fragment, String str) {
        return PermissionHelper.newInstance(fragment).permissionPermanentlyDenied(str);
    }

    public static boolean somePermissionDenied(Activity activity, String... strArr) {
        return PermissionHelper.newInstance(activity).somePermissionDenied(strArr);
    }

    public static boolean somePermissionDenied(Fragment fragment, String... strArr) {
        return PermissionHelper.newInstance(fragment).somePermissionDenied(strArr);
    }

    private static void notifyAlreadyHasPermissions(Object obj, int i2, String[] strArr) {
        int[] iArr = new int[strArr.length];
        for (int i3 = 0; i3 < strArr.length; i3++) {
            iArr[i3] = 0;
        }
        onRequestPermissionsResult(i2, strArr, iArr, obj);
    }

    private static void runAnnotatedMethods(Object obj, int i2) {
        Method[] declaredMethods;
        Class<?> cls = obj.getClass();
        if (isUsingAndroidAnnotations(obj)) {
            cls = cls.getSuperclass();
        }
        while (cls != null) {
            for (Method method : cls.getDeclaredMethods()) {
                AfterPermissionGranted afterPermissionGranted = (AfterPermissionGranted) method.getAnnotation(AfterPermissionGranted.class);
                if (afterPermissionGranted != null && afterPermissionGranted.value() == i2) {
                    if (method.getParameterTypes().length > 0) {
                        throw new RuntimeException("Cannot execute method " + method.getName() + " because it is non-void method and/or has input parameters.");
                    }
                    try {
                        if (!method.isAccessible()) {
                            method.setAccessible(true);
                        }
                        method.invoke(obj, new Object[0]);
                    } catch (IllegalAccessException e2) {
                        Log.e(TAG, "runDefaultMethod:IllegalAccessException", e2);
                    } catch (InvocationTargetException e3) {
                        Log.e(TAG, "runDefaultMethod:InvocationTargetException", e3);
                    }
                }
            }
            cls = cls.getSuperclass();
        }
    }

    private static boolean isUsingAndroidAnnotations(Object obj) {
        if (obj.getClass().getSimpleName().endsWith("_")) {
            try {
                return Class.forName("org.androidannotations.api.view.HasViews").isInstance(obj);
            } catch (ClassNotFoundException unused) {
                return false;
            }
        }
        return false;
    }
}
