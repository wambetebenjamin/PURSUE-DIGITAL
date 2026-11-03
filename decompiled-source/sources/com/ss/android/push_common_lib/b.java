package com.ss.android.push_common_lib;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import java.util.List;
/* compiled from: ICrossProcessAIDL.java */
/* loaded from: classes9.dex */
public interface b extends IInterface {
    String invoke(String str, String str2, List list);

    /* compiled from: ICrossProcessAIDL.java */
    /* loaded from: classes9.dex */
    public static abstract class a extends Binder implements b {

        /* renamed from: a  reason: collision with root package name */
        private static final String f18468a = "com.ss.android.push_common_lib.ICrossProcessAIDL";

        /* renamed from: b  reason: collision with root package name */
        static final int f18469b = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public a() {
            attachInterface(this, f18468a);
        }

        public static b asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(f18468a);
            if (queryLocalInterface != null && (queryLocalInterface instanceof b)) {
                return (b) queryLocalInterface;
            }
            return new C0333a(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i3) {
            if (i2 != 1) {
                if (i2 == 1598968902) {
                    parcel2.writeString(f18468a);
                    return true;
                }
                return super.onTransact(i2, parcel, parcel2, i3);
            }
            parcel.enforceInterface(f18468a);
            String invoke = invoke(parcel.readString(), parcel.readString(), parcel.readArrayList(getClass().getClassLoader()));
            parcel2.writeNoException();
            parcel2.writeString(invoke);
            return true;
        }

        /* compiled from: ICrossProcessAIDL.java */
        /* renamed from: com.ss.android.push_common_lib.b$a$a  reason: collision with other inner class name */
        /* loaded from: classes9.dex */
        private static class C0333a implements b {

            /* renamed from: a  reason: collision with root package name */
            private IBinder f18470a;

            public String getInterfaceDescriptor() {
                return a.f18468a;
            }

            C0333a(IBinder iBinder) {
                this.f18470a = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f18470a;
            }

            @Override // com.ss.android.push_common_lib.b
            public String invoke(String str, String str2, List list) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(a.f18468a);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeList(list);
                    this.f18470a.transact(1, obtain, obtain2, 0);
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
