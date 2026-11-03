package androidx.legacy.app;

import android.app.Activity;
import android.app.Fragment;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import java.util.Arrays;
@Deprecated
/* loaded from: classes.dex */
public class FragmentCompat {
    static final FragmentCompatImpl IMPL;
    private static PermissionCompatDelegate sDelegate;

    /* loaded from: classes.dex */
    interface FragmentCompatImpl {
        void requestPermissions(Fragment fragment, String[] strArr, int i2);

        void setUserVisibleHint(Fragment fragment, boolean z);

        boolean shouldShowRequestPermissionRationale(Fragment fragment, String str);
    }

    @Deprecated
    /* loaded from: classes.dex */
    public interface OnRequestPermissionsResultCallback {
        @Deprecated
        void onRequestPermissionsResult(int i2, String[] strArr, int[] iArr);
    }

    @Deprecated
    /* loaded from: classes.dex */
    public interface PermissionCompatDelegate {
        @Deprecated
        boolean requestPermissions(Fragment fragment, String[] strArr, int i2);
    }

    /* loaded from: classes.dex */
    static class FragmentCompatBaseImpl implements FragmentCompatImpl {
        @Override // androidx.legacy.app.FragmentCompat.FragmentCompatImpl
        public void setUserVisibleHint(Fragment fragment, boolean z) {
        }

        @Override // androidx.legacy.app.FragmentCompat.FragmentCompatImpl
        public boolean shouldShowRequestPermissionRationale(Fragment fragment, String str) {
            return false;
        }

        FragmentCompatBaseImpl() {
        }

        @Override // androidx.legacy.app.FragmentCompat.FragmentCompatImpl
        public void requestPermissions(final Fragment fragment, final String[] strArr, final int i2) {
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: androidx.legacy.app.FragmentCompat.FragmentCompatBaseImpl.1
                @Override // java.lang.Runnable
                public void run() {
                    int[] iArr = new int[strArr.length];
                    Activity activity = fragment.getActivity();
                    if (activity != null) {
                        PackageManager packageManager = activity.getPackageManager();
                        String packageName = activity.getPackageName();
                        int length = strArr.length;
                        for (int i3 = 0; i3 < length; i3++) {
                            iArr[i3] = packageManager.checkPermission(strArr[i3], packageName);
                        }
                    } else {
                        Arrays.fill(iArr, -1);
                    }
                    ((OnRequestPermissionsResultCallback) fragment).onRequestPermissionsResult(i2, strArr, iArr);
                }
            });
        }
    }

    /* loaded from: classes.dex */
    static class FragmentCompatApi15Impl extends FragmentCompatBaseImpl {
        FragmentCompatApi15Impl() {
        }

        @Override // androidx.legacy.app.FragmentCompat.FragmentCompatBaseImpl, androidx.legacy.app.FragmentCompat.FragmentCompatImpl
        public void setUserVisibleHint(Fragment fragment, boolean z) {
            fragment.setUserVisibleHint(z);
        }
    }

    /* loaded from: classes.dex */
    static class FragmentCompatApi23Impl extends FragmentCompatApi15Impl {
        FragmentCompatApi23Impl() {
        }

        @Override // androidx.legacy.app.FragmentCompat.FragmentCompatBaseImpl, androidx.legacy.app.FragmentCompat.FragmentCompatImpl
        public void requestPermissions(Fragment fragment, String[] strArr, int i2) {
            fragment.requestPermissions(strArr, i2);
        }

        @Override // androidx.legacy.app.FragmentCompat.FragmentCompatBaseImpl, androidx.legacy.app.FragmentCompat.FragmentCompatImpl
        public boolean shouldShowRequestPermissionRationale(Fragment fragment, String str) {
            return fragment.shouldShowRequestPermissionRationale(str);
        }
    }

    /* loaded from: classes.dex */
    static class FragmentCompatApi24Impl extends FragmentCompatApi23Impl {
        FragmentCompatApi24Impl() {
        }

        @Override // androidx.legacy.app.FragmentCompat.FragmentCompatApi15Impl, androidx.legacy.app.FragmentCompat.FragmentCompatBaseImpl, androidx.legacy.app.FragmentCompat.FragmentCompatImpl
        public void setUserVisibleHint(Fragment fragment, boolean z) {
            fragment.setUserVisibleHint(z);
        }
    }

    static {
        if (Build.VERSION.SDK_INT >= 24) {
            IMPL = new FragmentCompatApi24Impl();
        } else if (Build.VERSION.SDK_INT >= 23) {
            IMPL = new FragmentCompatApi23Impl();
        } else if (Build.VERSION.SDK_INT >= 15) {
            IMPL = new FragmentCompatApi15Impl();
        } else {
            IMPL = new FragmentCompatBaseImpl();
        }
    }

    @Deprecated
    public static void setPermissionCompatDelegate(PermissionCompatDelegate permissionCompatDelegate) {
        sDelegate = permissionCompatDelegate;
    }

    @Deprecated
    public static PermissionCompatDelegate getPermissionCompatDelegate() {
        return sDelegate;
    }

    @Deprecated
    public static void setMenuVisibility(Fragment fragment, boolean z) {
        fragment.setMenuVisibility(z);
    }

    @Deprecated
    public static void setUserVisibleHint(Fragment fragment, boolean z) {
        IMPL.setUserVisibleHint(fragment, z);
    }

    @Deprecated
    public static void requestPermissions(Fragment fragment, String[] strArr, int i2) {
        PermissionCompatDelegate permissionCompatDelegate = sDelegate;
        if (permissionCompatDelegate == null || !permissionCompatDelegate.requestPermissions(fragment, strArr, i2)) {
            IMPL.requestPermissions(fragment, strArr, i2);
        }
    }

    @Deprecated
    public static boolean shouldShowRequestPermissionRationale(Fragment fragment, String str) {
        return IMPL.shouldShowRequestPermissionRationale(fragment, str);
    }
}
