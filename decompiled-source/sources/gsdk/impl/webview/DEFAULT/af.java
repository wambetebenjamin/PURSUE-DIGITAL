package gsdk.impl.webview.DEFAULT;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import java.util.Map;
/* compiled from: IWebAidlCallback.java */
/* loaded from: classes10.dex */
public interface af extends IInterface {

    /* renamed from: a  reason: collision with root package name */
    public static final String f20859a = "com.bytedance.ttgame.module.webview.IWebAidlCallback";

    /* compiled from: IWebAidlCallback.java */
    /* loaded from: classes10.dex */
    public static class a implements af {
        @Override // gsdk.impl.webview.DEFAULT.af
        public void a(boolean z, String str, Map map) {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    void a(boolean z, String str, Map map);

    /* compiled from: IWebAidlCallback.java */
    /* loaded from: classes10.dex */
    public static abstract class b extends Binder implements af {

        /* renamed from: b  reason: collision with root package name */
        static final int f20860b = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public b() {
            attachInterface(this, af.f20859a);
        }

        public static af a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(af.f20859a);
            if (queryLocalInterface != null && (queryLocalInterface instanceof af)) {
                return (af) queryLocalInterface;
            }
            return new a(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i3) {
            if (i2 >= 1 && i2 <= 16777215) {
                parcel.enforceInterface(af.f20859a);
            }
            if (i2 == 1598968902) {
                parcel2.writeString(af.f20859a);
                return true;
            } else if (i2 == 1) {
                a(parcel.readInt() != 0, parcel.readString(), parcel.readHashMap(getClass().getClassLoader()));
                parcel2.writeNoException();
                return true;
            } else {
                return super.onTransact(i2, parcel, parcel2, i3);
            }
        }

        /* compiled from: IWebAidlCallback.java */
        /* loaded from: classes10.dex */
        static class a implements af {

            /* renamed from: b  reason: collision with root package name */
            private IBinder f20861b;

            public String a() {
                return af.f20859a;
            }

            a(IBinder iBinder) {
                this.f20861b = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f20861b;
            }

            @Override // gsdk.impl.webview.DEFAULT.af
            public void a(boolean z, String str, Map map) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(af.f20859a);
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeString(str);
                    obtain.writeMap(map);
                    this.f20861b.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
