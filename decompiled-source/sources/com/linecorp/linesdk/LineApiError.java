package com.linecorp.linesdk;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.PrintWriter;
import java.io.StringWriter;
/* loaded from: classes9.dex */
public class LineApiError implements Parcelable {
    public static final Parcelable.Creator<LineApiError> CREATOR = new Parcelable.Creator<LineApiError>() { // from class: com.linecorp.linesdk.LineApiError.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final LineApiError createFromParcel(Parcel parcel) {
            return new LineApiError(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final LineApiError[] newArray(int i2) {
            return new LineApiError[i2];
        }
    };
    public static final LineApiError DEFAULT = new LineApiError(-1, "");
    private static final int DEFAULT_HTTP_RESPONSE_CODE = -1;
    private final int httpResponseCode;
    private final String message;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public LineApiError(Exception exc) {
        this(-1, toString(exc));
    }

    public LineApiError(String str) {
        this(-1, str);
    }

    public LineApiError(int i2, Exception exc) {
        this(i2, toString(exc));
    }

    public LineApiError(int i2, String str) {
        this.httpResponseCode = i2;
        this.message = str;
    }

    private LineApiError(Parcel parcel) {
        this.httpResponseCode = parcel.readInt();
        this.message = parcel.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeInt(this.httpResponseCode);
        parcel.writeString(this.message);
    }

    private static String toString(Exception exc) {
        if (exc == null) {
            return null;
        }
        StringWriter stringWriter = new StringWriter();
        exc.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }

    public int getHttpResponseCode() {
        return this.httpResponseCode;
    }

    public String getMessage() {
        return this.message;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            LineApiError lineApiError = (LineApiError) obj;
            if (this.httpResponseCode != lineApiError.httpResponseCode) {
                return false;
            }
            String str = this.message;
            String str2 = lineApiError.message;
            if (str != null) {
                return str.equals(str2);
            }
            if (str2 == null) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i2 = this.httpResponseCode * 31;
        String str = this.message;
        return i2 + (str != null ? str.hashCode() : 0);
    }

    public String toString() {
        return "LineApiError{httpResponseCode=" + this.httpResponseCode + ", message='" + this.message + "'}";
    }
}
