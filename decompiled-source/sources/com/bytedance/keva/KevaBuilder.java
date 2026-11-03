package com.bytedance.keva;

import android.content.Context;
import android.util.Log;
import java.io.File;
import java.util.concurrent.Executor;
/* loaded from: classes2.dex */
public class KevaBuilder {
    private static KevaBuilder mInstance = new KevaBuilder();
    Context mContext;
    boolean mEnableArrayMap;
    boolean mEnableAsyncWrite;
    boolean mEnableExecutorOpt;
    boolean mEnableLoadOpt;
    boolean mEnableLockOpt;
    int mExecuteKevaType;
    Executor mExecutor;
    int mIsLoadFromNative;
    KevaMonitor mMonitor;
    String mPortedRepoName;
    File mWorkDir;
    int sIsEnableMultiProcessLoadFromNative;

    public static KevaBuilder getInstance() {
        KevaBuilder kevaBuilder = mInstance;
        if (kevaBuilder != null) {
            return kevaBuilder;
        }
        Log.w(KevaConstants.TAG, "have already did init, builder is invalid now!");
        return new KevaBuilder();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void clearInstance() {
        mInstance = null;
    }

    public KevaBuilder setContext(Context context) {
        this.mContext = context;
        return this;
    }

    public KevaBuilder setMonitor(KevaMonitor kevaMonitor) {
        this.mMonitor = kevaMonitor;
        return this;
    }

    public KevaBuilder setExecutor(Executor executor) {
        this.mExecutor = executor;
        return this;
    }

    public KevaBuilder setIsLoadFromNative(int i2) {
        this.mIsLoadFromNative = i2;
        return this;
    }

    public KevaBuilder setIsEnableMultiProcessLoadFromNative(int i2) {
        this.sIsEnableMultiProcessLoadFromNative = i2;
        return this;
    }

    public KevaBuilder setWorkDir(File file) {
        if (!file.exists()) {
            Log.i(KevaConstants.TAG, "work dir does not exist! try to create " + file.getPath());
            if (!file.mkdirs()) {
                Log.w(KevaConstants.TAG, "work dir fail to create!");
                return this;
            }
        }
        this.mWorkDir = file;
        return this;
    }

    public KevaBuilder setPortedRepoName(String str) {
        this.mPortedRepoName = str;
        return this;
    }

    public KevaBuilder setAsyncWriteEnable(boolean z) {
        this.mEnableAsyncWrite = z;
        return this;
    }

    public void setEnableLoadOpt(boolean z) {
        this.mEnableLoadOpt = z;
    }

    public void setEnableLockOpt(boolean z) {
        this.mEnableLockOpt = z;
    }

    public void setEnableExecutorOpt(boolean z) {
        this.mEnableExecutorOpt = z;
    }

    public void setExecuteType(int i2) {
        this.mExecuteKevaType = i2;
    }

    public static void onFlush() {
        KevaAsyncWriter.onFlush();
    }

    public void setEnableArrayMap(boolean z) {
        this.mEnableArrayMap = z;
    }

    public Context getContext() {
        return this.mContext;
    }
}
