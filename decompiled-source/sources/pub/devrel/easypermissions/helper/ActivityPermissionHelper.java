package pub.devrel.easypermissions.helper;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.util.Log;
import androidx.core.app.ActivityCompat;
import pub.devrel.easypermissions.RationaleDialogFragment;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes10.dex */
public class ActivityPermissionHelper extends PermissionHelper<Activity> {
    private static final String TAG = "ActPermissionHelper";

    public ActivityPermissionHelper(Activity activity) {
        super(activity);
    }

    @Override // pub.devrel.easypermissions.helper.PermissionHelper
    public void directRequestPermissions(int i2, String... strArr) {
        ActivityCompat.requestPermissions(getHost(), strArr, i2);
    }

    @Override // pub.devrel.easypermissions.helper.PermissionHelper
    public boolean shouldShowRequestPermissionRationale(String str) {
        return ActivityCompat.shouldShowRequestPermissionRationale(getHost(), str);
    }

    @Override // pub.devrel.easypermissions.helper.PermissionHelper
    public Context getContext() {
        return getHost();
    }

    @Override // pub.devrel.easypermissions.helper.PermissionHelper
    public void showRequestPermissionRationale(String str, String str2, String str3, int i2, int i3, String... strArr) {
        FragmentManager fragmentManager = getHost().getFragmentManager();
        if (fragmentManager.findFragmentByTag(RationaleDialogFragment.TAG) instanceof RationaleDialogFragment) {
            Log.d(TAG, "Found existing fragment, not showing rationale.");
        } else {
            RationaleDialogFragment.newInstance(str2, str3, str, i2, i3, strArr).showAllowingStateLoss(fragmentManager, RationaleDialogFragment.TAG);
        }
    }
}
