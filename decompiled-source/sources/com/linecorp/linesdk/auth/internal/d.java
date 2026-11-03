package com.linecorp.linesdk.auth.internal;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.linecorp.linesdk.a.f;
/* loaded from: classes9.dex */
final class d implements Parcelable {
    public static final Parcelable.Creator<d> CREATOR = new Parcelable.Creator<d>() { // from class: com.linecorp.linesdk.auth.internal.d.1
        @Override // android.os.Parcelable.Creator
        public final /* bridge */ /* synthetic */ d[] newArray(int i2) {
            return new d[i2];
        }

        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ d createFromParcel(Parcel parcel) {
            return new d(parcel, (byte) 0);
        }
    };

    /* renamed from: a  reason: collision with root package name */
    f f17318a;

    /* renamed from: b  reason: collision with root package name */
    String f17319b;

    /* renamed from: c  reason: collision with root package name */
    String f17320c;

    /* renamed from: d  reason: collision with root package name */
    int f17321d;

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    /* JADX WARN: $VALUES field not found */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* loaded from: classes9.dex */
    static final class a {

        /* renamed from: a  reason: collision with root package name */
        public static final int f17322a = 1;

        /* renamed from: b  reason: collision with root package name */
        public static final int f17323b = 2;

        /* renamed from: c  reason: collision with root package name */
        public static final int f17324c = 3;

        /* renamed from: d  reason: collision with root package name */
        public static final int f17325d = 4;

        /* renamed from: e  reason: collision with root package name */
        private static final /* synthetic */ int[] f17326e = {1, 2, 3, 4};

        public static int[] a() {
            return (int[]) f17326e.clone();
        }
    }

    /* synthetic */ d(Parcel parcel, byte b2) {
        this(parcel);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public d() {
        this.f17321d = a.f17322a;
    }

    private d(Parcel parcel) {
        this.f17321d = a.f17322a;
        String readString = parcel.readString();
        String readString2 = parcel.readString();
        this.f17318a = (TextUtils.isEmpty(readString) || TextUtils.isEmpty(readString2)) ? null : new f(readString, readString2);
        this.f17319b = parcel.readString();
        this.f17321d = a.a()[parcel.readByte()];
        this.f17320c = parcel.readString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        f fVar = this.f17318a;
        parcel.writeString(fVar == null ? null : fVar.f17277a);
        f fVar2 = this.f17318a;
        parcel.writeString(fVar2 != null ? fVar2.f17278b : null);
        parcel.writeString(this.f17319b);
        parcel.writeByte((byte) (this.f17321d - 1));
        parcel.writeString(this.f17320c);
    }
}
