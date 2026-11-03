package pub.devrel.easypermissions;

import android.app.Activity;
import android.content.DialogInterface;
import androidx.fragment.app.Fragment;
import java.util.Arrays;
import pub.devrel.easypermissions.EasyPermissions;
import pub.devrel.easypermissions.helper.PermissionHelper;
/* loaded from: classes10.dex */
class RationaleDialogClickListener implements DialogInterface.OnClickListener {
    private EasyPermissions.PermissionCallbacks mCallbacks;
    private RationaleDialogConfig mConfig;
    private Object mHost;
    private EasyPermissions.RationaleCallbacks mRationaleCallbacks;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RationaleDialogClickListener(RationaleDialogFragmentCompat rationaleDialogFragmentCompat, RationaleDialogConfig rationaleDialogConfig, EasyPermissions.PermissionCallbacks permissionCallbacks, EasyPermissions.RationaleCallbacks rationaleCallbacks) {
        Object activity;
        if (rationaleDialogFragmentCompat.getParentFragment() != null) {
            activity = rationaleDialogFragmentCompat.getParentFragment();
        } else {
            activity = rationaleDialogFragmentCompat.getActivity();
        }
        this.mHost = activity;
        this.mConfig = rationaleDialogConfig;
        this.mCallbacks = permissionCallbacks;
        this.mRationaleCallbacks = rationaleCallbacks;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public RationaleDialogClickListener(RationaleDialogFragment rationaleDialogFragment, RationaleDialogConfig rationaleDialogConfig, EasyPermissions.PermissionCallbacks permissionCallbacks, EasyPermissions.RationaleCallbacks rationaleCallbacks) {
        this.mHost = rationaleDialogFragment.getActivity();
        this.mConfig = rationaleDialogConfig;
        this.mCallbacks = permissionCallbacks;
        this.mRationaleCallbacks = rationaleCallbacks;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i2) {
        int i3 = this.mConfig.requestCode;
        if (i2 == -1) {
            String[] strArr = this.mConfig.permissions;
            EasyPermissions.RationaleCallbacks rationaleCallbacks = this.mRationaleCallbacks;
            if (rationaleCallbacks != null) {
                rationaleCallbacks.onRationaleAccepted(i3);
            }
            Object obj = this.mHost;
            if (obj instanceof Fragment) {
                PermissionHelper.newInstance((Fragment) obj).directRequestPermissions(i3, strArr);
                return;
            } else if (obj instanceof Activity) {
                PermissionHelper.newInstance((Activity) obj).directRequestPermissions(i3, strArr);
                return;
            } else {
                throw new RuntimeException("Host must be an Activity or Fragment!");
            }
        }
        EasyPermissions.RationaleCallbacks rationaleCallbacks2 = this.mRationaleCallbacks;
        if (rationaleCallbacks2 != null) {
            rationaleCallbacks2.onRationaleDenied(i3);
        }
        notifyPermissionDenied();
    }

    private void notifyPermissionDenied() {
        EasyPermissions.PermissionCallbacks permissionCallbacks = this.mCallbacks;
        if (permissionCallbacks != null) {
            permissionCallbacks.onPermissionsDenied(this.mConfig.requestCode, Arrays.asList(this.mConfig.permissions));
        }
    }
}
