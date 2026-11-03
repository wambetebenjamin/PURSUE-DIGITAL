package com.google.android.gms.fido.u2f.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: com.google.android.gms:play-services-fido@@20.0.1 */
@Deprecated
/* loaded from: classes4.dex */
public class ChannelIdValue extends AbstractSafeParcelable {
    private final ChannelIdValueType zza;
    private final String zzb;
    private final String zzc;
    public static final Parcelable.Creator<ChannelIdValue> CREATOR = new zzb();
    public static final ChannelIdValue ABSENT = new ChannelIdValue();
    public static final ChannelIdValue UNAVAILABLE = new ChannelIdValue("unavailable");
    public static final ChannelIdValue UNUSED = new ChannelIdValue("unused");

    /* compiled from: com.google.android.gms:play-services-fido@@20.0.1 */
    /* loaded from: classes4.dex */
    public enum ChannelIdValueType implements Parcelable {
        ABSENT(0),
        STRING(1),
        OBJECT(2);
        
        public static final Parcelable.Creator<ChannelIdValueType> CREATOR = new zza();
        private final int zzb;

        ChannelIdValueType(int i2) {
            this.zzb = i2;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            parcel.writeInt(this.zzb);
        }
    }

    /* compiled from: com.google.android.gms:play-services-fido@@20.0.1 */
    /* loaded from: classes4.dex */
    public static class UnsupportedChannelIdValueTypeException extends Exception {
        public UnsupportedChannelIdValueTypeException(int i2) {
            super(String.format("ChannelIdValueType %s not supported", Integer.valueOf(i2)));
        }
    }

    private ChannelIdValue() {
        this.zza = ChannelIdValueType.ABSENT;
        this.zzc = null;
        this.zzb = null;
    }

    public static ChannelIdValueType toChannelIdValueType(int i2) throws UnsupportedChannelIdValueTypeException {
        ChannelIdValueType[] values;
        for (ChannelIdValueType channelIdValueType : ChannelIdValueType.values()) {
            if (i2 == channelIdValueType.zzb) {
                return channelIdValueType;
            }
        }
        throw new UnsupportedChannelIdValueTypeException(i2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ChannelIdValue) {
            ChannelIdValue channelIdValue = (ChannelIdValue) obj;
            if (this.zza.equals(channelIdValue.zza)) {
                int ordinal = this.zza.ordinal();
                if (ordinal != 0) {
                    if (ordinal != 1) {
                        if (ordinal != 2) {
                            return false;
                        }
                        return this.zzc.equals(channelIdValue.zzc);
                    }
                    return this.zzb.equals(channelIdValue.zzb);
                }
                return true;
            }
            return false;
        }
        return false;
    }

    public JSONObject getObjectValue() {
        if (this.zzc == null) {
            return null;
        }
        try {
            return new JSONObject(this.zzc);
        } catch (JSONException e2) {
            throw new RuntimeException(e2);
        }
    }

    public String getObjectValueAsString() {
        return this.zzc;
    }

    public String getStringValue() {
        return this.zzb;
    }

    public ChannelIdValueType getType() {
        return this.zza;
    }

    public int getTypeAsInt() {
        return this.zza.zzb;
    }

    public int hashCode() {
        int i2;
        int hashCode;
        int hashCode2 = this.zza.hashCode() + 31;
        int ordinal = this.zza.ordinal();
        if (ordinal == 1) {
            i2 = hashCode2 * 31;
            hashCode = this.zzb.hashCode();
        } else if (ordinal != 2) {
            return hashCode2;
        } else {
            i2 = hashCode2 * 31;
            hashCode = this.zzc.hashCode();
        }
        return i2 + hashCode;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 2, getTypeAsInt());
        SafeParcelWriter.writeString(parcel, 3, getStringValue(), false);
        SafeParcelWriter.writeString(parcel, 4, getObjectValueAsString(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ChannelIdValue(int i2, String str, String str2) {
        try {
            this.zza = toChannelIdValueType(i2);
            this.zzb = str;
            this.zzc = str2;
        } catch (UnsupportedChannelIdValueTypeException e2) {
            throw new IllegalArgumentException(e2);
        }
    }

    private ChannelIdValue(String str) {
        this.zzb = (String) Preconditions.checkNotNull(str);
        this.zza = ChannelIdValueType.STRING;
        this.zzc = null;
    }

    public ChannelIdValue(JSONObject jSONObject) {
        this.zzc = (String) Preconditions.checkNotNull(jSONObject.toString());
        this.zza = ChannelIdValueType.OBJECT;
        this.zzb = null;
    }
}
