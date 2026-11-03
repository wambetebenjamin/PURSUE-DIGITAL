package com.linecorp.linesdk;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* loaded from: classes9.dex */
public class LineCredential implements Parcelable {
    public static final Parcelable.Creator<LineCredential> CREATOR = new Parcelable.Creator<LineCredential>() { // from class: com.linecorp.linesdk.LineCredential.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final LineCredential createFromParcel(Parcel parcel) {
            return new LineCredential(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final LineCredential[] newArray(int i2) {
            return new LineCredential[i2];
        }
    };
    private final LineAccessToken accessToken;
    private final List<String> permission;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public LineCredential(LineAccessToken lineAccessToken, List<String> list) {
        this.accessToken = lineAccessToken;
        this.permission = list;
    }

    private LineCredential(Parcel parcel) {
        this.accessToken = (LineAccessToken) parcel.readParcelable(LineAccessToken.class.getClassLoader());
        ArrayList arrayList = new ArrayList(8);
        parcel.readStringList(arrayList);
        this.permission = Collections.unmodifiableList(arrayList);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeParcelable(this.accessToken, i2);
        parcel.writeStringList(this.permission);
    }

    public LineAccessToken getAccessToken() {
        return this.accessToken;
    }

    public List<String> getPermission() {
        return this.permission;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        LineCredential lineCredential = (LineCredential) obj;
        if (this.accessToken.equals(lineCredential.accessToken)) {
            return this.permission.equals(lineCredential.permission);
        }
        return false;
    }

    public int hashCode() {
        return (this.accessToken.hashCode() * 31) + this.permission.hashCode();
    }

    public String toString() {
        return "LineCredential{accessToken=" + ((Object) "#####") + ", permission=" + this.permission + '}';
    }
}
