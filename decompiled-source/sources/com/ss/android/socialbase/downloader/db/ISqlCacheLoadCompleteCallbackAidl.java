package com.ss.android.socialbase.downloader.db;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import java.util.Map;
/* loaded from: classes9.dex */
public interface ISqlCacheLoadCompleteCallbackAidl extends IInterface {

    /* loaded from: classes9.dex */
    public static class Default implements ISqlCacheLoadCompleteCallbackAidl {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.ss.android.socialbase.downloader.db.ISqlCacheLoadCompleteCallbackAidl
        public void callback(Map map, Map map2) {
        }
    }

    void callback(Map map, Map map2);

    /* loaded from: classes9.dex */
    public static abstract class Stub extends Binder implements ISqlCacheLoadCompleteCallbackAidl {
        private static final String DESCRIPTOR = "com.ss.android.socialbase.downloader.db.ISqlCacheLoadCompleteCallbackAidl";
        static final int TRANSACTION_callback = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ISqlCacheLoadCompleteCallbackAidl asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof ISqlCacheLoadCompleteCallbackAidl)) {
                return (ISqlCacheLoadCompleteCallbackAidl) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i3) {
            if (i2 != 1) {
                if (i2 == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i2, parcel, parcel2, i3);
            }
            parcel.enforceInterface(DESCRIPTOR);
            ClassLoader classLoader = getClass().getClassLoader();
            callback(parcel.readHashMap(classLoader), parcel.readHashMap(classLoader));
            parcel2.writeNoException();
            return true;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes9.dex */
        public static class Proxy implements ISqlCacheLoadCompleteCallbackAidl {
            public static ISqlCacheLoadCompleteCallbackAidl sDefaultImpl;
            private IBinder mRemote;

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.ss.android.socialbase.downloader.db.ISqlCacheLoadCompleteCallbackAidl
            public void callback(Map map, Map map2) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeMap(map);
                    obtain.writeMap(map2);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().callback(map, map2);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ISqlCacheLoadCompleteCallbackAidl iSqlCacheLoadCompleteCallbackAidl) {
            if (Proxy.sDefaultImpl != null || iSqlCacheLoadCompleteCallbackAidl == null) {
                return false;
            }
            Proxy.sDefaultImpl = iSqlCacheLoadCompleteCallbackAidl;
            return true;
        }

        public static ISqlCacheLoadCompleteCallbackAidl getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
