package com.linecorp.linesdk.auth;

import android.os.Parcel;
import android.os.Parcelable;
import com.linecorp.linesdk.LineAccessToken;
import com.linecorp.linesdk.LineApiError;
import com.linecorp.linesdk.LineApiResponseCode;
import com.linecorp.linesdk.LineCredential;
import com.linecorp.linesdk.LineProfile;
/* loaded from: classes9.dex */
public class LineLoginResult implements Parcelable {
    public static final LineLoginResult CANCEL = new LineLoginResult(LineApiResponseCode.CANCEL, LineApiError.DEFAULT);
    public static final Parcelable.Creator<LineLoginResult> CREATOR = new Parcelable.Creator<LineLoginResult>() { // from class: com.linecorp.linesdk.auth.LineLoginResult.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final LineLoginResult createFromParcel(Parcel parcel) {
            return new LineLoginResult(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final LineLoginResult[] newArray(int i2) {
            return new LineLoginResult[i2];
        }
    };
    private final LineApiError errorData;
    private final LineCredential lineCredential;
    private final LineProfile lineProfile;
    private final LineApiResponseCode responseCode;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public LineLoginResult(LineProfile lineProfile, LineCredential lineCredential) {
        this(LineApiResponseCode.SUCCESS, lineProfile, lineCredential, LineApiError.DEFAULT);
    }

    public LineLoginResult(LineApiResponseCode lineApiResponseCode, LineApiError lineApiError) {
        this(lineApiResponseCode, null, null, lineApiError);
    }

    LineLoginResult(LineApiResponseCode lineApiResponseCode, LineProfile lineProfile, LineCredential lineCredential, LineApiError lineApiError) {
        this.responseCode = lineApiResponseCode;
        this.lineProfile = lineProfile;
        this.lineCredential = lineCredential;
        this.errorData = lineApiError;
    }

    private LineLoginResult(Parcel parcel) {
        this.responseCode = (LineApiResponseCode) parcel.readSerializable();
        this.lineProfile = (LineProfile) parcel.readParcelable(LineProfile.class.getClassLoader());
        this.lineCredential = (LineCredential) parcel.readParcelable(LineAccessToken.class.getClassLoader());
        this.errorData = (LineApiError) parcel.readParcelable(LineApiError.class.getClassLoader());
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeSerializable(this.responseCode);
        parcel.writeParcelable(this.lineProfile, i2);
        parcel.writeParcelable(this.lineCredential, i2);
        parcel.writeParcelable(this.errorData, i2);
    }

    public boolean isSuccess() {
        return this.responseCode == LineApiResponseCode.SUCCESS;
    }

    public LineApiResponseCode getResponseCode() {
        return this.responseCode;
    }

    public LineProfile getLineProfile() {
        return this.lineProfile;
    }

    public LineCredential getLineCredential() {
        return this.lineCredential;
    }

    public LineApiError getErrorData() {
        return this.errorData;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        LineLoginResult lineLoginResult = (LineLoginResult) obj;
        if (this.responseCode != lineLoginResult.responseCode) {
            return false;
        }
        LineProfile lineProfile = this.lineProfile;
        if (lineProfile == null ? lineLoginResult.lineProfile == null : lineProfile.equals(lineLoginResult.lineProfile)) {
            LineCredential lineCredential = this.lineCredential;
            if (lineCredential == null ? lineLoginResult.lineCredential == null : lineCredential.equals(lineLoginResult.lineCredential)) {
                return this.errorData.equals(lineLoginResult.errorData);
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        int hashCode = this.responseCode.hashCode() * 31;
        LineProfile lineProfile = this.lineProfile;
        int hashCode2 = (hashCode + (lineProfile != null ? lineProfile.hashCode() : 0)) * 31;
        LineCredential lineCredential = this.lineCredential;
        return ((hashCode2 + (lineCredential != null ? lineCredential.hashCode() : 0)) * 31) + this.errorData.hashCode();
    }

    public String toString() {
        return "LineLoginResult{errorData=" + this.errorData + ", responseCode=" + this.responseCode + ", lineProfile=" + this.lineProfile + ", lineCredential=" + this.lineCredential + '}';
    }
}
