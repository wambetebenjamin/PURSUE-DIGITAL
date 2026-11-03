package gsdk.impl.webview.DEFAULT;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
/* compiled from: IExitAidlInterface.java */
/* loaded from: classes10.dex */
public interface ad extends IInterface {

    /* renamed from: a  reason: collision with root package name */
    public static final String f20856a = "com.bytedance.ttgame.module.webview.IExitAidlInterface";

    /* compiled from: IExitAidlInterface.java */
    /* loaded from: classes10.dex */
    public static class a implements ad {
        @Override // gsdk.impl.webview.DEFAULT.ad
        public void a(String str) {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    void a(String str);

    /* compiled from: IExitAidlInterface.java */
    /* loaded from: classes10.dex */
    public static abstract class b extends Binder implements ad {

        /* renamed from: b  reason: collision with root package name */
        static final int f20857b = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public b() {
            attachInterface(this, ad.f20856a);
        }

        public static ad a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(ad.f20856a);
            if (queryLocalInterface != null && (queryLocalInterface instanceof ad)) {
                return (ad) queryLocalInterface;
            }
            return new a(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i3) {
            if (i2 >= 1 && i2 <= 16777215) {
                parcel.enforceInterface(ad.f20856a);
            }
            if (i2 == 1598968902) {
                parcel2.writeString(ad.f20856a);
                return true;
            } else if (i2 == 1) {
                a(parcel.readString());
                parcel2.writeNoException();
                return true;
            } else {
                return super.onTransact(i2, parcel, parcel2, i3);
            }
        }

        /* compiled from: IExitAidlInterface.java */
        /* loaded from: classes10.dex */
        static class a implements ad {

            /* renamed from: b  reason: collision with root package name */
            private IBinder f20858b;

            public String a() {
                return ad.f20856a;
            }

            a(IBinder iBinder) {
                this.f20858b = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f20858b;
            }

            @Override // gsdk.impl.webview.DEFAULT.ad
            public void a(String str) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ad.f20856a);
                    obtain.writeString(str);
                    this.f20858b.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
