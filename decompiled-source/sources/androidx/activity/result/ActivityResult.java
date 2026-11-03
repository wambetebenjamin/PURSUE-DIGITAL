package androidx.activity.result;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes.dex */
public final class ActivityResult implements Parcelable {
    public static final Parcelable.Creator<ActivityResult> CREATOR = new Parcelable.Creator<ActivityResult>() { // from class: androidx.activity.result.ActivityResult.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ActivityResult createFromParcel(Parcel parcel) {
            return new ActivityResult(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ActivityResult[] newArray(int i2) {
            return new ActivityResult[i2];
        }
    };
    private final Intent mData;
    private final int mResultCode;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ActivityResult(int i2, Intent intent) {
        this.mResultCode = i2;
        this.mData = intent;
    }

    ActivityResult(Parcel parcel) {
        this.mResultCode = parcel.readInt();
        this.mData = parcel.readInt() == 0 ? null : (Intent) Intent.CREATOR.createFromParcel(parcel);
    }

    public int getResultCode() {
        return this.mResultCode;
    }

    public Intent getData() {
        return this.mData;
    }

    public String toString() {
        return "ActivityResult{resultCode=" + resultCodeToString(this.mResultCode) + ", data=" + this.mData + '}';
    }

    public static String resultCodeToString(int i2) {
        return i2 != -1 ? i2 != 0 ? String.valueOf(i2) : "RESULT_CANCELED" : "RESULT_OK";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeInt(this.mResultCode);
        parcel.writeInt(this.mData == null ? 0 : 1);
        Intent intent = this.mData;
        if (intent != null) {
            intent.writeToParcel(parcel, i2);
        }
    }
}
