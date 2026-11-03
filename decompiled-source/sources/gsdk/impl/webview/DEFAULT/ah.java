package gsdk.impl.webview.DEFAULT;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
/* compiled from: IWebViewBinderPool.java */
/* loaded from: classes10.dex */
public interface ah extends IInterface {

    /* renamed from: a  reason: collision with root package name */
    public static final String f20866a = "com.bytedance.ttgame.module.webview.IWebViewBinderPool";

    /* compiled from: IWebViewBinderPool.java */
    /* loaded from: classes10.dex */
    public static class a implements ah {
        @Override // gsdk.impl.webview.DEFAULT.ah
        public IBinder a(int i2) {
            return null;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    IBinder a(int i2);

    /* compiled from: IWebViewBinderPool.java */
    /* loaded from: classes10.dex */
    public static abstract class b extends Binder implements ah {

        /* renamed from: b  reason: collision with root package name */
        static final int f20867b = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public b() {
            attachInterface(this, ah.f20866a);
        }

        public static ah a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(ah.f20866a);
            if (queryLocalInterface != null && (queryLocalInterface instanceof ah)) {
                return (ah) queryLocalInterface;
            }
            return new a(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i3) {
            if (i2 >= 1 && i2 <= 16777215) {
                parcel.enforceInterface(ah.f20866a);
            }
            if (i2 == 1598968902) {
                parcel2.writeString(ah.f20866a);
                return true;
            } else if (i2 == 1) {
                IBinder a2 = a(parcel.readInt());
                parcel2.writeNoException();
                parcel2.writeStrongBinder(a2);
                return true;
            } else {
                return super.onTransact(i2, parcel, parcel2, i3);
            }
        }

        /* compiled from: IWebViewBinderPool.java */
        /* loaded from: classes10.dex */
        static class a implements ah {

            /* renamed from: b  reason: collision with root package name */
            private IBinder f20868b;

            public String a() {
                return ah.f20866a;
            }

            a(IBinder iBinder) {
                this.f20868b = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f20868b;
            }

            @Override // gsdk.impl.webview.DEFAULT.ah
            public IBinder a(int i2) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ah.f20866a);
                    obtain.writeInt(i2);
                    this.f20868b.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readStrongBinder();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
