package com.bytedance.keva;

import android.os.Looper;
import android.util.Log;
import com.bytedance.keva.Keva;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
/* loaded from: classes2.dex */
public class KevaFuture extends Keva {
    private boolean isFromSp;
    private KevaImpl mFallBackInstance;
    private FutureTask<KevaImpl> mFuture;
    private volatile KevaImpl mKeva;
    private Callable<KevaImpl> mKevaCallable;
    private int mMode;
    protected String mName;
    private String mPath;

    /* JADX INFO: Access modifiers changed from: protected */
    public void onRepoLoad() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public KevaFuture(String str, String str2, int i2, Callable<KevaImpl> callable, boolean z) {
        this.mName = str;
        this.mMode = i2;
        this.isFromSp = z;
        this.mPath = str2;
        initTask(callable);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void initTask(final Callable<KevaImpl> callable) {
        if (KevaImpl.sIsLoadOptEnable) {
            this.mKevaCallable = new Callable<KevaImpl>() { // from class: com.bytedance.keva.KevaFuture.1
                private KevaImpl mResult;

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // java.util.concurrent.Callable
                public KevaImpl call() {
                    synchronized (this) {
                        if (this.mResult == null) {
                            this.mResult = (KevaImpl) callable.call();
                            KevaFuture.this.onRepoLoad();
                        }
                    }
                    return this.mResult;
                }
            };
        } else {
            this.mKevaCallable = new Callable<KevaImpl>() { // from class: com.bytedance.keva.KevaFuture.2
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // java.util.concurrent.Callable
                public KevaImpl call() {
                    KevaImpl kevaImpl = (KevaImpl) callable.call();
                    KevaFuture.this.onRepoLoad();
                    return kevaImpl;
                }
            };
        }
        this.mFuture = new KevaFutureTask(this.mKevaCallable);
        if (KevaImpl.sIsEnableExecutorOpt) {
            int i2 = KevaImpl.sExecuteKevaType;
            if (i2 == 1) {
                if (Looper.myLooper() == Looper.getMainLooper()) {
                    directRunCallable();
                    return;
                } else {
                    KevaImpl.sExecutor.execute(this.mFuture);
                    return;
                }
            } else if (i2 != 2) {
                if (i2 == 3) {
                    directRunCallable();
                    return;
                } else {
                    KevaImpl.sExecutor.execute(this.mFuture);
                    return;
                }
            } else if (Looper.myLooper() != Looper.getMainLooper()) {
                directRunCallable();
                return;
            } else {
                KevaImpl.sExecutor.execute(this.mFuture);
                return;
            }
        }
        KevaImpl.sExecutor.execute(this.mFuture);
    }

    private void directRunCallable() {
        try {
            KevaImpl.sMonitor.logDebug("main thread, direct execution");
            ((KevaFutureTask) this.mFuture).set(this.mKevaCallable.call());
        } catch (Exception e2) {
            KevaImpl.sMonitor.logDebug("Failed to execute: " + Log.getStackTraceString(e2));
            ((KevaFutureTask) this.mFuture).setException(e2);
        }
    }

    private KevaImpl obtain() {
        if (KevaImpl.sIsLoadOptEnable) {
            return obtainLoadOpt();
        }
        return obtainOrigin();
    }

    private KevaImpl obtainOrigin() {
        try {
            return this.mFuture.get();
        } catch (InterruptedException | ExecutionException e2) {
            KevaImpl.sMonitor.logDebug("fail to obtain keva future instance");
            KevaImpl.sMonitor.reportThrowable(5, this.mName, null, null, e2);
            if (this.mFallBackInstance == null) {
                KevaImpl emptyRepoImpl = KevaImpl.getEmptyRepoImpl(this.mName, this.mPath, this.mMode, this.isFromSp);
                this.mFallBackInstance = emptyRepoImpl;
                emptyRepoImpl.init(false);
            }
            return this.mFallBackInstance;
        }
    }

    private KevaImpl obtainLoadOpt() {
        if (this.mKeva == null) {
            synchronized (this) {
                if (this.mKeva == null) {
                    this.mKeva = getKeva();
                }
            }
        }
        return this.mKeva;
    }

    private KevaImpl getKeva() {
        try {
            if (this.mFuture.cancel(false)) {
                KevaImpl.sMonitor.logDebug("pass to cancel keva future instance");
                return this.mKevaCallable.call();
            }
            return this.mFuture.get();
        } catch (Exception e2) {
            KevaImpl.sMonitor.logDebug("fail to obtain keva future instance");
            KevaImpl.sMonitor.reportThrowable(5, this.mName, null, null, e2);
            if (this.mFallBackInstance == null) {
                KevaImpl emptyRepoImpl = KevaImpl.getEmptyRepoImpl(this.mName, this.mPath, this.mMode, this.isFromSp);
                this.mFallBackInstance = emptyRepoImpl;
                emptyRepoImpl.init(false);
            }
            return this.mFallBackInstance;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void checkMode(int i2) {
        if (this.mFuture.isDone()) {
            obtain().checkMode(i2);
        }
    }

    @Override // com.bytedance.keva.Keva
    public String name() {
        return obtain().name();
    }

    @Override // com.bytedance.keva.Keva
    public void storeFloat(String str, float f2) {
        obtain().storeFloat(str, f2);
    }

    @Override // com.bytedance.keva.Keva
    public void storeBoolean(String str, boolean z) {
        obtain().storeBoolean(str, z);
    }

    @Override // com.bytedance.keva.Keva
    public void storeInt(String str, int i2) {
        obtain().storeInt(str, i2);
    }

    @Override // com.bytedance.keva.Keva
    public void storeLong(String str, long j2) {
        obtain().storeLong(str, j2);
    }

    @Override // com.bytedance.keva.Keva
    public void storeDouble(String str, double d2) {
        obtain().storeDouble(str, d2);
    }

    @Override // com.bytedance.keva.Keva
    public void storeString(String str, String str2) {
        obtain().storeString(str, str2);
    }

    @Override // com.bytedance.keva.Keva
    public void storeStringSet(String str, Set<String> set) {
        obtain().storeStringSet(str, set);
    }

    @Override // com.bytedance.keva.Keva
    public void storeStringArray(String str, String[] strArr) {
        obtain().storeStringArray(str, strArr);
    }

    @Override // com.bytedance.keva.Keva
    public void storeBytes(String str, byte[] bArr) {
        obtain().storeBytes(str, bArr);
    }

    @Override // com.bytedance.keva.Keva
    public void storeStringJustDisk(String str, String str2) {
        obtain().storeStringJustDisk(str, str2);
    }

    @Override // com.bytedance.keva.Keva
    public void storeStringArrayJustDisk(String str, String[] strArr) {
        obtain().storeStringArrayJustDisk(str, strArr);
    }

    @Override // com.bytedance.keva.Keva
    public void storeStringSetJustDisk(String str, Set<String> set) {
        obtain().storeStringSetJustDisk(str, set);
    }

    @Override // com.bytedance.keva.Keva
    public void storeBytesJustDisk(String str, byte[] bArr) {
        obtain().storeBytesJustDisk(str, bArr);
    }

    @Override // com.bytedance.keva.Keva
    public void erase(String str) {
        obtain().erase(str);
    }

    @Override // com.bytedance.keva.Keva
    public void clear() {
        obtain().clear();
    }

    @Override // com.bytedance.keva.Keva
    public void dump() {
        obtain().dump();
    }

    @Override // com.bytedance.keva.Keva
    public int count() {
        return obtain().count();
    }

    @Override // com.bytedance.keva.Keva
    public Map<String, Object> buildNewMap(Map<String, Object> map) {
        return obtain().buildNewMap(map);
    }

    @Override // com.bytedance.keva.Keva
    public Map<String, ?> getAll() {
        return obtain().getAll();
    }

    @Override // com.bytedance.keva.Keva
    public Set<String> getAllKey() {
        return obtain().getAllKey();
    }

    @Override // com.bytedance.keva.Keva
    public byte[] getBytes(String str, byte[] bArr) {
        return obtain().getBytes(str, bArr);
    }

    @Override // com.bytedance.keva.Keva
    public String getString(String str, String str2) {
        return obtain().getString(str, str2);
    }

    @Override // com.bytedance.keva.Keva
    public Set<String> getStringSet(String str, Set<String> set) {
        return obtain().getStringSet(str, set);
    }

    @Override // com.bytedance.keva.Keva
    public String[] getStringArray(String str, String[] strArr) {
        return obtain().getStringArray(str, strArr);
    }

    @Override // com.bytedance.keva.Keva
    public byte[] getBytesJustDisk(String str, byte[] bArr) {
        return obtain().getBytesJustDisk(str, bArr);
    }

    @Override // com.bytedance.keva.Keva
    public String getStringJustDisk(String str, String str2) {
        return obtain().getStringJustDisk(str, str2);
    }

    @Override // com.bytedance.keva.Keva
    public String[] getStringArrayJustDisk(String str, String[] strArr) {
        return obtain().getStringArrayJustDisk(str, strArr);
    }

    @Override // com.bytedance.keva.Keva
    public Set<String> getStringSetJustDisk(String str, Set<String> set) {
        return obtain().getStringSetJustDisk(str, set);
    }

    @Override // com.bytedance.keva.Keva
    public int getInt(String str, int i2) {
        return obtain().getInt(str, i2);
    }

    @Override // com.bytedance.keva.Keva
    public long getLong(String str, long j2) {
        return obtain().getLong(str, j2);
    }

    @Override // com.bytedance.keva.Keva
    public double getDouble(String str, double d2) {
        return obtain().getDouble(str, d2);
    }

    @Override // com.bytedance.keva.Keva
    public float getFloat(String str, float f2) {
        return obtain().getFloat(str, f2);
    }

    @Override // com.bytedance.keva.Keva
    public boolean getBoolean(String str, boolean z) {
        return obtain().getBoolean(str, z);
    }

    @Override // com.bytedance.keva.Keva
    public boolean contains(String str) {
        return obtain().contains(str);
    }

    @Override // com.bytedance.keva.Keva
    public void registerChangeListener(Keva.OnChangeListener onChangeListener) {
        obtain().registerChangeListener(onChangeListener);
    }

    @Override // com.bytedance.keva.Keva
    public void unRegisterChangeListener(Keva.OnChangeListener onChangeListener) {
        obtain().unRegisterChangeListener(onChangeListener);
    }
}
