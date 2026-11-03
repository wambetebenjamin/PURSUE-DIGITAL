package com.ttnet.org.chromium.net;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.ProxyInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.bytedance.apm.perf.traffic.a;
import com.facebook.react.modules.systeminfo.AndroidInfoHelpers;
import com.ttnet.org.chromium.base.ContextUtils;
import com.ttnet.org.chromium.base.Log;
import com.ttnet.org.chromium.base.TraceEvent;
import com.ttnet.org.chromium.base.annotations.JNINamespace;
import com.ttnet.org.chromium.base.annotations.NativeClassQualifiedName;
import com.ttnet.org.chromium.build.BuildConfig;
import com.ttnet.org.chromium.net.ProxyChangeListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
@JNINamespace(a.KEY_TRAFFIC_STATUS_NET)
/* loaded from: classes9.dex */
public class ProxyChangeListener {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int GET_PROXY_CONFIG_DELAY_MILLIS = 1000;
    private static final String TAG = "ProxyChangeListener";
    private static boolean sEnabled = true;
    private Delegate mDelegate;
    private final Handler mHandler;
    private final Looper mLooper;
    private long mNativePtr;
    private ProxyReceiver mProxyReceiver;
    private BroadcastReceiver mRealProxyReceiver;

    /* loaded from: classes9.dex */
    public interface Delegate {
        void proxySettingsChanged();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes9.dex */
    public interface Natives {
        @NativeClassQualifiedName("ProxyConfigServiceAndroid::JNIDelegate")
        void proxySettingsChanged(long j2, ProxyChangeListener proxyChangeListener);

