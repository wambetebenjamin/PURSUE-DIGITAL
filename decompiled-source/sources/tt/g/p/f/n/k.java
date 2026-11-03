package tt.g.p.f.n;

import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.provider.Settings;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.lang.reflect.Method;
import java.util.concurrent.LinkedBlockingQueue;
/* loaded from: classes10.dex */
public class k {

    /* renamed from: a  reason: collision with root package name */
    static volatile String f21226a = "";

    /* loaded from: classes10.dex */
    private static final class b implements ServiceConnection {

        /* renamed from: a  reason: collision with root package name */
        boolean f21227a;

        /* renamed from: b  reason: collision with root package name */
        private final LinkedBlockingQueue<IBinder> f21228b;

        private b() {
            this.f21227a = false;
            this.f21228b = new LinkedBlockingQueue<>(1);
        }

        public IBinder a() {
            if (this.f21227a) {
                throw new IllegalStateException();
            }
            this.f21227a = true;
            return this.f21228b.take();
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                this.f21228b.put(iBinder);
            } catch (InterruptedException unused) {
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
        }
    }

    /* loaded from: classes10.dex */
    private static final class c implements IInterface {

        /* renamed from: a  reason: collision with root package name */
        private IBinder f21229a;

        private c(IBinder iBinder) {
            this.f21229a = iBinder;
        }

        public String a() {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                this.f21229a.transact(1, obtain, obtain2, 0);
                obtain2.readException();
                return obtain2.readString();
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this.f21229a;
        }
    }

    public static String a() {
        return Build.VERSION.SDK_INT >= 21 ? d() : c();
    }

    public static String a(long j2, String str, Context context) {
        String str2;
        String str3;
        try {
            ContentResolver contentResolver = context.getContentResolver();
            Uri parse = Uri.parse("content://settings/system");
            if (j2 == 0) {
                str3 = "GET_secure";
            } else if (j2 == 1) {
                str3 = "GET_system";
            } else if (j2 != 2) {
                return "";
            } else {
                str3 = "GET_global";
            }
            str2 = contentResolver.call(parse, str3, str, Bundle.EMPTY).getString("value", "");
        } catch (Throwable unused) {
            str2 = "";
        }
        return str2 == null ? "" : str2;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0043  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void a(android.content.Context r5) {
        /*
            android.os.Looper r0 = android.os.Looper.getMainLooper()
            android.os.Looper r1 = android.os.Looper.myLooper()
            if (r0 != r1) goto Lb
            return
        Lb:
            tt.g.p.f.n.k$b r0 = new tt.g.p.f.n.k$b
            r1 = 0
            r0.<init>()
            android.content.Intent r2 = new android.content.Intent
            java.lang.String r3 = "com.google.android.gms.ads.identifier.service.START"
            r2.<init>(r3)
            java.lang.String r3 = "com.google.android.gms"
            r2.setPackage(r3)
            r3 = 1
            boolean r2 = r5.bindService(r2, r0, r3)
            java.lang.String r3 = ""
            if (r2 == 0) goto L3f
            tt.g.p.f.n.k$c r2 = new tt.g.p.f.n.k$c     // Catch: java.lang.Throwable -> L37 java.lang.Exception -> L3c
            android.os.IBinder r4 = r0.a()     // Catch: java.lang.Throwable -> L37 java.lang.Exception -> L3c
            r2.<init>(r4)     // Catch: java.lang.Throwable -> L37 java.lang.Exception -> L3c
            java.lang.String r1 = r2.a()     // Catch: java.lang.Throwable -> L37 java.lang.Exception -> L3c
            r5.unbindService(r0)
            goto L40
        L37:
            r1 = move-exception
            r5.unbindService(r0)
            throw r1
        L3c:
            r5.unbindService(r0)
        L3f:
            r1 = r3
        L40:
            if (r1 != 0) goto L43
            goto L44
        L43:
            r3 = r1
        L44:
            tt.g.p.f.n.k.f21226a = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: tt.g.p.f.n.k.a(android.content.Context):void");
    }

    public static String b() {
        return f21226a;
    }

    public static boolean b(Context context) {
        try {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.hardware.usb.action.USB_STATE");
            Bundle extras = context.registerReceiver(null, intentFilter).getExtras();
            if (extras != null) {
                return extras.getBoolean("connected");
            }
        } catch (Exception unused) {
        }
        return false;
    }

    private static String c() {
        StringBuilder sb = new StringBuilder();
        String str = Build.CPU_ABI;
        if (str != null && !str.isEmpty()) {
            sb.append(str);
        }
        String str2 = Build.CPU_ABI2;
        if (str2 != null && !str2.isEmpty()) {
            sb.append(',').append(str2);
        }
        return sb.toString();
    }

    public static String c(Context context) {
        String str;
        try {
            str = Settings.Secure.getString(context.getContentResolver(), "android_id");
        } catch (Throwable unused) {
            str = "";
        }
        return str == null ? "" : str;
    }

    private static String d() {
        String[] strArr = Build.SUPPORTED_ABIS;
        if (strArr.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (String str : strArr) {
            sb.append(str).append(',');
        }
        return sb.deleteCharAt(sb.length() - 1).toString();
    }

    public static String d(Context context) {
        Intent registerReceiver = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        int intExtra = registerReceiver.getIntExtra("status", 0);
        int intExtra2 = registerReceiver.getIntExtra("plugged", 0);
        int intExtra3 = registerReceiver.getIntExtra("level", 0);
        int intExtra4 = registerReceiver.getIntExtra("scale", 0);
        return intExtra + "-" + intExtra2 + "-" + Math.round((intExtra4 != 0 ? intExtra3 / intExtra4 : 0.0f) * 100) + "-" + e(context);
    }

    public static String e(Context context) {
        double d2;
        try {
            Class<?> cls = Class.forName("com.android.internal.os.PowerProfile");
            Object newInstance = cls.getConstructor(Context.class).newInstance(context);
            Method declaredMethod = cls.getDeclaredMethod("getAveragePower", String.class);
            declaredMethod.setAccessible(true);
            d2 = ((Double) declaredMethod.invoke(newInstance, "battery.capacity")).doubleValue();
        } catch (Throwable unused) {
            d2 = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        }
        return Integer.toString((int) d2);
    }

    public static String f(Context context) {
        String str;
        try {
            str = Settings.Secure.getString(context.getContentResolver(), "bluetooth_name");
        } catch (Exception e2) {
            e2.printStackTrace();
            str = "";
        }
        return (str != null ? str : "").trim();
    }
}
