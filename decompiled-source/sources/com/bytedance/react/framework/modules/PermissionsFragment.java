package com.bytedance.react.framework.modules;

import android.app.Fragment;
import android.os.Build;
import com.facebook.react.bridge.Callback;
import com.facebook.react.modules.core.PermissionAwareActivity;
import com.facebook.react.modules.core.PermissionListener;
import javax.annotation.Nullable;
/* loaded from: classes2.dex */
public class PermissionsFragment extends Fragment implements PermissionAwareActivity {
    @Nullable
    private PermissionListener mPermissionListener;
    @Nullable
    private Callback mPermissionsCallback;

    @Override // android.app.Fragment
    public void onResume() {
        super.onResume();
        Callback callback = this.mPermissionsCallback;
        if (callback != null) {
            callback.invoke(new Object[0]);
            this.mPermissionsCallback = null;
        }
    }

    @Override // com.facebook.react.modules.core.PermissionAwareActivity
    public int checkPermission(String str, int i2, int i3) {
        if (getActivity() != null) {
            return getActivity().checkPermission(str, i2, i3);
        }
        return 0;
    }

    @Override // com.facebook.react.modules.core.PermissionAwareActivity
    public int checkSelfPermission(String str) {
        if (getActivity() == null || Build.VERSION.SDK_INT < 23) {
            return 0;
        }
        return getActivity().checkSelfPermission(str);
    }

    @Override // com.facebook.react.modules.core.PermissionAwareActivity
    public void requestPermissions(String[] strArr, int i2, PermissionListener permissionListener) {
        this.mPermissionListener = permissionListener;
        if (Build.VERSION.SDK_INT >= 23) {
            requestPermissions(strArr, i2);
        }
    }

    @Override // android.app.Fragment
    public void onRequestPermissionsResult(final int i2, final String[] strArr, final int[] iArr) {
        super.onRequestPermissionsResult(i2, strArr, iArr);
        this.mPermissionsCallback = new Callback() { // from class: com.bytedance.react.framework.modules.PermissionsFragment.1
            @Override // com.facebook.react.bridge.Callback
            public void invoke(Object... objArr) {
                if (PermissionsFragment.this.mPermissionListener == null || !PermissionsFragment.this.mPermissionListener.onRequestPermissionsResult(i2, strArr, iArr)) {
                    return;
                }
                PermissionsFragment.this.mPermissionListener = null;
            }
        };
    }
}