        @NativeClassQualifiedName("ProxyConfigServiceAndroid::JNIDelegate")
        void proxySettingsChangedTo(long j2, ProxyChangeListener proxyChangeListener, String str, int i2, String str2, String[] strArr);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static class ProxyConfig {
        public static final ProxyConfig DIRECT = new ProxyConfig("", 0, "", new String[0]);
        public final String[] mExclusionList;
        public final String mHost;
        public final String mPacUrl;
        public final int mPort;

        public ProxyConfig(String str, int i2, String str2, String[] strArr) {
            this.mHost = str;
            this.mPort = i2;
            this.mPacUrl = str2;
            this.mExclusionList = strArr;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static ProxyConfig fromProxyInfo(ProxyInfo proxyInfo) {
            if (proxyInfo == null) {
                return null;
            }
            String host = proxyInfo.getHost();
            Uri pacFileUrl = proxyInfo.getPacFileUrl();
            if (host == null) {
                host = "";
            }
            return new ProxyConfig(host, proxyInfo.getPort(), Uri.EMPTY.equals(pacFileUrl) ? null : pacFileUrl.toString(), proxyInfo.getExclusionList());
        }
    }

    private ProxyChangeListener() {
        Looper myLooper = Looper.myLooper();
        this.mLooper = myLooper;
        this.mHandler = new Handler(myLooper);
    }

    public static void setEnabled(boolean z) {
        sEnabled = z;
    }

    public void setDelegateForTesting(Delegate delegate) {
        this.mDelegate = delegate;
    }

    public static ProxyChangeListener create() {
        return new ProxyChangeListener();
    }

    public static String getProperty(String str) {
        return System.getProperty(str);
    }

    public void start(long j2) {
        TraceEvent scoped = TraceEvent.scoped("ProxyChangeListener.start");
        try {
            assertOnThread();
            this.mNativePtr = j2;
            registerReceiver();
            if (scoped != null) {
                scoped.close();
            }
        } catch (Throwable th) {
            if (scoped != null) {
                try {
                    scoped.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    public void stop() {
        assertOnThread();
        this.mNativePtr = 0L;
        unregisterReceiver();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public class ProxyReceiver extends BroadcastReceiver {
        private ProxyReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, final Intent intent) {
            if (intent.getAction().equals("android.intent.action.PROXY_CHANGE")) {
                ProxyChangeListener.this.mHandler.postDelayed(new Runnable() { // from class: com.ttnet.org.chromium.net.ProxyChangeListener$ProxyReceiver$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        ProxyChangeListener.ProxyReceiver.this.m719x76fd16da(intent);
                    }
                }, 1000L);
            }
        }

        /* renamed from: lambda$onReceive$0$com-ttnet-org-chromium-net-ProxyChangeListener$ProxyReceiver  reason: not valid java name */
        public /* synthetic */ void m719x76fd16da(Intent intent) {
            ProxyChangeListener.this.proxySettingsChanged(ProxyChangeListener.extractNewProxy(intent));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ProxyConfig extractNewProxy(Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras == null) {
            return null;
        }
        if (Build.VERSION.SDK_INT >= 21) {
            return ProxyConfig.fromProxyInfo((ProxyInfo) extras.get("android.intent.extra.PROXY_INFO"));
        }
        try {
            Object obj = extras.get("proxy");
            if (obj == null) {
                return null;
            }
            Class<?> cls = Class.forName("android.net.ProxyProperties");
            Method declaredMethod = cls.getDeclaredMethod("getHost", new Class[0]);
            Method declaredMethod2 = cls.getDeclaredMethod("getPort", new Class[0]);
            Method declaredMethod3 = cls.getDeclaredMethod("getExclusionList", new Class[0]);
            String str = (String) declaredMethod.invoke(obj, new Object[0]);
            int intValue = ((Integer) declaredMethod2.invoke(obj, new Object[0])).intValue();
            String[] split = ((String) declaredMethod3.invoke(obj, new Object[0])).split(",");
            if (Build.VERSION.SDK_INT >= 19) {
                String str2 = (String) cls.getDeclaredMethod("getPacFileUrl", new Class[0]).invoke(obj, new Object[0]);
                if (!TextUtils.isEmpty(str2)) {
                    return new ProxyConfig(str, intValue, str2, split);
                }
            }
            return new ProxyConfig(str, intValue, null, split);
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | NullPointerException | InvocationTargetException e2) {
            Log.e(TAG, "Using no proxy configuration due to exception:" + e2, new Object[0]);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void proxySettingsChanged(ProxyConfig proxyConfig) {
        assertOnThread();
        if (sEnabled) {
            Delegate delegate = this.mDelegate;
            if (delegate != null) {
                delegate.proxySettingsChanged();
            }
            if (this.mNativePtr == 0) {
                return;
            }
            if (proxyConfig != null) {
                ProxyChangeListenerJni.get().proxySettingsChangedTo(this.mNativePtr, this, proxyConfig.mHost, proxyConfig.mPort, proxyConfig.mPacUrl, proxyConfig.mExclusionList);
            } else {
                ProxyChangeListenerJni.get().proxySettingsChanged(this.mNativePtr, this);
            }
        }
    }

    private ProxyConfig getProxyConfig(Intent intent) {
        ProxyInfo defaultProxy = ((ConnectivityManager) ContextUtils.getApplicationContext().getSystemService("connectivity")).getDefaultProxy();
        if (defaultProxy == null) {
            return ProxyConfig.DIRECT;
        }
        if (Build.VERSION.SDK_INT < 29 || !AndroidInfoHelpers.DEVICE_LOCALHOST.equals(defaultProxy.getHost()) || defaultProxy.getPort() != -1) {
            return ProxyConfig.fromProxyInfo(defaultProxy);
        }
        return extractNewProxy(intent);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void updateProxyConfigFromConnectivityManager(final Intent intent) {
        this.mHandler.postDelayed(new Runnable() { // from class: com.ttnet.org.chromium.net.ProxyChangeListener$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                ProxyChangeListener.this.m718x3ba55146(intent);
            }
        }, 1000L);
    }

    /* renamed from: lambda$updateProxyConfigFromConnectivityManager$0$com-ttnet-org-chromium-net-ProxyChangeListener  reason: not valid java name */
    public /* synthetic */ void m718x3ba55146(Intent intent) {
        proxySettingsChanged(getProxyConfig(intent));
    }

    private void registerReceiver() {
        assertOnThread();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.PROXY_CHANGE");
        this.mProxyReceiver = new ProxyReceiver();
        if (Build.VERSION.SDK_INT < 23) {
            ContextUtils.registerNonExportedBroadcastReceiver(ContextUtils.getApplicationContext(), this.mProxyReceiver, intentFilter);
            return;
        }
        ContextUtils.registerNonExportedBroadcastReceiver(ContextUtils.getApplicationContext(), this.mProxyReceiver, new IntentFilter());
        this.mRealProxyReceiver = new ProxyBroadcastReceiver(this);
        ContextUtils.registerNonExportedBroadcastReceiver(ContextUtils.getApplicationContext(), this.mRealProxyReceiver, intentFilter);
    }

    private void unregisterReceiver() {
        assertOnThread();
        ContextUtils.getApplicationContext().unregisterReceiver(this.mProxyReceiver);
        if (this.mRealProxyReceiver != null) {
            ContextUtils.getApplicationContext().unregisterReceiver(this.mRealProxyReceiver);
        }
        this.mProxyReceiver = null;
        this.mRealProxyReceiver = null;
    }

    private boolean onThread() {
        return this.mLooper == Looper.myLooper();
    }

    private void assertOnThread() {
        if (BuildConfig.ENABLE_ASSERTS && !onThread()) {
            throw new IllegalStateException("Must be called on ProxyChangeListener thread.");
        }
    }

    private void runOnThread(Runnable runnable) {
        if (onThread()) {
            runnable.run();
        } else {
            this.mHandler.post(runnable);
        }
    }
}
