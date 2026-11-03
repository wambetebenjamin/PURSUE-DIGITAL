package com.google.android.gms.common.server.response;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.server.response.FastJsonResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
/* loaded from: classes4.dex */
public final class zan extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zan> CREATOR = new zao();
    final int zaa;
    private final HashMap zab;
    private final String zac;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zan(int i2, ArrayList arrayList, String str) {
        this.zaa = i2;
        HashMap hashMap = new HashMap();
        int size = arrayList.size();
        for (int i3 = 0; i3 < size; i3++) {
            zal zalVar = (zal) arrayList.get(i3);
            String str2 = zalVar.zab;
            HashMap hashMap2 = new HashMap();
            int size2 = ((ArrayList) Preconditions.checkNotNull(zalVar.zac)).size();
            for (int i4 = 0; i4 < size2; i4++) {
                zam zamVar = (zam) zalVar.zac.get(i4);
                hashMap2.put(zamVar.zab, zamVar.zac);
            }
            hashMap.put(str2, hashMap2);
        }
        this.zab = hashMap;
        this.zac = (String) Preconditions.checkNotNull(str);
        zad();
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        for (String str : this.zab.keySet()) {
            sb.append(str);
            sb.append(":\n");
            Map map = (Map) this.zab.get(str);
            for (String str2 : map.keySet()) {
                sb.append("  ");
                sb.append(str2);
                sb.append(": ");
                sb.append(map.get(str2));
            }
        }
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zaa);
        ArrayList arrayList = new ArrayList();
        for (String str : this.zab.keySet()) {
            arrayList.add(new zal(str, (Map) this.zab.get(str)));
        }
        SafeParcelWriter.writeTypedList(parcel, 2, arrayList, false);
        SafeParcelWriter.writeString(parcel, 3, this.zac, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final String zaa() {
        return this.zac;
    }

    public final Map zab(String str) {
        return (Map) this.zab.get(str);
    }

    public final void zac() {
        for (String str : this.zab.keySet()) {
            Map map = (Map) this.zab.get(str);
            HashMap hashMap = new HashMap();
            for (String str2 : map.keySet()) {
                hashMap.put(str2, ((FastJsonResponse.Field) map.get(str2)).zab());
            }
            this.zab.put(str, hashMap);
        }
    }

    public final void zad() {
        for (String str : this.zab.keySet()) {
            Map map = (Map) this.zab.get(str);
            for (String str2 : map.keySet()) {
                ((FastJsonResponse.Field) map.get(str2)).zai(this);
            }
        }
    }

    public final void zae(Class cls, Map map) {
        this.zab.put((String) Preconditions.checkNotNull(cls.getCanonicalName()), map);
    }

    public final boolean zaf(Class cls) {
        return this.zab.containsKey(Preconditions.checkNotNull(cls.getCanonicalName()));
    }

    public zan(Class cls) {
        this.zaa = 1;
        this.zab = new HashMap();
        this.zac = (String) Preconditions.checkNotNull(cls.getCanonicalName());
    }
}
