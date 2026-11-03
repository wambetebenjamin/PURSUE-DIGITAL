package gsdk.impl.webview.DEFAULT;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import gsdk.impl.webview.DEFAULT.af;
/* compiled from: ICloseAidlInterface.java */
/* loaded from: classes10.dex */
public interface ac extends IInterface {

    /* renamed from: a  reason: collision with root package name */
    public static final String f20853a = "com.bytedance.ttgame.module.webview.ICloseAidlInterface";

    /* compiled from: ICloseAidlInterface.java */
    /* loaded from: classes10.dex */
    public static class a implements ac {
        @Override // gsdk.impl.webview.DEFAULT.ac
        public void a(af afVar) {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    void a(af afVar);

    /* compiled from: ICloseAidlInterface.java */
    /* loaded from: classes10.dex */
    public static abstract class b extends Binder implements ac {

        /* renamed from: b  reason: collision with root package name */
        static final int f20854b = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public b() {
            attachInterface(this, ac.f20853a);
        }

        public static ac a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(ac.f20853a);
            if (queryLocalInterface != null && (queryLocalInterface instanceof ac)) {
                return (ac) queryLocalInterface;
            }
            return new a(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i3) {
            if (i2 >= 1 && i2 <= 16777215) {
                parcel.enforceInterface(ac.f20853a);
            }
            if (i2 == 1598968902) {
                parcel2.writeString(ac.f20853a);
                return true;
            } else if (i2 == 1) {
                a(af.b.a(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            } else {
                return super.onTransact(i2, parcel, parcel2, i3);
            }
        }

        /* compiled from: ICloseAidlInterface.java */
        /* loaded from: classes10.dex */
        static class a implements ac {

            /* renamed from: b  reason: collision with root package name */
            private IBinder f20855b;

            public String a() {
                return ac.f20853a;
            }

            a(IBinder iBinder) {
                this.f20855b = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f20855b;
            }

            @Override // gsdk.impl.webview.DEFAULT.ac
            public void a(af afVar) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ac.f20853a);
                    obtain.writeStrongInterface(afVar);
                    this.f20855b.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
