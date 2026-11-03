package com.facebook.react;

import android.app.Application;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.JSIModulePackage;
import com.facebook.react.bridge.JavaScriptExecutorFactory;
import com.facebook.react.bridge.ReactMarker;
import com.facebook.react.bridge.ReactMarkerConstants;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.devsupport.RedBoxHandler;
import com.facebook.react.uimanager.UIImplementationProvider;
import java.util.List;
/* loaded from: classes8.dex */
public abstract class ReactNativeHost {
    private final Application mApplication;
    private ReactInstanceManager mReactInstanceManager;

    protected String getBundleAssetName() {
        return "index.android.bundle";
    }

    protected String getJSBundleFile() {
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public JSIModulePackage getJSIModulePackage() {
        return null;
    }

    protected String getJSMainModuleName() {
        return "index.android";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public JavaScriptExecutorFactory getJavaScriptExecutorFactory() {
        return null;
    }

    protected abstract List<ReactPackage> getPackages();

    /* JADX INFO: Access modifiers changed from: protected */
    public RedBoxHandler getRedBoxHandler() {
        return null;
    }

    public abstract boolean getUseDeveloperSupport();

    /* JADX INFO: Access modifiers changed from: protected */
    public ReactNativeHost(Application application) {
        this.mApplication = application;
    }

    public ReactInstanceManager getReactInstanceManager() {
        if (this.mReactInstanceManager == null) {
            ReactMarker.logMarker(ReactMarkerConstants.GET_REACT_INSTANCE_MANAGER_START);
            this.mReactInstanceManager = createReactInstanceManager();
            ReactMarker.logMarker(ReactMarkerConstants.GET_REACT_INSTANCE_MANAGER_END);
        }
        return this.mReactInstanceManager;
    }

    public boolean hasInstance() {
        return this.mReactInstanceManager != null;
    }

    public void clear() {
        ReactInstanceManager reactInstanceManager = this.mReactInstanceManager;
        if (reactInstanceManager != null) {
            reactInstanceManager.destroy();
            this.mReactInstanceManager = null;
        }
    }

    protected ReactInstanceManager createReactInstanceManager() {
        ReactMarker.logMarker(ReactMarkerConstants.BUILD_REACT_INSTANCE_MANAGER_START);
        ReactInstanceManagerBuilder initialLifecycleState = ReactInstanceManager.builder().setApplication(this.mApplication).setJSMainModulePath(getJSMainModuleName()).setUseDeveloperSupport(getUseDeveloperSupport()).setRedBoxHandler(getRedBoxHandler()).setJavaScriptExecutorFactory(getJavaScriptExecutorFactory()).setUIImplementationProvider(getUIImplementationProvider()).setJSIModulesPackage(getJSIModulePackage()).setInitialLifecycleState(LifecycleState.BEFORE_CREATE);
        for (ReactPackage reactPackage : getPackages()) {
            initialLifecycleState.addPackage(reactPackage);
        }
        String jSBundleFile = getJSBundleFile();
        if (jSBundleFile != null) {
            initialLifecycleState.setJSBundleFile(jSBundleFile);
        } else {
            initialLifecycleState.setBundleAssetName((String) Assertions.assertNotNull(getBundleAssetName()));
        }
        ReactInstanceManager build = initialLifecycleState.build();
        ReactMarker.logMarker(ReactMarkerConstants.BUILD_REACT_INSTANCE_MANAGER_END);
        return build;
    }

    protected final Application getApplication() {
        return this.mApplication;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public UIImplementationProvider getUIImplementationProvider() {
        return new UIImplementationProvider();
    }
}
