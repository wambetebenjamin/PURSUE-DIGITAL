package com.bytedance.react.framework.core;

import com.facebook.react.ReactPackage;
/* loaded from: classes2.dex */
public class BRNReactManager {
    private static BRNReactCallBack mBRNReactCallBack;
    private static BRNReactManager mBRNReactManager;

    /* loaded from: classes2.dex */
    public interface BRNReactCallBack {
        ReactPackage getDefaultReactPackage();
    }

    public static BRNReactManager newInstance() {
        if (mBRNReactManager == null) {
            mBRNReactManager = new BRNReactManager();
        }
        return mBRNReactManager;
    }

    public ReactPackage getDefaultReactPackage() {
        BRNReactCallBack bRNReactCallBack = mBRNReactCallBack;
        if (bRNReactCallBack != null) {
            return bRNReactCallBack.getDefaultReactPackage();
        }
        return null;
    }

    public void setBRNReactManager(BRNReactCallBack bRNReactCallBack) {
        mBRNReactCallBack = bRNReactCallBack;
    }
}
