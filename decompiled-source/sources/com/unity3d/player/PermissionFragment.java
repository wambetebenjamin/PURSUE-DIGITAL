package com.unity3d.player;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import com.unity3d.player.UnityPermissions;
/* loaded from: classes10.dex */
public class PermissionFragment extends Fragment {
    public static final String PERMISSION_NAMES = "PermissionNames";
    public static final int UNITY_PERMISSION_REQUEST_CODE = 96489;
    private final Activity m_Activity;
    private final Looper m_Looper;
    private final IPermissionRequestCallbacks m_ResultCallbacks;

    public PermissionFragment() {
        this.m_ResultCallbacks = null;
        this.m_Activity = null;
        this.m_Looper = null;
    }

    public PermissionFragment(Activity activity, IPermissionRequestCallbacks iPermissionRequestCallbacks) {
        this.m_ResultCallbacks = iPermissionRequestCallbacks;
        this.m_Activity = activity;
        this.m_Looper = Looper.myLooper();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reportAllDenied(String[] strArr) {
        for (String str : strArr) {
            this.m_ResultCallbacks.onPermissionDenied(str);
        }
    }

    @Override // android.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestPermissions(getArguments().getStringArray(PERMISSION_NAMES), UNITY_PERMISSION_REQUEST_CODE);
    }

    @Override // android.app.Fragment
    public void onRequestPermissionsResult(int i2, String[] strArr, int[] iArr) {
        if (i2 != 96489) {
            return;
        }
        if (strArr.length != 0) {
            for (int i3 = 0; i3 < strArr.length && i3 < iArr.length; i3++) {
                int i4 = iArr[i3];
                IPermissionRequestCallbacks iPermissionRequestCallbacks = this.m_ResultCallbacks;
                if (iPermissionRequestCallbacks != null && this.m_Activity != null && this.m_Looper != null) {
                    if (iPermissionRequestCallbacks instanceof UnityPermissions.ModalWaitForPermissionResponse) {
                        iPermissionRequestCallbacks.onPermissionGranted(strArr[i3]);
                    } else {
                        String str = strArr[i3];
                        if (str == null) {
                            str = "<null>";
                        }
                        new Handler(this.m_Looper).post(new I(this.m_ResultCallbacks, str, iArr[i3], this.m_Activity.shouldShowRequestPermissionRationale(str)));
                    }
                }
            }
        } else if (this.m_ResultCallbacks != null && this.m_Activity != null && this.m_Looper != null) {
            String[] stringArray = getArguments().getStringArray(PERMISSION_NAMES);
            if (this.m_ResultCallbacks instanceof UnityPermissions.ModalWaitForPermissionResponse) {
                reportAllDenied(stringArray);
            } else {
                new Handler(this.m_Looper).post(new H(this, stringArray));
            }
        }
        FragmentTransaction beginTransaction = getActivity().getFragmentManager().beginTransaction();
        beginTransaction.remove(this);
        beginTransaction.commit();
    }
}
