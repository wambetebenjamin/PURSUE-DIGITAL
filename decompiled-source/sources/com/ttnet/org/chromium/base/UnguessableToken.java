package com.ttnet.org.chromium.base;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes9.dex */
public class UnguessableToken implements Parcelable {
    public static final Parcelable.Creator<UnguessableToken> CREATOR = new Parcelable.Creator<UnguessableToken>() { // from class: com.ttnet.org.chromium.base.UnguessableToken.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public UnguessableToken createFromParcel(Parcel parcel) {
            long readLong = parcel.readLong();
            long readLong2 = parcel.readLong();
            if (readLong == 0 || readLong2 == 0) {
                return null;
            }
            return new UnguessableToken(readLong, readLong2);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public UnguessableToken[] newArray(int i2) {
            return new UnguessableToken[i2];
        }
    };
    private final long mHigh;
    private final long mLow;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private UnguessableToken(long j2, long j3) {
        this.mHigh = j2;
        this.mLow = j3;
    }

    private static UnguessableToken create(long j2, long j3) {
        return new UnguessableToken(j2, j3);
    }

    public long getHighForSerialization() {
        return this.mHigh;
    }

    public long getLowForSerialization() {
        return this.mLow;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeLong(this.mHigh);
        parcel.writeLong(this.mLow);
    }

    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        UnguessableToken unguessableToken = (UnguessableToken) obj;
        return unguessableToken.mHigh == this.mHigh && unguessableToken.mLow == this.mLow;
    }

    public int hashCode() {
        long j2 = this.mLow;
        long j3 = this.mHigh;
        return (((int) (j2 ^ (j2 >>> 32))) * 31) + ((int) ((j3 >>> 32) ^ j3));
    }

    private UnguessableToken parcelAndUnparcelForTesting() {
        Parcel obtain = Parcel.obtain();
        writeToParcel(obtain, 0);
        obtain.setDataPosition(0);
        UnguessableToken createFromParcel = CREATOR.createFromParcel(obtain);
        obtain.recycle();
        return createFromParcel;
    }
}
