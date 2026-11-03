package com.ss.android.socialbase.downloader.depend;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.ss.android.socialbase.downloader.model.DownloadInfo;
/* loaded from: classes9.dex */
public interface IDownloadNotificationEventAidlListener extends IInterface {

    /* loaded from: classes9.dex */
    public static class Default implements IDownloadNotificationEventAidlListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.ss.android.socialbase.downloader.depend.IDownloadNotificationEventAidlListener
        public String getNotifyProcessName() {
            return null;
        }

        @Override // com.ss.android.socialbase.downloader.depend.IDownloadNotificationEventAidlListener
        public boolean interceptAfterNotificationSuccess(boolean z) {
            return false;
        }

        @Override // com.ss.android.socialbase.downloader.depend.IDownloadNotificationEventAidlListener
        public void onNotificationEvent(int i2, DownloadInfo downloadInfo, String str, String str2) {
        }
    }

    String getNotifyProcessName();

    boolean interceptAfterNotificationSuccess(boolean z);

    void onNotificationEvent(int i2, DownloadInfo downloadInfo, String str, String str2);

    /* loaded from: classes9.dex */
    public static abstract class Stub extends Binder implements IDownloadNotificationEventAidlListener {
        private static final String DESCRIPTOR = "com.ss.android.socialbase.downloader.depend.IDownloadNotificationEventAidlListener";
        static final int TRANSACTION_getNotifyProcessName = 3;
        static final int TRANSACTION_interceptAfterNotificationSuccess = 2;
        static final int TRANSACTION_onNotificationEvent = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IDownloadNotificationEventAidlListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IDownloadNotificationEventAidlListener)) {
                return (IDownloadNotificationEventAidlListener) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i3) {
            if (i2 == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                onNotificationEvent(parcel.readInt(), parcel.readInt() != 0 ? DownloadInfo.CREATOR.createFromParcel(parcel) : null, parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                return true;
            } else if (i2 == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                boolean interceptAfterNotificationSuccess = interceptAfterNotificationSuccess(parcel.readInt() != 0);
                parcel2.writeNoException();
                parcel2.writeInt(interceptAfterNotificationSuccess ? 1 : 0);
                return true;
            } else if (i2 != 3) {
                if (i2 == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i2, parcel, parcel2, i3);
            } else {
                parcel.enforceInterface(DESCRIPTOR);
                String notifyProcessName = getNotifyProcessName();
                parcel2.writeNoException();
                parcel2.writeString(notifyProcessName);
                return true;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes9.dex */
        public static class Proxy implements IDownloadNotificationEventAidlListener {
            public static IDownloadNotificationEventAidlListener sDefaultImpl;
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

            @Override // com.ss.android.socialbase.downloader.depend.IDownloadNotificationEventAidlListener
            public void onNotificationEvent(int i2, DownloadInfo downloadInfo, String str, String str2) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i2);
                    if (downloadInfo != null) {
                        obtain.writeInt(1);
                        downloadInfo.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onNotificationEvent(i2, downloadInfo, str, str2);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.ss.android.socialbase.downloader.depend.IDownloadNotificationEventAidlListener
            public boolean interceptAfterNotificationSuccess(boolean z) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().interceptAfterNotificationSuccess(z);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.ss.android.socialbase.downloader.depend.IDownloadNotificationEventAidlListener
            public String getNotifyProcessName() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getNotifyProcessName();
                    }
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IDownloadNotificationEventAidlListener iDownloadNotificationEventAidlListener) {
            if (Proxy.sDefaultImpl != null || iDownloadNotificationEventAidlListener == null) {
                return false;
            }
            Proxy.sDefaultImpl = iDownloadNotificationEventAidlListener;
            return true;
        }

        public static IDownloadNotificationEventAidlListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
