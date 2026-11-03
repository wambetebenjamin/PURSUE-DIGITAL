package com.google.android.gms.auth.api.proxy;

import android.app.PendingIntent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
/* compiled from: com.google.android.gms:play-services-auth-base@@18.0.4 */
/* loaded from: classes4.dex */
public class ProxyResponse extends AbstractSafeParcelable {
    public static final Parcelable.Creator<ProxyResponse> CREATOR = new zzb();
    public static final int STATUS_CODE_NO_CONNECTION = -1;
    public final byte[] body;
    public final int googlePlayServicesStatusCode;
    public final PendingIntent recoveryAction;
    public final int statusCode;
    final int zza;
    final Bundle zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ProxyResponse(int i2, int i3, PendingIntent pendingIntent, int i4, Bundle bundle, byte[] bArr) {
        this.zza = i2;
        this.googlePlayServicesStatusCode = i3;
        this.statusCode = i4;
        this.zzb = bundle;
        this.body = bArr;
        this.recoveryAction = pendingIntent;
    }

    public static ProxyResponse createErrorProxyResponse(int i2, PendingIntent pendingIntent, int i3, Map<String, String> map, byte[] bArr) {
        return new ProxyResponse(1, i2, pendingIntent, i3, zza(map), bArr);
    }

    private static Bundle zza(Map map) {
        Bundle bundle = new Bundle();
        if (map == null) {
            return bundle;
        }
        for (Map.Entry entry : map.entrySet()) {
            bundle.putString((String) entry.getKey(), (String) entry.getValue());
        }
        return bundle;
    }

    public Map<String, String> getHeaders() {
        if (this.zzb == null) {
            return Collections.emptyMap();
        }
        HashMap hashMap = new HashMap();
        for (String str : this.zzb.keySet()) {
            hashMap.put(str, this.zzb.getString(str));
        }
        return hashMap;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.googlePlayServicesStatusCode);
        SafeParcelWriter.writeParcelable(parcel, 2, this.recoveryAction, i2, false);
        SafeParcelWriter.writeInt(parcel, 3, this.statusCode);
        SafeParcelWriter.writeBundle(parcel, 4, this.zzb, false);
        SafeParcelWriter.writeByteArray(parcel, 5, this.body, false);
        SafeParcelWriter.writeInt(parcel, 1000, this.zza);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public ProxyResponse(int i2, PendingIntent pendingIntent, int i3, Bundle bundle, byte[] bArr) {
        this(1, i2, pendingIntent, i3, bundle, bArr);
    }

    public ProxyResponse(int i2, Map<String, String> map, byte[] bArr) {
        this(1, 0, null, i2, zza(map), bArr);
    }
}
