package gsdk.impl.webview.DEFAULT;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import gsdk.impl.webview.DEFAULT.af;
import java.util.Map;
/* compiled from: IWebAidlInterface.java */
/* loaded from: classes10.dex */
public interface ag extends IInterface {

    /* renamed from: a  reason: collision with root package name */
    public static final String f20862a = "com.bytedance.ttgame.module.webview.IWebAidlInterface";

    /* compiled from: IWebAidlInterface.java */
    /* loaded from: classes10.dex */
    public static class a implements ag {
        @Override // gsdk.impl.webview.DEFAULT.ag
        public Map a(int i2, String str, Map map) {
            return null;
        }

        @Override // gsdk.impl.webview.DEFAULT.ag
        public void a(int i2, String str, Map map, af afVar) {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    Map a(int i2, String str, Map map);

    void a(int i2, String str, Map map, af afVar);

    /* compiled from: IWebAidlInterface.java */
    /* loaded from: classes10.dex */
    public static abstract class b extends Binder implements ag {

        /* renamed from: b  reason: collision with root package name */
        static final int f20863b = 1;

        /* renamed from: c  reason: collision with root package name */
        static final int f20864c = 2;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public b() {
            attachInterface(this, ag.f20862a);
        }

        public static ag a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(ag.f20862a);
            if (queryLocalInterface != null && (queryLocalInterface instanceof ag)) {
                return (ag) queryLocalInterface;
            }
            return new a(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i3) {
            if (i2 >= 1 && i2 <= 16777215) {
                parcel.enforceInterface(ag.f20862a);
            }
            if (i2 == 1598968902) {
                parcel2.writeString(ag.f20862a);
                return true;
            }
            if (i2 == 1) {
                a(parcel.readInt(), parcel.readString(), parcel.readHashMap(getClass().getClassLoader()), af.b.a(parcel.readStrongBinder()));
                parcel2.writeNoException();
            } else if (i2 == 2) {
                Map a2 = a(parcel.readInt(), parcel.readString(), parcel.readHashMap(getClass().getClassLoader()));
                parcel2.writeNoException();
                parcel2.writeMap(a2);
            } else {
                return super.onTransact(i2, parcel, parcel2, i3);
            }
            return true;
        }

        /* compiled from: IWebAidlInterface.java */
        /* loaded from: classes10.dex */
        static class a implements ag {

            /* renamed from: b  reason: collision with root package name */
            private IBinder f20865b;

            public String a() {
                return ag.f20862a;
            }

            a(IBinder iBinder) {
                this.f20865b = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f20865b;
            }

            @Override // gsdk.impl.webview.DEFAULT.ag
            public void a(int i2, String str, Map map, af afVar) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ag.f20862a);
                    obtain.writeInt(i2);
                    obtain.writeString(str);
                    obtain.writeMap(map);
                    obtain.writeStrongInterface(afVar);
                    this.f20865b.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // gsdk.impl.webview.DEFAULT.ag
            public Map a(int i2, String str, Map map) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(ag.f20862a);
                    obtain.writeInt(i2);
                    obtain.writeString(str);
                    obtain.writeMap(map);
                    this.f20865b.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readHashMap(getClass().getClassLoader());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
