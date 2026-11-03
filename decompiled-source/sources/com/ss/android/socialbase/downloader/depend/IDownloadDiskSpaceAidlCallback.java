package com.ss.android.socialbase.downloader.depend;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
/* loaded from: classes9.dex */
public interface IDownloadDiskSpaceAidlCallback extends IInterface {

    /* loaded from: classes9.dex */
    public static class Default implements IDownloadDiskSpaceAidlCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.ss.android.socialbase.downloader.depend.IDownloadDiskSpaceAidlCallback
        public void onDiskCleaned() {
        }
    }

    void onDiskCleaned();

    /* loaded from: classes9.dex */
    public static abstract class Stub extends Binder implements IDownloadDiskSpaceAidlCallback {
        private static final String DESCRIPTOR = "com.ss.android.socialbase.downloader.depend.IDownloadDiskSpaceAidlCallback";
        static final int TRANSACTION_onDiskCleaned = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IDownloadDiskSpaceAidlCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IDownloadDiskSpaceAidlCallback)) {
                return (IDownloadDiskSpaceAidlCallback) queryLocalInterface;
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
            onDiskCleaned();
            parcel2.writeNoException();
            return true;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes9.dex */
        public static class Proxy implements IDownloadDiskSpaceAidlCallback {
            public static IDownloadDiskSpaceAidlCallback sDefaultImpl;
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

            @Override // com.ss.android.socialbase.downloader.depend.IDownloadDiskSpaceAidlCallback
            public void onDiskCleaned() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onDiskCleaned();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IDownloadDiskSpaceAidlCallback iDownloadDiskSpaceAidlCallback) {
            if (Proxy.sDefaultImpl != null || iDownloadDiskSpaceAidlCallback == null) {
                return false;
            }
            Proxy.sDefaultImpl = iDownloadDiskSpaceAidlCallback;
            return true;
        }

        public static IDownloadDiskSpaceAidlCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
