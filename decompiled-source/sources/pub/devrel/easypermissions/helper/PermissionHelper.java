package pub.devrel.easypermissions.helper;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import java.util.List;
/* loaded from: classes10.dex */
public abstract class PermissionHelper<T> {
    private T mHost;

    public abstract void directRequestPermissions(int i2, String... strArr);

    public abstract Context getContext();

    public abstract boolean shouldShowRequestPermissionRationale(String str);

    public abstract void showRequestPermissionRationale(String str, String str2, String str3, int i2, int i3, String... strArr);

    public static PermissionHelper<? extends Activity> newInstance(Activity activity) {
        if (Build.VERSION.SDK_INT < 23) {
            return new LowApiPermissionsHelper(activity);
        }
        if (activity instanceof AppCompatActivity) {
            return new AppCompatActivityPermissionsHelper((AppCompatActivity) activity);
        }
        return new ActivityPermissionHelper(activity);
    }

    public static PermissionHelper<Fragment> newInstance(Fragment fragment) {
        if (Build.VERSION.SDK_INT < 23) {
            return new LowApiPermissionsHelper(fragment);
        }
        return new SupportFragmentPermissionHelper(fragment);
    }

    public PermissionHelper(T t) {
        this.mHost = t;
    }

    private boolean shouldShowRationale(String... strArr) {
        for (String str : strArr) {
            if (shouldShowRequestPermissionRationale(str)) {
                return true;
            }
        }
        return false;
    }

    public void requestPermissions(String str, String str2, String str3, int i2, int i3, String... strArr) {
        if (shouldShowRationale(strArr)) {
            showRequestPermissionRationale(str, str2, str3, i2, i3, strArr);
        } else {
            directRequestPermissions(i3, strArr);
        }
    }

    public boolean somePermissionPermanentlyDenied(List<String> list) {
        for (String str : list) {
            if (permissionPermanentlyDenied(str)) {
                return true;
            }
        }
        return false;
    }

    public boolean permissionPermanentlyDenied(String str) {
        return !shouldShowRequestPermissionRationale(str);
    }

    public boolean somePermissionDenied(String... strArr) {
        return shouldShowRationale(strArr);
    }

    public T getHost() {
        return this.mHost;
    }
}
