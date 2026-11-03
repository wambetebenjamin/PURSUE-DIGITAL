package com.bytedance.push.settings.notification;

import android.app.NotificationChannel;
import android.os.Build;
import android.os.Parcel;
import android.text.TextUtils;
import android.util.Base64;
/* compiled from: NotificationChannelSerializable.java */
/* loaded from: classes2.dex */
public class a implements com.bytedance.push.settings.common.b {

    /* renamed from: a  reason: collision with root package name */
    private NotificationChannel f11816a;

    public a(NotificationChannel notificationChannel) {
        this.f11816a = notificationChannel;
    }

    public a() {
    }

    public NotificationChannel b() {
        return this.f11816a;
    }

    public boolean a(NotificationChannel notificationChannel) {
        if (Build.VERSION.SDK_INT >= 26) {
            return TextUtils.equals(notificationChannel.getId(), this.f11816a.getId());
        }
        return false;
    }

    @Override // com.bytedance.push.settings.common.b
    public void a(String str) {
        byte[] decode = Base64.decode(str, 0);
        Parcel obtain = Parcel.obtain();
        obtain.unmarshall(decode, 0, decode.length);
        obtain.setDataPosition(0);
        if (Build.VERSION.SDK_INT >= 26) {
            this.f11816a = (NotificationChannel) NotificationChannel.CREATOR.createFromParcel(obtain);
        }
    }

    @Override // com.bytedance.push.settings.common.b
    public String a() {
        Parcel obtain = Parcel.obtain();
        this.f11816a.writeToParcel(obtain, 0);
        byte[] marshall = obtain.marshall();
        obtain.recycle();
        return Base64.encodeToString(marshall, 0);
    }
}
