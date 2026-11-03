package com.bytedance.apm.impl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
/* compiled from: IRemoteLogProcessor.java */
/* loaded from: classes.dex */
public interface b extends IInterface {
    String getSlardarConfig();

    /* compiled from: IRemoteLogProcessor.java */
    /* loaded from: classes.dex */
    public static abstract class a extends Binder implements b {

        /* renamed from: a  reason: collision with root package name */
        static final int f1075a = 1;

        /* renamed from: b  reason: collision with root package name */
        private static final String f1076b = "com.bytedance.apm.impl.ICrossLogProcessor";

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public a() {
            attachInterface(this, f1076b);
        }

        public static b asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(f1076b);
            if (queryLocalInterface != null && (queryLocalInterface instanceof b)) {
                return (b) queryLocalInterface;
            }
            return new C0016a(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i3) {
            if (i2 != 1) {
                if (i2 == 1598968902) {
                    parcel2.writeString(f1076b);
                    return true;
                }
                return super.onTransact(i2, parcel, parcel2, i3);
            }
            parcel.enforceInterface(f1076b);
            String slardarConfig = getSlardarConfig();
            parcel2.writeNoException();
            parcel2.writeString(slardarConfig);
            return true;
        }

        /* compiled from: IRemoteLogProcessor.java */
        /* renamed from: com.bytedance.apm.impl.b$a$a  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        private static class C0016a implements b {

            /* renamed from: a  reason: collision with root package name */
            private IBinder f1077a;

            public String getInterfaceDescriptor() {
                return a.f1076b;
            }

            C0016a(IBinder iBinder) {
                this.f1077a = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f1077a;
            }

            @Override // com.bytedance.apm.impl.b
            public String getSlardarConfig() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(a.f1076b);
                    this.f1077a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
