package com.ss.android.socialbase.downloader.depend;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import java.util.List;
/* loaded from: classes9.dex */
public interface IDownloadForbiddenAidlCallback extends IInterface {

    /* loaded from: classes9.dex */
    public static class Default implements IDownloadForbiddenAidlCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.ss.android.socialbase.downloader.depend.IDownloadForbiddenAidlCallback
        public boolean hasCallback() {
            return false;
        }

        @Override // com.ss.android.socialbase.downloader.depend.IDownloadForbiddenAidlCallback
        public void onCallback(List<String> list) {
        }
    }

    boolean hasCallback();

    void onCallback(List<String> list);

    /* loaded from: classes9.dex */
    public static abstract class Stub extends Binder implements IDownloadForbiddenAidlCallback {
        private static final String DESCRIPTOR = "com.ss.android.socialbase.downloader.depend.IDownloadForbiddenAidlCallback";
        static final int TRANSACTION_hasCallback = 2;
        static final int TRANSACTION_onCallback = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IDownloadForbiddenAidlCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IDownloadForbiddenAidlCallback)) {
                return (IDownloadForbiddenAidlCallback) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i3) {
            if (i2 == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                onCallback(parcel.createStringArrayList());
                parcel2.writeNoException();
                return true;
            } else if (i2 != 2) {
                if (i2 == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i2, parcel, parcel2, i3);
            } else {
                parcel.enforceInterface(DESCRIPTOR);
                boolean hasCallback = hasCallback();
                parcel2.writeNoException();
                parcel2.writeInt(hasCallback ? 1 : 0);
                return true;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes9.dex */
        public static class Proxy implements IDownloadForbiddenAidlCallback {
            public static IDownloadForbiddenAidlCallback sDefaultImpl;
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

            @Override // com.ss.android.socialbase.downloader.depend.IDownloadForbiddenAidlCallback
            public void onCallback(List<String> list) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStringList(list);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onCallback(list);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.ss.android.socialbase.downloader.depend.IDownloadForbiddenAidlCallback
            public boolean hasCallback() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().hasCallback();
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IDownloadForbiddenAidlCallback iDownloadForbiddenAidlCallback) {
            if (Proxy.sDefaultImpl != null || iDownloadForbiddenAidlCallback == null) {
                return false;
            }
            Proxy.sDefaultImpl = iDownloadForbiddenAidlCallback;
            return true;
        }

        public static IDownloadForbiddenAidlCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
