package pub.devrel.easypermissions;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import pub.devrel.easypermissions.EasyPermissions;
/* loaded from: classes10.dex */
public class RationaleDialogFragment extends DialogFragment {
    public static final String TAG = "RationaleDialogFragment";
    private EasyPermissions.PermissionCallbacks mPermissionCallbacks;
    private EasyPermissions.RationaleCallbacks mRationaleCallbacks;
    private boolean mStateSaved = false;

    public static RationaleDialogFragment newInstance(String str, String str2, String str3, int i2, int i3, String[] strArr) {
        RationaleDialogFragment rationaleDialogFragment = new RationaleDialogFragment();
        rationaleDialogFragment.setArguments(new RationaleDialogConfig(str, str2, str3, i2, i3, strArr).toBundle());
        return rationaleDialogFragment;
    }

    @Override // android.app.DialogFragment, android.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        if (Build.VERSION.SDK_INT >= 17 && getParentFragment() != null) {
            if (getParentFragment() instanceof EasyPermissions.PermissionCallbacks) {
                this.mPermissionCallbacks = (EasyPermissions.PermissionCallbacks) getParentFragment();
            }
            if (getParentFragment() instanceof EasyPermissions.RationaleCallbacks) {
                this.mRationaleCallbacks = (EasyPermissions.RationaleCallbacks) getParentFragment();
            }
        }
        if (context instanceof EasyPermissions.PermissionCallbacks) {
            this.mPermissionCallbacks = (EasyPermissions.PermissionCallbacks) context;
        }
        if (context instanceof EasyPermissions.RationaleCallbacks) {
            this.mRationaleCallbacks = (EasyPermissions.RationaleCallbacks) context;
        }
    }

    @Override // android.app.DialogFragment, android.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        this.mStateSaved = true;
        super.onSaveInstanceState(bundle);
    }

    public void showAllowingStateLoss(FragmentManager fragmentManager, String str) {
        if ((Build.VERSION.SDK_INT < 26 || !fragmentManager.isStateSaved()) && !this.mStateSaved) {
            show(fragmentManager, str);
        }
    }

    @Override // android.app.DialogFragment, android.app.Fragment
    public void onDetach() {
        super.onDetach();
        this.mPermissionCallbacks = null;
    }

    @Override // android.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        setCancelable(false);
        RationaleDialogConfig rationaleDialogConfig = new RationaleDialogConfig(getArguments());
        return rationaleDialogConfig.createFrameworkDialog(getActivity(), new RationaleDialogClickListener(this, rationaleDialogConfig, this.mPermissionCallbacks, this.mRationaleCallbacks));
    }
}
