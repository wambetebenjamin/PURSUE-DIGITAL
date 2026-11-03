package com.ss.android.socialbase.downloader.depend;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
/* loaded from: classes9.dex */
public interface IChunkCntAidlCalculator extends IInterface {

    /* loaded from: classes9.dex */
    public static class Default implements IChunkCntAidlCalculator {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.ss.android.socialbase.downloader.depend.IChunkCntAidlCalculator
        public int calculateChunkCount(long j2) {
            return 0;
        }
    }

    int calculateChunkCount(long j2);

    /* loaded from: classes9.dex */
    public static abstract class Stub extends Binder implements IChunkCntAidlCalculator {
        private static final String DESCRIPTOR = "com.ss.android.socialbase.downloader.depend.IChunkCntAidlCalculator";
        static final int TRANSACTION_calculateChunkCount = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IChunkCntAidlCalculator asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IChunkCntAidlCalculator)) {
                return (IChunkCntAidlCalculator) queryLocalInterface;
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
            int calculateChunkCount = calculateChunkCount(parcel.readLong());
            parcel2.writeNoException();
            parcel2.writeInt(calculateChunkCount);
            return true;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes9.dex */
        public static class Proxy implements IChunkCntAidlCalculator {
            public static IChunkCntAidlCalculator sDefaultImpl;
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

            @Override // com.ss.android.socialbase.downloader.depend.IChunkCntAidlCalculator
            public int calculateChunkCount(long j2) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeLong(j2);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().calculateChunkCount(j2);
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IChunkCntAidlCalculator iChunkCntAidlCalculator) {
            if (Proxy.sDefaultImpl != null || iChunkCntAidlCalculator == null) {
                return false;
            }
            Proxy.sDefaultImpl = iChunkCntAidlCalculator;
            return true;
        }

        public static IChunkCntAidlCalculator getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
