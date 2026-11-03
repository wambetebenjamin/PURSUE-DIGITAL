package com.facebook.react;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.bytedance.react.framework.RNConfig;
import com.bytedance.react.framework.RNUtil;
import com.bytedance.react.framework.core.BRNLayoutInit;
import com.bytedance.react.framework.core.BRNLogger;
import com.bytedance.react.framework.core.BRNManager;
import com.bytedance.react.framework.core.RNNotSupportException;
import com.facebook.react.bridge.Callback;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.facebook.react.modules.core.PermissionListener;
import java.lang.ref.WeakReference;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.Nullable;
/* loaded from: classes8.dex */
public abstract class BaseReactFragment extends Fragment implements DefaultHardwareBackBtnHandler {
    private static final String TAG = "BaseReactFragment";
    private BRNLayoutInit mBRNLayoutInit;
    private PermissionListener mPermissionListener;
    private Callback mPermissionsCallback;
    private ReactInstanceManager mReactInstanceManager;
    protected ReactRootView mReactRootView;
    protected String mComponentName = "";
    protected Bundle mComponentParams = null;
    protected String mComponentModule = "";
    protected String mCommonBundle = "";
    protected String mBundleName = "";
    protected Bundle mCommonInfo = new Bundle();
    protected int mMode = 1;
    private AtomicBoolean mAtomicBoolean = new AtomicBoolean(false);
    protected View mRootLayout = null;

    @Override // com.facebook.react.modules.core.DefaultHardwareBackBtnHandler
    public void invokeDefaultOnBackPressed() {
    }

    @Nullable
    protected String getMainComponentName() {
        return this.mComponentName;
    }

    public void setComponentName(String str) {
        this.mComponentName = str;
    }

    public void setComponentParams(Bundle bundle) {
        this.mComponentParams = bundle;
    }

    public void setComponentModule(String str) {
        this.mComponentModule = str;
    }

    public void setBundleName(String str) {
        this.mBundleName = str;
    }

    public void setCommonBundle(String str) {
        this.mCommonBundle = str;
    }

    public void setCommonBundle(Bundle bundle) {
        this.mCommonInfo = bundle;
    }

    public void setMode(int i2) {
        this.mMode = i2;
    }

    public String getBunleName() {
        if (TextUtils.isEmpty(this.mBundleName)) {
            this.mBundleName = this.mComponentModule;
        }
        return this.mBundleName;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(com.bytedance.react.framework.R.layout.activity_base_rn, (ViewGroup) null);
        this.mRootLayout = inflate;
        this.mReactRootView = (ReactRootView) inflate.findViewById(com.bytedance.react.framework.R.id.rrv_root);
        return this.mRootLayout;
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        restoreParamsFromBundle(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        if (getActivity() == null) {
            return;
        }
        FragmentActivity activity = getActivity();
        if (!RNUtil.isSupportRN()) {
            BRNManager.newInstance().handleError(new RNNotSupportException(), this.mComponentParams);
            if (activity instanceof BaseReactActivity) {
                activity.finish();
                return;
            }
            return;
        }
        BRNLayoutInit bRNLayoutInit = new BRNLayoutInit();
        this.mBRNLayoutInit = bRNLayoutInit;
        bRNLayoutInit.setRootView(this.mReactRootView);
        this.mBRNLayoutInit.setupLayout(getMainComponentName(), this.mComponentModule, getBunleName(), this.mCommonBundle, this.mCommonInfo, this.mMode, getActivity(), this.mComponentParams, getMainComponentName());
        this.mReactInstanceManager = this.mBRNLayoutInit.getReactInstanceManager();
    }

    private void addErrorView() {
        ReactRootView reactRootView = this.mReactRootView;
        if (reactRootView != null) {
            reactRootView.post(new Runnable() { // from class: com.facebook.react.BaseReactFragment.1
                @Override // java.lang.Runnable
                public void run() {
                    if (BaseReactFragment.this.mAtomicBoolean.get()) {
                        return;
                    }
                    ScriptLoadUtil.showLoadErrorClearPage(new WeakReference(BaseReactFragment.this.mReactRootView));
                }
            });
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewStateRestored(Bundle bundle) {
        super.onViewStateRestored(bundle);
        restoreParamsFromBundle(bundle);
    }

    private void restoreParamsFromBundle(Bundle bundle) {
        if (bundle == null) {
            return;
        }
        this.mComponentName = bundle.getString(RNConfig.RN_COMPONENT);
        this.mComponentParams = bundle.getBundle("rctModuleParams");
        this.mComponentModule = bundle.getString("rctModuleName");
        this.mBundleName = bundle.getString(RNConfig.BUNDLE_NAME);
    }

    @Override // androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        BRNLogger.i(TAG, "onSaveInstanceState");
        bundle.putString(RNConfig.RN_COMPONENT, this.mComponentName);
        bundle.putString(RNConfig.BUNDLE_NAME, this.mBundleName);
        bundle.putBundle("rctModuleParams", this.mComponentParams);
        bundle.putString("rctModuleName", this.mComponentModule);
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        ReactInstanceManager reactInstanceManager = this.mReactInstanceManager;
        if (reactInstanceManager != null) {
            reactInstanceManager.onHostPause(getActivity());
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        ReactInstanceManager reactInstanceManager = this.mReactInstanceManager;
        if (reactInstanceManager != null) {
            reactInstanceManager.onHostResume(getActivity());
        }
        Callback callback = this.mPermissionsCallback;
        if (callback != null) {
            callback.invoke(new Object[0]);
            this.mPermissionsCallback = null;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        ReactRootView reactRootView = this.mReactRootView;
        if (reactRootView != null) {
            reactRootView.unmountReactApplication();
            this.mReactRootView = null;
        }
        ReactInstanceManager reactInstanceManager = this.mReactInstanceManager;
        if (reactInstanceManager != null) {
            reactInstanceManager.onHostDestroy(getActivity());
            this.mReactInstanceManager.destroy();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i2, int i3, Intent intent) {
        ReactInstanceManager reactInstanceManager = this.mReactInstanceManager;
        if (reactInstanceManager != null) {
            reactInstanceManager.onActivityResult(getActivity(), i2, i3, intent);
        }
        BRNManager.newInstance().handleShareResultOnActivityResult(i2, i3, intent);
    }

    public void requestPermissions(String[] strArr, int i2, PermissionListener permissionListener) {
        this.mPermissionListener = permissionListener;
        if (Build.VERSION.SDK_INT >= 23) {
            requestPermissions(strArr, i2);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onRequestPermissionsResult(final int i2, final String[] strArr, final int[] iArr) {
        this.mPermissionsCallback = new Callback() { // from class: com.facebook.react.BaseReactFragment.2
            @Override // com.facebook.react.bridge.Callback
            public void invoke(Object... objArr) {
                if (BaseReactFragment.this.mPermissionListener == null || !BaseReactFragment.this.mPermissionListener.onRequestPermissionsResult(i2, strArr, iArr)) {
                    return;
                }
                BaseReactFragment.this.mPermissionListener = null;
            }
        };
    }
}
