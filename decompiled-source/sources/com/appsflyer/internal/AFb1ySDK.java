package com.appsflyer.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Parcel;
import android.os.RemoteException;
import com.appsflyer.AFLogger;
import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
/* JADX INFO: Access modifiers changed from: package-private */
@Deprecated
/* loaded from: classes.dex */
public final class AFb1ySDK {
    AFb1ySDK() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static AFa1ySDK AFKeystoreWrapper(Context context) throws Exception {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw new IllegalStateException("Cannot be called from the main thread");
        }
        context.getPackageManager().getPackageInfo("com.android.vending", 0);
        AFa1zSDK aFa1zSDK = new AFa1zSDK((byte) 0);
        Intent intent = new Intent("com.google.android.gms.ads.identifier.service.START");
        intent.setPackage("com.google.android.gms");
        try {
            if (!context.bindService(intent, aFa1zSDK, 1)) {
                if (context != null) {
                    context.unbindService(aFa1zSDK);
                }
                throw new IOException("Google Play connection failed");
            } else if (aFa1zSDK.AFInAppEventType) {
                throw new IllegalStateException("Cannot call get on this connection more than once");
            } else {
                aFa1zSDK.AFInAppEventType = true;
                IBinder poll = aFa1zSDK.valueOf.poll(10L, TimeUnit.SECONDS);
                if (poll != null) {
                    AFa1tSDK aFa1tSDK = new AFa1tSDK(poll);
                    return new AFa1ySDK(aFa1tSDK.AFKeystoreWrapper(), aFa1tSDK.valueOf());
                }
                throw new TimeoutException("Timed out waiting for the service connection");
            }
        } finally {
            if (context != null) {
                context.unbindService(aFa1zSDK);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class AFa1ySDK {
        final String AFInAppEventParameterName;
        private final boolean AFInAppEventType;

        AFa1ySDK(String str, boolean z) {
            this.AFInAppEventParameterName = str;
            this.AFInAppEventType = z;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final boolean AFInAppEventType() {
            return this.AFInAppEventType;
        }
    }

    /* loaded from: classes.dex */
    static final class AFa1zSDK implements ServiceConnection {
        boolean AFInAppEventType;
        final LinkedBlockingQueue<IBinder> valueOf;

        @Override // android.content.ServiceConnection
        public final void onServiceDisconnected(ComponentName componentName) {
        }

        private AFa1zSDK() {
            this.valueOf = new LinkedBlockingQueue<>(1);
            this.AFInAppEventType = false;
        }

        /* synthetic */ AFa1zSDK(byte b2) {
            this();
        }

        @Override // android.content.ServiceConnection
        public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                this.valueOf.put(iBinder);
            } catch (InterruptedException e2) {
                AFLogger.afErrorLogForExcManagerOnly("onServiceConnected Interrupted", e2);
            }
        }
    }

    /* loaded from: classes.dex */
    static final class AFa1tSDK implements IInterface {
        private final IBinder AFInAppEventParameterName;

        AFa1tSDK(IBinder iBinder) {
            this.AFInAppEventParameterName = iBinder;
        }

        @Override // android.os.IInterface
        public final IBinder asBinder() {
            return this.AFInAppEventParameterName;
        }

        public final String AFKeystoreWrapper() throws RemoteException {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                this.AFInAppEventParameterName.transact(1, obtain, obtain2, 0);
                obtain2.readException();
                return obtain2.readString();
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }

        final boolean valueOf() throws RemoteException {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                obtain.writeInt(1);
                this.AFInAppEventParameterName.transact(2, obtain, obtain2, 0);
                obtain2.readException();
                return obtain2.readInt() != 0;
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }
    }
}
